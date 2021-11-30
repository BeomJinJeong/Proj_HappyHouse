package com.ssafy.happyhouse.model;

public class AptDealParams {
	String key;
	String word;
	PageDto pageDto;
	
	public AptDealParams(String key, String word) {
		super();
		this.key = key;
		this.word = word;
	}
	
	public AptDealParams(String key, String word, PageDto pageDto) {
		super();
		this.key = key;
		this.word = word;
		this.pageDto = pageDto;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public PageDto getPageDto() {
		return pageDto;
	}
	public void setPageDto(PageDto pageDto) {
		this.pageDto = pageDto;
	}
	
	
}
