/*
 Navicat Premium Data Transfer

 Source Server         : docker-3306
 Source Server Type    : MySQL
 Source Server Version : 50173
 Source Host           : 127.0.0.1:3306
 Source Schema         : planet

 Target Server Type    : MySQL
 Target Server Version : 50173
 File Encoding         : 65001

 Date: 23/01/2021 22:30:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jmxlog_namenode_jvmmetrics
-- ----------------------------
DROP TABLE IF EXISTS `jmxlog_namenode_jvmmetrics`;
CREATE TABLE `jmxlog_namenode_jvmmetrics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `memNonHeapUsedM` double DEFAULT NULL,
  `memNonHeapMaxM` double DEFAULT NULL,
  `memHeapUsedM` double DEFAULT NULL,
  `memHeapMaxM` double DEFAULT NULL,
  `memMaxM` double DEFAULT NULL,
  `gcCount` double DEFAULT NULL,
  `threadsNew` double DEFAULT NULL,
  `threadsRunnable` double DEFAULT NULL,
  `threadsBlocked` double DEFAULT NULL,
  `threadsWaiting` double DEFAULT NULL,
  `threadsTimedWaiting` double DEFAULT NULL,
  `threadsTerminated` double DEFAULT NULL,
  `logFatal` double DEFAULT NULL,
  `logError` double DEFAULT NULL,
  `logWarn` double DEFAULT NULL,
  `logInfo` double DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of jmxlog_namenode_jvmmetrics
-- ----------------------------
BEGIN;
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (1, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
