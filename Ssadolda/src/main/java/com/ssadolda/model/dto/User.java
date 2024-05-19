package com.ssadolda.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Data
@Getter
@Setter
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
