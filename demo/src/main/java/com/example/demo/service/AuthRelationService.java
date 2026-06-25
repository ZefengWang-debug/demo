package com.example.demo.service;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
public class AuthRelationService {
 private final UserRoleRepository urRepo; private final RolePermissionRepository rpRepo; private final RoleRepository roleRepo; private final PermissionRepository permRepo;
 public AuthRelationService(UserRoleRepository urRepo,RolePermissionRepository rpRepo,RoleRepository roleRepo,PermissionRepository permRepo){this.urRepo=urRepo;this.rpRepo=rpRepo;this.roleRepo=roleRepo;this.permRepo=permRepo;}
 public UserRole bindUserRole(Long userId,Long roleId){Optional<UserRole> old=urRepo.findByUserIdAndRoleId(userId,roleId); if(old.isPresent())return old.get(); UserRole ur=new UserRole();ur.setUserId(userId);ur.setRoleId(roleId);return urRepo.save(ur);}
 public RolePermission bindRolePermission(Long roleId,Long permissionId){Optional<RolePermission> old=rpRepo.findByRoleIdAndPermissionId(roleId,permissionId); if(old.isPresent())return old.get(); RolePermission rp=new RolePermission();rp.setRoleId(roleId);rp.setPermissionId(permissionId);return rpRepo.save(rp);}
 @Transactional public String removeUserRole(Long userId,Long roleId){urRepo.deleteByUserIdAndRoleId(userId,roleId);return "ok";}
 @Transactional public String removeRolePermission(Long roleId,Long permissionId){rpRepo.deleteByRoleIdAndPermissionId(roleId,permissionId);return "ok";}
 public List<UserRole> userRoles(Long userId){return urRepo.findByUserId(userId);}
 public List<RolePermission> rolePermissions(Long roleId){return rpRepo.findByRoleId(roleId);}
 public List<String> userRoleCodes(Long userId){List<String> list=new ArrayList<>(); for(UserRole ur:urRepo.findByUserId(userId)) roleRepo.findById(ur.getRoleId()).ifPresent(r->list.add(r.getRoleCode())); return list;}
 public List<String> userPermissionCodes(Long userId){List<String> list=new ArrayList<>(); for(UserRole ur:urRepo.findByUserId(userId)) for(RolePermission rp:rpRepo.findByRoleId(ur.getRoleId())) permRepo.findById(rp.getPermissionId()).ifPresent(p->list.add(p.getPermissionCode())); return list;}
 public String checkUserPermission(Long userId,String code){return userPermissionCodes(userId).contains(code)?"has permission":"no permission";}
}
