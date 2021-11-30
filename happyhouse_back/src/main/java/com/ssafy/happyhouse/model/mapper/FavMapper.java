package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface FavMapper {

	public void like(int aptno);
	public void CancleLikeNum(int aptno);
	public int searchLike(Favorite fav);
    public void doLike(Favorite fav);
    public void cancleLike(Favorite fav);
	public List<Integer> selectLike(String userid);
	public int selectAptLike(Favorite fav);
	
}
