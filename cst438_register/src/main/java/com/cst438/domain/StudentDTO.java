package com.cst438.domain;

import java.util.Objects;

public class StudentDTO {
	public int id;
	public String student_email;
	public String student_name;
	public int status_code;
	public String status;
	
	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", student_email=" + student_email + ", student_name=" + student_name
				+ ", status_code=" + status_code + ", status=" + status + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDTO other = (StudentDTO) obj;
		return id == other.id && Objects.equals(status, other.status) && status_code == other.status_code
				&& Objects.equals(student_email, other.student_email)
				&& Objects.equals(student_name, other.student_name);
	}
	
	
}
