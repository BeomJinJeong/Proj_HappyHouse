package com.ssafy.happyhouse.model;

public class PageResultDtoTest {
	private int pageNo;
	private int count;

	public PageResultDtoTest(int pageNo, int count) {
		this.pageNo = pageNo;
		this.count = count;
	}

	public int getPageNo() {
		return pageNo;
	}

	public int getCount() {
		return count;
	}
}
