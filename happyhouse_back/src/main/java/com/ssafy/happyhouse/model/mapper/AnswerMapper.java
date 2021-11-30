package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.Answer;

public interface AnswerMapper {
	int answerRegister(Answer answer);
	int answerDelete(int answerNo);
	List<Answer> listAll(int quesNo);
	int quesNo(int answerNo);
}
