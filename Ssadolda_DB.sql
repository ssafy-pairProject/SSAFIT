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
`userSeq` int AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE gyms(
`gymName` varchar(20) not null,
`gymAddress` varchar(50) not null,
`gymPhoneNumber` varchar(15) ,
`gymPrice` varchar(10),
`gymIntroduce` varchar(1000),
`gymImg` varchar(200),
`gymOperatingHours` varchar(100),
`gymAmenities` varchar(300),
`gymAdditionalService` varchar(300),
`gymSeq` INT AUTO_INCREMENT PRIMARY KEY
);


    select *
    from gyms;
    
