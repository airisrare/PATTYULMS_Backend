package com.example.pattyulms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;


import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

/*- Noah Roerig helped me configure the s3 bucket while Jack Blackwell 
//taught me how s3 worked overall. Thank you for being great classmates and 
teachers */




@Service
public class S3Util {
    //Bucket name
    private final String BUCKET = "pattyulms-bucket";

    //s3 Client used 
    @Autowired S3Client s3Client;

    @Value("${aws.s3.region}")
    private String region;

    

    public String uploadFile(
        //Style id is how we will organise our images
        String styleID,
        //filename is the actual file we are using 
        String fileName,
        InputStream inputStream,
        Optional<Map<String, String>> optionalMetaData)
        throws S3Exception, AwsServiceException, SdkClientException, IOException
        {
            //Make sure we are in the right region
            //System.out.println(region);
            ObjectMetadata objectMetadata = new ObjectMetadata();

            if(optionalMetaData != null){
                optionalMetaData.ifPresent(map ->{
                    if (!map.isEmpty()){
                        map.forEach(objectMetadata::addUserMetadata);
                    }
                });
            }
            // create the fileName by using the styleID as a directory, then 
        // use fileName. This will keep all images unique within their directories
        String finalFileName = String.format("%s/%s", styleID, fileName);

        //This is what we are requesting from the object request. Thi sof a payload
        PutObjectRequest request = PutObjectRequest.builder()
            .bucket(BUCKET)
            .key(finalFileName) 
            .build();

            s3Client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));

            //Using the parameters from the request we can create our URL string
            String s3Url = String.format("https://%s.s3.%s.amazonaws.com/%s", BUCKET, region, finalFileName);

            System.out.println(s3Url);
            return s3Url;
        }

        //Download file
        public byte[] downloadFile(String fileName){
            GetObjectRequest request = GetObjectRequest.builder().bucket(BUCKET).key(fileName).build();

            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(request);
            byte[] data = objectBytes.asByteArray();
            return data;
        }
    
}
