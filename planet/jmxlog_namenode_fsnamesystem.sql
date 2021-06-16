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

 Date: 23/01/2021 22:29:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jmxlog_namenode_fsnamesystem
-- ----------------------------
DROP TABLE IF EXISTS `jmxlog_namenode_fsnamesystem`;
CREATE TABLE `jmxlog_namenode_fsnamesystem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `missingBlocks` double DEFAULT NULL,
  `expiredHeartbeats` double DEFAULT NULL,
  `transactionsSinceLastCheckpoint` double DEFAULT NULL,
  `transactionsSinceLastLogRoll` double DEFAULT NULL,
  `lastCheckpointTime` double DEFAULT NULL,
  `capacityTotal` double DEFAULT NULL,
  `capacityUsed` double DEFAULT NULL,
  `capacityRemaining` double DEFAULT NULL,
  `capacityUsedNonDFS` double DEFAULT NULL,
  `totalLoad` double DEFAULT NULL,
  `snapshottableDirectories` double DEFAULT NULL,
  `filesTotal` double DEFAULT NULL,
  `pendingDataNodeMessageCount` double DEFAULT NULL,
  `staleDataNodes` double DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of jmxlog_namenode_fsnamesystem
-- ----------------------------
BEGIN;
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (1, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
