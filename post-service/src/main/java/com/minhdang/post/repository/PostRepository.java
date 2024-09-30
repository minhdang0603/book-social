package com.minhdang.post.repository;

import com.minhdang.post.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByUserId(String userId);

}
