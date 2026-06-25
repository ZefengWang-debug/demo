package com.example.demo.controller;
import com.example.demo.model.Role; import com.example.demo.service.RoleService;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/role")
public class RoleController {
 private final RoleService service; public RoleController(RoleService service){this.service=service;}
 @GetMapping("/list") public List<Role> list(){return service.list();}
 @PostMapping("/add") public Role add(@RequestBody Role obj){return service.add(obj);}
 @PutMapping("/update/{id}") public Role update(@PathVariable Long id,@RequestBody Role obj){return service.update(id,obj);}
 @DeleteMapping("/delete/{id}") public String delete(@PathVariable Long id){service.delete(id);return "deleted";}
}
