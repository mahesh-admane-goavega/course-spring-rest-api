package com.springrest.springrest.services;

import java.util.ArrayList;
import java.util.List;

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
	public Course updateCourse(long id, Course course) {
		for(int i = 0 ; i < list.size(); i++ ) {
			Course myCourse = list.get(i);
			if(myCourse.getId() == id) {
				list.set(i, course);
				return course;
			}
		}
		return null;
	}

	@Override
	public String deleteCourse(long id) {
		for(int i = 0; i < list.size(); i++) {
			Course myCourse = list.get(i);
			if(myCourse.getId()== id) {
				list.remove(i);
				return "Course Removed!";
			}
		}
		return "Not removed facing some issue!";
	}
}