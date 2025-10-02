package com.springbootProject.Student_Management_System.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springbootProject.Student_Management_System.entity.Student;
import com.springbootProject.Student_Management_System.service.StudentService;

import jakarta.validation.Valid;

// We use @Controller - to make this class as Spring MVC class

@Controller
public class StudentController {
	
	private StudentService studentService;
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	
	// handler method to handle list students and return model and view
	
	@GetMapping("/students")
	public String listStudent(Model model) {
		model.addAttribute("students",studentService.getAllUsers());
		return "students";  // here we are returning a students view where we use Model consisting of data
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student = new Student();
		model.addAttribute("student",student); // empty object for form binding
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@Valid @ModelAttribute("student") Student student,
			BindingResult result) {
		
		// back to form if it has errors
		if(result.hasErrors()) {
			return "create_student";
		}
		
		// if no errors are present
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model) {
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@Valid @PathVariable Long id, 
			@ModelAttribute("student") Student student, Model model, BindingResult result) {
		
		// if update form has errors
		if(result.hasErrors()) {
			return "edit_student";
		}
		
		// if there are no errors present
		
		// (1) get student from database :-
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		// (2) save update student object :-
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
		
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteById(id);
		return "redirect:/students";
	}
	
	
}
