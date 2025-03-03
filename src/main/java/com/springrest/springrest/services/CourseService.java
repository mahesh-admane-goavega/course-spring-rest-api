package com.springrest.springrest.services;

import java.util.List;

import com.springrest.springrest.entities.Course;

public interface CourseService {
	
	public List<Course> getCourses();
	
	public Course GetCourseById(long id);
	
	public Course addNewCourse(Course course);
	
	public Course updateCourse(Course course);	
	
	public void deleteCourse(long id);
}
