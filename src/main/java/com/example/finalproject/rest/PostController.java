package com.example.finalproject.rest;

import com.example.finalproject.rest.model.MessageResponse;
import com.example.finalproject.rest.model.PostRequest;
import com.example.finalproject.service.post.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user/post")
public class PostController {

    @Autowired
    PostServiceImpl postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest){
        postService.create(postRequest);
        return ResponseEntity.ok(new MessageResponse("Post created successfully"));
    }


}
