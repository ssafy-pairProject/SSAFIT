DROP DATABASE IF EXISTS `ssadolda_db`;

CREATE DATABASE `ssadolda_db` DEFAULT CHARACTER SET utf8mb4;

use ssadolda_db;


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

INSERT INTO users (userId,`password`,email,phoneNumber,name,nickname)
values ('ssafy1',1234,'ssafy1@naver.com','010-1111-1111','일싸피','로이더');


INSERT INTO gyms(gymName,gymAddress,gymPhoneNumber,gymPrice,gymIntroduce,gymOperatingHours,gymAmenities,gymAdditionalService)
VALUES('M휘트니스','유성구 봉명동','010-1234-5678',70000,'봉명에서 제일큰 헬스장!','24시간 영업','필라테스기구','wqe');
INSERT INTO gymStatistics (gymSeq)
VALUES(1);

INSERT INTO gyms(gymName,gymAddress,gymPhoneNumber,gymPrice,gymIntroduce,gymOperatingHours,gymAmenities,gymAdditionalService)
VALUES('투모어 휘트니스','유성구 학하로','0507-1358-3050',40000,'한밭대 최고 헬스장 무조건 빠져요!','06시30분~18시','PT전문','운동복 제공, 주차비무료');
INSERT INTO gymStatistics (gymSeq)
VALUES(2);

select *
from users;

select *
from gymlike;

select *
from gymreviews;

select *
from gymStatistics;







 
