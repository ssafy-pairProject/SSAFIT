package com.ssadolda.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class User {
	
	private String userId;
	private String password;
	private String email;
	private String phoneNumber;
	private String name;
	private String nickname;
	private int userSeq;
	
	private String userRole;
	
    private String img;
    private String orgimg;
    private MultipartFile file;
    
    

}
