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

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CourseDTO other = (CourseDTO) obj;
			if (building == null) {
				if (other.building != null)
					return false;
			} else if (!building.equals(other.building))
				return false;
			if (course_id != other.course_id)
				return false;
			if (endDate == null) {
				if (other.endDate != null)
					return false;
			} else if (!endDate.equals(other.endDate))
				return false;
			if (grade == null) {
				if (other.grade != null)
					return false;
			} else if (!grade.equals(other.grade))
				return false;
			if (id != other.id)
				return false;
			if (instructor == null) {
				if (other.instructor != null)
					return false;
			} else if (!instructor.equals(other.instructor))
				return false;
			if (room == null) {
				if (other.room != null)
					return false;
			} else if (!room.equals(other.room))
				return false;
			if (section != other.section)
				return false;
			if (startDate == null) {
				if (other.startDate != null)
					return false;
			} else if (!startDate.equals(other.startDate))
				return false;
			if (times == null) {
				if (other.times != null)
					return false;
			} else if (!times.equals(other.times))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
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
				+ ", semester=" + semester + ", courses=[" + courses + "] ]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleDTO other = (ScheduleDTO) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (semester == null) {
			if (other.semester != null)
				return false;
		} else if (!semester.equals(other.semester))
			return false;
		if (student_email == null) {
			if (other.student_email != null)
				return false;
		} else if (!student_email.equals(other.student_email))
			return false;
		if (student_id != other.student_id)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	

}
