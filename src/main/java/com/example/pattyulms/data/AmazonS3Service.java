package com.example.pattyulms.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmazonS3Service {
    @Autowired
    private com.example.pattyulms.util.S3Util s3Util;

    public byte[] download(String fileName){
        return s3Util.downloadFile(fileName);
    }
    

    
}
