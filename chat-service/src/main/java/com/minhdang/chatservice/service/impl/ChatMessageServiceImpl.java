package com.minhdang.chatservice.service.impl;

import com.minhdang.chatservice.dto.request.ChatMessageRequest;
import com.minhdang.chatservice.dto.response.ChatMessageResponse;
import com.minhdang.chatservice.entity.ChatMessage;
import com.minhdang.chatservice.entity.ParticipantInfo;
import com.minhdang.chatservice.exception.AppException;
import com.minhdang.chatservice.exception.ErrorCode;
import com.minhdang.chatservice.mapper.ChatMessageMapper;
import com.minhdang.chatservice.repository.ChatMessageRepository;
import com.minhdang.chatservice.repository.ConversationRepository;
import com.minhdang.chatservice.repository.httpclient.ProfileClient;
import com.minhdang.chatservice.service.ChatMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatMessageServiceImpl implements ChatMessageService {

    ChatMessageRepository chatMessageRepository;
    ConversationRepository conversationRepository;
    ProfileClient profileClient;
    ChatMessageMapper chatMessageMapper;

    @Override
    public List<ChatMessageResponse> getChatMessages(String conversationId) {

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        // Validate conversationId
        conversationRepository.findById(conversationId)
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND)) // Validate conversation exists
                .getParticipants().stream()
                .filter(participant -> participant.getUserId().equals(userId))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND)); // Validate user is a participant in the conversation

        // Fetch chat messages for the conversation
        List<ChatMessage> chatMessages = chatMessageRepository.findAllByConversationIdOrderByCreatedDateDesc(conversationId);

        return chatMessages.stream()
                .map(this::toChatMessageResponse) // Convert each ChatMessage to ChatMessageResponse
                .toList();
    }

    @Override
    public ChatMessageResponse create(ChatMessageRequest request) {

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        // Validate conversationId and message
        conversationRepository.findById(request.getConversationId())
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND)) // Validate conversation exists
                .getParticipants().stream()
                .filter(participant -> participant.getUserId().equals(userId))
                .findAny()
                .orElseThrow(() -> new AppException(ErrorCode.CONVERSATION_NOT_FOUND)); // Validate user is a participant in the conversation

        // Get user profile from ProfileClient
        var userProfileResponse = profileClient.getProfile(userId);

        if (Objects.isNull(userProfileResponse)) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

        var userProfile = userProfileResponse.getResult();

        // Build ChatMessage

        ChatMessage chatMessage = chatMessageMapper.toChatMessage(request);

        chatMessage.setSender(ParticipantInfo.builder()
                .userId(userProfile.getUserId())
                .firstName(userProfile.getFirstName())
                .lastName(userProfile.getLastName())
                .avatar(userProfile.getAvatar())
                .username(userProfile.getUsername())
                .build());

        chatMessage.setCreatedDate(Instant.now());

        // Save ChatMessage to repository
        chatMessage = chatMessageRepository.save(chatMessage);

        // Convert to ChatMessageResponse
        return toChatMessageResponse(chatMessage);
    }

    private ChatMessageResponse toChatMessageResponse(ChatMessage chatMessage) {
        var chatMessageResponse = chatMessageMapper.toChatMessageResponse(chatMessage);

        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        // Check if the message is sent by the current user
        chatMessageResponse.setMe(chatMessage.getSender().getUserId().equals(userId));

        return chatMessageResponse;
    }

}
