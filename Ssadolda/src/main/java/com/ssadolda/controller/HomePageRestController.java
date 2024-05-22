package com.ssadolda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssadolda.service.GymStatisticsService;
import com.ssadolda.service.UserService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "*", methods = {RequestMethod.GET , RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomePageRestController {
	private final GymStatisticsService statisticsservice; 
	private final UserService userservice;
	
	@GetMapping("/gymCount")
	public ResponseEntity<?> gymcount(){
		return new ResponseEntity<Integer>(statisticsservice.gymCount(),HttpStatus.OK);
	}
	
	@GetMapping("/userCount")
	public ResponseEntity<?> usercount(){
		return new ResponseEntity<Integer>(userservice.userCount(),HttpStatus.OK);
	}
	

}
