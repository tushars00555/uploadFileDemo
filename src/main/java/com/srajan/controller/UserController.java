package com.srajan.controller;
import com.srajan.repository.UserRepo;
import com.srajan.storage.StorageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * Created by tushar on 3/4/17.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final String STATUS = "status";
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String MESSAGE = "msg";
   @Autowired
   private StorageService storageService;
    @Autowired
    private Logger logger;
    @Autowired
    private UserRepo userRepo;


    //multipart file upload
    @RequestMapping(value = "/multipart", method = RequestMethod.POST)//,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String save1(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {

        String s = multipartFile.getOriginalFilename();
        String t = System.currentTimeMillis() + s;
       // File f = new File("/home/tushar/multipartImages/" + t);
       // multipartFile.transferTo(f);
        try {
            new FTPUpload().saveToFTPServer(multipartFile);
        }catch (Exception e){
            e.printStackTrace();
        }
        storageService.store(multipartFile, t);

        return "success";
    }


}
