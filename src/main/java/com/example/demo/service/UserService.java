package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepositorty;
    @Autowired
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepositorty.findAll().forEach(user -> users.add(user));
        return users;

    }
    public User getUser(Long id){
        return  userRepositorty.findById(id).get();

    }
    public User saveUser(  User user){
        log.info("Saving new User");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      return  userRepositorty.save(user);
    }
    public void deleteUser(Long id){
        userRepositorty.deleteById(id);
    }
    public void update(User user, Long id){
        userRepositorty.save(user);
    }
public Role saveRole(Role role){
        return roleRepository.save(role);
}
public void addRoleToUser(String userName,String roleName){
 User user =userRepositorty.findByUsername(userName);
 Role role = roleRepository.findByName(roleName);
 user.getRoles().add(role);
}
public User getUser(String username){
        log.info("username e gore arama ",username);
        return userRepositorty.findByUsername(username);
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositorty.findByUsername(username);
        if(user==null){
            log.info("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else{
            log.info("User found in database");
        }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
    public void updateOrSaveUser( User user){
        userRepositorty.save(user);
    }


    public List<User> findUser (String searchname) {




       return userRepositorty.findByUsernameOrNameOrSurname(searchname);
    }
}
