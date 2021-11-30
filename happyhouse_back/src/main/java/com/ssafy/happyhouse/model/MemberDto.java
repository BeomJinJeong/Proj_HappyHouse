package com.ssafy.happyhouse.model;

public class MemberDto {
	private String userid;
	private String username;
	private String userpwd;
	private String email;
	private String jibunAdd;
	private String roadAdd;
	private String restAdd;
	public String getRestAdd() {
		return restAdd;
	}
	public void setRestAdd(String restAdd) {
		this.restAdd = restAdd;
	}
	private String userphone;
	private String grade;
	private String aboutMe;
	
	
	public String getAboutMe() {
		return aboutMe;
	}
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getEmail() {
		return email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJibunAdd() {
		return jibunAdd;
	}
	public void setJibunAdd(String jibunAdd) {
		this.jibunAdd = jibunAdd;
	}
	public String getRoadAdd() {
		return roadAdd;
	}
	public void setRoadAdd(String roadAdd) {
		this.roadAdd = roadAdd;
	}

	

}
