package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) { this.userService = userService; }
    @GetMapping("/list") public List<User> list() { return userService.findAll(); }
    @PostMapping("/add") public User add(@RequestBody User user) { return userService.add(user); }
    @PutMapping("/update/{id}") public User update(@PathVariable Long id, @RequestBody User user) { return userService.update(id, user); }
    @DeleteMapping("/delete/{id}") public String delete(@PathVariable Long id) { userService.delete(id); return "deleted"; }
}
