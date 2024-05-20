package com.ssadolda.model.dto;

import lombok.Data;

@Data
public class GymReviewComment {
	// 대댓글 기능 미구현
    private int commentSeq;
    private int reviewSeq;
    private int userSeq;
    private String comment;
    private String commentRegDate;

}
