package com.abctech.ripoti.webapp.controller;

import com.abctech.ripoti.webapp.json.ripoti.RipotiIssue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "export")
public class ExportController {

    private static final Logger log = LoggerFactory.getLogger(ExportController.class);

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;

    @RequestMapping(value = "pdf",  produces = "application/pdf")
    @ResponseBody
    public byte[] pdf(@RequestParam String ripotiIssueJson) {
        String content = "";
        try {
            // Convert json to java object.
            log.debug("Converting json to object.");
            RipotiIssue ripotiIssue = jacksonObjectMapper.readValue(ripotiIssueJson, RipotiIssue.class);
            try {
                // Load PDF content.
                Map<String, Object> ftlParamMap = new LinkedHashMap<>();
                ftlParamMap.put("ripotiIssue", ripotiIssue);
                content = FreeMarkerTemplateUtils.processTemplateIntoString(
                        freeMarkerConfigurationFactory.createConfiguration().getTemplate("pdf.ftl"),
                        ftlParamMap);
            } catch (IOException | TemplateException e) {
                log.error("Can not load pdf template.", e);
            }
        } catch (IOException e) {
            log.error("Can not convert json to object.", e);
        }
        // Render PDF
        log.info("Rendering PDF");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ITextRenderer iTextRenderer = new ITextRenderer();
        iTextRenderer.setDocumentFromString(content);
        iTextRenderer.layout();
        try {
            iTextRenderer.createPDF(byteArrayOutputStream);
        } catch (DocumentException e) {
            log.error("Can not create pdf.", e);
        }
        byte[] contentBytes = byteArrayOutputStream.toByteArray();
        return contentBytes;
    }
}
