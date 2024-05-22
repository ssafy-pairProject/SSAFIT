package com.ssadolda.model.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssadolda.model.dto.User;

@Repository
public interface UserDao {

    int insertUser(User user);
    
    // userId로 유저고르기 
    User selectUserByUserId(String userId);

    User selectUserByUserSeq(int userSeq);

    List<User> selectUserList();

    int modifyUser(User user);

    int deleteUser(String userId);

    int removeUserByUserSeq(int userSeq);

    int uploadImage(int userSeq);
    
    boolean selectId(String userId);
    
    int userCount();
	
}
