package com.ssadolda.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/user")
public class UserRestController {

	private final UserService userservice;
	private final JwtUtil jwtUtil = new JwtUtil();

	@Value("${fileupload.upload.path}")
	private String uploadPath;

	public UserRestController(UserService userservice) {
		this.userservice = userservice;
	}

// 회원가입
	@PostMapping("/signup")
//public ResponseEntity<?> signup(@RequestBody User user, HttpSession session,@RequestPart("img") MultipartFile file) throws IllegalStateException, IOException {
	public ResponseEntity<?> signup(@RequestBody User user, HttpSession session)
			throws IllegalStateException, IOException {

		if (userservice.selectId(user.getUserId())) {
			return new ResponseEntity<String>("이미 존재하는 아이디 입니다!", HttpStatus.BAD_REQUEST);
		}
		int result = userservice.registUser(user);
//	// 파일 업로드 부분
//	if(file!=null) {
//		// 경로에 해당하는 디렉토리가 없으면 디렉토리  생성
//		File newFile = new File(uploadPath);
//		if(!newFile.exists()) {
//			newFile.mkdir();
//		}
//		
//		if(user != null) {
//			// user 객체에 파일의 이름을 저장 
//			user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
//			user.setOrgimg(file.getOriginalFilename());
//			
//			// 파일 생성부분 중요 
//			file.transferTo(new File(uploadPath + "/" + user.getImg()));
//			userservice.updateUser(user);
//		}
//	}

		if (result == 1) {
			return new ResponseEntity<String>("회원가입 성공!", HttpStatus.CREATED);
		} else {

			return new ResponseEntity<String>("실패!", HttpStatus.BAD_REQUEST);
		}

	}

// 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
		// 토큰값, 로그인한 유저 , 메세지, httpstatus를 담는 result
		
		System.out.println(user);
		Map<String, Object> result = new HashMap<String, Object>();
		HttpStatus status = null;
		User loginUser = null;

		try {
			loginUser = userservice.login(user);
			// 로그인 정보가 없을때 400에러 
			if (loginUser == null) {
				result.put("message", "로그인정보가 틀림");
				return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			// 예상치못한 에러가 발생했을 때 
			result.put("message", "알수없는 에러 발생");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			// 위에서 loginUser에 저장한 값에서 뽑아온 Id값이 있을 때
			if (loginUser.getUserId() != null || loginUser.getUserId().length() > 0) {
				// loginUser에서 가져온 아이디의 토큰값을 map에 넣어줌
				result.put("access-token", jwtUtil.createToken("id", loginUser.getUserId()));
				// loginUser 도 넣어줌
				result.put("loginUser", loginUser);
				// 로그인 성공 메세지도 담아줌 
				result.put("message", "로그인에 성공했습니다!");
				status = HttpStatus.ACCEPTED; // 202
			} else {
				result.put("message", "로그인에 실패했습니다.");
				status = HttpStatus.NO_CONTENT; // 204
			}
		} catch (UnsupportedEncodingException e) { // 이상한 글자 들어올 때
			result.put("message", "서버로그인에 실패했습니다.");
			status = HttpStatus.INTERNAL_SERVER_ERROR; // 500 에러
		}
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		try {
			session.removeAttribute("loginUser");
			session.removeAttribute("access-token");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	// 내 정보 수정
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User user) {

		try {
			int result = userservice.updateUser(user);
			if (result != 0)
				return new ResponseEntity<Integer>(result, HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

// 회원탈퇴
	@DeleteMapping("/quit/{userId}")
	public ResponseEntity<?> quit(@PathVariable String userId) {

		try {
			int result = userservice.quitUser(userId);
			if (result != 0) {
				return new ResponseEntity<>("정상적으로 탈퇴 되었습니다!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

// 회원 이미지 업로드
//@PutMapping("/uploadimg/{userSeq}")
	@PostMapping("/uploadimg/{userSeq}")
	public ResponseEntity<?> uploadimg(@PathVariable int userSeq, @RequestPart("img") MultipartFile file) {
		try {
			User user = null;

			if (file != null && file.getSize() > 0) {

				// resourceLoader를 통해 클래스패스 내의 경로를 받아옴
				System.out.println(file);

				// 경로에 해당하는 디렉토리가 없으면 디렉토리 생성
				File newFile = new File(uploadPath);
				if (!newFile.exists()) {
					newFile.mkdir();
				}

				// 유저 가져오는 부분
				user = userservice.selectUserBySeq(userSeq);

				// 유저가 있을경우에만 들어감
				if (user != null) {
					// user 객체에 파일의 이름을 저장
					user.setImg(System.currentTimeMillis() + "_" + file.getOriginalFilename());
					user.setOrgimg(file.getOriginalFilename());

					// 파일 생성부분 중요
					file.transferTo(new File(uploadPath + "/" + user.getImg()));
				}
			}
			int result = userservice.updateUser(user);

			if (result != 0) {
				return new ResponseEntity<Integer>(result, HttpStatus.OK);
			} else
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
//	 내 정보

	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("죄송합니다 : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
