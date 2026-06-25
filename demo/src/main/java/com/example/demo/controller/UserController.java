package com.example.demo.controller;
import com.example.demo.model.User; import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/user")
public class UserController {
 private final UserService service; public UserController(UserService service){this.service=service;}
 @GetMapping("/list") public List<User> list(){return service.list();}
 @PostMapping("/add") public User add(@RequestBody User u){return service.add(u);}
 @PutMapping("/update/{id}") public User update(@PathVariable Long id,@RequestBody User u){return service.update(id,u);}
 @DeleteMapping("/delete/{id}") public String delete(@PathVariable Long id){service.delete(id);return "deleted";}
}
