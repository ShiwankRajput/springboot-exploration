package com.springbootProject.Student_Management_System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springbootProject.Student_Management_System.entity.Student;

@Service
public interface StudentService {

	List<Student> getAllUsers();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	
	Student updateStudent(Student student);
	
	void deleteById(Long id);
}
