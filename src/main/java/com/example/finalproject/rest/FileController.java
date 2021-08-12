package com.example.finalproject.rest;

import com.example.finalproject.service.file.AWSS3Service;
import com.example.finalproject.service.userDetails.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private AWSS3Service service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@AuthenticationPrincipal MyUserDetails myUserDetails ,
                                             @RequestPart(value= "file") final MultipartFile multipartFile) {
        service.uploadFile(myUserDetails,multipartFile);
        final String response = "[" + multipartFile.getOriginalFilename() + "] uploaded successfully.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
