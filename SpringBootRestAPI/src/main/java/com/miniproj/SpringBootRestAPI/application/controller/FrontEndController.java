package com.miniproj.SpringBootRestAPI.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miniproj.SpringBootRestAPI.model.Course;
import com.miniproj.SpringBootRestAPI.model.Student;
import com.miniproj.SpringBootRestAPI.service.StudentService;

@Controller
public class FrontEndController {

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(ModelMap model) {

		List<Student> retrieveAllStudents = studentService.retrieveAllStudents();
		model.put("allStudents", retrieveAllStudents);
		return "welcome";
	}

	@RequestMapping(value = "/list-details", method = RequestMethod.GET)
	public String listStudentDetails(@RequestParam String StudentId, ModelMap model,
			RedirectAttributes redirectAttributes) {
		List<Course> retrieveCourses = studentService.retrieveCourses(StudentId);
		model.put("StudentId", StudentId);
		model.put("courses", retrieveCourses);
		redirectAttributes.addAttribute("StudentId", StudentId);
		return "list-details";
	}

	@RequestMapping(value = "/add-details", method = RequestMethod.GET)
	public String getAddDetailsPage(ModelMap model) {
		model.addAttribute("newCourse", new Course("", "", "", new ArrayList<String>()));
		return "add-details";
	}

	@RequestMapping(value = "/add-details", method = RequestMethod.POST)
	public String postAddDetailsPage(@RequestParam String StudentId, @RequestParam String CourseId,
			@RequestBody Course newCourseDetails, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("StudentId", StudentId);
		studentService.addCourse(StudentId, newCourseDetails);
		return "add-details";
	}

	@RequestMapping(value = "/update-details", method = RequestMethod.GET)
	public String updateCourseDetails(ModelMap model, @RequestParam String StudentId, @RequestParam String CourseId,
			RedirectAttributes redirectAttributes) {

		Course oldCourseDetails = studentService.retrieveCourse(StudentId, CourseId);
//		redirectAttributes.addAttribute("StudentId", StudentId);
		model.put("oldCourseDetails", oldCourseDetails);
		return "update-details";
	}

	@RequestMapping(value = "/update-details", method = RequestMethod.POST)
	public String postUpdatedCourseDetails(@RequestParam String StudentId, @RequestParam String CourseId,
			@RequestBody Course updatedCourseDetails, @Valid Course oldCourseDetails, BindingResult result,
			ModelMap model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors())
			return "update-details";
		redirectAttributes.addAttribute("StudentId", StudentId);
		studentService.modifiedUpdateCourseMethod(StudentId, CourseId, oldCourseDetails, updatedCourseDetails);
		return "redirect:list-details";
	}

	@RequestMapping(value = "/delete-details", method = RequestMethod.GET)
	public String deleteCourseDetails(@RequestParam String StudentId, @RequestParam String CourseId,
			RedirectAttributes redirectAttributes) {
		studentService.deleteCourse(StudentId, CourseId);
		redirectAttributes.addAttribute("StudentId", StudentId);
		return "redirect:list-details";
	}

}
