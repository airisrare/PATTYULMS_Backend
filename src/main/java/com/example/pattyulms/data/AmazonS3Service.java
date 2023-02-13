package com.example.pattyulms.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*- Noah Roerig helped me configure the s3 bucket while Jack Blackwell 
//taught me how s3 worked overall. Thank you for being great classmates and 
teachers */


@Service
public class AmazonS3Service {
    @Autowired
    private com.example.pattyulms.util.S3Util s3Util;

    public byte[] download(String fileName){
        return s3Util.downloadFile(fileName);
    }
    

    
}
