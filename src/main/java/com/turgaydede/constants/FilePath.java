package com.turgaydede.constants;

public enum FilePath {
    XLS("files/person.xls", "/xls/"),
    XLSX("files/person.xlsx", "/xlsx/"),
    CSV("files/person.csv", "/csv/"),
    JSON("files/person.json", "/json/"),
    XML("files/person.xml", "/xml/");

    private final String sourcePath;
    private final String destDir;

    FilePath(String sourcePath, String destDir) {
        this.sourcePath = sourcePath;
        this.destDir = destDir;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestDir() {
        return destDir;
    }
}
