package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto login(MemberDto memberDto) throws SQLException;
	public int searchLike(Favorite fav);
    public void doLike(Favorite fav);
    public void cancleLike(Favorite fav);
    public int regist(MemberDto memberDto) throws SQLException;
    public int updateProfile(MemberDto memberDto) throws SQLException;
	
}
