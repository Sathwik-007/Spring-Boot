package com.sathwik.springboot.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sathwik.springboot.model.Question;
import com.sathwik.springboot.model.Survey;

@Component
public class SurveyService {
	private static List<Survey> surveys = new ArrayList<>();
	static {
		Question question1 = new Question("Question1",
				"Largest Country in the World", "Russia", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question2 = new Question("Question2",
				"Most Populus Country in the World", "China", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question3 = new Question("Question3",
				"Highest GDP in the World", "United States", Arrays.asList(
						"India", "Russia", "United States", "China"));
		Question question4 = new Question("Question4",
				"Second largest english speaking country", "India", Arrays
						.asList("India", "Russia", "United States", "China"));

		/*
		 * Backup code with only one survey
		 * */
		
//		List<Question> questions = new ArrayList<>(Arrays.asList(question1,
//				question2, question3, question4));
//
//		Survey survey = new Survey("Survey1", "My Favorite Survey",
//				"Description of the Survey", questions);
		
//		surveys.add(survey);
		
		/*
		 * Adding another survey Survey2
		 * */
		
		Question suryey2Question1 = new Question("Question1", 
				"Desc for question 1", "correct answer for q1", Arrays.asList(
				"option1", "option2", "option3", "option4"));
		Question suryey2Question2 = new Question("Question2", 
				"Desc for question 2", "correct answer for q2", Arrays.asList(
				"option1", "option2", "option3", "option4"));
		Question suryey2Question3 = new Question("Question3", 
				"Desc for question 3", "correct answer for q3", Arrays.asList(
				"option1", "option2", "option3", "option4"));
		Question suryey2Question4 = new Question("Question4", 
				"Desc for question 4", "correct answer for q4", Arrays.asList(
				"option1", "option2", "option3", "option4"));
		
		List<Question> survey1Questions = new ArrayList<>(Arrays.asList(question1,
				question2, question3, question4));
		
		List<Question> survey2Questions = new ArrayList<>(Arrays.asList(suryey2Question1,
				suryey2Question2, suryey2Question3, suryey2Question4));
		

		Survey survey1 = new Survey("Survey1", "My Favorite Survey",
				"Description of the Survey", survey1Questions);
		
		Survey survey2 = new Survey("Survey2", "Sample Survey 2",
				"Description of the Sample Survey 2", survey2Questions);
		
		surveys.add(survey1);
		surveys.add(survey2);
	}

	public List<Survey> retrieveAllSurveys() {
		return surveys;
	}

	public Survey retrieveSurvey(String surveyId) {
		for (Survey survey : surveys) {
			if (survey.getId().equals(surveyId)) {
				return survey;
			}
		}
		return null;
	}

	public List<Question> retrieveQuestions(String surveyId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		return survey.getQuestions();
	}

	public Question retrieveQuestion(String surveyId, String questionId) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		for (Question question : survey.getQuestions()) {
			if (question.getId().equals(questionId)) {
				return question;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public Question addQuestion(String surveyId, Question question) {
		Survey survey = retrieveSurvey(surveyId);

		if (survey == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		question.setId(randomId);

		survey.getQuestions().add(question);

		return question;
	}
}