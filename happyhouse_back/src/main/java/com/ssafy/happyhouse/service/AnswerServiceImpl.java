package com.ssafy.happyhouse.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Answer;
import com.ssafy.happyhouse.model.mapper.AnswerMapper;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int answerRegister(Answer answer) {
		return sqlSession.getMapper(AnswerMapper.class).answerRegister(answer);
	}

	@Override
	public int answerDelete(int answerNo) {
		return sqlSession.getMapper(AnswerMapper.class).answerDelete(answerNo);
	}

	@Override
	public int quesNo(int answerNo) {
		return sqlSession.getMapper(AnswerMapper.class).quesNo(answerNo);
	}

	@Override
	public List<Answer> listAll(int quesNo) {
		return sqlSession.getMapper(AnswerMapper.class).listAll(quesNo);
	}
}
