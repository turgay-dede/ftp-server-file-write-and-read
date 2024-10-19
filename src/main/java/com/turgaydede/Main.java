package com.turgaydede;

import com.turgaydede.constants.FilePath;
import com.turgaydede.service.FtpService;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FtpService ftpService = new FtpService();
        String server = "127.0.0.1";
        String username = "myuser";
        String password = "mypass";

        FTPClient ftpClient = ftpService.connectToFtp(server, username, password);

        ftpService.uploadAllFiles(ftpClient,"filess");


        ftpService.readFile(ftpClient, FilePath.XLS.getDestDir());
        System.out.println("-----------------------------------------------------------------------------------------");

        ftpService.readFile(ftpClient, FilePath.XLSX.getDestDir());
        System.out.println("-----------------------------------------------------------------------------------------");

        ftpService.readFile(ftpClient, FilePath.CSV.getDestDir());
        System.out.println("-----------------------------------------------------------------------------------------");

        ftpService.readFile(ftpClient, FilePath.JSON.getDestDir());
        System.out.println("-----------------------------------------------------------------------------------------");

        ftpService.readFile(ftpClient, FilePath.XML.getDestDir());
    }
}