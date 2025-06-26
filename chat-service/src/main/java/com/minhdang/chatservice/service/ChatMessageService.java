package com.minhdang.chatservice.service;

import com.minhdang.chatservice.dto.request.ChatMessageRequest;
import com.minhdang.chatservice.dto.response.ChatMessageResponse;

import java.util.List;

public interface ChatMessageService {
    List<ChatMessageResponse> getChatMessages(String conversationId);

    ChatMessageResponse create(ChatMessageRequest request);
}
