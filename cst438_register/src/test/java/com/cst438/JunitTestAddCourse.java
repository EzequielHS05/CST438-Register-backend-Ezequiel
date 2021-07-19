package com.cst438;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Base64;
import java.util.Calendar;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.cst438.controller.ScheduleController;
import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.ScheduleDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.service.GradebookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.ContextConfiguration;

/* 
 * Example of using junit with mockito for mock objects
 *  the database repositories are mocked with test data.
 *  
 * Mockmvc is used to test a simulated REST call to the RestController
 * 
 * the return value and database update are verified.
 */
@ContextConfiguration(classes = { ScheduleController.class })
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
public class JunitTestAddCourse {

	static final String URL = "http://localhost:8080";

	public static final int TEST_COURSE_ID = 40442;

	@MockBean
	CourseRepository courseRepository;

	@MockBean
	StudentRepository studentRepository;

	@MockBean
	EnrollmentRepository enrollmentRepository;

	@MockBean
	GradebookService gradebookService;

	@Autowired
	private MockMvc mvc;

	// This object will be magically initialized by the initFields method below.
	// private JacksonTester<CityInfo> json;

	@Before
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void addCourse()  throws Exception {
		
		Course course = new Course();
		course.setCourse_id(TEST_COURSE_ID);
		course.setSemester("Fall");
		course.setYear(2021);		
		Calendar c = Calendar.getInstance();
		c.set(2021,  8,  16);
		course.setStart(new java.sql.Date( c.getTimeInMillis() ));
		c.set(2021,  12, 16);
		course.setEnd(new java.sql.Date( c.getTimeInMillis() ));
		
		Student student = new Student();
		student.setEmail("test@csumb.edu");
		student.setName("test");
		student.setStatusCode(0);
		student.setStudent_id(1);
		
		Enrollment enrollment = new Enrollment();
		enrollment.setCourse(course);
		enrollment.setEnrollment_id(1);
		enrollment.setSemester("Fall");
		enrollment.setStudent(student);
		enrollment.setYear(2021);
		
		// given  -- stubs for database repositories that return test data
	    given(courseRepository.findByCourse_id(TEST_COURSE_ID)).willReturn(course);
	    given(studentRepository.findByEmail("test@csumb.edu")).willReturn(student);
	    given(enrollmentRepository.save(any(Enrollment.class))).willReturn(enrollment);
	  
		
		ScheduleDTO.CourseDTO courseDTO = new ScheduleDTO.CourseDTO();
		courseDTO.course_id = TEST_COURSE_ID;
		
		// then 
		MockHttpServletResponse response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/schedule")
			      .content(asJsonString(courseDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON)
			      .header("Authorization", "Basic "+Base64.getEncoder().encodeToString("test@csumb.edu:test".getBytes())))
				.andReturn().getResponse();
		
		
		// verify that return status = OK (value 200) 
		assertEquals(200, response.getStatus());
		
		// verify that returned data has non zero primary key
		ScheduleDTO.CourseDTO result = fromJsonString(response.getContentAsString(), ScheduleDTO.CourseDTO.class);
		assertNotEquals( 0  , result.id);
		
		// verify that repository save method was called.
		verify(enrollmentRepository).save(any(Enrollment.class));

	}

	private static String asJsonString(final Object obj) {
		try {

			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	private static <T> T  fromJsonString(String str, Class<T> valueType ) {
		try {
			return new ObjectMapper().readValue(str, valueType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
