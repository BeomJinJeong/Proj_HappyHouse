package com.ssafy.happyhouse.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.AptDeal;
import com.ssafy.happyhouse.model.AptDealParams;
import com.ssafy.happyhouse.model.AptInfo;
import com.ssafy.happyhouse.model.InterAreaParams;
@Component
public interface AptMapper {

	public ArrayList<AptDeal> selectAllApt(Map<String, Object> params);
	public int selectAptCount(Map<String, Object> params);
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
	public List<Integer> selectHot();
	public AptInfo aptInfo(String dong, String aptName);
	public AptInfo aptInfoNo(int no);
	public String sigu(int code);
}
