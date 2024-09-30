package com.minhdang.post.service.impl;

import com.minhdang.post.dto.request.PostRequest;
import com.minhdang.post.dto.response.PostResponse;
import com.minhdang.post.entity.Post;
import com.minhdang.post.mapper.PostMapper;
import com.minhdang.post.repository.PostRepository;
import com.minhdang.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    PostMapper postMapper;

    @Override
    public PostResponse createPost(PostRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Post post = Post.builder()
                .content(request.getContent())
                .userId(authentication.getName()) // Subject field in jwt
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .build();

        log.info("User {} created post at {}", authentication.getName(), post.getCreatedDate());
        post = postRepository.save(post);
        log.info("User {} created post success", authentication.getName());

        return postMapper.toPostResponse(post);
    }

    @Override
    public List<PostResponse> getMyPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = authentication.getName(); // Subject field in jwt

        return postRepository.findByUserId(userId).stream()
                .map(postMapper::toPostResponse).toList();
    }

}
