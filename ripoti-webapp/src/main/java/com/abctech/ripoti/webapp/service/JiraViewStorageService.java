package com.abctech.ripoti.webapp.service;

import com.abctech.ripoti.webapp.json.jira.rapidview.View;
import com.abctech.ripoti.webapp.properties.RipotiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Service
public class JiraViewStorageService {

    private static final Logger log = LoggerFactory.getLogger(JiraViewStorageService.class);

    @Autowired
    private RipotiProperties ripotiProperties;

    /**
     * Save View[] to file storage.
     * @param views
     */
    public void save(View[] views) {
        File file = new File(ripotiProperties.getViewStoragePath());
        try {
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(views);
            objectOutputStream.close();
        } catch (IOException e) {
            log.warn("Can not save view to storage.");
        }
    }

    /**
     * Load View[] from file storage.
     * @return Array of View or null if can not load file.
     */
    public View[] load() {
        File file = new File(ripotiProperties.getViewStoragePath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            View[] views = (View[]) objectInputStream.readObject();
            objectInputStream.close();
            return views;
        } catch (ClassNotFoundException | IOException e) {
            log.debug("Can not load view from storage.", e);
        }
        return null;
    }
}
