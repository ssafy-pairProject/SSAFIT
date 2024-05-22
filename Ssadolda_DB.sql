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

INSERT INTO gyms (gymName, gymAddress, gymPhoneNumber, gymPrice, gymIntroduce, gymImg, gymOperatingHours, gymAmenities, gymAdditionalService, userLikeCnt, gymviews)
VALUES
('헬스킹짐', '서울특별시 강남구 테헤란로 123', '010-1234-5678', '50000', '최고의 시설과 트레이너가 함께하는 헬스킹짐입니다.', 'healthking.jpg', '6:00 - 22:00', '수영장, 사우나, 요가룸', '퍼스널 트레이닝, 영양 상담', 10, 100),
('헬스팰리스', '서울특별시 송파구 올림픽로 456', '010-2345-6789', '45000', '건강한 라이프스타일을 위한 헬스팰리스에 오신 것을 환영합니다.', 'healthpalace.jpg', '5:00 - 23:00', '헬스기구, 유산소, 단체 수업', '마사지 테라피, 식단 관리', 20, 200),
('파워짐', '서울특별시 서초구 서초대로 789', '010-3456-7890', '60000', '최고급 시설과 트레이너가 있는 파워짐입니다.', 'powergym.jpg', '24시간', '런닝머신, 프리웨이트, 크로스핏', '단체 수업, 물리치료', 30, 300),
('얼티밋헬스', '서울특별시 종로구 종로 1010', '010-4567-8901', '55000', '궁극의 헬스 경험을 제공하는 얼티밋헬스입니다.', 'ultimatehealth.jpg', '5:00 - 24:00', '헬스기구, 프리웨이트, 수영장', '스포츠 의학, 개인 코칭', 15, 150),
('피크퍼포먼스', '서울특별시 강동구 천호대로 1111', '010-5678-9012', '70000', '전문 트레이너와 함께하는 피크퍼포먼스입니다.', 'peakperformance.jpg', '6:00 - 22:00', '에어로빅, 웨이트 리프팅, 수영', '보충제 판매, 웰니스 프로그램', 25, 250),
('아이언파라다이스', '서울특별시 마포구 마포대로 1212', '010-6789-0123', '65000', '진지한 보디빌더를 위한 아이언파라다이스입니다.', 'ironparadise.jpg', '24시간', '중량 운동, 유산소, 단체 수업', '근력 훈련, 근육 증가 프로그램', 5, 50),
('파워하우스', '서울특별시 용산구 한강대로 1313', '010-7890-1234', '48000', '파워하우스: 당신의 피트니스 여정을 함께 합니다.', 'powerhouse.jpg', '5:00 - 23:00', '헬스기구, 단체 수업, 사우나', '자세 교정, 피트니스 평가', 8, 80),
('피트니스팩토리', '서울특별시 영등포구 여의대로 1414', '010-8901-2345', '53000', '피트니스팩토리: 운동과 재미를 동시에.', 'fitnessfactory.jpg', '6:00 - 22:00', '크로스핏, 요가, 유산소', '댄스 수업, 그룹 운동', 18, 180),
('더짐', '서울특별시 중구 을지로 1515', '010-9012-3456', '47000', '더짐에서 다양한 피트니스 옵션을 제공받으세요.', 'thegym.jpg', '24시간', '런닝머신, 자전거, 프리웨이트', '개인 코칭, 온라인 수업', 12, 120),
('플렉스존', '서울특별시 동작구 상도로 1616', '010-0123-4567', '52000', '플렉스존: 당신의 근육과 일정에 맞추세요.', 'flexzone.jpg', '5:00 - 24:00', '웨이트, 헬스기구, 단체 수업', '피트니스 챌린지, 웰니스 코칭', 22, 220);
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







 
