package com.example.demo.controller;
import com.example.demo.model.Permission; import com.example.demo.service.PermissionService;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/permission")
public class PermissionController {
 private final PermissionService service; public PermissionController(PermissionService service){this.service=service;}
 @GetMapping("/list") public List<Permission> list(){return service.list();}
 @PostMapping("/add") public Permission add(@RequestBody Permission obj){return service.add(obj);}
 @PutMapping("/update/{id}") public Permission update(@PathVariable Long id,@RequestBody Permission obj){return service.update(id,obj);}
 @DeleteMapping("/delete/{id}") public String delete(@PathVariable Long id){service.delete(id);return "deleted";}
}
