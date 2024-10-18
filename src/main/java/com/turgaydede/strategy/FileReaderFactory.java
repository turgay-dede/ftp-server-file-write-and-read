package com.turgaydede.strategy;

import com.turgaydede.strategy.excel.XLSFileReader;
import com.turgaydede.strategy.excel.XLSXFileReader;

public class FileReaderFactory {
    public static FileReaderStrategy getStrategy(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "csv":
                return new CsvFileReader();
            case "xml":
                return new XmlFileReader();
            case "json":
                return new JsonFileReader();
            case "xls":
                return new XLSFileReader();
            case "xlsx":
                return new XLSXFileReader();
            default:
                throw new IllegalArgumentException("Unsupported file format: " + fileExtension);
        }
    }
}
