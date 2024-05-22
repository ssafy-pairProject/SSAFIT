package com.ssadolda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssadolda.model.dto.GymReview;
import com.ssadolda.service.GymReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins="*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/gymreview")
@RequiredArgsConstructor
public class GymReviewController {
	private final GymReviewService gymReviewService;
	
	// 리뷰 등록 ->평점 DB에 더해주는 것 서비스에서 실행
//	@PostMapping("/regist")
//	public ResponseEntity<?> registReview(@RequestBody GymReview review){
//		int result = gymReviewService.registGymReview(review);
//		if(result!=0) {
//			return new ResponseEntity<String>("리뷰 등록에 성공",HttpStatus.CREATED);
//		}
//		else {
//			return new ResponseEntity<String>("리뷰 등록에 실패",HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@PostMapping("/regist")
	public ResponseEntity<?> registReview(@RequestBody GymReview review) {
		System.out.println(review);
	    try {
	        int result = gymReviewService.registGymReview(review);
	        if (result != 0) {
	            return new ResponseEntity<>("리뷰 등록에 성공", HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>("리뷰 등록에 실패", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	//리뷰 전체 조회 
//	@GetMapping("/{gymSeq}")
//	public ResponseEntity<?> readAllReview(@PathVariable int gymSeq){
//		List<GymReview> list = gymReviewService.readAllGymReview(gymSeq);
//		if(list!=null)
//		return new ResponseEntity<List<GymReview>>(list,HttpStatus.OK);
//		else {
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//	}
	
	@GetMapping("/{gymSeq}")
	public ResponseEntity<?> readAllReview(@PathVariable int gymSeq) {
	    try {
	        List<GymReview> list = gymReviewService.readAllGymReview(gymSeq);
	        if (list != null) {
	            return new ResponseEntity<>(list, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	// 리뷰 삭제
//	@DeleteMapping("/{reviewSeq}")
//	public ResponseEntity<?> deleteReview(@PathVariable int reviewSeq){
//		int result = gymReviewService.deleteGymReview(reviewSeq);
//		
//		if(result!=0)
//			return new ResponseEntity<String>("삭제에 성공했습니다", HttpStatus.OK);
//		else {
//			return new ResponseEntity<String>("삭제에 실패했습니다", HttpStatus.BAD_REQUEST);
//		}
//	}
	@DeleteMapping("/{reviewSeq}")
	public ResponseEntity<?> deleteReview(@PathVariable int reviewSeq) {
	    try {
	        int result = gymReviewService.deleteGymReview(reviewSeq);
	        if (result != 0) {
	            return new ResponseEntity<>("삭제에 성공했습니다", HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("삭제에 실패했습니다", HttpStatus.BAD_REQUEST);
	        }
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	

}
