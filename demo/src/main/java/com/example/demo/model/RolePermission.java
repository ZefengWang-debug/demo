package com.example.demo.model;
import jakarta.persistence.*;
@Entity
@Table(name="sys_role_permission")
public class RolePermission {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
 private Long id;
 private Long roleId; private Long permissionId;

 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public Long getRoleId(){return roleId;} public void setRoleId(Long roleId){this.roleId=roleId;} public Long getPermissionId(){return permissionId;} public void setPermissionId(Long permissionId){this.permissionId=permissionId;}
}
