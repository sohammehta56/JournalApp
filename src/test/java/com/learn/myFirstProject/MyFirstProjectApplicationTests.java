package com.learn.myFirstProject;

import com.learn.myFirstProject.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class MyFirstProjectApplicationTests {

	@Autowired
	private UserRepositoryImpl userRepository;

	@Test
	void contextLoads() {
		assertNotNull(userRepository.getUsersForSentimentAnalysis());
	}

}
