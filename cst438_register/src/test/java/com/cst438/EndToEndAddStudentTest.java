package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.controller.StudentController;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

@SpringBootTest
public class EndToEndAddStudentTest {
	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_EMAIL = "testing1@csumb.edu";

	public static final int TEST_ID = 333; 
	
	private static final String TEST_NAME = "test user";
	
	public static final int SLEEP_DURATION = 1000;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void AddStudentTest() throws Exception{
		
		//check if there's a student already registered, and if it is then delete it
		Student check = null;
		do {
			check = studentRepository.findByEmail(TEST_USER_EMAIL);
			if(check != null) {
				studentRepository.delete(check);
			}
		}while(check != null);
		
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			//locate "Add Student" button
			WebElement we = driver.findElement(By.xpath("//a[@href='/student']"));
			we.click();
			Thread.sleep(SLEEP_DURATION);
			
			//Enter student email and name
			driver.findElement(By.xpath("//input[@name='student_name']")).sendKeys(TEST_NAME);
			driver.findElement(By.xpath("//input[@name='student_email']")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.xpath("//button")).click();
			Thread.sleep(SLEEP_DURATION);
			
			//check if student was added to the database
			Student student = studentRepository.findByEmail(TEST_USER_EMAIL);
			assertEquals(TEST_USER_EMAIL, student.getEmail());
		}catch(Exception e) {
			throw e;
		}finally {
			Student student = studentRepository.findByEmail(TEST_USER_EMAIL);
			if(student != null) {
				studentRepository.delete(student);
			}
			
			driver.quit();
		}
	}
}
