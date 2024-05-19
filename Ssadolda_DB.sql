DROP DATABASE IF EXISTS `ssadolda_db`;

CREATE DATABASE `ssadolda_db` DEFAULT CHARACTER SET utf8mb4;

use ssadolda_db;
drop table users;

create table users(
`userId` varchar(12) unique,
`password` varchar(20) not null,
`email` varchar(50) not null,
`phoneNumber` varchar(15) not null,
`name` varchar(10) not null,
`nickname` varchar(20) not null,
`img` varchar(200) ,
`orgimg` varchar(200),
`userRole` varchar(20) default 'role_user',
`userSeq` int AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE gyms(
`gymName` varchar(20) not null,
`gymAddress` varchar(50) not null,
`gymPhoneNumber` varchar(15) ,
`gymPrice` varchar(10),
`gymIntroduce` TEXT,
`gymImg` varchar(200),
`gymOperatingHours` varchar(100),
`gymAmenities` varchar(300),
`gymAdditionalService` varchar(300),
`userLikeCnt` int default 0,
`gymviews` int	default 0,
`gymSeq` INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE gymlike (
  `userSeq` INT NOT NULL,
  `gymSeq` INT NOT NULL,
  `likeSeq` INT AUTO_INCREMENT PRIMARY KEY ,

  FOREIGN KEY (`userSeq`) REFERENCES `users`(`userSeq`) ON DELETE CASCADE,
  FOREIGN KEY (`gymSeq`) REFERENCES `gyms`(`gymSeq`) ON DELETE CASCADE
);

CREATE TABLE gymreviews (
  `reviewSeq` INT AUTO_INCREMENT PRIMARY KEY,
  `userSeq` INT NOT NULL,
  `gymSeq` INT NOT NULL,
  `reviewDate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `rating` INT NOT NULL,
  `comment` TEXT,
  FOREIGN KEY (`userSeq`) REFERENCES `users`(`userSeq`) ON DELETE CASCADE,
  FOREIGN KEY (`gymSeq`) REFERENCES `gyms`(`gymSeq`) ON DELETE CASCADE,
  CHECK (`rating` >= 1 AND `rating` <= 5), -- 평점 범위 체크
  INDEX `idx_userSeq` (`userSeq`),
  INDEX `idx_gymSeq` (`gymSeq`)
);	

CREATE TABLE gymStatistics (
    gymSeq INT NOT NULL,
    totalViewCount INT DEFAULT 0,
    averageRating DOUBLE DEFAULT 0,
    reviewCount INT DEFAULT 0,
    totalLikeCount INT DEFAULT 0,
    PRIMARY KEY (gymSeq),
    FOREIGN KEY (gymSeq) REFERENCES gyms(gymSeq) ON DELETE CASCADE
);
  
  



 
