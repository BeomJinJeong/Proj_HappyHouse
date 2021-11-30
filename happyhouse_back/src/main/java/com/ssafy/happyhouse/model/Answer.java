package com.ssafy.happyhouse.model;

public class Answer {
	int no;
	String content;
	String date;
	int quesno;
	
	public int getNo() { return no; }
	public void setNo(int no) { this.no = no; }
	
	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }
	
	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }
	
	public int getQuesno() { return quesno; }
	public void setQuesno(int quesno) { this.quesno = quesno; }
	
	@Override
	public String toString() {
		return "Answer [no=" + no + ", content=" + content + ", date=" + date + ", quesno=" + quesno + "]";
	}
}
