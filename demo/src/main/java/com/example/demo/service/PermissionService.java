package com.example.demo.service;

import com.example.demo.model.Permission;
import com.example.demo.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> list() { return permissionRepository.findAll(); }

    public Permission add(Permission permission) { return permissionRepository.save(permission); }

    public Permission update(Long id, Permission permission) {
        Permission old = permissionRepository.findById(id).orElseThrow(() -> new RuntimeException("permission not found"));
        old.setPermissionName(permission.getPermissionName());
        old.setPermissionCode(permission.getPermissionCode());
        old.setRemark(permission.getRemark());
        return permissionRepository.save(old);
    }

    public void delete(Long id) { permissionRepository.deleteById(id); }
}
