package com.learn.myFirstProject.controller;

import com.learn.myFirstProject.entity.User;
import com.learn.myFirstProject.repository.UserRepository;
import com.learn.myFirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody final User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        final User existingUser = userService.getUserByUsername(username);
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        userService.saveNewUser(existingUser);

        return new ResponseEntity(existingUser, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userRepository.deleteByUsername(username);
        return new ResponseEntity("User Deleted Successfully", HttpStatus.NO_CONTENT);
    }
}
