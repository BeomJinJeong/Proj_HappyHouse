package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Answer;
import com.ssafy.happyhouse.model.QnA;
import com.ssafy.happyhouse.service.AnswerService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/answer")
public class AnswerController {
	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);

	@Autowired
	private AnswerService answerService;
	
	@ApiOperation(value = "answer 등록", response = Integer.class)
	@PostMapping("/register")
	public List<Answer> register(@RequestBody Answer answer) {
		answerService.answerRegister(answer);
		return answerList(answer.getQuesno());
	}
	
	@ApiOperation(value = "answer 삭제", response = Integer.class)
	@DeleteMapping("/delete/{no}")
	public List<Answer> delete(@PathVariable("no") int answerNo) {
		int quesNo = answerService.quesNo(answerNo);
		answerService.answerDelete(answerNo);
		return answerList(quesNo);
	}
	
	public List<Answer> answerList(int quesNo) {
		return answerService.listAll(quesNo);
	}
}
