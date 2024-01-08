package com.example.prueba.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.prueba.service.UploadService;
import com.example.prueba.util.JwtUtil;
import com.google.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.prueba.dto.LoginDto;
import com.example.prueba.model.User;
import com.example.prueba.repository.UserRepository;
import com.example.prueba.response.userResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Inject
    private UploadService upload;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        User user = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if(user==null){
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        }
        String token = JwtUtil.generateToken(loginDto.getUsername());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity<Resource> getAllUsersNoPw() throws IOException {

        List<Object[]> users = userRepository.findAllNoPw();

        List<String> outputList;
        outputList = users
                .stream()
                .map((obj) -> Objects.toString(obj, null))
                .collect(Collectors.toList());

        Resource resource = new ByteArrayResource(outputList.toString().getBytes());

        return ResponseEntity.ok()
                .contentLength(outputList.toString().length())
                .contentType(MediaType.APPLICATION_JSON)
                .body(resource);

    }

    @PostMapping("/copy")
    public ResponseEntity<String> uploadFile (@RequestParam MultipartFile file) throws IOException {
        upload.process(IOUtils.toByteArray((InputStream) file));
        return new ResponseEntity<>("File read correctly", HttpStatus.OK);
    }

}
