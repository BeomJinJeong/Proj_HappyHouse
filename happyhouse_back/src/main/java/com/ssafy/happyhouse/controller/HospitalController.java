package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.service.HospitalService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value = "/hospital")
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	@GetMapping(value = "")
	public ResponseEntity<Map<String, Object>> board() {
		Map<String, Object> params = new HashMap<>();
		params.put("pageNo", params.getOrDefault("pageNo", "1"));
		params.put("listSize", params.getOrDefault("listSize", "100"));
//		params.put("pageNo", Integer.parseInt((String)params.get("pageNo")));
//		params.put("listSize", Integer.parseInt((String)params.get("listSize")));
//		params.put("key", params.getOrDefault("key", "subject"));
//		params.put("word", params.getOrDefault("word", null));		
		
//		if(params.get("key").equals("")) {
//			params.put("key", "subject");
//		}
//		if(params.get("word") != null && params.get("word").equals("")) {
//			params.put("word", null);
//		}
		System.out.println("success");
//		boardPage(1);
		return new ResponseEntity<Map<String, Object>>(hospitalService.hospitalList(params), HttpStatus.OK);
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<Map<String, Object>> boardPage(@PathVariable("page") int page) {
		Map<String, Object> params = new HashMap<>();
		params.put("pageNo", params.getOrDefault("pageNo", page));
		params.put("listSize", params.getOrDefault("listSize", "100"));
		
		return new ResponseEntity<Map<String, Object>>(hospitalService.hospitalList(params), HttpStatus.OK);
	}
	
	@GetMapping("/{key}/{word}")
	public ResponseEntity<Map<String, Object>> boardSearchList(	@PathVariable("key") String key, 
																@PathVariable("word") String word){
		System.out.println("검색요청 들어옴");
		Map<String, Object> params = new HashMap<>();
		params.put("pageNo", params.getOrDefault("pageNo", "1"));
		params.put("listSize", params.getOrDefault("listSize", "100"));
		params.put("key", params.getOrDefault("key", key));
		params.put("word", params.getOrDefault("word", word));
		
		return new ResponseEntity<Map<String, Object>>(hospitalService.hospitalList(params), HttpStatus.OK);
	}
}
