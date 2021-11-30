package com.ssafy.happyhouse.service;

import java.util.Map;

import com.ssafy.happyhouse.model.Hospital;

public interface HospitalService {

	Map<String, Object> hospitalList(Map<String, Object> params);

	void hospitalRegister(Hospital hospital);

	Hospital hospitalInfo(String hospitalidx);

	void hospitalModify(Hospital hospital);

	void hospitalDelete(String hospitalidx);
}
