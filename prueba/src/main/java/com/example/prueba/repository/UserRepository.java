package com.example.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.prueba.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();
    @Query(value="SELECT id, username, fullname, address, phone FROM users", nativeQuery = true)
    List<Object[]> findAllNoPw();

}
