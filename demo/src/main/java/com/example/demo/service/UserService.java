package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User add(User user) {
        if (user.getStatus() == null) user.setStatus("NORMAL");
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        User old = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setName(user.getName());
        old.setEmail(user.getEmail());
        old.setStatus(user.getStatus());
        return userRepository.save(old);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
