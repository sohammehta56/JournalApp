package com.learn.myFirstProject.service;

import com.learn.myFirstProject.entity.User;
import com.learn.myFirstProject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserDetailServiceImplTests {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(User.builder().username("Soham").password("soham").roles(
                Arrays.asList("USER")).build());
        UserDetails userDetails = userDetailService.loadUserByUsername("Soham");
        assertNotNull(userDetails);
    }
}
