package com.ssafy.happyhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AptDeal;
import com.ssafy.happyhouse.model.AptDealParams;
import com.ssafy.happyhouse.model.AptInfo;
import com.ssafy.happyhouse.model.InterAreaParams;
import com.ssafy.happyhouse.model.PageDto;
import com.ssafy.happyhouse.model.PageResultDto;
import com.ssafy.happyhouse.model.mapper.AptMapper;
import com.ssafy.happyhouse.model.mapper.FavMapper;

@Service
public class AptServiceImpl implements AptService{
	@Autowired
	private AptMapper aptMapper;

	@Override
	public Map<String, Object> aptSelect(Map<String, Object> params) throws Exception {
		System.out.println(params.get("key"));
		System.out.println(params.get("word"));
		System.out.println(params.get("pageNo"));
		PageDto pageDto = new PageDto();
		pageDto.setPageNo((int)params.get("pageNo"));
		pageDto.setListSize((int)params.get("listSize"));
		params.put("begin",  Integer.toString(pageDto.getBegin()));
				
		List<AptDeal> list = aptMapper.selectAllApt(params);
		int count = aptMapper.selectAptCount(params);
		
		PageResultDto prd = new PageResultDto(pageDto.getPageNo(), count);
		
		Map<String, Object> res = new HashMap<>();
		res.put("aptlist", list);
		res.put("pageResult", prd);
		return res;
	}
	
	@Override
	public void like(int no) {
		aptMapper.like(no);
	}

	@Override
	public AptDeal selectApt(int no) {
		return aptMapper.selectApt(no);
	}

	@Override
	public int insertIArea(InterAreaParams interAreaParams) {
		return aptMapper.insertIArea(interAreaParams);
	}

	@Override
	public List<InterAreaParams> selectIArea(String id) {
		return aptMapper.selectIArea(id);
	}

	@Override
	public int deleteIArea(Map<String, String> map) {
		return aptMapper.deleteIArea(map);
	}

	@Override
	public List<AptInfo> aptInfoList() {
		return aptMapper.aptInfoList();
	}
	
	@Override
	public List<AptDeal> aptInfoSearch(Map<String, String> params) {
		return aptMapper.aptInfoSearch(params);
	}

	@Override
	public List<String> dong() {
		return aptMapper.dong();
	}

	@Override
	public List<AptDeal> aptDealList(Map<String, String> params) {
		return aptMapper.aptDealList(params);
	}

	@Override
	public List<AptInfo> aptInfoSearchCoor(Map<String, Double> params) {
		return aptMapper.aptInfoSearchCoor(params);
	}
	
	@Override
	public Map<String, Object> selectHot(){
		List<Integer> list = aptMapper.selectHot();
		Map<String, Object> result = new HashMap<>();
		result.put("list", list);
		
		return result;
	}

	@Override
	public AptInfo aptInfo(String dong, String aptName) {
		return aptMapper.aptInfo(dong, aptName);
	}

	@Override
	public AptInfo aptInfoNo(int no) {
		return aptMapper.aptInfoNo(no);
	}

	@Override
	public String sigu(int code) {
		return aptMapper.sigu(code);
	}
}
