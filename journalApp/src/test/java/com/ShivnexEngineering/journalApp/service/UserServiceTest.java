package com.ShivnexEngineering.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ShivnexEngineering.journalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserRepository userRepository;
	
	@Disabled
	@Test
	public void testSubtract() {
		assertEquals(0,4-4);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {
		"Abhinav Rana",
		"Askhit Verma" //test fails here
	})
	public void testFindByUserName(String userName) {
		assertNotNull(userRepository.findByUserName(userName));
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"2,10,12",
		"3,3,6"
	})
	public void testAdd(int a, int b, int expected) {
		assertEquals(expected, a+b);
	}
	
}
