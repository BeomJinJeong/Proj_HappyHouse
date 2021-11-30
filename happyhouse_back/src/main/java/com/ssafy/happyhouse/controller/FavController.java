package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.service.FavService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/fav")
public class FavController {

	@Autowired
	private FavService favService;

	public static final Logger logger = LoggerFactory.getLogger(FavController.class);

	/** 관심아파트 등록, 삭제처리 */
	@GetMapping("/like/{userid}/{aptno}")
	@ResponseBody
	public void like(@PathVariable("userid") String userid, @PathVariable("aptno") int aptno) {
		System.out.println(userid + " likes " + aptno + "!");
		Favorite fav = new Favorite();

		fav.setUserid(userid);
		fav.setAptno(aptno);

		if (favService.searchLike(fav) == 0) {
			System.out.println("doLike");
			favService.doLike(fav);
			favService.like(aptno);
			System.out.println(aptno + ": like!");
		} else {
			System.out.println("cancleLike");
			favService.cancleLike(fav);
			favService.CancleLikeNum(aptno);
		}

	}

	/** 관심아파트 조회 */
	@GetMapping("/selectLike/{userid}")
	public ResponseEntity<Map<String, Object>> selectLike(@PathVariable("userid") String userid) {
		System.out.println("관심아파트 조회 : " + userid);
		Map<String, Object> result = favService.selectLike(userid);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	/** 관심아파트 조회(가격순 정렬) */
	@GetMapping("/selectLike/byDealAmount/{userid}")
	public ResponseEntity<Map<String, Object>> selectLikeByDealAmount(@PathVariable("userid") String userid) {
		System.out.println("관심아파트 조회 : " + userid);
		Map<String, Object> result = favService.selectLikeByDealAmount(userid);
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	/** 특정 유저의 관심목록에 aptno가 존재하는지 확인 */
	@GetMapping("/selectLike/{userid}/{aptno}")
	public ResponseEntity<Map<String, Object>> selectAptLike(@PathVariable("userid") String userid,
															 @PathVariable("aptno") int aptno) {
		System.out.println("관심아파트 조회 : " + userid + ':' + aptno);
		
		Favorite fav = new Favorite();
		fav.setAptno(aptno);
		fav.setUserid(userid);
		
		Map<String, Object> result = new HashMap<>();
		System.out.println("isExist? : " + favService.selectAptLike(fav));
		result.put("isExist", favService.selectAptLike(fav));

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	/** 관심목록 평균 구하기 */
	@GetMapping("/getLikeAvg/{userid}")
	public ResponseEntity<Map<String, Object>> getLikeAvg(@PathVariable("userid") String userid) {
		System.out.println("관심아파트 조회 : " + userid);
		Map<String, Object> result = favService.getAvg(userid);
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
}
