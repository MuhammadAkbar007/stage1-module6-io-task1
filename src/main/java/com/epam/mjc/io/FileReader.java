package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try {
            // read stream as chars from file
            FileInputStream fs = new FileInputStream(file);
            int ch;
            StringBuilder sb = new StringBuilder();
            while ((ch = fs.read()) != -1) {
                // storing chars in string
                sb.append(Character.toString(ch));
            }
            // close stream input
            fs.close();

            // separate key-values from string
            Map<String, String> keyValuePairs = new HashMap<>();
            String[] lines = sb.toString().split("\n");
            for (String line : lines) {
                String[] pairs = line.split(": ", 2);
                if (pairs.length == 2) {
                    keyValuePairs.put(pairs[0].trim(), pairs[1].trim());
                }
            }

            // taking variables from map in correct data types
            String name = keyValuePairs.get("Name");
            int age = Integer.parseInt(keyValuePairs.get("Age"));
            String email = keyValuePairs.get("Email");
            long phone = Long.parseLong(keyValuePairs.get("Phone"));

            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
