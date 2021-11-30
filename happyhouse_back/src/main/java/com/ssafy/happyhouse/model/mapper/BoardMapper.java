package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.Board;

public interface BoardMapper {

	List<Board> select(Map<String, Object> params);

	int selectCnt(Map<String, Object> params);

	void insert(Board board);

	Board boardInfo(int boardIdx);

	void increaseCount(int boardIdx);
	
	int lastIdx();
	
	void boardModify(Board board);

	void boardDelete(int boardIdx);
}
