package com.cst438.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cst438.domain.CourseDTOG;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

@RestController
public class CourseController {
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	/*
	 * endpoint used by gradebook service to transfer final course grades
	 */
	@PutMapping("/course/{course_id}")
	public void updateCourseGrades( @RequestBody CourseDTOG courseDTO, @PathVariable("course_id") int course_id) {
		
		// process the list of student grades
		for (CourseDTOG.GradeDTO g: courseDTO.grades) {
			Enrollment e = enrollmentRepository.findByEmailAndCourseId(g.student_email, course_id);
			e.setCourseGrade(g.grade);
			enrollmentRepository.save(e);
			System.out.println("final grade update "+g.student_email+" "+course_id +" "+g.grade);
		}
	}

}
