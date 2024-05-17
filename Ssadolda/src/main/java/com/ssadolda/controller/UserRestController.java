package com.ssadolda.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssadolda.model.dto.User;
import com.ssadolda.service.UserService;
import com.ssadolda.util.JwtUtil;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/user")
public class UserRestController {

	private final UserService userservice;
	private final JwtUtil jwtUtil = new JwtUtil();
	private final ResourceLoader resLoader;

public UserRestController (UserService userservice,ResourceLoader resLoader) {
	this.userservice=userservice;
	this.resLoader=resLoader;
}
	

// 회원가입
@PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody User user, HttpSession session) {
	if(userservice.selectId(user.getUserId())) {
		return new ResponseEntity<String>("이미 존재하는 아이디 입니다!",HttpStatus.BAD_REQUEST);
	}
	int result =userservice.registUser(user);
	String msg="";
	if(result==1) {
		msg = "회원가입에 성공했습니다!";
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	}else {
		msg = "회원가입에 실패했습니다.";
				return new ResponseEntity<String>(msg,HttpStatus.BAD_REQUEST);
	}
	
}



// 로그인
@PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(User user){
	// 토큰값, 로그인한 유저 , 메세지, httpstatus를 담는 result
	Map<String,Object> result = new HashMap<String, Object>();
	HttpStatus status = null;
	User loginUser = userservice.login(user);
	
	if(loginUser==null) {
		throw new IllegalArgumentException("loginUser에 null이 뜸");
	}
	try {
		if(user.getUserId()!=null||user.getUserId().length()>0) {
			result.put("access-token", jwtUtil.createToken("id", user.getUserId()));
			result.put("loginUser",loginUser);
			result.put("message","SUCCESS");
			status= HttpStatus.ACCEPTED; // 202
		}else {
			result.put("message", "FAIL");
			status=HttpStatus.NO_CONTENT; //204
		}
	} catch (UnsupportedEncodingException e) { //이상한 글자 들어올 때
		result.put("message", "FAIL");	
		status=HttpStatus.INTERNAL_SERVER_ERROR; // 500 에러
	}
	return new ResponseEntity<Map<String,Object>>(result,status);
}
// 로그아웃
@GetMapping("/logout")
public ResponseEntity<?> logout(HttpSession session){
	try {
		session.removeAttribute("loginUser");
		session.removeAttribute("access-token");
		return new ResponseEntity<>(HttpStatus.OK);
	}catch (Exception e) {
		return exceptionHandling(e);
	}
	
}
 // 내 정보 수정
@PutMapping("/update")
public ResponseEntity<?> updateUser(@RequestBody User user){
	
	try {
		int result = userservice.updateUser(user);
		if(result != 0)
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	} catch(Exception e) {
		return exceptionHandling(e);
	}
	
}

// 회원탈퇴
@DeleteMapping("/quit/{userId}")
public ResponseEntity<?> quit (@PathVariable String userId){
	
	try {
		int result = userservice.quitUser(userId);
		if(result!=0) {
			return new ResponseEntity<>("정상적으로 탈퇴 되었습니다!",HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	} catch (Exception e) {
		return exceptionHandling(e);
	}
}

// 회원 이미지 업로드
@PutMapping("/uploadimg/{userId}")
public ResponseEntity<?> uploadimg(@PathVariable int userSeq, @RequestParam("img") MultipartFile file){
	try {
		User user =null;
		
		if(file !=null && file.getSize()>0) {
			Resource res = resLoader.getResource("classpath:static/resources/upload");
			if(!res.getFile().exists())
				res.getFile().mkdir();
			
			List<User> list = userservice.userList();
			
			for(int i=0; i<list.size();i++) {
				if(list.get(i).getUserSeq()==userSeq)
					user = list.get(i);
			}
			if(user != null) {
//				user.setImg(file.getOriginalFilename());
				user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
				user.setOrgimg(file.getOriginalFilename());

				file.transferTo(new File(res.getFile().getCanonicalPath() + "/" + user.getImg()));
			}
		}

		int result = userservice.updateUser(user);

		if(result != 0) {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
		else
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	} catch(Exception e) {
		return exceptionHandling(e);
	}
}




private ResponseEntity<String> exceptionHandling(Exception e) {
	e.printStackTrace();
	return new ResponseEntity<String>("죄송합니다 : "+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}
	
}
