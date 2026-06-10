package com.example.demo.repository;

import com.example.demo.model.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {
    List<UserPermission> findByUserId(Long userId);
    void deleteByUserIdAndPermissionId(Long userId, Long permissionId);
}
