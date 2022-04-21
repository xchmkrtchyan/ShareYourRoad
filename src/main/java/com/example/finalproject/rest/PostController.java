package com.example.finalproject.rest;

import com.example.finalproject.persistence.model.post.model.Post;
import com.example.finalproject.persistence.model.MessageResponse;
import com.example.finalproject.persistence.model.PostRequest;
import com.example.finalproject.service.post.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/allTrips")
    public ResponseEntity<?> getAllPosts(){
        List<Post> allPosts = postService.getAllPosts();
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping("/allTrips/{name}")
    public ResponseEntity<?> searchByName(@PathVariable("name") String name){
        return ResponseEntity.ok(postService.findByName(name));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePost(@RequestBody PostRequest postRequest,@RequestParam Long id){
        postService.updatePost(postRequest);
        return ResponseEntity.ok(new MessageResponse("Post updated successfully"));
    }

}
