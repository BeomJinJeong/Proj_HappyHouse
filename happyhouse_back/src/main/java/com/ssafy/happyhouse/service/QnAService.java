package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.QnA;

public interface QnAService {

	public Map<String, Object> qnaList(Map<String, Object> params);

	public Map<String, Object> qnaInfo(int qnaNo);

	public int qnaRegister(QnA qna);

	public int qnaModify(QnA qna);

	public int qnaDelete(int qnaNo);
}
