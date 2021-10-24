package com.cst438.domain;

import java.util.List;

public class CourseDTOG {

	public static class GradeDTO {
		public String student_email;
		public String student_name;
		public String grade;
		
		@Override
		public String toString() {
			return "[email=" + student_email + ", name=" + student_name + ", grade=" + grade + "]";
		}
		
	}

	public int course_id;
	public List<GradeDTO> grades;
	
	@Override
	public String toString() {
		return "CourseDTOG [course_id=" + course_id + ", grades=[" + grades + "] ]";
	}


}
