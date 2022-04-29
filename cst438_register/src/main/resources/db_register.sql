-- script file to create and load tables for registration service.
create schema register;

use register;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `year` int(11) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `course_id` int(11) NOT NULL,
  `section` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `times` varchar(50) DEFAULT NULL,
  `building` varchar(20) DEFAULT NULL,
  `room` varchar(20) DEFAULT NULL,
  `instructor` varchar(50) DEFAULT NULL,
  `start` date DEFAULT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ;

INSERT INTO `course` VALUES 
(2020,'Fall',30157,1,'BUS 203 - Financial Accounting','We 6:00PM - 7:20PM','506','112','cchou@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30163,1,'BUS 306 - Fundamentals of Marketing','Mo 11:00AM - 11:50AM','Library','1180','anariswari@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30291,1,'BUS 304 - Business Communication, Pro-seminar & Critical Thinking','Mo 8:00AM - 9:50AM','506','108','kposteher@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30303,1,'BUS 299 - Business Analytics Fundamentals','Tu 6:00PM - 8:50PM','506','107','bgupta@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30321,1,'BUS 206 - Business Law','Fr 8:00AM - 9:20AM','506','113','jhaselton@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30443,1,'BUS 305 - Principles of Management','Fr 8:00AM - 9:50AM','506','112','jjolley@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30483,1,'BUS 205 - Managerial Accounting','Tu 8:00AM - 9:50AM','506','112','gschneider.csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30607,1,'CST 300 - Major ProSeminar','Fr 10:00AM - 11:50AM','506','110','aattia@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30743,1,'CST 231 - Problm-Solving/Programng','MoWe 4:00PM - 4:50PM','506','117','csatyavolu@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30779,1,'HCOM 225 - Literature,Film & Culture','Mo 8:00PM - 9:15PM','504','1103','qwang@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',30869,1,'BUS 300S - Business Ethics in Action','We 2:00PM - 3:20PM','TBA','','mkimm@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',31045,1,'CST 363 - Introduction to Database Systems','MoWe 4:00PM - 5:50PM','506','104','dwisneski@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',31249,1,'CST 237 - Intro to Computer Architecture','TuTh 2:00PM - 3:50PM','506','104','sislam@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',31253,1,'BUS 307 - Finance','We 2:00PM - 3:50PM','506','112','hwieland@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',31747,1,'CST 238 - Introduction to Data Structures','Mo 2:00PM - 2:50PM','506','117','jgross@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',32041,1,'CST 205 - Multimedia Design and Programming','MoWe 4:00PM - 4:50PM','506','110','abiblarz@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',32045,1,'CST 227 - Design Fundamentals','MoWe 3:00PM - 3:50PM','506','118','aattia@csumb.edu','2020-08-24','2020-12-13'),
(2020,'Fall',32127,1,'HCOM 25 - Writing and Reading Success Workshop','Th 8:00AM - 8:50AM','Student Services','H104','','2020-08-24','2020-12-13'),
(2020,'Fall',32129,2,'HCOM 25 - Writing and Reading Success Workshop','Th 12:00PM - 12:50PM','Student Services','H103','','2020-08-24','2020-12-13'),
(2021,'Fall',40157,1,'BUS 203 - Financial Accounting','We 6:00PM - 7:20PM','506','112','cchou@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40163,1,'BUS 306 - Fundamentals of Marketing','Mo 11:00AM - 11:50AM','Library','1180','anariswari@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40291,1,'BUS 304 - Business Communication, Pro-seminar & Critical Thinking','Mo 8:00AM - 9:50AM','506','108','kposteher@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40303,1,'BUS 299 - Business Analytics Fundamentals','Tu 6:00PM - 8:50PM','506','107','bgupta@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40321,1,'BUS 206 - Business Law','Fr 8:00AM - 9:20AM','506','113','jhaselton@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40443,1,'BUS 305 - Principles of Management','Fr 8:00AM - 9:50AM','506','112','jjolley@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40483,1,'BUS 205 - Managerial Accounting','Tu 8:00AM - 9:50AM','506','112','gschneider.csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40607,1,'CST 300 - Major ProSeminar','Fr 10:00AM - 11:50AM','506','110','aattia@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40743,1,'CST 231 - Problm-Solving/Programng','MoWe 4:00PM - 4:50PM','506','117','csatyavolu@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40779,1,'HCOM 225 - Literature,Film & Culture','Mo 8:00PM - 9:15PM','504','1103','qwang@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',40869,1,'BUS 300S - Business Ethics in Action','We 2:00PM - 3:20PM','TBA','','mkimm@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',41045,1,'CST 363 - Introduction to Database Systems','MoWe 4:00PM - 5:50PM','506','104','dwisneski@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',41249,1,'CST 237 - Intro to Computer Architecture','TuTh 2:00PM - 3:50PM','506','104','sislam@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',41253,1,'BUS 307 - Finance','We 2:00PM - 3:50PM','506','112','hwieland@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',41747,1,'CST 238 - Introduction to Data Structures','Mo 2:00PM - 2:50PM','506','117','jgross@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',42041,1,'CST 205 - Multimedia Design and Programming','MoWe 4:00PM - 4:50PM','506','110','abiblarz@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',42045,1,'CST 227 - Design Fundamentals','MoWe 3:00PM - 3:50PM','506','118','aattia@csumb.edu','2021-08-23','2021-12-17'),
(2021,'Fall',42127,1,'HCOM 25 - Writing and Reading Success Workshop','Th 8:00AM - 8:50AM','Student Services','H104','','2021-08-23','2021-12-17'),
(2021,'Fall',42129,2,'HCOM 25 - Writing and Reading Success Workshop','Th 12:00PM - 12:50PM','Student Services','H103','','2021-08-23','2021-12-17'),
(2021,'Spring',50290,1,'BUS 304 - Business Communication, Pro-seminar & Critical Thinking','Tu 8:00AM - 9:50AM','506','223','kposteher@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50442,1,'BUS 305 - Principles of Management','Fr 2:00PM - 3:50PM','506','112','mkimm@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50484,1,'BUS 205 - Managerial Accounting','We 8:00AM - 9:50AM','506','112','gschneider.csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50542,1,'CST 312 - Network Security','TuTh 6:00PM - 7:50PM','506','110','csatyavolu@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50608,1,'CST 300 - Major ProSeminar','Fr 10:00AM - 11:50AM','506','104','bsu@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50706,1,'CST 325 - Graphics Programming','TuTh 4:00PM - 5:50PM','TBA','','mguerrero@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50878,1,'CST 311 - Intro to Computer Networks','TuTh 2:00PM - 3:50PM','506','110','csatyavolu@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50942,1,'CST 286 - Physics of Computing','MoWe 2:00PM - 3:50PM','506','104','snarayanan@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',50996,1,'CST 383 - Introduction to Data Science','TuTh 10:00AM - 11:50AM','506','110','gbruns@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',51042,1,'CST 346 - Human-Computer Interaction','TuTh 4:00PM - 5:20PM','506','118','kwatson@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',51090,1,'BUS 302 - Bus Milestone Experience','Mo 11:00AM - 11:50AM','506','107','esmith@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',52042,1,'CST 205 - Multimedia Design and Programming','MoWe 5:00PM - 5:50PM','506','110','abiblarz@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',52044,1,'CST 227 - Design Fundamentals','MoWe 2:00PM - 2:50PM','506','118','aattia@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',52128,1,'HCOM 25 - Writing and Reading Success Workshop','TTh 10:00AM - 10:50AM','Library','1173','','2021-01-25','2021-05-16'),
(2021,'Spring',52160,1,'HCOM 224 - American Indigenous Literatures and Cultures','TuTh 2:00PM - 3:15PM','506','111','estromberg@csumb.edu','2021-01-25','2021-05-16'),
(2021,'Spring',54116,1,'CST 336 - Internet Programming','Tu 12:00PM - 1:50PM','TBA','','mlara@csumb.edu','2021-01-25','2021-05-16');


DROP TABLE IF EXISTS `student`; 
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `status_code` int(11) NOT NULL,
  PRIMARY KEY (`student_id`)
)  ;

INSERT INTO `student` VALUES 
(1,'test','test@csumb.edu',NULL,0),
(2,'david','dwisneski@csumb.edu',NULL,0);

DROP TABLE IF EXISTS `enrollment`; 
CREATE TABLE `enrollment` (
  `enrollment_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `semester` char(10) NOT NULL,
  `course_id` int(11) NOT NULL,
  `course_grade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enrollment_id`),
  KEY `FKbhhcqkw1px6yljqg92m0sh2gt` (`course_id`),
  KEY `FKio7fsy3vhvfgv7c0gjk15nyk4` (`student_id`),
  CONSTRAINT `FKbhhcqkw1px6yljqg92m0sh2gt` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FKio7fsy3vhvfgv7c0gjk15nyk4` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
)  ;
