package com.abctech.ripoti.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class JiraAuthFileStorageService implements IJiraAuthStorageService {

    private static final Logger log = LoggerFactory.getLogger(JiraAuthFileStorageService.class);

    @Override
    public String getAuthorizationValue() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader("/etc/ripoti"));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            log.error("Can not get authorization value from a file.");
            return null;
        }
        return sb.toString().trim();
    }
}
