package com.springbootProject.Student_Management_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

}



// for checking whether data is filled properly or not :-

/*

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Student student1 = new Student("Shiwank","Kumar","shiwank41@gmail.com");
		studentRepository.save(student1);
		
		Student student2 = new Student("Raj","Verma","raj43@gmail.com");
		studentRepository.save(student2);
		
		Student student3 = new Student("Arush","Bhola","arushBhola@gmail.com");
		studentRepository.save(student3);
		
	}

}

*/


