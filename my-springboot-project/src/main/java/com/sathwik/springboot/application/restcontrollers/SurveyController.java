package com.sathwik.springboot.application.restcontrollers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sathwik.springboot.model.Question;
import com.sathwik.springboot.service.SurveyService;

@RestController
public class SurveyController {

	@Autowired
	SurveyService surveyService;

	@GetMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveQuestionForSurvey(@PathVariable String surveyId) {
		return surveyService.retrieveQuestions(surveyId);
	}

//	@PostMapping("/surveys/{surveyId}/questions")
//	public ResponseEntity<Void> addAQuestionToSurvey1(@PathVariable String surveyId,
//			@RequestBody Question newQuestion) {
//
//		Question question = surveyService.addQuestion(surveyId, newQuestion);
//
//		if (question == null)
//			return ResponseEntity.noContent().build();
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(question.getId())
//				.toUri();
//		return ResponseEntity.created(location).build();
//	}

	@PostMapping("surveys/{surveyId}/questions")
	public ResponseEntity<?> addQuestionToASurvey(@PathVariable String surveyId, @RequestBody Question newQuestion) {

		Question question = surveyService.addQuestion(surveyId, newQuestion);

		if (question == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{someotherId}").buildAndExpand(question.getId()).toUri();
		
		return ResponseEntity.created(location).build();

	}

	@GetMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveDetailsForAQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		return surveyService.retrieveQuestion(surveyId, questionId);
	}
}
