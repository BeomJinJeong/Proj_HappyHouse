package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.PageDto;
import com.ssafy.happyhouse.model.PageResultDto;
import com.ssafy.happyhouse.model.PageResultDtoTest;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int boardRegister(Board board) {
		sqlSession.getMapper(BoardMapper.class).insert(board);
		return sqlSession.getMapper(BoardMapper.class).lastIdx();
	}

	@Override
	@Transactional
	public Board boardInfo(int boardIdx) {
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.increaseCount(boardIdx);
		return boardMapper.boardInfo(boardIdx);
	}

	@Override
	public void boardModify(Board board) {
		sqlSession.getMapper(BoardMapper.class).boardModify(board);
	}

	@Override
	public void boardDelete(int boardIdx) {
		sqlSession.getMapper(BoardMapper.class).boardDelete(boardIdx);
	}

	@Override
	public Map<String, Object> boardList(Map<String, Object> params) {
		
		PageDto pageDto = new PageDto();
		pageDto.setPageNo((int)params.get("pageNo"));
		pageDto.setListSize((int)params.get("listSize"));
		params.put("begin", Integer.toString(pageDto.getBegin()));
		
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		
		List<Board> list = boardMapper.select(params);
		int count = boardMapper.selectCnt(params);
		
		PageResultDto prd = new PageResultDto(pageDto.getPageNo(), count);
		PageResultDtoTest prdt = new PageResultDtoTest(pageDto.getPageNo(), count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("boardList", list);
		result.put("pageResult", prdt);
		
		return result;
	}

}
