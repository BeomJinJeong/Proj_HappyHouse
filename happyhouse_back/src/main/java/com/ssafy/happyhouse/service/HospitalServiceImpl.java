package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.Hospital;
import com.ssafy.happyhouse.model.PageDto;
import com.ssafy.happyhouse.model.PageResultDto;
import com.ssafy.happyhouse.model.mapper.HospitalMapper;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Map<String, Object> hospitalList(Map<String, Object> params) {
		PageDto pageDto = new PageDto();
		pageDto.setPageNo(Integer.parseInt((String)params.get("pageNo")));
		pageDto.setListSize(Integer.parseInt((String)params.get("listSize")));
		params.put("begin", Integer.toString(pageDto.getBegin()));
		
		HospitalMapper hospitalMapper = sqlSession.getMapper(HospitalMapper.class);
		
		List<Board> list = hospitalMapper.select(params);
		int count = hospitalMapper.selectCnt(params);
		
		PageResultDto prd = new PageResultDto(pageDto.getPageNo(), count);
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		result.put("pageResult", prd);
		
		return result;
	}

	@Override
	public void hospitalRegister(Hospital hospital) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hospital hospitalInfo(String hospitalidx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hospitalModify(Hospital hospital) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hospitalDelete(String hospitalidx) {
		// TODO Auto-generated method stub
		
	}

}
