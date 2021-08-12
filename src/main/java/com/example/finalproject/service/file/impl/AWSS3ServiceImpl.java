package com.example.finalproject.service.file.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.finalproject.persistence.user.UserRepository;
import com.example.finalproject.persistence.user.model.User;
import com.example.finalproject.service.file.AWSS3Service;
import com.example.finalproject.service.userDetails.model.MyUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(AWSS3ServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    private String accessKey = "AKIASAQ356ILOPXJMQ4G";

    private String secretKey = "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";

    private String bucketName = "shareyourroad";

    @Override
    public void uploadFile(MyUserDetails myUserDetails, MultipartFile multipartFile) {
        LOGGER.info("File upload in progress.");
        try {
            String url = uploadFileToS3Bucket(bucketName, multipartFile);
            Long id = myUserDetails.getId();
            User byId = userRepository.getById(id);
            /*byId.setImgUrl(url);*/
            userRepository.save(byId);
            LOGGER.info("File upload is completed.");
        } catch (AmazonServiceException | IOException ex) {
            LOGGER.info("File upload is failed.");
            LOGGER.error("Error= {} while uploading file.", ex.getMessage());
        }
    }

    private File convertMultiPartFileToFile(MultipartFile multipartFile) {
        final File file = new File(multipartFile.getOriginalFilename());
        try (final FileOutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (final IOException ex) {
            LOGGER.error("Error converting the multi-part file to file= ", ex.getMessage());
        }
        return file;
    }

    private String uploadFileToS3Bucket(String bucketName, MultipartFile multipartFile) throws IOException {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        LOGGER.info("Uploading file with name= " + multipartFile.getOriginalFilename());
        final PutObjectRequest putObjectRequest;
            putObjectRequest = new PutObjectRequest(bucketName, "media/" + multipartFile.getOriginalFilename(), multipartFile.getInputStream(),new ObjectMetadata())
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            client.putObject(putObjectRequest);
            URL url = client.getUrl(bucketName, "media/" + multipartFile.getOriginalFilename());
            String urlString = url.toExternalForm();
            return urlString;
        }
    }