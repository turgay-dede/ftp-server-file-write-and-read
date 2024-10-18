package com.turgaydede.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turgaydede.model.json.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonFileReader implements FileReaderStrategy {
    @Override
    public void readFile(InputStream inputStream) {
        System.out.println("Reading JSON file: ");
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Person person = objectMapper.readValue(inputStream, Person.class);

            System.out.println("Parsed JSON Data: " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
