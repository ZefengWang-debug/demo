package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));
        Map<String, Object> result = new HashMap<>();
        result.put("message", "login success");
        result.put("username", username);
        return result;
    }

    @GetMapping("/me")
    public Map<String, Object> me() {
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> result = new HashMap<>();
        result.put("authenticated", subject.isAuthenticated());
        result.put("principal", subject.getPrincipal());
        return result;
    }

    @GetMapping("/need-login")
    public String needLogin() { return "need login"; }
    @GetMapping("/unauthorized")
    public String unauthorized() { return "unauthorized"; }
}
