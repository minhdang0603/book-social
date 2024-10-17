package com.minhdang.post.service;

import com.minhdang.post.dto.request.PostRequest;
import com.minhdang.post.dto.response.PageResponse;
import com.minhdang.post.dto.response.PostResponse;


public interface PostService {

    PostResponse createPost(PostRequest request);

    PageResponse<PostResponse> getMyPosts(int page, int size);
}
