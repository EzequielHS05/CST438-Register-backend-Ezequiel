package com.cst438;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import com.cst438.service.GradebookService;
import com.cst438.service.GradebookServiceMQ;
import com.cst438.service.GradebookServiceREST;

@SpringBootApplication
public class Cst4380wRegistrationApplication  {  

	public static void main(String[] args) {
		SpringApplication.run(Cst4380wRegistrationApplication.class, args);
	}
	
	
	@Bean(name = "GradebookService")
	@ConditionalOnProperty(prefix = "gradebook", name = "service", havingValue = "MQ")
	public GradebookService gradebookServiceMQ() {
		return new GradebookServiceMQ();
	}
	
	
	@Bean(name = "GradebookService")
	@ConditionalOnProperty(prefix = "gradebook", name = "service", havingValue = "REST")
	public GradebookService gradebookServiceREST() {
		return new GradebookServiceREST();
	}
	
	@Bean(name = "GradebookService")
	@ConditionalOnProperty(prefix="gradebook", name="service", havingValue = "default")
	public GradebookService gradebookDefault() {
		return new GradebookService();
	}

}
