package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.model.Answer;

public interface AnswerService {

	int answerRegister(Answer answer);
	int answerDelete(int answerNo);
	int quesNo(int answerNo);
	List<Answer> listAll(int quesNo);
}
