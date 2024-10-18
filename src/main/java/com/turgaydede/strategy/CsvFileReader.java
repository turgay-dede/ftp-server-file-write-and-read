package com.turgaydede.strategy;

import java.io.*;

public class CsvFileReader implements FileReaderStrategy {
    @Override
    public void readFile(InputStream inputStream) {
        System.out.println("Reading CSV file: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                for (String value : values) {
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}