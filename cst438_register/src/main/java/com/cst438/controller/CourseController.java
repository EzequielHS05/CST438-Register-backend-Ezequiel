package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.domain.CourseDTOG;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class CourseController {
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	/*
	 * endpoint used by gradebook service to transfer final course grades
	 */
	@PutMapping("/course/{course_id}")
	@Transactional
	public void updateCourseGrades( @RequestBody CourseDTOG courseDTO, @PathVariable("course_id") int course_id) {
		
		//TODO  complete this method in homework 4
		for(CourseDTOG.GradeDTO grade : courseDTO.grades) {
			Enrollment enrollment = enrollmentRepository.findByEmailAndCourseId(grade.student_email, course_id);
			enrollment.setCourseGrade(grade.grade);
			enrollmentRepository.save(enrollment);
		}
	}
}
