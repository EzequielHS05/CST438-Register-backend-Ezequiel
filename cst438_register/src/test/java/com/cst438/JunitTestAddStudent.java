package com.cst438;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import com.cst438.controller.StudentController;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { StudentController.class })
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
public class JunitTestAddStudent {
	static final String URL = "http://localhost:8080";
	public static final String TEST_STUDENT_EMAIL = "testing@csumb.edu";
	public static final String TEST_STUDENT_NAME  = "test";

	
	@MockBean
	StudentRepository studentRepository;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void addNewStudent() throws Exception{
		MockHttpServletResponse response;
		


		//adding student
		given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(null);
		
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.id = 1;
		studentDTO.student_email = TEST_STUDENT_EMAIL;
		studentDTO.student_name = TEST_STUDENT_NAME;
		response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/student")
			      .content(asJsonString(studentDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertEquals(200, response.getStatus());
		
		StudentDTO result = fromJsonString(response.getContentAsString(), StudentDTO.class);
		assertNotEquals(0, result.id);
		
	}
	
	@Test
	public void repeatedEmail() throws Exception {
		MockHttpServletResponse response;
		//trying to add with same email
		Student s1 = new Student();
		s1.setEmail(TEST_STUDENT_EMAIL);
		given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(s1);
		StudentDTO studentDTO = new StudentDTO();

		studentDTO.student_email = TEST_STUDENT_EMAIL;
		studentDTO.student_name = TEST_STUDENT_NAME;
		
		response = mvc.perform(
				MockMvcRequestBuilders
			      .post("/student")
			      .content(asJsonString(studentDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertEquals(400, response.getStatus());
	
	}
	
	@Test
	public void changeStatus() throws Exception {
		MockHttpServletResponse response;
		//changing status
		Student s1 = new Student();
		s1.setEmail(TEST_STUDENT_EMAIL);
		s1.setName(TEST_STUDENT_NAME);
		s1.setStudent_id(1);
		s1.setStatus("Hold");
		s1.setStatusCode(1);
		given(studentRepository.findByEmail(TEST_STUDENT_EMAIL)).willReturn(s1);
		StudentDTO studentDTO = new StudentDTO();

		
		response = mvc.perform(
				MockMvcRequestBuilders
			      .put("/student/"+TEST_STUDENT_EMAIL)
			      .content(asJsonString(studentDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();
		assertEquals(200, response.getStatus());
		
		verify(studentRepository).save(any(Student.class));
		
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
