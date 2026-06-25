package com.example.demo.repository;
import com.example.demo.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long>{
 List<RolePermission> findByRoleId(Long roleId);
 Optional<RolePermission> findByRoleIdAndPermissionId(Long roleId, Long permissionId);
 void deleteByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
