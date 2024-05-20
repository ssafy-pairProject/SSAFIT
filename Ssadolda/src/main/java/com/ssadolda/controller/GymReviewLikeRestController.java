package com.ssadolda.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.ssadolda.model.dto.Gym;
import com.ssadolda.service.GymReviewLikeService;

	import io.swagger.annotations.ApiModel;
	import io.swagger.annotations.ApiOperation;
	import lombok.RequiredArgsConstructor;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import java.util.HashMap;
	import java.util.List;

	@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
	@RestController
	@RequiredArgsConstructor
	@RequestMapping("/gymlike")
	public class GymReviewLikeRestController {

	    private final GymReviewLikeService gymReviewLikeService;
	    
	    //좋아요
	    @PostMapping("/like/{userSeq}/{gymSeq}")
	    public ResponseEntity<?> Like(@PathVariable int userSeq, @PathVariable int gymSeq) {

	        try {
	        	// 좋아요 이미 눌렀는지 체크
	            if (gymReviewLikeService.isUserLikedGym(userSeq, gymSeq)) {
	                return new ResponseEntity<String>("이미 좋아요 눌려있어요", HttpStatus.BAD_REQUEST);
	            }
	            HashMap<String, Integer> map = new HashMap<>();

	            map.put("userSeq", userSeq);
	            map.put("gymSeq", gymSeq);

	            int result = gymReviewLikeService.createLike(map);
	            gymReviewLikeService.incrementLike(gymSeq);
	            
	            if(result != 0)
	                return new ResponseEntity<Integer>(result, HttpStatus.OK);
	            else
	                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);

	        } catch(Exception e) {
	            return exceptionHandling(e);
	        }
	    }
	    
	    // 좋아요 취소
	    @DeleteMapping("/unlike/{userSeq}/{gymSeq}")
	    public ResponseEntity<?> unlike(@PathVariable int userSeq, @PathVariable int gymSeq) {

	        try {
	        	// 좋아요 눌러져있었는지 체크
	            if (!gymReviewLikeService.isUserLikedGym(userSeq, gymSeq)) {
	                return new ResponseEntity<String>("아직 좋아요 안눌렀어요", HttpStatus.BAD_REQUEST);
	            }
	            HashMap<String, Integer> map = new HashMap<>();

	            map.put("userSeq", userSeq);
	            map.put("gymSeq", gymSeq);

	            int result = gymReviewLikeService.deleteLike(map);
	            gymReviewLikeService.decreaseLike(gymSeq);
	            if(result != 0)
	                return new ResponseEntity<>(result, HttpStatus.OK);
	            else
	                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } catch(Exception e) {
	            return exceptionHandling(e);
	        }
	    }
	    
	    // 찜 개수 출력
	    @GetMapping("/count/{gymSeq}")
	    public ResponseEntity<?> countlike(@PathVariable int gymSeq){
	    	int result = gymReviewLikeService.countLike(gymSeq);
	    	return new ResponseEntity<Integer>(result,HttpStatus.OK);
	    	
	    }

//	    @ApiOperation(value = "특정 리뷰 좋아요 여부")
//	    @GetMapping("/reviewlike/{userSeq}/{reviewSeq}")
//	    public ResponseEntity<?> getUserLikeList(@PathVariable int userSeq, @PathVariable int reviewSeq) {
//
//	        try {
//	            HashMap<String, Integer> map = new HashMap<>();
//
//	            map.put("userSeq", userSeq);
//	            map.put("reviewSeq", reviewSeq);
//
//	            VideoReviewLikeInfo reviewLike = lService.readReviewLike(map);
//
//	            if(reviewLike != null)
//	                return new ResponseEntity<VideoReviewLikeInfo>(reviewLike, HttpStatus.OK);
//	            else
//	                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	        } catch(Exception e) {
//	            return exceptionHandling(e);
//	        }
//	    }

	    // gymlike db에 저장된 정보는 헬스장번호-유저번호 데이터가 있으면 좋아요를 한거임
	    // -> 거기서 특정 유저와 매핑된 모든 헬스장 번호들의 정보를 리스트에 담아서 가져옴
	    	// 유저의 찜 목록
	    @GetMapping("/reviewlist/{userSeq}")
	    public ResponseEntity<?> getReviewLikeListByUserSeq(@PathVariable int userSeq) {

	        try {
	            List<Gym> list = gymReviewLikeService.readLikeByUserSeq(userSeq);

	            if(list != null)
	                return new ResponseEntity<List<Gym>>(list, HttpStatus.OK);
	            else
	                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	        } catch(Exception e) {
	            return exceptionHandling(e);
	        }
	    }

	    

	    private ResponseEntity<String> exceptionHandling(Exception e) {

	        e.printStackTrace();

	        return new ResponseEntity<String>("Sorry: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


