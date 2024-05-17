package com.ssadolda.controller;

import java.util.List;


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
import org.springframework.web.bind.annotation.RestController;

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
	
	//헬스장 전체
	@GetMapping("/list")
	public ResponseEntity<?> readAllGym(){
		List<Gym> gym = gymboardservice.readAllGym();
			return new ResponseEntity<List<Gym>>( gym,HttpStatus.OK);
		
	}

	// 헬스장 등록
	// 관리자 권한 나중에추가
	@PostMapping("/regist")
	public ResponseEntity<?> registGym(@RequestBody Gym gym){
		int result = gymboardservice.registGym(gym);
		try {
			if(result!=0) {
				return new ResponseEntity<String>("체육관등록 성공",HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>("등록에 실패하였습니다",HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	//헬스장 수정
	@PutMapping("/modify")
	public ResponseEntity<?> modifyGym(@RequestBody Gym gym){
//		gym.setGymSeq(gymSeq);
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
