package com.minhdang.chatservice.mapper;

import com.minhdang.chatservice.dto.response.ConversationResponse;
import com.minhdang.chatservice.entity.Conversation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    ConversationResponse toConversationResponse(Conversation conversation);

    List<ConversationResponse> toConversationResponseList(List<Conversation> conversations);
}
