package com.example.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    private String roleCode;
    private String remark;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
    public String getRoleCode() { return roleCode; }
    public void setRoleCode(String roleCode) { this.roleCode = roleCode; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
