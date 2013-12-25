package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.json.ripoti.ParentIssue;
import com.abctech.ripoti.webapp.json.ripoti.RipotiIssue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping(value = "export")
public class ExportController {

    private static final Logger log = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @RequestMapping(value = "pdf",  produces = "application/pdf")
    @ResponseBody
    public byte[] pdf(@RequestParam String ripotiIssueJson) {
        // Convert json to java
        try {
            RipotiIssue ripotiIssue = jacksonObjectMapper.readValue(ripotiIssueJson, RipotiIssue.class);
            for(ParentIssue parentIssue : ripotiIssue.getParentIssues()) {
                log.debug("Parent : {} | {}", parentIssue.getTitle(), parentIssue.getTimeSpent().getValue());
            }
            // TODO Display pdf ...
        } catch (IOException e) {
            log.warn("Can not convert json to object.", e);
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString("<html><body>Hello</body></html>");
        renderer.layout();
        try {
            renderer.createPDF(output);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        byte[] content = output.toByteArray();
        return content;
    }
}
