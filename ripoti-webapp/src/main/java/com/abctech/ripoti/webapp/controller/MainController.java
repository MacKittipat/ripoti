package com.abctech.ripoti.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:auth";
    }

    @RequestMapping(value = "auth")
    public String auth(Model model) {
        model.addAttribute("pageContent", "main/auth");
        return "layout";
    }
}
