package com.ssafy.happyhouse.model;

public class Hospital {
	int hospital_id;
	String sido;
	String gungu;
	String hospital_name;
	String hospital_address;
	String htype;
	String hcall;
	
	public int getHospital_id() { return hospital_id; }
	public void setHospital_id(int hospital_id) { this.hospital_id = hospital_id; }
	
	public String getSido() { return sido; }
	public void setSido(String sido) { this.sido = sido; }
	
	public String getGungu() { return gungu; }
	public void setGungu(String gungu) { this.gungu = gungu; }
	
	public String getHospital_name() { return hospital_name; }
	public void setHospital_name(String hospital_name) { this.hospital_name = hospital_name; }
	
	public String getHospital_address() { return hospital_address; }
	public void setHospital_address(String hospital_address) { this.hospital_address = hospital_address; }
	
	public String getHtype() { return htype; }
	public void setHtype(String htype) { this.htype = htype; }
	
	public String getHcall() { return hcall; }
	public void setHcall(String hcall) { this.hcall = hcall; }
}
