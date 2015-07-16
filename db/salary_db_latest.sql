/*
MySQL Data Transfer
Source Host: localhost
Source Database: salary_db
Target Host: localhost
Target Database: salary_db
Date: 2015/7/16 17:04:07
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
  `EMP_ID` varchar(100) DEFAULT NULL,
  `TEACHER_NAME` varchar(100) DEFAULT NULL,
  `YF_SALARY` varchar(100) DEFAULT NULL,
  `SF_SALARY` varchar(100) DEFAULT NULL,
  `JC_SALARY` varchar(100) DEFAULT NULL,
  `GW_SALARY` varchar(100) DEFAULT NULL,
  `XJ_SALARY` varchar(100) DEFAULT NULL,
  `GL_SALARY` varchar(100) DEFAULT NULL,
  `TG_SALARY` varchar(100) DEFAULT NULL,
  `JT_SALARY` varchar(100) DEFAULT NULL,
  `QTJB_SALARY` varchar(100) DEFAULT NULL,
  `SYDWJTBTHJ_ALLOWANCE` varchar(100) DEFAULT NULL,
  `ZWBT_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `TGJT_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `JXJT_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `JHLJT_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `BZR_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `GGXBT_WYBT_ALLOWANCE` varchar(100) DEFAULT NULL,
  `QTBZ_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `KP_ AWARD` varchar(100) DEFAULT NULL,
  `QT_SALARY` varchar(100) DEFAULT NULL,
  `DSZN_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `BFGZYF_SALARY` varchar(100) DEFAULT NULL,
  `ZF_ ALLOWANCE` varchar(100) DEFAULT NULL,
  `KF_TOTAL` varchar(100) DEFAULT NULL,
  `KGJJ_MONEY` varchar(100) DEFAULT NULL,
  `KYALBX_MONEY` varchar(100) DEFAULT NULL,
  `KYILBX_MONEY` varchar(100) DEFAULT NULL,
  `DBBZJ_MONEY` varchar(100) DEFAULT NULL,
  `IIT_MONEY` varchar(100) DEFAULT NULL,
  `KIIT_MONEY` varchar(100) DEFAULT NULL,
  `YEAR` varchar(50) DEFAULT NULL,
  `MONTH` varchar(50) DEFAULT NULL,
  `DATE_SALARY` datetime DEFAULT NULL,
  `SFJS_TAX` varchar(100) DEFAULT NULL,
  `CREATOR` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATER` varchar(100) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
CREATE TABLE `teacher_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMP_ID` varchar(100) DEFAULT NULL,
  `TEACHER_ID` varchar(100) DEFAULT NULL,
  `QUERY_PASSWORD` varchar(100) DEFAULT NULL,
  `TEACHER_NAME` varchar(100) DEFAULT NULL,
  `CREATOR` varchar(100) DEFAULT NULL,
  `CREATED_DATE` datetime DEFAULT NULL,
  `UPDATER` varchar(100) DEFAULT NULL,
  `LAST_UPDATED_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin_info` VALUES ('1', 'admin', '1234', null, null, null, null);
INSERT INTO `order_info` VALUES ('1', '502164725', '2015-07-07 15:51:07');
