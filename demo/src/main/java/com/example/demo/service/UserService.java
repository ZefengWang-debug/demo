package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserService {
 private final UserRepository repo;
 public UserService(UserRepository repo){this.repo=repo;}
 public List<User> list(){return repo.findAll();}
 public User add(User u){if(u.getStatus()==null)u.setStatus("NORMAL"); return repo.save(u);}
 public User update(Long id,User u){User old=repo.findById(id).orElseThrow(); old.setUsername(u.getUsername()); old.setPassword(u.getPassword()); old.setName(u.getName()); old.setEmail(u.getEmail()); old.setStatus(u.getStatus()); return repo.save(old);}
 public void delete(Long id){repo.deleteById(id);}
}
