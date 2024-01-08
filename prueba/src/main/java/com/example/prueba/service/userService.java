package com.example.prueba.service;

import com.example.prueba.model.User;
import com.example.prueba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class userService {

    @Autowired
    private UserRepository userRepository;

}
