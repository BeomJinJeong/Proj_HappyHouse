package com.ssafy.happyhouse.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		return memberMapper.login(memberDto);
	}

	@Override
	public String getServerInfo() {
		return "사용자에게 전달하고 싶은 중요정보";
	}
	@Override
    public int searchLike(Favorite fav) {
        System.out.println("Service: searchLike");
        return sqlSession.getMapper(MemberMapper.class).searchLike(fav);
    }
    @Override
    public void doLike(Favorite fav) {
        sqlSession.getMapper(MemberMapper.class).doLike(fav);
    }
    @Override
    public void cancleLike(Favorite fav) {
        sqlSession.getMapper(MemberMapper.class).cancleLike(fav);
    }

	@Override
	public int regist(MemberDto memberDto) throws Exception {
		return memberMapper.regist(memberDto);
	}

	@Override
	public int updateProfile(MemberDto memberDto) throws Exception {
		return memberMapper.updateProfile(memberDto);
	}
}
