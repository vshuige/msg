/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : msg

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-03-22 20:22:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `phonemessagerecord`
-- ----------------------------
DROP TABLE IF EXISTS `phonemessagerecord`;
CREATE TABLE `phonemessagerecord` (
  `serno` varchar(32) NOT NULL DEFAULT '',
  `phonenum` varchar(11) DEFAULT NULL,
  `phonemsg` varchar(6) DEFAULT NULL,
  `createtime` varchar(14) DEFAULT NULL,
  `sendtime` varchar(14) DEFAULT NULL,
  `sendtype` varchar(32) DEFAULT NULL,
  `count` varchar(4) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `checkdate` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`serno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonemessagerecord
-- ----------------------------
INSERT INTO `phonemessagerecord` VALUES ('20170319211528215001259876270083', '15267182670', '342427', '20170319214122', '20170319214122', 'register', '5', '0', '20170319214116');

-- ----------------------------
-- Table structure for `phonemsgtype`
-- ----------------------------
DROP TABLE IF EXISTS `phonemsgtype`;
CREATE TABLE `phonemsgtype` (
  `msgType` varchar(32) NOT NULL DEFAULT '',
  `templetPrefix` varchar(256) DEFAULT NULL,
  `limitTimes` int(11) DEFAULT NULL,
  `limitPeriod` int(11) DEFAULT NULL,
  `minSpace` int(11) DEFAULT NULL,
  `templetSuffix` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`msgType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonemsgtype
-- ----------------------------
INSERT INTO `phonemsgtype` VALUES ('register', '【开源FB注册】您的短信验证码为：', '5', '180', '10', '，请不要泄露出去');
