package com.example.demo.service;

import com.example.demo.model.UserRole;
import com.example.demo.model.RolePermission;
import com.example.demo.repository.UserRoleRepository;
import com.example.demo.repository.RolePermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AuthRelationService {
    private final UserRoleRepository userRoleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public AuthRelationService(UserRoleRepository userRoleRepository, RolePermissionRepository rolePermissionRepository) {
        this.userRoleRepository = userRoleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    public UserRole bindUserRole(Long userId, Long roleId) {
        UserRole ur = new UserRole();
        ur.setUserId(userId);
        ur.setRoleId(roleId);
        return userRoleRepository.save(ur);
    }

    @Transactional
    public String removeUserRole(Long userId, Long roleId) {
        userRoleRepository.deleteByUserIdAndRoleId(userId, roleId);
        return "ok";
    }

    public RolePermission bindRolePermission(Long roleId, Long permissionId) {
        RolePermission rp = new RolePermission();
        rp.setRoleId(roleId);
        rp.setPermissionId(permissionId);
        return rolePermissionRepository.save(rp);
    }

    @Transactional
    public String removeRolePermission(Long roleId, Long permissionId) {
        rolePermissionRepository.deleteByRoleIdAndPermissionId(roleId, permissionId);
        return "ok";
    }

    public List<UserRole> userRoles(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }

    public List<RolePermission> rolePermissions(Long roleId) {
        return rolePermissionRepository.findByRoleId(roleId);
    }
}
