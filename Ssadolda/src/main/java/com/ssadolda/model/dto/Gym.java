package com.ssadolda.model.dto;

import lombok.Data;

@Data
public class Gym {

	private String gymName;	// 이름
	private String gymAddress;	// 주소
	private String gymPhoneNumber;	// 전화번호
	private String gymPrice; // 가격
	private String gymIntroduce; // 헬스장 소개
	private String gymImg; // 헬스장 사진
	private String gymOperatingHours; // 운영시간
	private String gymAdditionalService; // 부가서비스
	private String gymAmenities; // 편의 시설
	private int gymSeq; // 헬스장 고유 번호

}
