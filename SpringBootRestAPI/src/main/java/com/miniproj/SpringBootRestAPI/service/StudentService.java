
package com.miniproj.SpringBootRestAPI.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.miniproj.SpringBootRestAPI.model.Course;
import com.miniproj.SpringBootRestAPI.model.Student;

@Component
public class StudentService {
	

	private static List<Student> students = new ArrayList<>();

	static {
		// Initialize Data
		Course course1 = new Course("Course1", "Machine Learning", "10 Steps",
				Arrays.asList("Collecting Data", "Preparing the Data", "Choosing the model", "Training the model"));
		Course course2 = new Course("Course2", "Deep Learning", "15 Steps",
				Arrays.asList("Intro","Getting technical", "Backpropagation", "Gradient Descent"));
		Course course3 = new Course("Course3", "Big Data", "20 Steps",
				Arrays.asList("Understand Hadoop", "YARN", "MapReduce", "Floom and Sqoop"));
		Course course4 = new Course("Course4", "Blockchain", "Most popular course",
				Arrays.asList("Intro", "Types of blockchain", "Solidity", "Project"));
		
		Student Ironman = new Student("Student1", "Robert D Junior", "Ironman, Avenger",
				new ArrayList<>(Arrays.asList(course1, course2, course3, course4)));

		Student CapAmerica = new Student("Student2", "Chris Evans", "Captain America, Avenger",
				new ArrayList<>(Arrays.asList(course1, course2, course3, course4)));

		students.add(Ironman);
		students.add(CapAmerica);
	}

	public List<Student> retrieveAllStudents() {
		return students;
	}

	public Student retrieveStudent(String studentId) {
		for (Student student : students) {
			if (student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}
	
	public List<Course> retrieveCourses(String studentId) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		return student.getCourses();
	}

	public Course retrieveCourse(String studentId, String courseId) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		for (Course course : student.getCourses()) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}

		return null;
	}

//	private SecureRandom random = new SecureRandom();

	public Course addCourse(String studentId, Course course) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

//		String randomId = new BigInteger(130, random).toString(32);
//		course.setId(randomId);
		course.setId(course.getId());
		
		student.getCourses().add(course);

		return course;
	}

	public Course updateCourse(String studentId, String courseId, Course newCourse) {
		Student student = retrieveStudent(studentId);
		if(student == null)
			return null;
		List<Course> c = student.getCourses();
		c.remove(retrieveCourse(studentId, courseId));
		c.add(newCourse);
		return newCourse;
	}

	public boolean deleteCourse(String studentId, String courseId) {
		Student student = retrieveStudent(studentId);
		if(student == null)
			return false;
		
		List<Course> courses = retrieveCourses(studentId);
		courses.remove(retrieveCourse(studentId, courseId));
		return true;
		
	}
	
	

	public void modifiedUpdateCourseMethod(String studentId, String courseId, Course oldCourseDetails, Course updatedCourseDetails) {
		List<Course> retrieveCourses = retrieveCourses(studentId);
		retrieveCourses.remove(oldCourseDetails);
		retrieveCourses.add(updatedCourseDetails);
	}
	
}