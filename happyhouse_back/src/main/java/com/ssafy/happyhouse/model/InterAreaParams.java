package com.ssafy.happyhouse.model;

public class InterAreaParams {
	private String id;
	private String key;
	private String search;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public InterAreaParams(String id, String key, String search) {
		super();
		this.id = id;
		this.key = key;
		this.search = search;
	}
	
}
