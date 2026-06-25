package com.example.demo.repository;
import com.example.demo.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface UserRoleRepository extends JpaRepository<UserRole,Long>{
 List<UserRole> findByUserId(Long userId);
 Optional<UserRole> findByUserIdAndRoleId(Long userId, Long roleId);
 void deleteByUserIdAndRoleId(Long userId, Long roleId);
}
