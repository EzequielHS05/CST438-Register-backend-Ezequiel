package com.cst438;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cst438.service.GradebookService;
import com.cst438.service.GradebookServiceMQ;
import com.cst438.service.GradebookServiceREST;

@SpringBootApplication
public class Cst4380wRegistrationApplication extends WebSecurityConfigurerAdapter {

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
	
	@Override
   	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors();
 		http.authorizeRequests().antMatchers("/**").permitAll();
	}
	
	// this is necessary to allow cross-origin http requests from React front end. 
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://accounts.google.com"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
