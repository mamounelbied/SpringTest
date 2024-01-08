package com.example.prueba.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static java.lang.Thread.sleep;

@Component
public class UploadService {
    @Async
    public void process(byte[] bs)  {
        System.out.println(new String(bs));
    }

}
