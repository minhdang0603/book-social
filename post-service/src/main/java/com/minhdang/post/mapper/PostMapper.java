package com.minhdang.post.mapper;

import com.minhdang.post.dto.response.PostResponse;
import com.minhdang.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostResponse toPostResponse(Post post);

}
