package com.cst438;

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

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;

/*
 * This example shows how to use selenium testing using the web driver 
 * with Chrome browser.
 * 
 *  - Buttons, input, and anchor elements are located using XPATH expression.
 *  - onClick( ) method is used with buttons and anchor tags.
 *  - Input fields are located and sendKeys( ) method is used to enter test data.
 *  - Spring Boot JPA is used to initialize, verify and reset the database before
 *      and after testing.
 */

@SpringBootTest
public class EndToEndScheduleTest {

	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_EMAIL = "test@csumb.edu";

	public static final int TEST_COURSE_ID = 40442;

	public static final String TEST_SEMESTER = "2021 Fall";

	public static final int SLEEP_DURATION = 1000; // 1 second.

	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Test
	public void addCourseTest() throws Exception {

		Enrollment x = null;
		do {
			x = enrollmentRepository.findByEmailAndCourseId(TEST_USER_EMAIL, TEST_COURSE_ID);
			if (x != null)
				enrollmentRepository.delete(x);
		} while (x != null);

		// set the driver location and start driver
		//@formatter:off
		// browser	property name 				Java Driver Class
		// edge 	webdriver.edge.driver 		EdgeDriver
		// FireFox 	webdriver.firefox.driver 	FirefoxDriver
		// IE 		webdriver.ie.driver 		InternetExplorerDriver
		//@formatter:on

		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
		WebDriver driver = new ChromeDriver();
		// Puts an Implicit wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		try {

			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);

			// select the last of the radio buttons 
			WebElement we = driver.findElement(By.xpath("(//input[@type='radio'])[last()]"));
			we.click();

			// Locate and click "Get Schedule" button
			driver.findElement(By.xpath("//a")).click();
			Thread.sleep(SLEEP_DURATION);

			// Locate and click "Add Course" button
			driver.findElement(By.xpath("//button")).click();
			Thread.sleep(SLEEP_DURATION);

			// enter course no 40442 and click "Add"
			driver.findElement(By.xpath("//input[@name='course_id']")).sendKeys(Integer.toString(TEST_COURSE_ID));
			driver.findElement(By.xpath("//button[span='Add']")).click();
			Thread.sleep(SLEEP_DURATION);

			// verify that new course shows in schedule.
			Course course = courseRepository.findById(TEST_COURSE_ID).get();
			we = driver.findElement(By.xpath("//div[@data-field='title' and @data-value='" + course.getTitle() + "']"));
			assertNotNull(we, "Added course does not show in schedule.");

			// verify that enrollment row has been inserted to database.
			Enrollment e = enrollmentRepository.findByEmailAndCourseId(TEST_USER_EMAIL, TEST_COURSE_ID);
			assertNotNull(e, "Course enrollment not found in database.");

		} catch (Exception ex) {
			throw ex;
		} finally {

			// clean up database.
			Enrollment e = enrollmentRepository.findByEmailAndCourseId(TEST_USER_EMAIL, TEST_COURSE_ID);
			if (e != null)
				enrollmentRepository.delete(e);

			driver.quit();
		}

	}
}
