package com.learn.myFirstProject.controller;

import com.learn.myFirstProject.entity.User;
import com.learn.myFirstProject.repository.UserRepository;
import com.learn.myFirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users= userRepository.findAll();
        if(!users.isEmpty()){
            return new ResponseEntity(users, HttpStatus.OK);
        }
        return new ResponseEntity("No Users Found", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<String> createAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
        return new ResponseEntity("Admin Added Successfully", HttpStatus.CREATED);
    }
}
