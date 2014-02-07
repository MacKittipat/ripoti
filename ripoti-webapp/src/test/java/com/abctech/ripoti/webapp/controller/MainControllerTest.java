package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.service.IJiraAuthStorageService;
import com.abctech.ripoti.webapp.service.JiraRestService;
import com.abctech.ripoti.webapp.service.JiraToRipotiService;
import com.abctech.ripoti.webapp.service.JiraViewStorageService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

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
                .andExpect(model().attribute("pageContent", "main/auth"));
    }

//    @Test
//    public void testAuthLoginSuccess() throws Exception {
//        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn(null);
//        JiraSession jiraSession = new JiraSession();
//        jiraSession.setName("name");
//        when(jiraRestService.getJiraSession(anyString())).thenReturn(jiraSession);
//        when(jiraAuthStorageService.getAuthorizationValue()).thenReturn("")
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
//                .requestAttr("username", "myuser")
//                .requestAttr("password", "mypass"))
//                .andExpect(model().attribute("pageContent", "main/auth"));
//    }
}
