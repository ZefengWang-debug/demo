package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(username, password));
        return "login success";
    }

    @GetMapping("/check")
    public String check(@RequestParam String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject.isPermitted(permission) ? "has permission" : "no permission";
    }
}
