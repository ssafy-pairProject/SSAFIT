package com.ssadolda.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssadolda.model.dao.UserDao;
import com.ssadolda.model.dto.User;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserDao userdao;
	
	@Override
	public List<User> userList() {
		return userdao.selectUserList();
	}
	@Override
	public int registUser(User user) {
		return userdao.insertUser(user);
	}
	// 로그인 
	@Override
	public User login(User user) {
		User loginUser = userdao.selectUserByUserId(user.getUserId());
		
		if(loginUser!=null &&loginUser.getPassword().equals(user.getPassword())) {
			return loginUser;
		}
		return null;
		
	}


	@Override
	public int updateUser(User user) {
		return userdao.modifyUser(user);
	}
	@Override
	public int quitUser(String userId) {
	
		return userdao.deleteUser(userId);
	}
	
	@Override
	public User myinfo(int userSeq) {
		return userdao.selectUserByUserSeq(userSeq);
	}
	@Override
	public int uploadImage(int userSeq) {
		return userdao.uploadImage(userSeq);
	}
	
	@Override
	public boolean selectId(String userId) {
		log.info("Service :: selectId.....");
		return userdao.selectId(userId);
	}
	
	@Override
	public User selectUserBySeq (int userSeq) {
		return userdao.selectUserByUserSeq(userSeq);
	}
	@Override
	public User selectUserByUserId(String userId) {
		return userdao.selectUserByUserId(userId);
	}
	@Override
	public int userCount() {
		return userdao.userCount();
	}
	


}
