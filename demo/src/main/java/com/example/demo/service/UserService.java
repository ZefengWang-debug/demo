package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public List<User> findAll() { return userRepository.findAll(); }
    public User add(User user) { return userRepository.save(user); }
    public User update(Long id, User user) {
        User old = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found: " + id));
        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setName(user.getName());
        old.setEmail(user.getEmail());
        return userRepository.save(old);
    }
    public void delete(Long id) { userRepository.deleteById(id); }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
