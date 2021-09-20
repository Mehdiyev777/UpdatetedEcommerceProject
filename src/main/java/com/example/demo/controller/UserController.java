package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.ReqUser;
import com.example.demo.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    @Autowired
   private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public UserController(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @GetMapping("/users")
    public ResponseEntity <List<User>>getUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));

    }
    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form){
  userService.addRoleToUser(form.getUserName(),form.getRoleName());
  return ResponseEntity.ok().build();
    }
    @Data
    class RoleToUserForm{
       private String userName;
       private String roleName;
    }
    @PostMapping (value="/update")
        public void updateUser(@RequestBody User user){
        userService.updateOrSaveUser(user);

    }
    @GetMapping(value = "/search/{userName}")
    public List <User> findUser(@PathVariable("userName") String userName){
       return userService.findUser(userName);

    }
    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestParam("userId") Long userId){
        userService.deleteUser(userId);
    }
}
