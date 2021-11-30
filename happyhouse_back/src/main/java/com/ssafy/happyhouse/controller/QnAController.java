package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.PageDto;
import com.ssafy.happyhouse.model.QnA;
import com.ssafy.happyhouse.service.QnAService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnAController {
	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	
	@Autowired
	private QnAService qnaService;
	
	@ApiOperation(value = "question 상세정보", response = Map.class)
	@GetMapping("/detail/{no}")
	public Map<String, Object> detail(@PathVariable("no") int qnaNo) {
		return qnaService.qnaInfo(qnaNo);
	}

	@ApiOperation(value = "검색 x, 페이지 선택 x, question 전체 중 첫 10개 갖고옴", response = Map.class)
	@GetMapping("")
	public Map<String, Object> listAll() {
		
		Map<String, Object> params = new HashMap<String, Object>();		
		params.put("pageNo", 1);
		params.put("listSize", 10);
		return qnaService.qnaList(params);
	}
	
	@ApiOperation(value = "검색 o, 페이지 선택 o", response = Map.class)
	@GetMapping("/{key}/{word}/{page}")
	public Map<String, Object> listSearchPage(@PathVariable("page") int page,
						  @PathVariable("key") String key,
						  @PathVariable("word") String word) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("pageNo", page);
		params.put("listSize", 10);
		params.put("key", key);
		params.put("word", word);		
		
		return qnaService.qnaList(params);
	}
	
	@ApiOperation(value = "검색 x, 페이지 선택 o", response = Map.class)
	@GetMapping("/{page}")
	public Map<String, Object> listNotSearch(@PathVariable("page") int page) {
		System.out.println("여기들어왔우ㅡㅁ");
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("pageNo", page);
		params.put("listSize", 10);		
		
		return qnaService.qnaList(params);
	}
	
	@ApiOperation(value = "검색 o, 페이지 선택 x", response = Map.class)
	@GetMapping("/{key}/{word}")
	public Map<String, Object> listSearch(@PathVariable("key") String key,
										  @PathVariable("word") String word) {
		System.out.println("여기들어왔음");
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("pageNo", 1);
		params.put("listSize", 10);
		params.put("key", key);
		params.put("word", word);
		
		Map<String, Object> result = qnaService.qnaList(params);
		System.out.println("컨트" + result);
		return result;
	}
	
	@ApiOperation(value = "question 등록", response = Map.class)
	@PostMapping("/register")
	public Map<String, Object> register(@RequestBody QnA qna) {
		
		qnaService.qnaRegister(qna);
		return listAll();
	}
	
	@ApiOperation(value = "question 업데이트", response = Map.class)
	@PutMapping("/update")
	public Map<String, Object> modify(@RequestBody QnA qna) {
		qnaService.qnaModify(qna);
		return listAll();
	}
	
	@ApiOperation(value = "question 삭제", response = Map.class)
	@DeleteMapping("/delete/{no}")
	public Map<String, Object> delete(@PathVariable("no") int qnaNo) {
		qnaService.qnaDelete(qnaNo);
		return listAll();
	}
}
