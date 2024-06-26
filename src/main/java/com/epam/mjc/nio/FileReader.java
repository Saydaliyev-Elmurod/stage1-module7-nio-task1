package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileReader {
    Logger logger = Logger.getLogger(FileReader.class.getSimpleName());

    public Profile getDataFromFile(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(file))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line).append("\n");
            }
            return parseToObject(text.toString());
        } catch (IOException e) {
            logger.log(Level.WARNING, "File not found or not exist");
        }
        return null;
    }

    private Profile parseToObject(String text) {
        Profile profile = new Profile();
        String[] arr = text.split("\n");
        for (String line : arr) {
            String[] a = line.split(": ");
            if ("Name".equals(a[0])) {
                profile.setName(a[1]);
            } else if ("Age".equals(a[0])) {
                profile.setAge(Integer.parseInt(a[1]));
            } else if ("Email".equals(a[0])) {
                profile.setEmail(a[1]);
            } else if ("Phone".equals(a[0])) {
                profile.setPhone(Long.parseLong(a[1]));
            }
        }
        return profile;
    }
}
