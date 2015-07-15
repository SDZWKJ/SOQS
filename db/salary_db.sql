/*
MySQL Data Transfer
Source Host: localhost
Source Database: salary_db
Target Host: localhost
Target Database: salary_db
Date: 2015/7/15 1:16:32
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
CREATE TABLE `admin_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_ID` varchar(100) NOT NULL,
  `LOGIN_PASSWORD` varchar(100) DEFAULT NULL,
  `CREATOR` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATER` varchar(100) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
CREATE TABLE `order_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for salary_info
-- ----------------------------
CREATE TABLE `salary_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEACHER_ID` varchar(100) NOT NULL,
  `TEACHER_NAME` varchar(100) DEFAULT NULL,
  `YEAR` varchar(100) DEFAULT NULL,
  `MONTH` varchar(100) DEFAULT NULL,
  `SALARY` varchar(100) DEFAULT NULL,
  `CREATOR` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATER` varchar(100) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
CREATE TABLE `teacher_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEACHER_ID` varchar(100) NOT NULL,
  `QUERY_PASSWORD` varchar(100) DEFAULT NULL,
  `TEACHER_NAME` varchar(100) DEFAULT NULL,
  `CREATOR` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATER` varchar(100) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin_info` VALUES ('1', 'admin', '1234', null, null, null, null);
INSERT INTO `salary_info` VALUES ('132', '1111111111111111111', '李老师', '15', '1', '9000', null, null, null, null);
INSERT INTO `salary_info` VALUES ('133', '1111111111111111111', '李老师', '15', '2', '9001', null, null, null, null);
INSERT INTO `salary_info` VALUES ('134', '1111111111111111111', '李老师', '15', '3', '9002', null, null, null, null);
INSERT INTO `salary_info` VALUES ('135', '1111111111111111111', '李老师', '15', '4', '9003', null, null, null, null);
INSERT INTO `salary_info` VALUES ('136', '1111111111111111111', '李老师', '15', '5', '9004', null, null, null, null);
INSERT INTO `salary_info` VALUES ('137', '3333333333333333333', '王老师', '15', '1', '7000', null, null, null, null);
INSERT INTO `salary_info` VALUES ('138', '3333333333333333333', '王老师', '15', '2', '7001', null, null, null, null);
INSERT INTO `salary_info` VALUES ('139', '3333333333333333333', '王老师', '15', '3', '7002', null, null, null, null);
INSERT INTO `salary_info` VALUES ('140', '3333333333333333333', '王老师', '15', '4', '7003', null, null, null, null);
INSERT INTO `salary_info` VALUES ('141', '3333333333333333333', '王老师', '15', '5', '7004', null, null, null, null);
INSERT INTO `salary_info` VALUES ('142', '3333333333333333333', '王老师', '15', '6', '7005', null, null, null, null);
INSERT INTO `salary_info` VALUES ('143', '5555555555555555555', '刘老师', '15', '1', '8000', null, null, null, null);
INSERT INTO `salary_info` VALUES ('144', '5555555555555555555', '刘老师', '15', '2', '8001', null, null, null, null);
INSERT INTO `salary_info` VALUES ('145', '5555555555555555555', '刘老师', '15', '3', '8002', null, null, null, null);
INSERT INTO `salary_info` VALUES ('146', '5555555555555555555', '刘老师', '15', '4', '8003', null, null, null, null);
INSERT INTO `salary_info` VALUES ('147', '5555555555555555555', '刘老师', '15', '5', '8004', null, null, null, null);
INSERT INTO `salary_info` VALUES ('148', '5555555555555555555', '刘老师', '15', '6', '8005', null, null, null, null);
INSERT INTO `salary_info` VALUES ('149', '5555555555555555555', '刘老师', '15', '7', '8006', null, null, null, null);
INSERT INTO `salary_info` VALUES ('150', '6666666666666666666', '张老师', '15', '1', '6800', null, null, null, null);
INSERT INTO `salary_info` VALUES ('151', '6666666666666666666', '张老师', '15', '2', '6801', null, null, null, null);
INSERT INTO `salary_info` VALUES ('152', '6666666666666666666', '张老师', '15', '3', '6802', null, null, null, null);
INSERT INTO `salary_info` VALUES ('153', '6666666666666666666', '张老师', '15', '4', '6803', null, null, null, null);
INSERT INTO `salary_info` VALUES ('154', '6666666666666666666', '张老师', '15', '5', '6804', null, null, null, null);
INSERT INTO `salary_info` VALUES ('155', '6666666666666666666', '张老师', '15', '6', '6805', null, null, null, null);
INSERT INTO `teacher_info` VALUES ('33', '1111111111111111111', '1234', '李老师', null, null, null, null);
INSERT INTO `teacher_info` VALUES ('34', '3333333333333333333', '3333', '王老师', null, null, null, null);
INSERT INTO `teacher_info` VALUES ('35', '5555555555555555555', '5555', '刘老师', null, null, null, null);
INSERT INTO `teacher_info` VALUES ('36', '6666666666666666666', '6666', '张老师', null, null, null, null);
