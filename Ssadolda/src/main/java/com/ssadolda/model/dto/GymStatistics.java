package com.ssadolda.model.dto;

import lombok.Data;

@Data
public class GymStatistics {
	    private int gymSeq;
	    private int totalViewCount; //조회수
	    private double averageRating;
		private int reviewCount; //리뷰 갯수
		private int totalLikeCount;
}
