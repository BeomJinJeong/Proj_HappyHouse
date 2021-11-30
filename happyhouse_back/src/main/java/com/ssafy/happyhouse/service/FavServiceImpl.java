package com.ssafy.happyhouse.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AptDeal;
import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.model.mapper.AptMapper;
import com.ssafy.happyhouse.model.mapper.FavMapper;

@Service
public class FavServiceImpl implements FavService {

	@Autowired
	private FavMapper favMapper;
	
	@Autowired
	private AptMapper aptMapper;

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void CancleLikeNum(int aptno) {
		favMapper.CancleLikeNum(aptno);
	}

	@Override
	public void like(int aptno) {
		favMapper.like(aptno);
	}

	@Override
	public int searchLike(Favorite fav) {
		System.out.println("Service: searchLike");
		return sqlSession.getMapper(FavMapper.class).searchLike(fav);
	}

	@Override
	public void doLike(Favorite fav) {
		sqlSession.getMapper(FavMapper.class).doLike(fav);
	}

	@Override
	public void cancleLike(Favorite fav) {
		sqlSession.getMapper(FavMapper.class).cancleLike(fav);
	}

	@Override
	public Map<String, Object> selectLike(String userid) {
		List<Integer> list = sqlSession.getMapper(FavMapper.class).selectLike(userid);
		List<AptDeal> aptlist = new ArrayList<>();
		for (int aptno : list) {
			aptlist.add(aptMapper.selectApt(aptno));
		}
		System.out.println("관심아파트 수 : " + aptlist.size());
		Map<String, Object> result = new HashMap<>();
		result.put("list", aptlist);

		return result;
	}
	@Override
	public Map<String, Object> selectLikeByDealAmount(String userid) {
		List<Integer> list = sqlSession.getMapper(FavMapper.class).selectLike(userid);
		List<AptDeal> aptlist = new ArrayList<>();
		for (int aptno : list) {
			aptlist.add(aptMapper.selectApt(aptno));
		}
		System.out.println("관심아파트 수 : " + aptlist.size());
		
		Collections.sort(aptlist, new Comparator<AptDeal>() {
			@Override
			public int compare(AptDeal apt1, AptDeal apt2) {
				System.out.println(apt1.getDealAmount());
				StringBuilder sb = new StringBuilder();
				String[] pricearr1 = apt1.getDealAmount().trim().split(",");
				String[] pricearr2 = apt2.getDealAmount().trim().split(",");
				int price1, price2;
				for(String price:pricearr1) {
					sb.append(price);
				}
				price1 = Integer.parseInt(sb.toString());
				sb.setLength(0);
				for(String price:pricearr2) {
					sb.append(price);
				}
				price2 = Integer.parseInt(sb.toString());
				
				return price1 - price2;
			}
		});
		
		Map<String, Object> result = new HashMap<>();
		result.put("list", aptlist);
		
		return result;
		
	}
	@Override
	public boolean selectAptLike(Favorite fav) {
		if(sqlSession.getMapper(FavMapper.class).selectAptLike(fav)==0) return false;
		else return true;
	}
	
	@Override
	public Map<String, Object> getAvg(String userid) {
		List<Integer> list = sqlSession.getMapper(FavMapper.class).selectLike(userid);
		List<AptDeal> aptlist = new ArrayList<>();
		for (int aptno : list) {
			aptlist.add(aptMapper.selectApt(aptno));
		}
		System.out.println("관심아파트 수 : " + aptlist.size());
		int avgDealAmount = 0; // 평균가격
		double avgArea = 0;	// 평균 면적
		double avgDPA = 0; // 1평당가격 평균
		StringBuilder sb = new StringBuilder();
		for(AptDeal apt : aptlist) {
			sb.setLength(0);
			String[] temps = apt.getDealAmount().trim().split(",");
			for(String temp : temps) {
				sb.append(temp);
			}
			int ada = Integer.parseInt(sb.toString());
			double aa = Double.parseDouble(apt.getArea());
			avgDealAmount += ada;
			avgArea += aa;
			avgDPA += ada/aa;
		}
		int listSize = aptlist.size();
		avgDealAmount = avgDealAmount / listSize;
		avgArea = Math.round((avgArea / listSize)*100)/(100.0);
		avgDPA = Math.round(avgDPA / listSize);
		System.out.println(userid + "의 관심매물 평균 가격은" + avgDealAmount + "만원 입니다.");
		System.out.println(userid + "의 관심매물 평균 평수는" + avgArea + "평 입니다.");
		System.out.println(userid + "의 관심매물 평균 1평당 가격은" + avgDPA + "만원 입니다.");
		Map<String, Object> result = new HashMap<>();
		result.put("avgDealAmount", avgDealAmount);
		result.put("avgArea", avgArea);
		result.put("avgDPA", avgDPA);

		return result;
	}
	
}
