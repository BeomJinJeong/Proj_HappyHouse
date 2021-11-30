package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.Board;

public interface HospitalMapper {
	
	List<Board> select(Map<String, Object> params);

	int selectCnt(Map<String, Object> params);
}
