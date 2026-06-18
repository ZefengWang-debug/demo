package com.example.demo.service;

import com.example.demo.model.ChatMessage;
import com.example.demo.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageService {
    private final ChatMessageRepository repo;
    public ChatMessageService(ChatMessageRepository repo) { this.repo = repo; }
    public ChatMessage saveMessage(Long sessionId, Long senderId, String senderType, String content) {
        ChatMessage m = new ChatMessage();
        m.setSessionId(sessionId); m.setSenderId(senderId); m.setSenderType(senderType); m.setContent(content);
        m.setSendTime(LocalDateTime.now());
        return repo.save(m);
    }
    public List<ChatMessage> listBySession(Long sessionId) { return repo.findBySessionIdOrderBySendTimeAsc(sessionId); }
}
