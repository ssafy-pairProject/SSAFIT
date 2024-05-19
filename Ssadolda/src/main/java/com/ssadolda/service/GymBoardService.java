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
	
	// 찜 버튼 다른곳에 구현
	
	public int gymLikeBtn();
	
	// 헬스장 조회수, 좋아요수 조건부 정렬 미구현 
	
	public List<Gym> arrayGym();
	
	//헬스장 조회수 증가
	
	void incrementViewCount(int gymSeq);
	
	int insertGymStatistics(int gymSeq);

}
