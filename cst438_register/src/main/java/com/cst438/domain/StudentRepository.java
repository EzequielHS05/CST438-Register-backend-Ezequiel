package com.cst438.domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository <Student, Integer> {
	
	public Student findByEmail(String email);

}
