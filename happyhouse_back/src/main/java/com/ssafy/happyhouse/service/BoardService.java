package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.Board;

public interface BoardService {

	Map<String, Object> boardList(Map<String, Object> params);

	int boardRegister(Board board);

	Board boardInfo(int boardIdx);

	void boardModify(Board board);

	void boardDelete(int boardIdx);
}
