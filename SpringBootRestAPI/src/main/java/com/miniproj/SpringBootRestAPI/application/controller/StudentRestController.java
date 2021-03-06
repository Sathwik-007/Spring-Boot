package com.miniproj.SpringBootRestAPI.application.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miniproj.SpringBootRestAPI.model.Course;
import com.miniproj.SpringBootRestAPI.service.StudentService;

@RestController
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	/**
	 * url : http://localhost:8080/students/{Student1}/courses/
	 * 
	 * @param studentId
	 * @return
	 */

	@GetMapping("/students/{studentId}/courses")
	public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
		return studentService.retrieveCourses(studentId);
	}

	/**
	 * url : http://localhost:8080/students/{Student1}/courses/{Course1}
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	@GetMapping("/students/{studentId}/courses/{courseId}")
	public Course retrieveDetailsForCourse(@PathVariable String studentId, @PathVariable String courseId) {
		return studentService.retrieveCourse(studentId, courseId);
	}

	/**
	 * url : http://localhost:8080/students/{Student1}/courses/Course1
	 * 
	 * @param studentId
	 * @param newCourse
	 * @return
	 */

	@PostMapping("/students/{studentId}/courses")
	public ResponseEntity<Void> registerStudentForCourse(@PathVariable String studentId,
			@RequestBody Course newCourse) {

		Course course = studentService.addCourse(studentId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/students/{studentId}/courses/{courseId}")
	public ResponseEntity<Void> updateStudentCourse(@PathVariable String courseId, @PathVariable String studentId,
			@RequestBody Course newCourse) {

		Course course = studentService.updateCourse(studentId, courseId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{courseId}").buildAndExpand(course.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	

	@DeleteMapping("students/{studentId}/courses/{courseId}")
	public ResponseEntity<Void> deleteCourseId(@PathVariable String courseId, @PathVariable String studentId) {
		if(!studentService.deleteCourse(studentId, courseId))
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return ResponseEntity.noContent().build();
	}

}
