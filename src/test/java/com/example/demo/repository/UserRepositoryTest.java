package com.example.demo.repository;

import com.example.demo.model.Address;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
private UserRepository userRepository;
    @Test
    public void saveUser(){
//User user = new User();
//user.setName("fdfdsf");
//userRepository.save(user);
//userRepository.deleteById(56l);
        //userRepository.deleteById(57l);
        //userRepository.deleteById(58l);
    }
    @Test
    public void findStudent(){
List<User> userList =userRepository.findByUsernameOrNameOrSurname("gamer");
        System.out.println(userList);

    }
}