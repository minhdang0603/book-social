package com.minhdang.post.controller;

import com.minhdang.post.dto.request.PostRequest;
import com.minhdang.post.dto.response.ApiResponse;
import com.minhdang.post.dto.response.PostResponse;
import com.minhdang.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {

    PostService postService;

    @PostMapping("/")
    ApiResponse<PostResponse> createPost(@RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request))
                .build();
    }

    @GetMapping("/my-posts")
    ApiResponse<List<PostResponse>> getMyPosts() {
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getMyPosts())
                .build();
    }

}