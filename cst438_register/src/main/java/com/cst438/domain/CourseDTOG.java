package com.cst438.domain;

import java.util.List;

public class CourseDTOG {

	public static class GradeDTO {
		public String student_email;
		public String student_name;
		public String grade;
	}

	public int course_id;
	public List<GradeDTO> grades;


}
