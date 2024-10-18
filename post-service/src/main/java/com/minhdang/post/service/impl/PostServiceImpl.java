package com.minhdang.post.service.impl;

import com.minhdang.post.dto.request.PostRequest;
import com.minhdang.post.dto.response.PageResponse;
import com.minhdang.post.dto.response.PostResponse;
import com.minhdang.post.dto.response.UserProfileResponse;
import com.minhdang.post.entity.Post;
import com.minhdang.post.helper.DateTimeFormatter;
import com.minhdang.post.mapper.PostMapper;
import com.minhdang.post.repository.PostRepository;
import com.minhdang.post.repository.httpclient.ProfileClient;
import com.minhdang.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {

    DateTimeFormatter dateTimeFormatter;
    PostRepository postRepository;
    PostMapper postMapper;
    ProfileClient profileClient;

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

        var response = postMapper.toPostResponse(post);
        response.setCreated(dateTimeFormatter.format(post.getCreatedDate()));

        return response;
    }

    @Override
    public PageResponse<PostResponse> getMyPosts(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userId = authentication.getName(); // Subject field in jwt

        UserProfileResponse userProfile = null;

        try {
            userProfile = profileClient.getUserProfile(userId).getResult();
        } catch (Exception e) {
            log.error("Error while getting user profile {}", e.getMessage());
        }


        Sort sort = Sort.by("createdDate").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        String username = userProfile != null ? userProfile.getUsername() : null;
        var pageData = postRepository.findByUserId(pageable, userId);

        var postList = pageData.getContent().stream().map(post -> {
            var postResponse = postMapper.toPostResponse(post);
            postResponse.setCreated(dateTimeFormatter.format(post.getCreatedDate()));
            postResponse.setUsername(username);
            return postResponse;
        }).toList();

        return PageResponse.<PostResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalElements(pageData.getTotalElements())
                .totalPages(pageData.getTotalPages())
                .data(postList)
                .build();
    }

}
