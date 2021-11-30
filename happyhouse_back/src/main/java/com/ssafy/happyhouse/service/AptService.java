package com.ssafy.happyhouse.service;

import java.util.List;
import java.util.Map;

import org.springframework.util.MultiValueMap;

import com.ssafy.happyhouse.model.AptDeal;
import com.ssafy.happyhouse.model.AptInfo;
import com.ssafy.happyhouse.model.InterAreaParams;
import com.ssafy.happyhouse.model.PageDto;

public interface AptService {
	public Map<String, Object> aptSelect(Map<String, Object> params) throws Exception;
	public AptDeal selectApt(int no);
	public int insertIArea(InterAreaParams interAreaParams);
	public List<InterAreaParams> selectIArea(String id);
	public int deleteIArea(Map<String, String> map);
	public List<AptInfo> aptInfoList();
	public List<AptDeal> aptInfoSearch(Map<String, String> params);
	public List<String> dong();
	public List<AptDeal> aptDealList(Map<String, String> params);
	public void like(int no);
	public List<AptInfo> aptInfoSearchCoor(Map<String, Double> params);
	public Map<String, Object> selectHot();
	public AptInfo aptInfo(String dong, String aptName);
	public AptInfo aptInfoNo(int no);
	public String sigu(int code);
}
