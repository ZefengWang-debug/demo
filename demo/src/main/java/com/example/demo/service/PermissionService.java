package com.example.demo.service;

import com.example.demo.model.Permission;
import com.example.demo.model.UserPermission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.UserPermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final UserPermissionRepository userPermissionRepository;

    public PermissionService(PermissionRepository permissionRepository, UserPermissionRepository userPermissionRepository) {
        this.permissionRepository = permissionRepository;
        this.userPermissionRepository = userPermissionRepository;
    }

    public List<Permission> findAll() { return permissionRepository.findAll(); }
    public Permission add(Permission permission) { return permissionRepository.save(permission); }
    public Permission update(Long id, Permission permission) {
        Permission old = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found: " + id));
        old.setCode(permission.getCode());
        old.setName(permission.getName());
        old.setDescription(permission.getDescription());
        return permissionRepository.save(old);
    }
    public void delete(Long id) { permissionRepository.deleteById(id); }
    public Permission findByCode(String code) { return permissionRepository.findByCode(code).orElse(null); }
    public List<UserPermission> listUserPermissions(Long userId) { return userPermissionRepository.findByUserId(userId); }
    public UserPermission grant(Long userId, Long permissionId) {
        UserPermission up = new UserPermission();
        up.setUserId(userId);
        up.setPermissionId(permissionId);
        return userPermissionRepository.save(up);
    }
    @Transactional
    public void revoke(Long userId, Long permissionId) {
        userPermissionRepository.deleteByUserIdAndPermissionId(userId, permissionId);
    }
}
