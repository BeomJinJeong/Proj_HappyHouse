package com.ssafy.happyhouse.model;

public class QnA {
	private int no;
	private String title;
	private String content;
	private String userid;
	private String date;
	
	public int getNo() { return no; 
	}public void setNo(int no) { this.no = no; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public String getUserid() { return userid; }
	public void setUserid(String userid) { this.userid = userid; }
	
	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }

	@Override
	public String toString() {
		return "QnA [no=" + no + ", title=" + title + ", content=" + content + ", userid=" + userid + ", date=" + date
				+ "]";
	}
}
