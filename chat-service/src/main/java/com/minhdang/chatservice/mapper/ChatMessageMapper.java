package com.minhdang.chatservice.mapper;

import com.minhdang.chatservice.dto.request.ChatMessageRequest;
import com.minhdang.chatservice.dto.response.ChatMessageResponse;
import com.minhdang.chatservice.entity.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageResponse toChatMessageResponse(ChatMessage chatMessage);

    ChatMessage toChatMessage(ChatMessageRequest request);

    List<ChatMessageResponse> toChatMessageResponse(List<ChatMessage> chatMessages);
}
