package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.Answer;
import com.ssafy.happyhouse.model.QnA;

public interface QnAMapper {
	public QnA qnaInfo(int qnaNo);
	public int qnaRegister(QnA qna);
	public int qnaModify(QnA qna);
	public int qnaDelete(int qnaNo);
	public List<QnA> qnaList(Map<String, Object> params);
	public int qnaCnt(Map<String, Object> params);
	public List<Answer> qnaAnswerList(int qnaNo);
}
