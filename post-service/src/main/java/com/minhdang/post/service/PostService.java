package com.minhdang.post.service;

import com.minhdang.post.dto.request.PostRequest;
import com.minhdang.post.dto.response.PostResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    PostResponse createPost(PostRequest request);

    List<PostResponse> getMyPosts();
}
