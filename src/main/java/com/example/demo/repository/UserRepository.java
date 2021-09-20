package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
User findByUsername(String username);
@Query(value ="  select u  FROM User u  WHERE u.name =?1 or u.surname=?1 or u.username=?1 ")
List<User> findByUsernameOrNameOrSurname(String searchname);




}
