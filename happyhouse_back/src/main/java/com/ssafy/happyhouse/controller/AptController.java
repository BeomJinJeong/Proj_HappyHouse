package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.AptDeal;
import com.ssafy.happyhouse.model.AptInfo;
import com.ssafy.happyhouse.model.InterAreaParams;
import com.ssafy.happyhouse.model.Member;
import com.ssafy.happyhouse.service.AptService;

@CrossOrigin("*")
@Controller
@RequestMapping("/apt")
public class AptController {
	
	@Autowired
	private AptService aptService;
	
	@GetMapping
	public String goList() {
		return "apt";
	}
	
	@GetMapping("/like/{no}")
	@ResponseBody
	public void Like(@PathVariable int no) {
		System.out.println(no + ": like!");
		aptService.like(no);
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> selectAllList(String key, String word, int page) throws Exception{
		Map<String, Object> params = new HashMap<>();
		
		params.put("key", key);
		params.put("word", word);
		params.put("pageNo", page);
		params.put("listSize", 10);
		Map<String, Object> result = aptService.aptSelect(params);
		
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/detail/{no}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<AptDeal> selectApt(@PathVariable String no) {
		AptDeal tmp = aptService.selectApt(Integer.parseInt(no));
		if(tmp.getRentMoney() == null) tmp.setRentMoney("월세 없음");
		return new ResponseEntity<AptDeal>(tmp, HttpStatus.OK);
	}
	
	@GetMapping("/insertArea")
	@ResponseBody
	public InterAreaParams insertIArea(Model model, HttpServletRequest request, String key, String word) {
		System.out.println("hi");
		HttpSession session = request.getSession();
		String id =((Member) session.getAttribute("userinfo")).getUserid();
		InterAreaParams params = new InterAreaParams(id, key, word);
		aptService.insertIArea(params);
		
		model.addAttribute("iArea", aptService.selectIArea(id));
//		session.setAttribute("iArea", params);
		
//		System.out.println(session.getAttribute("iArea"));
		return params;
	}
	
	@GetMapping("/showIArea")
	@ResponseBody
	public List<InterAreaParams> selectIArea(Model model, HttpServletRequest request, String key, String word) {
		HttpSession session = request.getSession();
		String id =((Member) session.getAttribute("userinfo")).getUserid();
		return aptService.selectIArea(id);
	}
	
	@GetMapping("/iAreaDelete")
	@ResponseBody
	public List<InterAreaParams> deleteIArea(Model model, HttpServletRequest request, String delete, String key, String word) {
		HttpSession session = request.getSession();
		String id =((Member) session.getAttribute("userinfo")).getUserid();
		
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("search", delete);
		
		aptService.deleteIArea(map);
		
		return aptService.selectIArea(id);
	}
	
	@GetMapping("/infoList")
	@ResponseBody
	public ResponseEntity<List<AptInfo>> aptInfoList() {
		return new ResponseEntity(aptService.aptInfoList(), HttpStatus.OK);
	}
	
	@GetMapping("/infoList/{key}/{word}")
	@ResponseBody
	public ResponseEntity<List<AptDeal>> aptInfoSearch
	(@PathVariable("key") String key, 
	 @PathVariable("word") String word) {
		Map<String, String> params = new HashMap<>();
		params.put("key", key);
		params.put("word", word);
		List<AptDeal> list = aptService.aptInfoSearch(params);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@GetMapping("/infoList/{sLat}/{lLat}/{sLng}/{lLng}")
	@ResponseBody
	public ResponseEntity<List<AptDeal>> aptInfoSearchCoor
	(@PathVariable("sLat") double sLat, 
	 @PathVariable("lLat") double lLat,
	 @PathVariable("sLng") double sLng,
	 @PathVariable("lLng") double lLng) {
		Map<String, Double> params = new HashMap<>();
		params.put("sLat", sLat);
		params.put("lLat", lLat);
		params.put("sLng", sLng);
		params.put("lLng", lLng);
		List<AptInfo> list = aptService.aptInfoSearchCoor(params);
		System.out.println(list);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@GetMapping("/dealList/{dong}/{aptName}")
	@ResponseBody
	public ResponseEntity<List<AptDeal>> aptDealList
	(@PathVariable("dong") String dong, 
	 @PathVariable("aptName") String aptName) {
		Map<String, String> params = new HashMap<>();
		params.put("dong", dong);
		params.put("aptName", aptName);
		List<AptDeal> list = aptService.aptDealList(params);
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	@ResponseBody
	public ResponseEntity<List<String>> dong() {
		return new ResponseEntity(aptService.dong(), HttpStatus.OK);
	}
	
	/** 인기매물 top10 */
	@GetMapping("/hot10")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> selectHot() {
		System.out.println("인기 top10!");
		Map<String, Object> result = aptService.selectHot();
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping("info/{dong}/{aptName}")
	@ResponseBody
	public ResponseEntity<AptInfo> aptInfo(
			@PathVariable("dong") String dong,
			@PathVariable("aptName") String aptName) {
		return new ResponseEntity<AptInfo>(aptService.aptInfo(dong, aptName), HttpStatus.OK);
	}
	
	@GetMapping("info/{no}")
	@ResponseBody
	public ResponseEntity<AptInfo> aptInfoNo(@PathVariable("no") int no) {
		return new ResponseEntity<AptInfo>(aptService.aptInfoNo(no), HttpStatus.OK);
	}
	
	@GetMapping("code/{code}")
	@ResponseBody
	public ResponseEntity<String> sigu(@PathVariable("code") int code) {
		return new ResponseEntity<String>(aptService.sigu(code), HttpStatus.OK);
	}
}