package com.ssadolda.service;

import java.util.List;

import com.ssadolda.model.dto.Gym;

public interface GymBoardService {
	
	// 헬스장 등록
	public int registGym(Gym gym);
	
	// 헬스장 수정
	public int modifyGym(Gym gym);
	
	// 헬스장 삭제
	public int deleteGym(int gymSeq);
	
	// 헬스장 모든 리스트
	public List<Gym> readAllGym();
	
	// 헬스장 상세 조회
	
	public Gym gymDetail(int gymSeq);
	
	// 찜 버튼
	
	public int gymLikeBtn();
	
	// 헬스장 정렬
	
	public List<Gym> arrayGym();

}