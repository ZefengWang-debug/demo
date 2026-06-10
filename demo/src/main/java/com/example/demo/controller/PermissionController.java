package com.example.demo.controller;

import com.example.demo.model.Permission;
import com.example.demo.model.UserPermission;
import com.example.demo.service.PermissionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    private final PermissionService permissionService;
    public PermissionController(PermissionService permissionService) { this.permissionService = permissionService; }
    @GetMapping("/list") public List<Permission> list() { return permissionService.findAll(); }
    @PostMapping("/add") public Permission add(@RequestBody Permission permission) { return permissionService.add(permission); }
    @PutMapping("/update/{id}") public Permission update(@PathVariable Long id, @RequestBody Permission permission) { return permissionService.update(id, permission); }
    @DeleteMapping("/delete/{id}") public String delete(@PathVariable Long id) { permissionService.delete(id); return "deleted"; }
    @PostMapping("/grant") public UserPermission grant(@RequestParam Long userId, @RequestParam Long permissionId) { return permissionService.grant(userId, permissionId); }
    @DeleteMapping("/revoke") public String revoke(@RequestParam Long userId, @RequestParam Long permissionId) { permissionService.revoke(userId, permissionId); return "revoked"; }
    @GetMapping("/user/{userId}") public List<UserPermission> userPermission(@PathVariable Long userId) { return permissionService.listUserPermissions(userId); }
}
