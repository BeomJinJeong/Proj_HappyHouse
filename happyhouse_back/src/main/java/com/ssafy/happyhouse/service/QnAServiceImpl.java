package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.PageDto;
import com.ssafy.happyhouse.model.PageResultDto;
import com.ssafy.happyhouse.model.QnA;
import com.ssafy.happyhouse.model.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {

	private static final Logger logger = LoggerFactory.getLogger(QnAServiceImpl.class);

	@Autowired
	private SqlSession sqlSession;

	// ok
	@Override
	@Transactional
	public Map<String, Object> qnaList(Map<String, Object> params) {
		System.out.println("서비스까지 들어ㅏ옴");
		PageDto pageDto = new PageDto();
		pageDto.setPageNo((int)params.get("pageNo"));
		pageDto.setListSize((int)params.get("listSize"));
		params.put("begin", Integer.toString(pageDto.getBegin()));
		
		QnAMapper qnaMapper = sqlSession.getMapper(QnAMapper.class);
		
		List<QnA> list = qnaMapper.qnaList(params);
		int count = qnaMapper.qnaCnt(params);
		PageResultDto prd = new PageResultDto(pageDto.getPageNo(), count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("pageResult", prd);
		System.out.println(result);
		return result;
	}

	// ok
	@Override
	@Transactional
	public Map<String, Object> qnaInfo(int qnaNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("qnaInfo", sqlSession.getMapper(QnAMapper.class).qnaInfo(qnaNo));
		result.put("qnaAnswerList", sqlSession.getMapper(QnAMapper.class).qnaAnswerList(qnaNo));
		System.out.println(result);
		return result;
	}

	// ok
	@Override
	public int qnaRegister(QnA qna) {
		return sqlSession.getMapper(QnAMapper.class).qnaRegister(qna);
	}

	//ok
	@Override
	public int qnaModify(QnA qna) {
		return sqlSession.getMapper(QnAMapper.class).qnaModify(qna);
	}

	@Override
	public int qnaDelete(int qnaNo) {
		return sqlSession.getMapper(QnAMapper.class).qnaDelete(qnaNo);
	}
}
