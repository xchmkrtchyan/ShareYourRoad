package com.example.finalproject.service.file;

import com.example.finalproject.service.userDetails.model.MyUserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface AWSS3Service {
    void uploadFile(MyUserDetails myUserDetails , final MultipartFile multipartFile);
}
