package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> list() { return roleRepository.findAll(); }

    public Role add(Role role) { return roleRepository.save(role); }

    public Role update(Long id, Role role) {
        Role old = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("role not found"));
        old.setRoleName(role.getRoleName());
        old.setRoleCode(role.getRoleCode());
        old.setRemark(role.getRemark());
        return roleRepository.save(old);
    }

    public void delete(Long id) { roleRepository.deleteById(id); }
}
