package com.example.demo.controller;

import com.example.demo.model.UserRole;
import com.example.demo.model.RolePermission;
import com.example.demo.service.AuthRelationService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/auth-relation")
public class AuthRelationController {
    private final AuthRelationService service;
    public AuthRelationController(AuthRelationService service) { this.service = service; }

    @PostMapping("/user-role")
    public UserRole bindUserRole(@RequestParam Long userId, @RequestParam Long roleId) {
        return service.bindUserRole(userId, roleId);
    }

    @DeleteMapping("/user-role")
    public String removeUserRole(@RequestParam Long userId, @RequestParam Long roleId) {
        return service.removeUserRole(userId, roleId);
    }

    @PostMapping("/role-permission")
    public RolePermission bindRolePermission(@RequestParam Long roleId, @RequestParam Long permissionId) {
        return service.bindRolePermission(roleId, permissionId);
    }

    @DeleteMapping("/role-permission")
    public String removeRolePermission(@RequestParam Long roleId, @RequestParam Long permissionId) {
        return service.removeRolePermission(roleId, permissionId);
    }

    @GetMapping("/user/{userId}/roles")
    public List<UserRole> userRoles(@PathVariable Long userId) {
        return service.userRoles(userId);
    }

    @GetMapping("/role/{roleId}/permissions")
    public List<RolePermission> rolePermissions(@PathVariable Long roleId) {
        return service.rolePermissions(roleId);
    }
}
