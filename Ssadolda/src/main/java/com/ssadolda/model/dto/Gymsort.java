package com.ssadolda.model.dto;

import lombok.Data;

@Data
public class Gymsort {
    private int gymSeq;
    private String gymName;
    private String gymAddress;
    private String gymPhoneNumber;
    private String gymPrice;
    private String gymIntroduce;
    private String gymImg;
    private String gymOperatingHours;
    private String gymAmenities;
    private String gymAdditionalService;
    private int userLikeCnt;
    private int gymviews;

    // 추가 필드 (gymStatistics와 JOIN된 데이터)
    private int totalViewCount;
    private double averageRating;
    private int reviewCount;
    private int totalLikeCount;

    // getters and setters
}