package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "sys_permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permissionName;
    private String permissionCode;
    private String remark;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPermissionName() { return permissionName; }
    public void setPermissionName(String permissionName) { this.permissionName = permissionName; }
    public String getPermissionCode() { return permissionCode; }
    public void setPermissionCode(String permissionCode) { this.permissionCode = permissionCode; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
