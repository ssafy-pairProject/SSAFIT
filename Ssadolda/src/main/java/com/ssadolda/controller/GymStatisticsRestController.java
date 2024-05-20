package com.ssadolda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssadolda.model.dto.GymStatistics;
import com.ssadolda.service.GymStatisticsService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

@RestController
@RequiredArgsConstructor
@RequestMapping("/gymstatistics")
public class GymStatisticsRestController {
	
	private final GymStatisticsService statisticsservice; 
	
	// 헬스장의 총 조회수,리뷰수,좋아요수,평점평균 가져오기
	@GetMapping("/{gymSeq}")
	public ResponseEntity<?> getinfo(@PathVariable int gymSeq){
		GymStatistics gyminfo = statisticsservice.getGymStatistics(gymSeq);
		
		if(gyminfo!=null) {
			return new ResponseEntity<GymStatistics>(gyminfo, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

}
