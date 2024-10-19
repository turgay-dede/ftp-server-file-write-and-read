package com.turgaydede.service;

import com.turgaydede.constants.FilePath;
import com.turgaydede.strategy.FileReaderFactory;
import com.turgaydede.strategy.FileReaderStrategy;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpService {

    public FTPClient connectToFtp(String server, String username, String password) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.connect(server);
        boolean succeeded = ftp.login(username, password);
        check(ftp, "login", succeeded);
        System.out.println("Connected to " + server + ".");

        ftp.setConnectTimeout(60000);
        ftp.setDataTimeout(60000);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
        System.out.println("Switched to passive mode.");

        return ftp;
    }

    public void uploadAllFiles(FTPClient ftpClient, String folderPath) throws IOException {
        File folder = new File(folderPath);

        if (!folder.exists()) {
            System.out.println("Folder does not exist: " + folderPath);
            return;
        }

        if (!folder.isDirectory()) {
            System.out.println("Provided path is not a directory: " + folderPath);
            return;
        }

        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No files found in the folder: " + folderPath);
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                uploadFile(ftpClient, file);
            }
        }
    }


    public void uploadFile(FTPClient ftpClient, File localFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(localFile)) {
            String fileExtension = getFileExtension(localFile.getName());
            String destDir = FilePath.getDestDirByExtension(fileExtension);
            String remoteFile = destDir + localFile.getName();
            System.out.println("Uploading file to FTP: " + remoteFile);

            boolean done = ftpClient.storeFile(remoteFile, inputStream);
            if (done) {
                System.out.println("File uploaded successfully: " + localFile.getName());
            } else {
                System.out.println("File upload failed: " + localFile.getName());
            }
        }
    }

    public void readFile(FTPClient ftp, String filePath) throws IOException {
        System.out.println("Reading file from FTP: " + filePath);

        FTPFile[] files = ftp.listFiles(filePath);
        InputStream inputStream = null;
        for (FTPFile file : files) {
            String fileName = file.getName();

            System.out.println("Available file: " + fileName + ", Size: " + file.getSize() + " bytes");

            inputStream = ftp.retrieveFileStream(filePath + fileName);

            if (inputStream == null) {
                System.out.println("Failed to retrieve file. FTP Reply: " + ftp.getReplyString());
            } else {
                boolean success = ftp.completePendingCommand();
                if (success) {
                    System.out.println("File transfer completed successfully.");
                    String fileExtension = getFileExtension(fileName);
                    FileReaderStrategy strategy = FileReaderFactory.getStrategy(fileExtension);
                    strategy.readFile(inputStream);

                } else {
                    System.out.println("Failed to complete the file transfer.");
                }
            }
        }

    }
    public String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf(".") == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private static void check(FTPClient ftp, String cmd, boolean succeeded) throws IOException {
        if (!succeeded) {
            throw new IOException("FTP error during command: " + cmd + " - " + ftp.getReplyString());
        }
    }
}
