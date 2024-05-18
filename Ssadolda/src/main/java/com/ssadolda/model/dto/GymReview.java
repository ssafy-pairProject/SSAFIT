package com.ssadolda.model.dto;

import lombok.Data;

@Data
public class GymReview {
//	  `reviewSeq` INT AUTO_INCREMENT PRIMARY KEY,
//	  `userSeq` INT NOT NULL,
//	  `gymSeq` INT NOT NULL,
//	  `reviewDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
//	  `rating` INT NOT NULL,
//	  `comment
	private int reviewSeq;
	private int userSeq;
	private int gymSeq;
	private String reviewDate;
	private int rating;
	private String comment;

}
