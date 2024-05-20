package com.ssadolda.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssadolda.model.dto.Gym;
import com.ssadolda.service.GymBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping("/gym")
@RequiredArgsConstructor
public class GymBoardRestController {

	private final GymBoardService gymboardservice;
	
	@Value("${fileupload.upload.path}")
	private String uploadPath;

	
	//헬스장 전체
	@GetMapping("/list")
	public ResponseEntity<?> readAllGym(){
		List<Gym> gym = gymboardservice.readAllGym();
			return new ResponseEntity<List<Gym>>( gym,HttpStatus.OK);
		
	}
	//헬스장 리뷰 평점 평균? 
	// gymstatistic에 저장해야됨?
	// averageRating에 리뷰 등록 마다점수를 더함 (또 리뷰 삭제시에 점수 빼줘야함)
	// 그다음에 해당 헬스장 리뷰 갯수로 나누면 될거같음.
	

	// 헬스장 등록
	// 관리자 권한 나중에추가
	@PostMapping("/regist")
	public ResponseEntity<?> registGym(@RequestBody Gym gym, @RequestPart MultipartFile file){
		int result = gymboardservice.registGym(gym);
		try {
			if(file!=null) {
				// 폴더생성?
				// 경로에 해당하는 디렉토리가 없으면 디렉토리  생성
				File newFile = new File(uploadPath);
				if(!newFile.exists()) {
					newFile.mkdir();
				}
				
				if(gym!=null) {
					gym.setGymImg(System.currentTimeMillis()+"_"+file.getOriginalFilename());
					file.transferTo(new File(uploadPath + "/" + gym.getGymImg()));
				}
				gymboardservice.modifyGym(gym);
				
			}
			
			if(result!=0) {
				return new ResponseEntity<String>("체육관등록 성공",HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("등록에 실패하였습니다",HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	//헬스장 수정
	@PutMapping("/modify/{gymSeq}")
	public ResponseEntity<?> modifyGym(@RequestBody Gym gym , @PathVariable("gymSeq") int gymSeq){
//		gym.setGymSeq(gymSeq);
		gym.setGymSeq(gymSeq);
		
		int result = gymboardservice.modifyGym(gym);
		
		try {
			if(result!=0) {
				return new ResponseEntity<String>("수정했습니다",HttpStatus.OK);
			}else {
				return new ResponseEntity<String>("수정에 실패하였습니다",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
//	 헬스장 삭제
	@DeleteMapping("/delete/{gymSeq}")
	public ResponseEntity<?> deleteGym(@RequestBody Gym gym,@PathVariable int gymSeq){
		try {
			int result = gymboardservice.deleteGym(gymSeq);
			if(result!=0)
				return new ResponseEntity<String>("성공적으로 삭제 했습니다",HttpStatus.OK);
			else {
				return new ResponseEntity<String>("실패했습니다",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
		
	}
	
	//헬스장 상세
	@GetMapping("/{gymSeq}")
	public ResponseEntity<?> detailGym(@PathVariable int gymSeq){
		try {
			Gym gym = gymboardservice.gymDetail(gymSeq);
			gymboardservice.incrementViewCount(gymSeq);
			if(gym!=null) 
			return new ResponseEntity<Gym>(gym, HttpStatus.OK);
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();

		return new ResponseEntity<String>("Sorry : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
