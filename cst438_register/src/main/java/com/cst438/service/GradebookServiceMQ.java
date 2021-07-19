package com.cst438.service;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.cst438.domain.CourseDTOG;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentDTO;
import com.cst438.domain.EnrollmentRepository;


public class GradebookServiceMQ implements GradebookService {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	
	public GradebookServiceMQ() {
		System.out.println("MQ grade book service");
	}
	
	// send message to grade book service about new student enrollment in course
	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {
		 
		EnrollmentDTO enrollmentDTO = new EnrollmentDTO(student_email, student_name, course_id);
		
		rabbitTemplate.convertAndSend("gradebook-exchange", "", // routing key none.
				enrollmentDTO);
		
		System.out.println("Message send to gradbook service for student "+ student_email +" " + course_id);  
		
	}
	
	@RabbitListener(queues = "registration-queue")
	public void receive(CourseDTOG courseDTOG) {
		System.out.println("Receive enrollment :" + courseDTOG);

		// process the list of student grades
		for (CourseDTOG.GradeDTO g : courseDTOG.grades) {
			Enrollment e = enrollmentRepository.findByEmailAndCourseId(g.student_email, courseDTOG.course_id);
			e.setCourseGrade(g.grade);
			enrollmentRepository.save(e);
			System.out.println("final grade update " + g.student_email + " " + courseDTOG.course_id + " " + g.grade);
		}
	}
	
	

}
