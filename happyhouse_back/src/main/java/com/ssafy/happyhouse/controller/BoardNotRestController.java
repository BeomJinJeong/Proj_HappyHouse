package com.ssafy.happyhouse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.model.PageDto;
import com.ssafy.happyhouse.service.BoardService;

@CrossOrigin(value = {"*"}, maxAge = 6000)
@Controller
@RequestMapping(value = "/ansseumdonttouch")
public class BoardNotRestController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping(value = "")
	public String board(@RequestParam Map<String, Object> params, Model model) {
		PageDto pageDto = new PageDto();
		
		params.put("pageNo", params.getOrDefault("pageNo", "1"));
		params.put("listSize", params.getOrDefault("listSize", "10"));
		params.put("pageNo", Integer.parseInt((String)params.get("pageNo")));
		params.put("listSize", Integer.parseInt((String)params.get("listSize")));
		params.put("key", params.getOrDefault("key", "subject"));
		params.put("word", params.getOrDefault("word", null));		
		
		if(params.get("key").equals("")) {
			params.put("key", "subject");
		}
		if(params.get("word") != null && params.get("word").equals("")) {
			params.put("word", null);
		}
			
		
		System.out.println(params.get("pageNo"));
		System.out.println(params.get("listSize"));
		System.out.println(params.get("key"));
		System.out.println(params.get("word"));
		
		model.addAttribute("result", boardService.boardList(params));
		model.addAttribute("params", params);
		return "brd/board";
	}
	
	@GetMapping("/gowrite")
	public String gowriteArticle() {
		return "brd/boardInsert";
	}
	
	@PostMapping("write")
	public String writeArticle(Board board) {
		boardService.boardRegister(board);
		return "redirect:/board";
	}
	
//	@GetMapping("detail")
//	public String detailArticle(String boardidx, Model model) {
//		model.addAttribute("info", boardService.boardInfo(boardidx));
//		return "brd/boardDetail";
//	}
}
