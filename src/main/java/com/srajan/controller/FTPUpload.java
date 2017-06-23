package com.srajan.controller;


import com.srajan.domain.UploadFile;
import com.srajan.repository.UploadFileRepo;
import com.srajan.storage.StorageService;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;




/**
 * Created by tushar on 22/6/17.
 */
@RestController
@RequestMapping(value = "/uploadfile")
public class FTPUpload {
    @Autowired
    private StorageService storageService;
    @Autowired
    private Logger logger;
    @Autowired
    private UploadFileRepo uploadFileRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveToFTPServer(MultipartFile multipartFile) throws Exception {
        UploadFile uploadFile=new UploadFile();

        String hostAddress = uploadFile.getUploadFile();

        String ftpFolderPath = uploadFile.getUploadFile();


        Integer port = 21;
        File filePath = null;
        FTPClient ftp = new FTPClient();
        FileInputStream fis = null;

        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftp.connect(hostAddress, port);

        System.out.println("FTP URL is:" + ftp.getDefaultPort());
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }

//        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
            System.out.println("Connected......");
            File directory = new File("/home/tushar/multipartImages");

            File[] fList = directory.listFiles();
            InputStream input = null;

            for (File file : fList) {

                String fileName = file.getName();
                filePath = new File(directory, fileName);
                System.out.println(filePath);
                input = new FileInputStream(filePath);
                ftp.storeFile(ftpFolderPath + fileName, input);
                System.out.println("Upload file");
                filePath.delete();

                System.out.println("Delete success");
            }

            ftp.logout();
            input.close();
        }
    }



