package com.example.finalproject.service.post.impl;

import com.example.finalproject.repository.PostRepository;
import com.example.finalproject.persistence.model.post.model.Post;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.persistence.model.user.model.User;
import com.example.finalproject.persistence.model.PostRequest;
import com.example.finalproject.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public Post getPostByID(Long id) {
        Post byId = postRepository.getById(id);
        return byId;
    }

    @Override
    public void updatePost(PostRequest postRequest) {
        Post byId = postRepository.getById(postRequest.getId());
        byId.setStartPoint(postRequest.getStartPoint());
        byId.setEndPoint(postRequest.getEndPoint());
        byId.setPassengers(postRequest.getPassengers());
        byId.setStartPointDate(postRequest.getStartPointDate());
        byId.setEndPointDate(postRequest.getEndPointDate());
        postRepository.save(byId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post findByName(String name) {
        Post postByStartPoint = postRepository.getPostByStartPoint(name);
        return postByStartPoint;
    }
}







