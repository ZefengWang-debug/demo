package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService) { this.roleService = roleService; }

    @GetMapping("/list")
    public List<Role> list() { return roleService.list(); }

    @PostMapping("/add")
    public Role add(@RequestBody Role role) { return roleService.add(role); }

    @PutMapping("/update/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        return roleService.update(id, role);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roleService.delete(id);
        return "deleted";
    }
}
