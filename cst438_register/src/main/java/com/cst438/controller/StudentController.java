package com.cst438.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;


@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/student")
	public StudentDTO getStudent(@RequestParam("student_id") int student_id) {
		System.out.println("/student");
		Student find = studentRepository.findById(student_id).orElse(null);
		if (find != null) {
			System.out.println("/schedule student "+ find.getName()+" "+find.getStudent_id());
			
			StudentDTO s = createStudentDTO(find);
			return s;
		} else {
			System.out.println("/schedule student not found. "+student_id);
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Student not found. " );
		}
		
	}
	
	@PostMapping("/student")
	@Transactional
	public StudentDTO addNewStudent(@RequestBody StudentDTO student) {
		
		Student check = studentRepository.findByEmail(student.student_email);
		if (check == null) {
			Student registerStudent = new Student();
			registerStudent.setStudent_id(student.id);
			registerStudent.setName(student.student_name);
			registerStudent.setEmail(student.student_email);
			registerStudent.setStatusCode(student.status_code);
			studentRepository.save(registerStudent);
			StudentDTO result = createStudentDTO(registerStudent);
			return result;
		} else {
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Email is already registered: .  " + student.student_email);
		}
	}
	
	@PutMapping("/student/{id}")
	public void updateStudentStatus(@RequestBody StudentDTO student, @PathVariable int student_id) {
		Student checkIfAvailable = studentRepository.findById(student_id).orElse(null);
		if (checkIfAvailable != null) {
			//updating student status
			checkIfAvailable.setStatus(student.status);
			checkIfAvailable.setStatusCode(student.status_code);
			studentRepository.save(checkIfAvailable);
		} else {
			// Student not found 
			throw  new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid student ID. "+ student_id);
		}
	}
	
	
	private StudentDTO createStudentDTO(Student s){
		StudentDTO sdto = new StudentDTO();
		sdto.id = s.getStudent_id();
		sdto.student_name = s.getName();
		sdto.student_email = s.getEmail();
		sdto.status_code = s.getStatusCode();
		return sdto;
	}
	

}
