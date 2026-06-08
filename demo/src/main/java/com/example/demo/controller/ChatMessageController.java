package com.example.demo.controller;
import com.example.demo.model.ChatMessage;
import com.example.demo.service.ChatMessageService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/message")
public class ChatMessageController {
    private final ChatMessageService service;
    public ChatMessageController(ChatMessageService service) { this.service = service; }
    @PostMapping("/send")
    public ChatMessage send(@RequestParam Long sessionId, @RequestParam Long senderId, @RequestParam String senderType, @RequestParam String content) {
        return service.saveMessage(sessionId, senderId, senderType, content);
    }
    @GetMapping("/list/{sessionId}") public List<ChatMessage> list(@PathVariable Long sessionId) { return service.listBySession(sessionId); }
}
