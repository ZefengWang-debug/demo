package com.example.demo.controller;
import com.example.demo.model.ChatSession;
import com.example.demo.service.ChatSessionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/session")
public class ChatSessionController {
    private final ChatSessionService service;
    public ChatSessionController(ChatSessionService service) { this.service = service; }
    @PostMapping("/create") public ChatSession create(@RequestParam Long userId, @RequestParam Long customerId) { return service.create(userId, customerId); }
    @GetMapping("/list") public List<ChatSession> list() { return service.list(); }
    @PutMapping("/close/{id}") public ChatSession close(@PathVariable Long id) { return service.close(id); }
}
