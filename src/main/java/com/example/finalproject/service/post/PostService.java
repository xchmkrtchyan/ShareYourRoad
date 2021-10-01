package com.example.finalproject.service.post;

import com.example.finalproject.persistence.post.model.Post;
import com.example.finalproject.rest.model.PostRequest;

import java.util.List;

public interface PostService {

    void create(PostRequest postRequest);

    Post getPostByID(Long id);

    void updatePost(PostRequest postRequest);

    List<Post> getAllPosts();

    Post findByName(String name);
}
