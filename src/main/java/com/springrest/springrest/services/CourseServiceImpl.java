package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springrest.springrest.entities.Course;

@Service
public class CourseServiceImpl implements CourseService 
{
	List<Course> list;

	public CourseServiceImpl() {
		list = new ArrayList<>();
		list.add(new Course(1, "JavaScript And Drizzle", "This is course is teach you DTO in javaScript using Drizzle"));
		list.add(new Course(2, "Complete ReactJs Course", "Here you will learn about React A to Z concepts"));
		list.add(new Course(3, "Core Java Complete", "Using this course learn complete Java programming language in deep."));
	}

	@Override
	public List<Course> getCourses() {

		return list;
	}

	@Override
	public Course GetCourseById(long id) {

		Course c = null;
		for(Course course:list) {
			if(course.getId() == id) {
				c = course;
				break;
			}
		}
		return c;
	}

	@Override
	public Course addNewCourse(Course course) {
		if(course != null) {
			list.add(course);
		}
		return course;
	}

	@Override
	public Course updateCourse(Course course) {
		
		list.forEach(e -> {
			if(e.getId() == course.getId()) {
				e.setTitle(course.getTitle());
				e.setDescription(course.getDescription());
			}
		});
		return course;
		
	}

	@Override
	public void deleteCourse(long id) {
		list = this.list.stream().filter(i -> i.getId() != id).collect(Collectors.toList());
	}
}