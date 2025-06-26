package com.minhdang.chatservice.service;

import com.minhdang.chatservice.dto.request.ConversationRequest;
import com.minhdang.chatservice.dto.response.ConversationResponse;

import java.util.List;

public interface ConversationService {
    List<ConversationResponse> myConversations();

    ConversationResponse create(ConversationRequest request);
}
