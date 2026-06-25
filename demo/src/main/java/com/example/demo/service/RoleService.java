package com.example.demo.service;
import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RoleService {
 private final RoleRepository repo;
 public RoleService(RoleRepository repo){this.repo=repo;}
 public List<Role> list(){return repo.findAll();}
 public Role add(Role r){return repo.save(r);}
 public Role update(Long id,Role r){Role old=repo.findById(id).orElseThrow(); old.setRoleName(r.getRoleName()); old.setRoleCode(r.getRoleCode()); old.setRemark(r.getRemark()); return repo.save(old);}
 public void delete(Long id){repo.deleteById(id);}
}
