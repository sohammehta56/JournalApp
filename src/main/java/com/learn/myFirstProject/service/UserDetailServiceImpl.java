package com.learn.myFirstProject.service;

import com.learn.myFirstProject.entity.User;
import com.learn.myFirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.nonNull(user)) {
            return org.springframework.security.core.userdetails.User.builder()
                                                              .username(user.getUsername())
                                                              .password(user.getPassword())
                                                              .roles(user.getRoles().toArray(new String[0]))
                                                                         .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
