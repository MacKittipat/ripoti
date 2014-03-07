package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.json.jira.JiraSession;
import com.abctech.ripoti.webapp.json.jira.rapidview.View;
import com.abctech.ripoti.webapp.json.jira.search.Issue;
import com.abctech.ripoti.webapp.json.jira.search.Search;
import com.abctech.ripoti.webapp.json.jira.sprintquery.Sprint;
import com.abctech.ripoti.webapp.json.ripoti.RipotiIssue;
import com.abctech.ripoti.webapp.service.IJiraAuthStorageService;
import com.abctech.ripoti.webapp.service.JiraRestService;
import com.abctech.ripoti.webapp.service.JiraToRipotiService;
import com.abctech.ripoti.webapp.service.JiraViewStorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @InjectMocks
    private MainController mainController;

    @Mock
    private JiraRestService jiraRestService;

    @Mock
    private IJiraAuthStorageService jiraAuthStorageService;

    @Mock
    private JiraToRipotiService jiraToRipotiService;

    @Mock
    private JiraViewStorageService jiraViewStorageService;

    @Mock
    private ObjectMapper jacksonObjectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(redirectedUrl("auth"));
        mockMvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(redirectedUrl("auth"));
    }

    @Test
    public void testAuthLoggedIn() throws Exception {
        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn("authvalue");
        mockMvc.perform(MockMvcRequestBuilders.get("/auth"))
                .andExpect(redirectedUrl("report"));
    }

    @Test
    public void testAuthNotLoggedIn() throws Exception {
        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/auth"))
                .andExpect(model().attribute("pageContent", "main/auth")).andExpect(view()
                .name("layout"));
    }

    @Test
    public void testAuthLoginSuccess() throws Exception {
        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn(null, "123");
        JiraSession jiraSession = new JiraSession();
        jiraSession.setName("name");
        when(jiraRestService.getJiraSession(anyString())).thenReturn(jiraSession);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .requestAttr("username", "myuser")
                .requestAttr("password", "mypass"))
                .andExpect(model().attribute("pageContent", "main/auth"))
                .andExpect(view().name("redirect:report"));
    }

    @Test
    public void testAuthLoginFail() throws Exception {
        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn(null);
        JiraSession jiraSession = new JiraSession();
        jiraSession.setName("name");
        when(jiraRestService.getJiraSession(anyString())).thenThrow(HttpClientErrorException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .requestAttr("username", "myuser")
                .requestAttr("password", "mypass"))
                .andExpect(model().attribute("pageContent", "main/auth"))
                .andExpect(model().attributeExists("errorMsg"));
    }

    @Test
    public void testReport() throws Exception {
        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn("123");
        View[] views = new View[2];
        View view = new View();
        view.setId(1);
        view.setName("Board 1");
        views[0] = view;
        view = new View();
        view.setId(2);
        view.setName("Board 2");
        views[1] = view;
        when(jiraViewStorageService.load()).thenReturn(views);
        Sprint[] sprints = new Sprint[3];
        Sprint sprint = new Sprint();
        sprint.setId(1);
        sprint.setName("Sprint 1");
        sprints[0] = sprint;
        sprint = new Sprint();
        sprint.setId(2);
        sprint.setName("Sprint 2");
        sprints[1] = sprint;
        sprint = new Sprint();
        sprint.setId(3);
        sprint.setName("Sprint 3");
        sprints[2] = sprint;
        when(jiraRestService.getSprints(anyString(), anyInt())).thenReturn(sprints);
        when(jiraRestService.getSearch(anyString(), anyInt())).thenReturn(new Search());
        when(jiraToRipotiService.convert(any(Issue[].class))).thenReturn(new RipotiIssue());
        when(jacksonObjectMapper.writeValueAsString(any(RipotiIssue.class)))
                .thenReturn("{test:\"test\"}");
        mockMvc.perform(MockMvcRequestBuilders.get("/report")
                .param("viewId", "1")
                .param("sprintId", "1"))
                .andExpect(model().attribute("pageContent", "main/report"))
                .andExpect(model().attributeExists("viewMap"))
                .andExpect(model().attributeExists("sprintMap"))
                .andExpect(model().attribute("viewName", "Board 1"))
                .andExpect(model().attribute("sprintName", "Sprint 1"))
                .andExpect(model().attributeExists("ripotiJson"))
                .andExpect(view().name("layout"));
        verify(jacksonObjectMapper).writeValueAsString(any(RipotiIssue.class));
    }

    @Test
    public void testReportErrorJsonProcessing() throws Exception {
        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn("123");
        View[] views = new View[2];
        View view = new View();
        view.setId(1);
        view.setName("Board 1");
        views[0] = view;
        view = new View();
        view.setId(2);
        view.setName("Board 2");
        views[1] = view;
        when(jiraViewStorageService.load()).thenReturn(views);
        Sprint[] sprints = new Sprint[3];
        Sprint sprint = new Sprint();
        sprint.setId(1);
        sprint.setName("Sprint 1");
        sprints[0] = sprint;
        sprint = new Sprint();
        sprint.setId(2);
        sprint.setName("Sprint 2");
        sprints[1] = sprint;
        sprint = new Sprint();
        sprint.setId(3);
        sprint.setName("Sprint 3");
        sprints[2] = sprint;
        when(jiraRestService.getSprints(anyString(), anyInt())).thenReturn(sprints);
        when(jiraRestService.getSearch(anyString(), anyInt())).thenReturn(new Search());
        when(jiraToRipotiService.convert(any(Issue[].class))).thenReturn(new RipotiIssue());
        when(jacksonObjectMapper.writeValueAsString(any(RipotiIssue.class)))
                .thenThrow(JsonProcessingException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/report")
                .param("viewId", "1")
                .param("sprintId", "1"))
                .andExpect(model().attribute("pageContent", "main/report"))
                .andExpect(model().attributeExists("viewMap"))
                .andExpect(model().attributeExists("sprintMap"))
                .andExpect(model().attribute("viewName", "Board 1"))
                .andExpect(model().attribute("sprintName", "Sprint 1"))
                .andExpect(model().attributeDoesNotExist("ripotiJson"))
                .andExpect(view().name("layout"));
        verify(jacksonObjectMapper).writeValueAsString(any(RipotiIssue.class));
    }

    @Test
    public void testUpdateBoard() throws Exception {
        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn("authvalue");
        View[] views = new View[2];
        View view = new View();
        view.setId(1);
        view.setName("Board 1");
        views[0] = view;
        view = new View();
        view.setId(2);
        view.setName("Board 2");
        views[1] = view;
        when(jiraRestService.getViews(anyString())).thenReturn(views);
        mockMvc.perform(MockMvcRequestBuilders.get("/updateboard"))
                .andExpect(redirectedUrl("report"));
        verify(jiraViewStorageService).save(any(View[].class));
    }
}
