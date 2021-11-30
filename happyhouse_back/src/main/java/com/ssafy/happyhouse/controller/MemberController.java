package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Favorite;
import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.service.JwtService;
import com.ssafy.happyhouse.service.MemberService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class MemberController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MemberService memberService;
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			MemberDto loginUser = memberService.login(memberDto);
			System.out.println(memberDto.getUserpwd());
			if(loginUser != null) {
//				jwt.io에서 확인
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);
				
//				토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//				response.setHeader("auth-token", token);
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginUser.getUserid());
				resultMap.put("user-name", loginUser.getUsername());
				resultMap.put("user-pwd", loginUser.getUserpwd());
				resultMap.put("user-jibunAdd", loginUser.getJibunAdd());
				resultMap.put("user-roadAdd", loginUser.getRoadAdd());
				resultMap.put("user-email", loginUser.getEmail());
				resultMap.put("user-phone", loginUser.getUserphone());
				resultMap.put("user-grade", loginUser.getGrade());
				resultMap.put("user-restAdd", loginUser.getRestAdd());
				resultMap.put("user-aboutMe", loginUser.getAboutMe());
//				resultMap.put("status", true);
//				resultMap.put("data", loginUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@PostMapping("/mvEdit")
	public ResponseEntity<String> mvEdit(HttpServletResponse response, HttpSession session){
		return new ResponseEntity<String>("true", HttpStatus.OK);
	}

//	@PostMapping("/info")
//	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, HttpSession session) {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		try {
//			MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
//			if(memberDto != null) {
//				// 보너스로 토큰에 담긴 정보도 전달해보자. 서버에서 토큰을 사용하는 방법임을 알 수 있다.
//				resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//	
//				resultMap.put("status", true);
//				resultMap.put("info", info);
//				resultMap.put("request_body", memberDto);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (RuntimeException e) {
//			logger.error("정보조회 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
	@PostMapping("/regist")
	public ResponseEntity<Map<String, Object>> regist(@RequestBody MemberDto memberDto, HttpServletResponse response, HttpSession session){
		System.out.println(memberDto.getUserid());
		System.out.println(memberDto.getEmail());
		int rs = 0;
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			rs = memberService.regist(memberDto);
			String token = jwtService.create(memberDto);
			logger.trace("로그인 토큰정보 : {}", token);
			resultMap.put("auth-token", token);
			resultMap.put("user-id", memberDto.getUserid());
			resultMap.put("user-name", memberDto.getUsername());
			resultMap.put("user-pwd", memberDto.getUserpwd());
			resultMap.put("user-jibunAdd", memberDto.getJibunAdd());
			resultMap.put("user-roadAdd", memberDto.getRoadAdd());
			resultMap.put("user-restAdd", memberDto.getRestAdd());
			resultMap.put("user-email", memberDto.getEmail());
			resultMap.put("user-phone", memberDto.getUserphone());
			resultMap.put("user-grade", memberDto.getGrade());
			resultMap.put("user-aboutMe", memberDto.getAboutMe());
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	@PutMapping("/updateProfile")
	public ResponseEntity<Map<String, Object>> updateProfile(@RequestBody MemberDto memberDto){
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(memberDto.getUserid());
		
		try {
			int rs = memberService.updateProfile(memberDto);
			String token = jwtService.create(memberDto);
			logger.trace("로그인 토큰정보 : {}", token);
			resultMap.put("auth-token", token);
			resultMap.put("user-id", memberDto.getUserid());
			resultMap.put("user-name", memberDto.getUsername());
			resultMap.put("user-pwd", memberDto.getUserpwd());
			resultMap.put("user-jibunAdd", memberDto.getJibunAdd());
			resultMap.put("user-roadAdd", memberDto.getRoadAdd());
			resultMap.put("user-restAdd", memberDto.getRestAdd());
			resultMap.put("user-email", memberDto.getEmail());
			resultMap.put("user-phone", memberDto.getUserphone());
			resultMap.put("user-grade", memberDto.getGrade());
			resultMap.put("user-aboutMe", memberDto.getAboutMe());
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
//			String info = memberService.getServerInfo();
			
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//
//			resultMap.put("status", true);
//			resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/like/{userid}/{aptno}")
    @ResponseBody
    public void like
        (@PathVariable("userid") String userid,
         @PathVariable("aptno") int aptno) {
        System.out.println(userid + " likes " + aptno + "!");
        Favorite fav = new Favorite();
        
        fav.setUserid(userid);
        fav.setAptno(aptno);
        
        if(memberService.searchLike(fav)==0) {
            System.out.println("doLike");
            memberService.doLike(fav);
        }
        else {
            System.out.println("cancleLike");
            memberService.cancleLike(fav);
        }

    }

}
