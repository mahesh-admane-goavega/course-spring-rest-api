package com.springrest.springrest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

import jakarta.persistence.Access;

@RestController
@CrossOrigin(origins =  "*")
public class MyController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/message")
	public String getMessage() {
		System.out.println("Method Called");
		return "Hello Mahesh";
	}
	
	
	// get all the courses
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getCourses()
	{
		try {
			 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			 System.out.println("AUTH FROM CONTROLLER" + authentication);
			 System.out.println("CALLED");
			List<Course> courses = this.courseService.getCourses();
			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get single course by course id
	@CrossOrigin(origins = "http://localhost:3000") 
	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Course> getCourses(@PathVariable String courseId) 
	{
		try {
			Course course = this.courseService.GetCourseById(Long.parseLong(courseId));
			if(course != null) {				
				return new ResponseEntity<>(course, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// create new course
	@PostMapping(path = "/courses", consumes = "application/json")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		try {
			Course newCourse = this.courseService.addNewCourse(course);
			return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// update course
	@PutMapping(path = "/courses")
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		try {
			Course updatedCourse = this.courseService.updateCourse(course);
			if(updatedCourse != null) {
				return new ResponseEntity<>(updatedCourse, HttpStatus.OK);				
			}
			else
			{
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// delete course
	@DeleteMapping(path = "/courses/{id}")
	public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String id) {
		try {
			this.courseService.deleteCourse(Long.parseLong(id));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
