package com.example.finalproject.persistence.post;

import com.example.finalproject.persistence.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    boolean findByStartPoint(String name);
    Post getPostByStartPoint(String startPoint);
}
