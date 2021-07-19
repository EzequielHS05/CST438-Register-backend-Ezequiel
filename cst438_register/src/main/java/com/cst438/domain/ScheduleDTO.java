package com.cst438.domain;

import java.util.List;

public class ScheduleDTO {

	public static class CourseDTO {

		public int id;
		public int course_id;
		public int section;
		public String title;
		public String times;
		public String building;
		public String room;
		public String instructor;
		public String startDate;
		public String endDate;
		public String grade;

		@Override
		public String toString() {
			return "CourseDTO [id=" + id + ", course_id=" + course_id + ", section=" + section + ", title=" + title
					+ ", times=" + times + ", building=" + building + ", room=" + room + ", instructor=" + instructor
					+ ", startDate=" + startDate + ", endDate=" + endDate + ", grade=" + grade + "]";
		}
	}

	public String student_email;
	public int student_id;
	public int year;
	public String semester;
	public List<CourseDTO> courses;

	@Override
	public String toString() {
		return "ScheduleDTO [student_email=" + student_email + ", student_id=" + student_id + ", year=" + year
				+ ", semester=" + semester + ", courses=" + courses + "]";
	}

}
