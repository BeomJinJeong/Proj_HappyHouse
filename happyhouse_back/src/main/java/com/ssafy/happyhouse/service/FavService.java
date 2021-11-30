package com.ssafy.happyhouse.service;

import java.util.Map;

import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.model.MemberDto;

public interface FavService {
	public void like(int aptno);
	public void CancleLikeNum(int aptno);
	public int searchLike(Favorite fav);
    public void doLike(Favorite fav);
    public void cancleLike(Favorite fav);
    public Map<String, Object> selectLike(String userid);
    public Map<String, Object> selectLikeByDealAmount(String userid);
    public boolean selectAptLike(Favorite fav);
    public Map<String, Object> getAvg(String userid);
}
