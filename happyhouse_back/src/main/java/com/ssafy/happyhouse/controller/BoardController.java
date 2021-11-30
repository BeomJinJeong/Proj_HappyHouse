package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Board;
import com.ssafy.happyhouse.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@ApiOperation(value = "공지사항 상세정보", response = ResponseEntity.class)
	@GetMapping("/detail/{idx}")
	public ResponseEntity<Board> detail(@PathVariable("idx") int boardIdx) {
		Board info = boardService.boardInfo(boardIdx);
		if (info != null && info.getSubject() != "" && info.getSubject() != null) {
			return new ResponseEntity<Board>(info, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "검색 X, 페이지 선택 X, 공지사항 전체 중 첫 10개 갖고 옴.", response = ResponseEntity.class)
	@GetMapping(value = "")
	public ResponseEntity<Map<String, Object>> board() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageNo", 1);
		params.put("listSize", 10);
		Map<String, Object> result = boardService.boardList(params);

		List<Board> a = (List<Board>) result.get("boardList");
		if (a != null && a.size() != 0) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "검색 o, 페이지 선택 o", response = ResponseEntity.class)
	@GetMapping("/{key}/{word}/{page}")
	public ResponseEntity<Map<String, Object>> listSearchPage
			(@PathVariable("key") String key,
			 @PathVariable("word") String word,
			 @PathVariable("page") int page) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("pageNo", page);
		params.put("listSize", 10);
		params.put("key", key);
		params.put("word", word);

		Map<String, Object> result = boardService.boardList(params);

		List<Board> a = (List<Board>) result.get("boardList");
		if (a != null && a.size() != 0) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "검색 x, 페이지 선택 o", response = ResponseEntity.class)
	@GetMapping("/{page}")
	public ResponseEntity<Map<String, Object>> listNotSearch(@PathVariable("page") int page) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("pageNo", page);
		params.put("listSize", 10);		
		
		Map<String, Object> result = boardService.boardList(params);

		List<Board> a = (List<Board>) result.get("boardList");
		if (a != null && a.size() != 0) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "검색 o, 페이지 선택 x", response = ResponseEntity.class)
	@GetMapping("/{key}/{word}")
	public ResponseEntity<Map<String, Object>> listSearch(@PathVariable("key") String key,
										  @PathVariable("word") String word) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("pageNo", 1);
		params.put("listSize", 10);
		params.put("key", key);
		params.put("word", word);
		
		Map<String, Object> result = boardService.boardList(params);

		List<Board> a = (List<Board>) result.get("boardList");
		if (a != null && a.size() != 0) {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공지사항 등록", response = ResponseEntity.class)
	@PostMapping("/register")
	public ResponseEntity<Board> register(@RequestBody Board board) {
		
		int idx = boardService.boardRegister(board);
		
		return new ResponseEntity<Board>(boardService.boardInfo(idx), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 업데이트", response = ResponseEntity.class)
	@PutMapping("/update")
	public ResponseEntity<Board> modify(@RequestBody Board board) {
		boardService.boardModify(board);
		return new ResponseEntity<Board>(boardService.boardInfo(board.getIdx()), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 삭제", response = ResponseEntity.class)
	@DeleteMapping("/delete/{no}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable("no") int boardIdx) {
		boardService.boardDelete(boardIdx);
		return board();
	}
}
