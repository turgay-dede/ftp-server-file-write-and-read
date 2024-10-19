package com.turgaydede.constants;

public enum FilePath {
    XLS("/xls/"),
    XLSX("/xlsx/"),
    CSV("/csv/"),
    JSON("/json/"),
    XML("/xml/");

    private final String destDir;

    FilePath(String destDir) {
        this.destDir = destDir;
    }
    public String getDestDir() {
        return destDir;
    }

    public static String getDestDirByExtension(String fileExtension) {
        String extension = fileExtension.startsWith(".") ? fileExtension.substring(1).toUpperCase() : fileExtension.toUpperCase();

        for (FilePath filePath : FilePath.values()) {
            if (filePath.name().equals(extension)) {
                return filePath.getDestDir();
            }
        }
        throw new IllegalArgumentException("Unknown file extension: " + fileExtension);
    }
}
