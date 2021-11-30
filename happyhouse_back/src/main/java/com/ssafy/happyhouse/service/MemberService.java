package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public String getServerInfo();
	public int searchLike(Favorite fav);
    public void doLike(Favorite fav);
    public void cancleLike(Favorite fav);
    public int regist(MemberDto memberDto) throws Exception;
    public int updateProfile(MemberDto memberDto) throws Exception;
}
