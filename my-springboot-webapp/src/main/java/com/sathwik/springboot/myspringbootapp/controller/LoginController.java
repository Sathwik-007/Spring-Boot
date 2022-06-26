package com.sathwik.springboot.myspringbootapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sathwik.springboot.web.model.Todo;
import com.sathwik.springboot.web.service.LoginService;
import com.sathwik.springboot.web.service.TodoService;

@Controller
@ComponentScan("com.sathwik.springboot.web")
@SessionAttributes("name")
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	TodoService todoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String LoginPage(ModelMap model) {

		model.put("name", "Sathwik");
		return "welcome";
	}
	
	public String returnCurrentLoggedInUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails new_name = (UserDetails) principal;
			return new_name.getUsername();
		}
		return principal.toString();
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomePage() {
		return "welcome";
	}


	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {
		List<Todo> retrieveTodos = todoService.retrieveTodos((String) model.get("name"));
		model.put("todos", retrieveTodos);
		return "list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String addTodo(ModelMap model) {
		/*
		 * Creating a new default todo object for which new description is given and
		 * rest of the defaults are assigned, given a name "todo" and made available in
		 * the model.
		 */
		model.addAttribute("todo", new Todo(0, (String) model.get("name"), "", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String redirectToListTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
		/*
		 * When submit is clicked, the todo object i.e available in the model contains
		 * username, description and hence can be added to the actual todo-list.
		 * 
		 */
		if (result.hasErrors())
			return "todo";
		todoService.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
		Todo oldTodo = todoService.retrieveTodo(id);
		model.put("oldTodo", oldTodo);
		return "update-todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String afterUpdateShowListTodos(ModelMap model, @Valid Todo oldTodo, BindingResult result) {
		if(result.hasErrors())
			return "update-todo";
		oldTodo.setUser((String)model.get("name"));
		todoService.updateTodo(oldTodo);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:list-todos";
	}
}
