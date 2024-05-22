package com.ssadolda.service;


import java.util.List;

import com.ssadolda.model.dto.User;



public interface UserService {
	
	// 모든 유저 정보
	public List<User> userList();
	
	// 회원가입
	public int registUser(User user);
	
	// 로그인
	public User login(User user);
	
	// 내 정보
	User myinfo(int userSeq);
	
	// 내 정보 수정
	public int updateUser(User user);
	
	// 회원 탈퇴
	public int quitUser(String userId);
	
	// 회원 이미지
	int uploadImage(int userSeq);
	
	// 회원 중복 체크
	boolean selectId(String userId);

	User selectUserBySeq(int userSeq);
	
	User selectUserByUserId(String userId);
	
}
