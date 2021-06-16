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

 Date: 31/01/2021 14:19:37
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of jmxlog_namenode_fsnamesystem
-- ----------------------------
BEGIN;
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (1, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (2, 0, 0, 265, 265, 1611387355061, 62725623808, 848900096, 47473786880, 14402936832, 2, 0, 992, 0, 0, '2021-01-24 10:06:37');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (3, 0, 0, 814, 814, 1611394557601, 62725623808, 848900096, 47471775744, 14404947968, 2, 0, 992, 0, 0, '2021-01-24 10:06:37');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (4, 0, 0, 265, 265, 1611387355061, 62725623808, 848900096, 47473786880, 14402936832, 2, 0, 992, 0, 0, '2021-01-24 10:44:09');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (5, 0, 0, 814, 814, 1611394557601, 62725623808, 848900096, 47471775744, 14404947968, 2, 0, 992, 0, 0, '2021-01-24 10:44:09');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (6, 0, 0, 265, 265, 1611387355061, 62725623808, 848900096, 47473786880, 14402936832, 2, 0, 992, 0, 0, '2021-01-24 10:54:17');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (7, 0, 0, 814, 814, 1611394557601, 62725623808, 848900096, 47471775744, 14404947968, 2, 0, 992, 0, 0, '2021-01-24 10:54:17');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (8, 0, 0, 265, 265, 1611387355061, 62725623808, 848900096, 47473786880, 14402936832, 2, 0, 992, 0, 0, '2021-01-24 12:05:53');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (9, 0, 0, 814, 814, 1611394557601, 62725623808, 848900096, 47471775744, 14404947968, 2, 0, 992, 0, 0, '2021-01-24 12:05:53');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (10, 0, 0, 265, 265, 1611387355061, 62725623808, 848900096, 47473786880, 14402936832, 2, 0, 992, 0, 0, '2021-01-24 12:10:30');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (11, 0, 0, 814, 814, 1611394557601, 62725623808, 848900096, 47471775744, 14404947968, 2, 0, 992, 0, 0, '2021-01-24 12:10:30');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (12, 0, 0, 265, 265, 1611387355061, 62725623808, 848900096, 47473786880, 14402936832, 2, 0, 992, 0, 0, '2021-01-24 12:14:12');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (13, 0, 0, 814, 814, 1611394557601, 62725623808, 848900096, 47471775744, 14404947968, 2, 0, 992, 0, 0, '2021-01-24 12:14:12');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (14, 0, 0, 478, 478, 1611465381331, 62725623808, 848900096, 47177212070, 14699511642, 6, 0, 992, 0, 0, '2021-01-24 13:41:27');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (15, 0, 0, 484, 484, 1611465381331, 62725623808, 848900096, 47177207974, 14699515738, 6, 0, 992, 0, 0, '2021-01-24 13:41:45');
INSERT INTO `jmxlog_namenode_fsnamesystem` VALUES (16, 0, 0, 92, 92, 1611468983341, 62725623808, 848900300, 47176491174, 14700232334, 6, 0, 994, 0, 0, '2021-01-24 14:20:34');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of jmxlog_namenode_jvmmetrics
-- ----------------------------
BEGIN;
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (1, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (2, 32.06373, 130, 94.262886, 889, 889, 5, 0, 9, 0, 3, 36, 0, 0, 0, 678, 8, '2021-01-24 10:06:37');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (3, 32.439545, 130, 144.10568, 889, 889, 6, 0, 9, 0, 3, 36, 0, 0, 0, 2372, 10, '2021-01-24 10:06:37');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (4, 32.06373, 130, 94.262886, 889, 889, 5, 0, 9, 0, 3, 36, 0, 0, 0, 678, 8, '2021-01-24 10:44:09');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (5, 32.439545, 130, 144.10568, 889, 889, 6, 0, 9, 0, 3, 36, 0, 0, 0, 2372, 10, '2021-01-24 10:44:09');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (6, 32.06373, 130, 94.262886, 889, 889, 5, 0, 9, 0, 3, 36, 0, 0, 0, 678, 8, '2021-01-24 10:54:17');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (7, 32.439545, 130, 144.10568, 889, 889, 6, 0, 9, 0, 3, 36, 0, 0, 0, 2372, 10, '2021-01-24 10:54:17');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (8, 32.06373, 130, 94.262886, 889, 889, 5, 0, 9, 0, 3, 36, 0, 0, 0, 678, 8, '2021-01-24 12:05:53');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (9, 32.439545, 130, 144.10568, 889, 889, 6, 0, 9, 0, 3, 36, 0, 0, 0, 2372, 10, '2021-01-24 12:05:53');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (10, 32.06373, 130, 94.262886, 889, 889, 5, 0, 9, 0, 3, 36, 0, 0, 0, 678, 8, '2021-01-24 12:10:30');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (11, 32.439545, 130, 144.10568, 889, 889, 6, 0, 9, 0, 3, 36, 0, 0, 0, 2372, 10, '2021-01-24 12:10:30');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (12, 32.06373, 130, 94.262886, 889, 889, 5, 0, 9, 0, 3, 36, 0, 0, 0, 678, 8, '2021-01-24 12:14:12');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (13, 32.439545, 130, 144.10568, 889, 889, 6, 0, 9, 0, 3, 36, 0, 0, 0, 2372, 10, '2021-01-24 12:14:12');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (14, 33.63556, 130, 52.174957, 889, 889, 7, 0, 9, 0, 3, 36, 0, 0, 0, 3157, 9, '2021-01-24 13:41:27');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (15, 33.63556, 130, 52.37789, 889, 889, 7, 0, 9, 0, 3, 36, 0, 0, 0, 3161, 9, '2021-01-24 13:41:45');
INSERT INTO `jmxlog_namenode_jvmmetrics` VALUES (16, 33.64425, 130, 102.53388, 889, 889, 7, 0, 9, 0, 3, 36, 0, 0, 0, 3609, 9, '2021-01-24 14:20:34');
COMMIT;

-- ----------------------------
-- Table structure for log_test
-- ----------------------------
DROP TABLE IF EXISTS `log_test`;
CREATE TABLE `log_test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of log_test
-- ----------------------------
BEGIN;
INSERT INTO `log_test` VALUES (2, 'guojing');
INSERT INTO `log_test` VALUES (33, 'guojing');
INSERT INTO `log_test` VALUES (34, 'guojing');
INSERT INTO `log_test` VALUES (35, 'guojing');
INSERT INTO `log_test` VALUES (36, 'guojing');
INSERT INTO `log_test` VALUES (37, 'guojing');
INSERT INTO `log_test` VALUES (38, 'guojing');
INSERT INTO `log_test` VALUES (39, 'guojing');
INSERT INTO `log_test` VALUES (40, 'guojing');
COMMIT;

-- ----------------------------
-- Table structure for sys_permissions
-- ----------------------------
DROP TABLE IF EXISTS `sys_permissions`;
CREATE TABLE `sys_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionsName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `valid` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'ac7c80f2d174c311a1dbed18c8cbff9c', 'efb5cbbd988ab9f46258a100a77a0fbc', 1, '2021-01-26 19:38:05', '2021-01-26 19:38:10');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
