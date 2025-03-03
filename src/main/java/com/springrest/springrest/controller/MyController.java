package com.springrest.springrest.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.entities.Course;
import com.springrest.springrest.services.CourseService;

@RestController
public class MyController {

	@Autowired
	private CourseService courseService;
	
	// get all the courses
	@GetMapping("/courses")
	public List<Course> getCourses()
	{
		return this.courseService.getCourses();
	}
	
	// get single course by course id
	@GetMapping("/courses/{courseId}")
	public Course getCourses(@PathVariable String courseId) 
	{
		return this.courseService.GetCourseById(Long.parseLong(courseId));
	}
	
	// create new course
	@PostMapping(path = "/courses", consumes = "application/json")
	public Course addCourse(@RequestBody Course course) {
		return this.courseService.addNewCourse(course);
	}
	
	
	// update course
	@PutMapping(path = "/update-course/{courseId}")
	public Course updateCourse(@PathVariable String courseId, @RequestBody Course course) {
		return this.courseService.updateCourse(Long.parseLong(courseId), course);
	}
	
	
	// delete course
	@DeleteMapping(path = "/delete-course/{id}")
	public String deleteCourse(@PathVariable String id) {
		return this.courseService.deleteCourse(Long.parseLong(id));
	}
}
