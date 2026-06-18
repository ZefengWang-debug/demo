package com.example.demo.service;

import com.example.demo.model.ChatSession;
import com.example.demo.repository.ChatSessionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatSessionService {
    private final ChatSessionRepository repo;
    public ChatSessionService(ChatSessionRepository repo) { this.repo = repo; }
    public ChatSession create(Long userId, Long customerId) {
        ChatSession s = new ChatSession();
        s.setUserId(userId); s.setCustomerId(customerId); s.setStatus("OPEN");
        s.setCreateTime(LocalDateTime.now()); s.setUpdateTime(LocalDateTime.now());
        return repo.save(s);
    }
    public List<ChatSession> list() { return repo.findAll(); }
    public ChatSession close(Long id) {
        ChatSession s = repo.findById(id).orElseThrow(() -> new RuntimeException("Session not found: " + id));
        s.setStatus("CLOSED"); s.setUpdateTime(LocalDateTime.now());
        return repo.save(s);
    }
}
