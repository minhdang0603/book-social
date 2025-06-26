package com.minhdang.chatservice.service.impl;

import com.minhdang.chatservice.dto.request.ConversationRequest;
import com.minhdang.chatservice.dto.response.ConversationResponse;
import com.minhdang.chatservice.entity.Conversation;
import com.minhdang.chatservice.entity.ParticipantInfo;
import com.minhdang.chatservice.exception.AppException;
import com.minhdang.chatservice.exception.ErrorCode;
import com.minhdang.chatservice.mapper.ConversationMapper;
import com.minhdang.chatservice.repository.ConversationRepository;
import com.minhdang.chatservice.repository.httpclient.ProfileClient;
import com.minhdang.chatservice.service.ConversationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversationServiceImpl implements ConversationService {

    ConversationRepository conversationRepository;
    ProfileClient profileClient;

    ConversationMapper conversationMapper;

    @Override
    public List<ConversationResponse> myConversations() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Conversation> conversations = conversationRepository.findAllByParticipantIdsContains(userId);

        return conversations.stream()
                .map(this::toConversationResponse)
                .toList();
    }

    @Override
    public ConversationResponse create(ConversationRequest request) {

        // Fetch user info
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();
        List<String> participantIds = request.getParticipantIds();

        var currentUserInfoResponse = profileClient.getProfile(currentUserId);

        var participantInfoResponse = profileClient.getProfile(
                request.getParticipantIds().getFirst()
        );

        if(Objects.isNull(participantInfoResponse) || Objects.isNull(currentUserInfoResponse)){
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

        var userInfo = currentUserInfoResponse.getResult();
        var participantInfo = participantInfoResponse.getResult();

        List<String> userIds = List.of(currentUserId, request.getParticipantIds().getFirst());

        var sortedIds = userIds.stream().sorted().toList(); // Sort to ensure consistent hash generation

        String participantHash = generateParticipantHash(sortedIds); // Generate a unique hash for the participants

        // Check if conversation already exists
        var existingConversation = conversationRepository.findByParticipantsHash(participantHash)
                .orElse(null);
        if(!Objects.isNull(existingConversation)){
            return toConversationResponse(existingConversation);
        }

        List<ParticipantInfo> participants = List.of(
                ParticipantInfo.builder()
                        .userId(currentUserId)
                        .username(userInfo.getUsername())
                        .avatar(userInfo.getAvatar())
                        .firstName(userInfo.getFirstName())
                        .lastName(userInfo.getLastName())
                        .build(),
                ParticipantInfo.builder()
                        .userId(participantInfo.getUserId())
                        .username(participantInfo.getUsername())
                        .avatar(participantInfo.getAvatar())
                        .firstName(participantInfo.getFirstName())
                        .lastName(participantInfo.getLastName())
                        .build()
        );

        // Build conversation info
        Conversation conversation = Conversation.builder()
                .type(request.getType())
                .participantsHash(participantHash)
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .participants(participants)
                .build();

        conversation = conversationRepository.save(conversation);

        return toConversationResponse(conversation);
    }

    private String generateParticipantHash(List<String> ids) {
        StringJoiner stringJoiner = new StringJoiner("_");
        ids.forEach(stringJoiner::add);

        // SHA 256 hash can be used here for better uniqueness

        return stringJoiner.toString();
    }

    private ConversationResponse toConversationResponse(Conversation conversation) {
        String currentUserId = SecurityContextHolder.getContext().getAuthentication().getName();

        ConversationResponse conversationResponse = conversationMapper.toConversationResponse(conversation);

        conversation.getParticipants().stream()
                .filter(participantInfo -> !participantInfo.getUserId().equals(currentUserId))
                .findFirst().ifPresent(participantInfo -> {
                    conversationResponse.setConversationName(participantInfo.getUsername());
                    conversationResponse.setConversationAvatar(participantInfo.getAvatar());
                });

        return conversationResponse;
    }
}
