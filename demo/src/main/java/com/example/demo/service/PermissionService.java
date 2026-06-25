package com.example.demo.service;
import com.example.demo.model.Permission;
import com.example.demo.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PermissionService {
 private final PermissionRepository repo;
 public PermissionService(PermissionRepository repo){this.repo=repo;}
 public List<Permission> list(){return repo.findAll();}
 public Permission add(Permission p){return repo.save(p);}
 public Permission update(Long id,Permission p){Permission old=repo.findById(id).orElseThrow(); old.setPermissionName(p.getPermissionName()); old.setPermissionCode(p.getPermissionCode()); old.setRemark(p.getRemark()); return repo.save(old);}
 public void delete(Long id){repo.deleteById(id);}
}
