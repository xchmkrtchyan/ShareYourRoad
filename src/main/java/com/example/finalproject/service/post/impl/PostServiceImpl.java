package com.example.finalproject.service.post.impl;

import com.example.finalproject.persistence.post.PostRepository;
import com.example.finalproject.persistence.post.model.Post;
import com.example.finalproject.persistence.user.UserRepository;
import com.example.finalproject.persistence.user.model.User;
import com.example.finalproject.rest.model.PostRequest;
import com.example.finalproject.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void create(PostRequest postRequest) {
        Optional<User> byUsername = userRepository.findByUsername(postRequest.getUsername());
        User user = byUsername.get();
        Post post = new Post(
                postRequest.getUsername(),
                postRequest.getStartPoint(),
                postRequest.getEndPoint(),
                postRequest.getStartPointDate(),
                postRequest.getEndPointDate(),
                postRequest.getPassengers());
        postRepository.save(post);
        user.setPost(post);
        userRepository.save(user);
    }
}
