/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : vue-system

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 19/07/2024 17:20:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `tpl_web_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '前端模板类型（element-ui模版 element-plus模版）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `orm_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '持久层框架(0-mybatis,1-mybatis-plus)',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 194 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('VueSystemScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (6, 'oss云存储-是否开启云存储', 'sys.oss.enable', 'true', 'Y', 'admin', '2022-07-27 14:52:18', 'admin', '2022-07-29 15:02:59', '开启云存储需要配置application.yml里的oss相关参数（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (7, '是否开启邮箱通知', 'sys_email_enable', 'true', 'Y', 'admin', '2022-09-20 14:45:07', 'admin', '2022-09-23 22:16:59', '后台回复留言，邮箱通知留言者,开启需要配置email相关参数 （true开启，false关闭）');
INSERT INTO `sys_config` VALUES (8, 'RabbitMQ开关', 'sys_rabbit_enable', 'false', 'Y', 'admin', '2023-01-16 16:41:31', 'admin', '2024-04-24 09:24:45', '开启-true，关闭-false（开启需要配置RabbitMQ相关参数,目前仅发送邮件用到该中间件）');

-- ----------------------------
-- Table structure for sys_count
-- ----------------------------
DROP TABLE IF EXISTS `sys_count`;
CREATE TABLE `sys_count`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `article_count` bigint(20) NULL DEFAULT NULL COMMENT '文章总数',
  `summary_count` bigint(20) NULL DEFAULT NULL COMMENT '归档总数',
  `user_count` bigint(20) NULL DEFAULT NULL COMMENT '用户总数',
  `click_count` bigint(20) NULL DEFAULT NULL COMMENT '点击总数',
  `like_count` bigint(20) NULL DEFAULT NULL COMMENT '点赞总数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `click_day_count` bigint(20) NULL DEFAULT NULL COMMENT '日点击数',
  `like_day_count` bigint(20) NULL DEFAULT NULL COMMENT '日点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '统计归档' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_count
-- ----------------------------
INSERT INTO `sys_count` VALUES ('027b1d86845487e74f31e867c1adebd7', 50, 6, 0, 81, 1, '2024-03-31 12:00:01', 7, 0);
INSERT INTO `sys_count` VALUES ('039bb3e019bcb78914f3e1ec9d697d99', 43, 6, 0, 14, 1, '2024-02-21 12:00:00', 2, 0);
INSERT INTO `sys_count` VALUES ('047d7e0285061cbd2d033e887ecbd47a', 47, 6, 0, 19, 1, '2024-03-02 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('04df3091a3027a843bbbbcd996c9f2b7', 48, 6, 0, 37, 1, '2024-03-12 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('054144995d385c426a561d13d3895db6', 49, 6, 0, 47, 1, '2024-03-21 12:00:00', 3, 0);
INSERT INTO `sys_count` VALUES ('05d605e14d449d6edfce1ece4d1f18d5', 50, 6, 0, 81, 1, '2024-04-01 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('0cbda4b8906ce02f8cf813da62605e75', 47, 6, 0, 28, 1, '2024-03-05 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('0cc85fb8c5e686440b1b21c6df97111b', 46, 6, 0, 108, 2, '2024-04-26 12:00:00', 4, 0);
INSERT INTO `sys_count` VALUES ('0d140da4e8aad22a2cd45c3c9cae18b1', 47, 6, 0, 19, 1, '2024-03-01 12:00:00', 4, 0);
INSERT INTO `sys_count` VALUES ('0d65f7330fdff1b9030e0456e1a336d0', 43, 6, 0, 12, 1, '2024-02-17 12:00:01', 2, 0);
INSERT INTO `sys_count` VALUES ('0f2d8d59088ddd06308b77edc564ff25', 59, 6, 0, 122, 2, '2024-05-03 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('11af78fde8646cf1d36d45f8f8911a0b', 46, 6, 0, 109, 2, '2024-04-28 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('1285a38c0b7420a9c7782fa8e1509edb', 59, 6, 0, 136, 2, '2024-05-08 12:00:00', 10, 0);
INSERT INTO `sys_count` VALUES ('167449024ef1d1ec0813f653b10c13c0', 46, 6, 0, 94, 1, '2024-04-16 12:00:00', 3, 0);
INSERT INTO `sys_count` VALUES ('193a75fcb477af624fb3b6cce4efec38', 48, 6, 0, 38, 1, '2024-03-15 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('19eceaf075163e1759a0234306517bc3', 58, 6, 0, 207, 2, '2024-07-05 15:01:16', 207, 2);
INSERT INTO `sys_count` VALUES ('1d108ab8cc01964d8fd037585b9bd9fc', 50, 6, 0, 68, 1, '2024-03-28 12:00:03', 4, 0);
INSERT INTO `sys_count` VALUES ('1f6c5f13f443ae57e532a5b38c790cac', 59, 6, 0, 122, 2, '2024-05-04 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('25cf34367f94d1a648dfc1756e43ebe2', 43, 6, 0, 2, 0, '2024-02-07 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('2a1df35d01f80fd2638830c777dbf45b', 58, 6, 0, 147, 2, '2024-05-16 15:58:25', 147, 2);
INSERT INTO `sys_count` VALUES ('2e0a8c7f618f6f8b99daaa1ec0d9e9b9', 48, 6, 0, 37, 1, '2024-03-11 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('2fe03a1a86dd36f0da77b4aca7e43781', 48, 6, 0, 36, 1, '2024-03-10 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('321ac4ecb324dbb42ae8b205373a3a13', 45, 6, 0, 89, 1, '2024-04-13 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('3240bdb4961013a3e4b702791b0370f5', 59, 6, 0, 126, 2, '2024-05-07 12:00:00', 4, 0);
INSERT INTO `sys_count` VALUES ('332f5a47a871bbc77b8f2b8345af7acc', 50, 6, 0, 64, 1, '2024-03-26 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('355f4326cb7d1eb48134384da923304d', 47, 6, 0, 28, 1, '2024-03-04 12:00:00', 9, 0);
INSERT INTO `sys_count` VALUES ('373ec9a8312e307a4203721caf69c27b', 43, 6, 0, 4, 0, '2024-02-11 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('390ec74392db568cff0198474cdbbb9a', 48, 6, 0, 38, 1, '2024-03-13 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('395f63bf0a44c33aedc786128fd12481', 50, 6, 0, 81, 1, '2024-04-05 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('3b3c5e3dcc953af43cc28a41e4500de5', 46, 6, 0, 98, 2, '2024-04-21 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('3b5cb60f90c690cba951e7f6b6b1cb58', 58, 6, 0, 207, 2, '2024-07-03 16:53:18', 207, 2);
INSERT INTO `sys_count` VALUES ('3c0551677c2756df9b826ed1826401c3', 45, 6, 0, 14, 1, '2024-02-23 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('3ccf9b2f961dbb92a702b85c684d88d0', 49, 6, 0, 50, 1, '2024-03-22 12:00:02', 3, 0);
INSERT INTO `sys_count` VALUES ('3f7f280e0b12241dc9068489e7bdd3f0', 48, 6, 0, 38, 1, '2024-03-16 12:00:02', 0, 0);
INSERT INTO `sys_count` VALUES ('436cc344ff2e9cc38fc17500ee8e8226', 43, 6, 0, 4, 0, '2024-02-08 12:00:01', 2, 0);
INSERT INTO `sys_count` VALUES ('43b27e89f66c2da10b7886a34536ce34', 48, 6, 0, 38, 1, '2024-03-14 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('4907e12f7700cebab06b0324f8fb4dae', 45, 6, 0, 89, 1, '2024-04-14 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('4ce193fcca635b6eb1a0f372b093f151', 45, 6, 0, 14, 1, '2024-02-24 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('4d8826d3539e10ff3fab2d501b9db804', 58, 6, 0, 147, 2, '2024-05-17 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('4de8a244f09674aef26d0e09383d12de', 46, 6, 0, 14, 1, '2024-02-27 12:00:04', 0, 0);
INSERT INTO `sys_count` VALUES ('4ea12a6d11eef4692e53f00f248caf9c', 45, 6, 0, 14, 1, '2024-02-26 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('4f30d43496d0bf7e9c8151d9d3d3763a', 45, 6, 0, 91, 1, '2024-04-15 12:00:00', 2, 0);
INSERT INTO `sys_count` VALUES ('56ec6381b8ae8769a744120d30a7f663', 43, 6, 0, 4, 0, '2024-02-09 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('57bb0726dd4f3c8bf02c81b7acdc19c2', 45, 6, 0, 88, 1, '2024-04-11 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('59bdd67d6171cbeaab685d05cb7a819d', 43, 6, 0, 4, 0, '2024-02-10 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('60419b45936b83cde97986d9592749a8', 43, 6, 0, 7, 1, '2024-02-14 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('648c06cfb98d4629410403f268b7b731', 50, 6, 0, 74, 1, '2024-03-30 12:00:02', 0, 0);
INSERT INTO `sys_count` VALUES ('64b3a134b5ec2003f647f2e992c0ca69', 59, 6, 0, 122, 2, '2024-05-06 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('653e99a181c69f838caf9bfdd3446d04', 59, 6, 0, 120, 2, '2024-04-29 12:00:00', 11, 0);
INSERT INTO `sys_count` VALUES ('6718f5caefbe22f56a5e3c35fad5bb1a', 48, 6, 0, 44, 1, '2024-03-20 12:00:00', 4, 0);
INSERT INTO `sys_count` VALUES ('68fb9c14fca073f9d22ffea7abe7fe33', 46, 6, 0, 99, 2, '2024-04-24 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('69ff14af180220327be8f38e11eeb051', 59, 6, 0, 122, 2, '2024-05-02 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('6a9dd63743e7adf7e97a9d4c4b600482', 46, 6, 0, 109, 2, '2024-04-27 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('6bd0f9137682f71395c4fc3322a30a4c', 50, 6, 0, 81, 1, '2024-04-04 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('6cb42a70d1716e26110a42bb0536c32d', 50, 6, 0, 81, 1, '2024-04-03 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('6d5100f5a9c6a8120d9c6a1d27a05f40', 43, 6, 0, 7, 1, '2024-02-13 12:00:00', 3, 1);
INSERT INTO `sys_count` VALUES ('70b759ee007c9869531b09b18da641aa', 47, 6, 0, 15, 1, '2024-02-28 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('7220730369abc3fe20df8f7fa2e0ca09', 47, 6, 0, 31, 1, '2024-03-08 12:00:00', 2, 0);
INSERT INTO `sys_count` VALUES ('79574c5545ace9e79845fc6a8506c767', 59, 6, 0, 147, 2, '2024-05-14 12:00:00', 5, 0);
INSERT INTO `sys_count` VALUES ('7edb9f73b640053596587105a3de6efa', 59, 6, 0, 141, 2, '2024-05-09 12:00:00', 5, 0);
INSERT INTO `sys_count` VALUES ('85f7fb43c2b2b835cda4b315fe97d0bf', 58, 6, 0, 147, 2, '2024-05-31 15:43:36', 147, 2);
INSERT INTO `sys_count` VALUES ('876f65b6f61c4db6813014acb4d5246e', 47, 6, 0, 19, 1, '2024-03-03 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('8818feaa3e670887a61548812d014f28', 50, 6, 0, 81, 1, '2024-04-02 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('8a45a3dcab350545487e720247b91473', 43, 6, 0, 12, 1, '2024-02-20 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('8b5091c533a85ff1774abc5afa229dc6', 58, 6, 0, 165, 2, '2024-06-25 09:22:31', 165, 2);
INSERT INTO `sys_count` VALUES ('9064bdc37970d575a54b2f4ac5d8d672', 48, 6, 0, 36, 1, '2024-03-09 12:00:00', 5, 0);
INSERT INTO `sys_count` VALUES ('91c4013175a30cdd2b3f642968528351', 43, 6, 0, 7, 1, '2024-02-15 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('94c37ce8c06d069e7250a64a568a20c4', 46, 6, 0, 99, 2, '2024-04-23 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('9539509ff192770668ba222d1fe46e9e', 47, 6, 0, 28, 1, '2024-03-06 12:00:04', 0, 0);
INSERT INTO `sys_count` VALUES ('9668fcfdf2605eec2b9fe39db3b6a4ae', 50, 6, 0, 64, 1, '2024-03-27 12:00:01', 0, 0);
INSERT INTO `sys_count` VALUES ('98790d2523ac84cfbf82bb0338c541f2', 59, 6, 0, 141, 2, '2024-05-10 17:06:29', 0, 0);
INSERT INTO `sys_count` VALUES ('99a0c6b6510545ea136951428f22e9e6', 46, 6, 0, 104, 2, '2024-04-25 12:00:00', 5, 0);
INSERT INTO `sys_count` VALUES ('9aa18bc82e2944146bfb3b021835000e', 58, 6, 0, 147, 2, '2024-06-21 11:27:59', 147, 2);
INSERT INTO `sys_count` VALUES ('9dafd26447e392ba2fb3414b45882ed2', 58, 6, 0, 147, 2, '2024-06-04 15:29:14', 147, 2);
INSERT INTO `sys_count` VALUES ('a19df499dfccae6c41ecae63fc3c4368', 59, 6, 0, 122, 2, '2024-05-05 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('a834b0af7ea82970f3d8191a34a95baa', 50, 6, 0, 64, 1, '2024-03-25 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('a887b0e8a5c462cea085b7b427f34eac', 45, 6, 0, 81, 1, '2024-04-06 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('a931dc4af16b00423255ad1048070a28', 50, 6, 0, 74, 1, '2024-03-29 12:00:00', 6, 0);
INSERT INTO `sys_count` VALUES ('aa978eee4e4ec3f01c394f94f25c5543', 59, 6, 0, 122, 2, '2024-04-30 12:00:00', 2, 0);
INSERT INTO `sys_count` VALUES ('ac258d659c89db7fa2c3f681691d3ade', 45, 6, 0, 88, 1, '2024-04-12 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('ada8eb6af748ceb34d8d457ba8d66623', 45, 6, 0, 14, 1, '2024-02-22 12:00:02', 0, 0);
INSERT INTO `sys_count` VALUES ('aff39a26b7bbc1aed7528ef2dca82998', 59, 6, 0, 142, 2, '2024-05-12 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('b552bd423abae9e6d3f61c65a02a6bf5', 43, 6, 0, 10, 1, '2024-02-16 12:00:00', 3, 0);
INSERT INTO `sys_count` VALUES ('bb7fc9d465e60e6d069fa1bf96a82466', 43, 6, 0, 2, 0, '2024-02-05 19:02:54', 2, 0);
INSERT INTO `sys_count` VALUES ('bc74c384ba44b81915f541ddd011b57e', 48, 6, 0, 40, 1, '2024-03-17 12:00:00', 2, 0);
INSERT INTO `sys_count` VALUES ('bd7603e6130234b0be797b82f17fc616', 59, 6, 0, 142, 2, '2024-05-13 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('be72fb4e459f07e8fc90d6f5a2c24ee4', 47, 6, 0, 15, 1, '2024-02-29 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('c3d897fb1baa50608942823a9dd2c3ca', 46, 6, 0, 98, 2, '2024-04-22 12:00:02', 0, 0);
INSERT INTO `sys_count` VALUES ('c88116943454208dd420d50cd4f924d9', 48, 6, 0, 40, 1, '2024-03-18 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('c9dfabb80fa1bf3f25b3d16f231d39e6', 59, 6, 0, 142, 2, '2024-05-11 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('caa301a3d1b8242df881ba400fd5da10', 58, 6, 0, 201, 2, '2024-06-26 12:00:00', 36, 0);
INSERT INTO `sys_count` VALUES ('cca4c7ac1c1093df5db5a5c3e166d93c', 47, 6, 0, 29, 1, '2024-03-07 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('cd2bf5134c2028ddf160c4deb09b71bc', 43, 6, 0, 2, 0, '2024-02-06 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('cd56f3550fdd49206fe5711b1ae945c8', 49, 6, 0, 60, 1, '2024-03-23 12:00:01', 10, 0);
INSERT INTO `sys_count` VALUES ('cfecdde5873fbd771eb86c49f6102009', 45, 6, 0, 81, 1, '2024-04-07 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('d1a81771ec8a7b2dc06bb574d395dfbe', 45, 6, 0, 88, 1, '2024-04-09 12:00:00', 7, 0);
INSERT INTO `sys_count` VALUES ('d2a906341f1b1d90de4ac8f36530b9d2', 46, 6, 0, 98, 2, '2024-04-20 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('d4ec32449406dcecddecf0fee5a5c10b', 45, 6, 0, 88, 1, '2024-04-10 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('d5ee263435d2242b5cd95101f6a755b9', 50, 6, 0, 64, 1, '2024-03-24 12:00:00', 4, 0);
INSERT INTO `sys_count` VALUES ('d72479035fc9413076fcb716b5d7d1d7', 45, 6, 0, 14, 1, '2024-02-25 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('d8f4e46013d7611b07bc6b6a8ca6edde', 59, 6, 0, 122, 2, '2024-05-01 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('e862432c759de96a3cbd044f48b4dd40', 43, 6, 0, 12, 1, '2024-02-18 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('ee2aec95b09c46ed534ee6d51dbd6325', 48, 6, 0, 40, 1, '2024-03-19 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('eee3eed5f22af2c850a0e30bcf132e5b', 43, 6, 0, 4, 0, '2024-02-12 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('ef7d5a66b75a6d254514fa497d8f8e7a', 43, 6, 0, 12, 1, '2024-02-19 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('f5a0be287ea7fb8fbca4e0d81cbd596e', 45, 6, 0, 81, 1, '2024-04-08 12:00:00', 0, 0);
INSERT INTO `sys_count` VALUES ('f5e7acdd779296148816e952917aa191', 46, 6, 0, 97, 2, '2024-04-18 12:00:00', 3, 1);
INSERT INTO `sys_count` VALUES ('f9ae133aa9c420af4b919d8a653cc2e6', 46, 6, 0, 98, 2, '2024-04-19 12:00:00', 1, 0);
INSERT INTO `sys_count` VALUES ('fe6ff339636be2f3f391b77583572a12', 46, 6, 0, 94, 1, '2024-04-17 12:00:00', 0, 0);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', 'default', 'Y', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2024-05-06 11:56:52', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', 'default', 'N', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2024-05-06 11:56:56', '性别女');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2022-07-21 11:52:18', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (101, 0, '编程', '0', 'sys_article_type', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:20:03', '', NULL, '编程');
INSERT INTO `sys_dict_data` VALUES (102, 1, '日常', '1', 'sys_article_type', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:20:43', '', NULL, '日常');
INSERT INTO `sys_dict_data` VALUES (103, 0, 'Java', '0', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:21:22', '', NULL, 'Java');
INSERT INTO `sys_dict_data` VALUES (104, 0, 'SQL', '1', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 01:21:38', 'admin', '2022-08-08 09:27:52', 'SQL');
INSERT INTO `sys_dict_data` VALUES (105, 1, '下架', '0', 'sys_article_status', NULL, 'danger', 'N', '0', 'admin', '2022-07-22 19:22:30', 'admin', '2022-08-19 15:48:22', '文章下架');
INSERT INTO `sys_dict_data` VALUES (106, 0, '发布', '1', 'sys_article_status', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 19:22:53', 'admin', '2022-08-19 15:48:16', '文章发布');
INSERT INTO `sys_dict_data` VALUES (107, 0, '默认', '0', 'sys_article_top', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 19:24:59', 'admin', '2022-07-22 19:27:42', '不置顶');
INSERT INTO `sys_dict_data` VALUES (108, 0, '置顶', '1', 'sys_article_top', NULL, 'success', 'N', '0', 'admin', '2022-07-22 19:25:07', 'admin', '2022-08-05 15:51:39', '置顶');
INSERT INTO `sys_dict_data` VALUES (109, 0, '原创', '0', 'sys_article_origin', NULL, 'success', 'N', '0', 'admin', '2022-07-22 19:27:31', 'admin', '2022-08-05 15:51:53', '文章原创');
INSERT INTO `sys_dict_data` VALUES (110, 0, '转载', '1', 'sys_article_origin', NULL, 'primary', 'N', '0', 'admin', '2022-07-22 19:28:10', '', NULL, '转载');
INSERT INTO `sys_dict_data` VALUES (118, 0, 'Spring', '2', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-08 09:27:25', 'admin', '2022-08-08 09:27:39', 'spring');
INSERT INTO `sys_dict_data` VALUES (119, 0, 'Spring MVC', '3', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-08 09:28:23', '', NULL, 'Spring MVC');
INSERT INTO `sys_dict_data` VALUES (120, 0, 'MyBatis', '4', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-08 09:28:42', 'admin', '2022-08-08 09:28:54', 'Mybatis');
INSERT INTO `sys_dict_data` VALUES (125, 0, 'BUG', '2', 'sys_article_type', NULL, 'primary', 'N', '0', 'admin', '2022-08-19 15:45:26', 'admin', '2022-08-19 15:45:32', 'BUG');
INSERT INTO `sys_dict_data` VALUES (126, 0, '算法', '5', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-19 15:46:31', '', NULL, '算法');
INSERT INTO `sys_dict_data` VALUES (127, 0, 'JUC', '6', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-08-29 09:25:52', '', NULL, 'JUC');
INSERT INTO `sys_dict_data` VALUES (137, 0, 'OSS', '7', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-09-26 10:06:37', '', NULL, 'OSS');
INSERT INTO `sys_dict_data` VALUES (138, 0, '短信', '9', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-09-26 14:51:32', '', NULL, '短信');
INSERT INTO `sys_dict_data` VALUES (139, 0, 'NIO', '10', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-09-30 15:07:08', '', NULL, 'NIO');
INSERT INTO `sys_dict_data` VALUES (140, 0, 'Netty', '11', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-10-14 14:57:51', 'admin', '2022-10-14 14:58:09', NULL);
INSERT INTO `sys_dict_data` VALUES (141, 0, 'API', '12', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-11-15 08:24:18', 'admin', '2022-11-15 08:24:24', '');
INSERT INTO `sys_dict_data` VALUES (142, 0, '中间件', '13', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-11-22 02:51:33', '', NULL, '中间件');
INSERT INTO `sys_dict_data` VALUES (147, 0, '文档', '14', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2022-12-14 07:49:27', '', NULL, '文档');
INSERT INTO `sys_dict_data` VALUES (148, 0, 'Docker', '15', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-01-05 09:17:07', '', NULL, 'Docker');
INSERT INTO `sys_dict_data` VALUES (149, 0, 'RabbitMQ', '16', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-01-05 09:17:30', '', NULL, 'RabbitMQ');
INSERT INTO `sys_dict_data` VALUES (150, 0, 'ElasticSearch', '17', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-02-10 02:18:42', '', NULL, 'ElasticSearch');
INSERT INTO `sys_dict_data` VALUES (151, 0, 'Logstash', '18', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-02-10 02:19:12', '', NULL, 'Logstash');
INSERT INTO `sys_dict_data` VALUES (161, 0, 'k8s', '19', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-04 07:48:46', '', NULL, 'k8s');
INSERT INTO `sys_dict_data` VALUES (162, 0, '云原生', '20', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-04 07:49:09', '', NULL, '云原生');
INSERT INTO `sys_dict_data` VALUES (165, 0, 'SpringBoot', '21', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-13 07:59:30', 'admin', '2023-07-13 07:59:40', 'SpringBoot');
INSERT INTO `sys_dict_data` VALUES (166, 0, 'Swagger', '22', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-13 08:00:01', '', NULL, 'Swagger');
INSERT INTO `sys_dict_data` VALUES (167, 0, 'Redis', '23', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-07-13 08:42:52', '', NULL, 'Redis');
INSERT INTO `sys_dict_data` VALUES (168, 0, 'uni-app', '24', 'sys_article_tag', NULL, 'primary', 'N', '0', 'admin', '2023-09-05 03:25:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (169, 0, '正常', '1', 'currency_status', NULL, 'default', 'N', '0', 'admin', '2024-07-18 16:34:31', '', NULL, '正常');
INSERT INTO `sys_dict_data` VALUES (170, 0, '停用', '0', 'currency_status', NULL, 'default', 'N', '0', 'admin', '2024-07-18 16:34:48', '', NULL, '停用');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-04-27 02:10:24', 'admin', '2022-07-22 06:57:38', '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-04-27 02:10:24', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '文章分类', 'sys_article_type', '0', 'admin', '2022-07-20 01:14:22', 'admin', '2024-05-06 10:34:21', '文章分类列表');
INSERT INTO `sys_dict_type` VALUES (104, '文章标签', 'sys_article_tag', '0', 'admin', '2022-07-22 01:19:31', 'admin', '2024-05-06 10:34:19', '文章标签列表');
INSERT INTO `sys_dict_type` VALUES (106, '文章状态', 'sys_article_status', '0', 'admin', '2022-07-22 17:09:06', 'admin', '2024-05-06 10:34:17', '文章状态列表');
INSERT INTO `sys_dict_type` VALUES (107, '文章置顶', 'sys_article_top', '0', 'admin', '2022-07-22 19:23:51', 'admin', '2024-05-06 10:34:13', '文章是否置顶列表');
INSERT INTO `sys_dict_type` VALUES (108, '文章原创', 'sys_article_origin', '0', 'admin', '2022-07-22 19:26:02', 'admin', '2024-05-06 10:34:10', '文章是否原创列表');
INSERT INTO `sys_dict_type` VALUES (110, '通用状态', 'currency_status', '0', 'admin', '2024-07-18 16:34:13', '', NULL, '通用状态');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-04-27 02:14:22');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-05-13 07:16:40');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-11 01:19:05');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-11 06:03:09');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-11 07:08:20');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-20 00:52:08');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 08:29:27');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '1', '验证码错误', '2022-07-21 09:20:15');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 09:20:21');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '退出成功', '2022-07-21 09:30:15');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 09:30:22');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-07-21 11:05:38');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `parent_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '所属上级',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '名称',
  `type` tinyint(3) NOT NULL DEFAULT 0 COMMENT '类型(0:目录,1:菜单,2:按钮)',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort_value` int(11) NULL DEFAULT NULL COMMENT '排序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0186f5e32d71f917bd4cb4d693346cde', '8be50fe7341b636c2115ea7c9cf36854', '列表', 2, '', '', 'btn.config.list', '', 1, '1', '2024-05-11 17:50:49', '2024-05-11 17:50:49', '0');
INSERT INTO `sys_menu` VALUES ('05fa1dd782ad6b196769e9d9585ff6f3', '1c2d6c42822884c157897af502ca251e', '编辑', 2, '/tool/gen-edit/index/:tableId', 'tool/gen/editTable', 'btn.gen.edit', '', 1, '1', '2024-07-18 16:36:06', '2024-07-19 17:02:42', '0');
INSERT INTO `sys_menu` VALUES ('0c2f7bcab57cd177ddce93fa5057c993', '1c2d6c42822884c157897af502ca251e', '创建表', 2, '', '', 'btn.gen.sql', '', 1, '1', '2024-07-19 17:10:09', '2024-07-19 17:10:09', '0');
INSERT INTO `sys_menu` VALUES ('1298ab16f667edbece17a2b73cf51fc0', '8be50fe7341b636c2115ea7c9cf36854', '删除', 2, '', '', 'btn.config.del', '', 1, '1', '2024-05-11 17:50:33', '2024-05-11 17:50:33', '0');
INSERT INTO `sys_menu` VALUES ('16e3dc94b0a0b0ab3eb491d4e172295d', 'be190c8cd6238ea9119a0befa954cf7a', '网站信息', 1, 'information', 'system/information/index', '', 'el-icon-s-platform', 8, '1', '2024-05-08 10:42:56', '2024-05-08 10:46:20', '0');
INSERT INTO `sys_menu` VALUES ('1c2d6c42822884c157897af502ca251e', 'be190c8cd6238ea9119a0befa954cf7a', '代码生成', 1, 'gen', 'tool/gen/index', '', 'el-icon-s-flag', 1, '1', '2024-07-18 16:32:30', '2024-07-18 16:32:30', '0');
INSERT INTO `sys_menu` VALUES ('240307c217e98687e93b05c16a5424aa', '8be50fe7341b636c2115ea7c9cf36854', '修改', 2, '', '', 'btn.config.edit', '', 1, '1', '2024-05-11 17:50:21', '2024-05-11 17:50:21', '0');
INSERT INTO `sys_menu` VALUES ('2d812ec5ddaedd70c93a2f78ab984334', '5ef83c1291ff3e1f435c3dffbf1be55a', '删除', 2, '', '', 'btn.notice.del', '', 1, '1', '2024-05-11 17:34:43', '2024-05-11 17:34:43', '0');
INSERT INTO `sys_menu` VALUES ('30318a16f713de861d99a789e0caac77', '85413b9370843cdb477dfbabf62bf6e5', '删除', 2, '', '', 'btn.dict.del', '', 1, '1', '2024-05-11 17:39:28', '2024-05-11 17:39:28', '0');
INSERT INTO `sys_menu` VALUES ('3aac4a4b0b7284bc52b5308e776d14ea', '440867f56dbf23944789d672b158bd32', '删除', 2, '', '', 'btn.role.del', '', 1, '1', '2024-05-11 17:14:33', '2024-05-11 17:14:33', '0');
INSERT INTO `sys_menu` VALUES ('3d1d3a7f07e06ed44d8c451125b42963', 'e4dca1309de7f2d9efa1f09beb5ae34a', '删除', 2, '', '', 'btn.user.del', '', 1, '1', '2024-05-11 17:59:49', '2024-05-11 17:59:49', '0');
INSERT INTO `sys_menu` VALUES ('41b3f4ce407fa92523593c6edda2dab8', 'e4dca1309de7f2d9efa1f09beb5ae34a', '修改', 2, '', '', 'btn.user.edit', '', 1, '1', '2024-05-11 17:59:35', '2024-05-11 17:59:35', '0');
INSERT INTO `sys_menu` VALUES ('440867f56dbf23944789d672b158bd32', 'be190c8cd6238ea9119a0befa954cf7a', '角色管理', 1, 'role', 'system/role/index', '', 'el-icon-s-help', 2, '1', '2024-05-08 10:39:34', '2024-05-08 10:39:45', '0');
INSERT INTO `sys_menu` VALUES ('471c8a9a73d0e2bfa59eb40adffb3869', 'be190c8cd6238ea9119a0befa954cf7a', '菜单管理', 1, 'menu', 'system/menu/index', '', 'el-icon-menu', 3, '1', '2024-05-08 10:40:21', '2024-05-08 10:40:21', '0');
INSERT INTO `sys_menu` VALUES ('4cfb361d436950364b6f99cf12501425', 'b602e401093b6a40da8dc0101570bcf9', '列表', 2, '', '', 'btn.job.list', '', 1, '1', '2024-05-13 09:23:09', '2024-05-13 09:23:09', '0');
INSERT INTO `sys_menu` VALUES ('5c98ab7391bde87525c3a41a4d0e72cf', 'e4dca1309de7f2d9efa1f09beb5ae34a', '列表', 2, '', '', 'btn.user.list', '', 1, '1', '2024-05-11 18:00:01', '2024-05-11 18:00:01', '0');
INSERT INTO `sys_menu` VALUES ('5e567f2c0646723f318be3290811da6b', '440867f56dbf23944789d672b158bd32', '新增', 2, '', '', 'btn.role.add', '', 1, '1', '2024-05-11 17:14:04', '2024-05-11 17:14:04', '0');
INSERT INTO `sys_menu` VALUES ('5ea2d48af011f309547f007d5cabbd27', '471c8a9a73d0e2bfa59eb40adffb3869', '列表', 2, '', '', 'btn.menu.list', '', 1, '1', '2024-05-11 17:28:42', '2024-05-11 17:28:42', '0');
INSERT INTO `sys_menu` VALUES ('5ef83c1291ff3e1f435c3dffbf1be55a', 'be190c8cd6238ea9119a0befa954cf7a', '公告管理', 1, 'notice', 'system/notice/index', '', 'el-icon-warning-outline', 6, '1', '2024-05-08 10:43:22', '2024-05-08 10:46:06', '0');
INSERT INTO `sys_menu` VALUES ('6092364759758c2cd63808bdceba4405', '471c8a9a73d0e2bfa59eb40adffb3869', '删除', 2, '', '', 'btn.menu.del', '', 1, '1', '2024-05-11 17:28:29', '2024-05-11 17:28:29', '0');
INSERT INTO `sys_menu` VALUES ('6491c0ddc37084a317b7bf0d5c4817cb', '8be50fe7341b636c2115ea7c9cf36854', '新增', 2, '', '', 'btn.config.add', '', 1, '1', '2024-05-11 17:49:55', '2024-05-11 17:49:55', '0');
INSERT INTO `sys_menu` VALUES ('6be6b8ccf9fba11ce330f2efaeb51c94', 'b602e401093b6a40da8dc0101570bcf9', '新增', 2, '', '', 'btn.job.add', '', 1, '1', '2024-05-13 09:22:21', '2024-05-13 09:22:21', '0');
INSERT INTO `sys_menu` VALUES ('77b30e9d5b7f261153c67947ae97963e', '471c8a9a73d0e2bfa59eb40adffb3869', '新增', 2, '', '', 'btn.menu.add', '', 1, '1', '2024-05-11 17:27:57', '2024-05-11 17:27:57', '0');
INSERT INTO `sys_menu` VALUES ('7e6e754ee2d1f2e0339b5a275a45acdd', 'dae1346779d28f39a06d1e1ce3026781', '列表', 2, '', '', 'btn.operlog.list', '', 1, '1', '2024-05-13 09:29:56', '2024-05-13 09:29:56', '0');
INSERT INTO `sys_menu` VALUES ('85413b9370843cdb477dfbabf62bf6e5', 'be190c8cd6238ea9119a0befa954cf7a', '字典分类', 1, 'dict', 'system/dict/index', '', 'el-icon-tickets', 4, '1', '2024-05-08 10:40:46', '2024-05-08 10:45:53', '0');
INSERT INTO `sys_menu` VALUES ('8be50fe7341b636c2115ea7c9cf36854', 'be190c8cd6238ea9119a0befa954cf7a', '参数设置', 1, 'config', 'system/config/index', '', 'el-icon-setting', 7, '1', '2024-05-08 10:41:52', '2024-05-08 10:46:10', '0');
INSERT INTO `sys_menu` VALUES ('a14e227cf950c10bc96bfc9061487a87', '1c2d6c42822884c157897af502ca251e', '生成代码', 2, '', '', 'btn.gen.gen', '', 1, '1', '2024-07-19 17:07:46', '2024-07-19 17:07:46', '0');
INSERT INTO `sys_menu` VALUES ('a1cf74abe6ef8075975281c08bfe1895', '85413b9370843cdb477dfbabf62bf6e5', '字典数据', 2, 'data/:id(\\d+)', 'system/dict/data', 'btn.dict.data', '', 1, '1', '2024-05-08 10:41:13', '2024-05-08 15:52:27', '0');
INSERT INTO `sys_menu` VALUES ('a3a7a0cf00f49ca90765dd869d5ce399', '85413b9370843cdb477dfbabf62bf6e5', '列表', 2, '', '', 'btn.dict.list', '', 1, '1', '2024-05-11 17:39:55', '2024-05-11 17:39:55', '0');
INSERT INTO `sys_menu` VALUES ('a88286a2308fb14175868ded546953b7', 'dae1346779d28f39a06d1e1ce3026781', '删除', 2, '', '', 'btn.operlog.del', '', 1, '1', '2024-05-13 09:29:37', '2024-05-13 09:29:37', '0');
INSERT INTO `sys_menu` VALUES ('af19e30c6aa4f2fa195918528273aa6d', '5ef83c1291ff3e1f435c3dffbf1be55a', '列表', 2, '', '', 'btn.notice.list', '', 1, '1', '2024-05-11 17:35:01', '2024-05-11 17:35:01', '0');
INSERT INTO `sys_menu` VALUES ('af1ddec5003e789ea5d167e7b8b0a773', '16e3dc94b0a0b0ab3eb491d4e172295d', '修改', 2, '', '', 'btn.information.edit', '', 1, '1', '2024-05-11 17:11:14', '2024-05-11 17:11:14', '0');
INSERT INTO `sys_menu` VALUES ('afef72e3ee28af35f2de19ba4c1dc88e', 'b602e401093b6a40da8dc0101570bcf9', '修改', 2, '', '', 'btn.job.edit', '', 1, '1', '2024-05-13 09:22:44', '2024-05-13 09:22:44', '0');
INSERT INTO `sys_menu` VALUES ('b602e401093b6a40da8dc0101570bcf9', 'be190c8cd6238ea9119a0befa954cf7a', '定时任务', 1, 'job', 'system/job/index', '', 'el-icon-s-unfold', 5, '1', '2024-05-08 10:42:17', '2024-05-08 10:45:59', '0');
INSERT INTO `sys_menu` VALUES ('b9d414c8de0b6ba6e2c44ab59e0ce8a9', '1c2d6c42822884c157897af502ca251e', '删除', 2, '', '', 'btn.gen.del', '', 1, '1', '2024-07-19 17:04:43', '2024-07-19 17:04:43', '0');
INSERT INTO `sys_menu` VALUES ('ba5f42390d98d1ac546119627cec0b37', '5ef83c1291ff3e1f435c3dffbf1be55a', '新增', 2, '', '', 'btn.notice.add', '', 1, '1', '2024-05-11 17:34:10', '2024-05-11 17:34:10', '0');
INSERT INTO `sys_menu` VALUES ('be190c8cd6238ea9119a0befa954cf7a', '0', '系统管理', 0, 'system', 'Layout', '', 'el-icon-s-tools', 6, '1', '2024-05-08 10:33:07', '2024-05-08 10:38:51', '0');
INSERT INTO `sys_menu` VALUES ('cd0f17b0d5ff3de2ddc974e07681f13e', '85413b9370843cdb477dfbabf62bf6e5', '修改', 2, '', '', 'btn.dict.edit', '', 1, '1', '2024-05-11 17:39:15', '2024-05-11 17:39:15', '0');
INSERT INTO `sys_menu` VALUES ('d839e3275f5fa4b708929c93f1bc531a', '5ef83c1291ff3e1f435c3dffbf1be55a', '修改', 2, '', '', 'btn.notice.edit', '', 1, '1', '2024-05-11 17:34:34', '2024-05-11 17:34:34', '0');
INSERT INTO `sys_menu` VALUES ('dae1346779d28f39a06d1e1ce3026781', 'be190c8cd6238ea9119a0befa954cf7a', '日志管理', 1, 'operlog', 'monitor/operlog/index', '', 'el-icon-edit', 9, '1', '2024-05-08 10:43:53', '2024-05-08 10:46:26', '0');
INSERT INTO `sys_menu` VALUES ('db75d07485333f3a052cac79b54b7c59', 'e4dca1309de7f2d9efa1f09beb5ae34a', '新增', 2, '', '', 'btn.user.add', '', 1, '1', '2024-05-11 17:59:17', '2024-05-11 17:59:17', '0');
INSERT INTO `sys_menu` VALUES ('e1466d34d8c97f3b0cd10370d95ebea3', '471c8a9a73d0e2bfa59eb40adffb3869', '修改', 2, '', '', 'btn.menu.edit', '', 1, '1', '2024-05-11 17:28:12', '2024-05-11 17:28:12', '0');
INSERT INTO `sys_menu` VALUES ('e18a1c6b71f13161ef1580f4fc2bd740', '440867f56dbf23944789d672b158bd32', '列表', 2, '', '', 'btn.role.list', '', 1, '1', '2024-05-11 17:14:55', '2024-05-11 17:14:55', '0');
INSERT INTO `sys_menu` VALUES ('e4dca1309de7f2d9efa1f09beb5ae34a', 'be190c8cd6238ea9119a0befa954cf7a', '用户管理', 1, 'user', 'system/user/index', '', 'el-icon-s-custom', 1, '1', '2024-05-08 10:36:58', '2024-05-08 10:36:58', '0');
INSERT INTO `sys_menu` VALUES ('efafe29a11921a7026b73d59dfcaa0b4', '440867f56dbf23944789d672b158bd32', '修改', 2, '', '', 'btn.role.edit', '', 1, '1', '2024-05-11 17:14:19', '2024-05-11 17:14:19', '0');
INSERT INTO `sys_menu` VALUES ('f0c2e6792c806012822fcef73c0b44c3', '1c2d6c42822884c157897af502ca251e', '同步', 2, '', '', 'btn.gen.sync', '', 1, '1', '2024-07-19 17:08:05', '2024-07-19 17:08:05', '0');
INSERT INTO `sys_menu` VALUES ('f60544908c9665fe8a7bbc8718531f99', 'b602e401093b6a40da8dc0101570bcf9', '删除', 2, '', '', 'btn.job.del', '', 1, '1', '2024-05-13 09:22:57', '2024-05-13 09:22:57', '0');
INSERT INTO `sys_menu` VALUES ('f61f9dc267e64d6fd6b509030ef9b756', '1c2d6c42822884c157897af502ca251e', '列表', 2, '', '', 'btn.gen.list', '', 1, '1', '2024-07-19 17:03:03', '2024-07-19 17:03:03', '0');
INSERT INTO `sys_menu` VALUES ('f65a794f7aaa73d994048c53a928fbec', '16e3dc94b0a0b0ab3eb491d4e172295d', '列表', 2, '', '', 'btn.information.list', '', 1, '1', '2024-05-11 17:11:37', '2024-05-11 17:11:37', '0');
INSERT INTO `sys_menu` VALUES ('f8bd0f8742c68ddfae6acaa2a26cef69', '1c2d6c42822884c157897af502ca251e', '导入', 2, '', '', 'btn.gen.import', '', 1, '1', '2024-07-19 17:03:54', '2024-07-19 17:04:20', '0');
INSERT INTO `sys_menu` VALUES ('ff635db54aeba2e9ca4968973347685e', '85413b9370843cdb477dfbabf62bf6e5', '新增', 2, '', '', 'btn.dict.add', '', 1, '1', '2024-05-11 17:38:57', '2024-05-11 17:38:57', '0');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态（1：正常 0：停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('6f79a34b896e91cefe4a8dc396f76bb0', '超级管理员', 'SUPER', '超级管理员', '2024-05-06 15:56:39', '2024-05-06 15:56:39', '0', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('07b1e59d4e9ca867fccedd69ff31e67e', '6f79a34b896e91cefe4a8dc396f76bb0', 'b71103cc3723538c6ccb7bf3c054c835', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('089b3aa79439325257005c29e99b6218', '6f79a34b896e91cefe4a8dc396f76bb0', '76b0773b82f7e9c410a025f1bda0f3c3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('094b49406baf537e67be8b7976fe36c6', '6f79a34b896e91cefe4a8dc396f76bb0', '77b30e9d5b7f261153c67947ae97963e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('0ea163686e578065c8bed5583e60c098', '6f79a34b896e91cefe4a8dc396f76bb0', '5ea2d48af011f309547f007d5cabbd27', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('132dbcf08d7f651c3c5c21ce10c3de80', '6f79a34b896e91cefe4a8dc396f76bb0', '5f3c62238b5a3dc58f05bc560cb4d599', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('14101b1385818ac2fc5728e21ddaa94b', '6f79a34b896e91cefe4a8dc396f76bb0', 'af19e30c6aa4f2fa195918528273aa6d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('1a7518c44dcb687236544e2a18fe14cd', '6f79a34b896e91cefe4a8dc396f76bb0', '5e567f2c0646723f318be3290811da6b', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('1aaaf92e0bdcfbabfbfa1ba2346e3d66', '6f79a34b896e91cefe4a8dc396f76bb0', 'f2fbf976272ba320421d6382df1ae7d8', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('2399937dc10fdf16445f6f82fa4c2201', '6f79a34b896e91cefe4a8dc396f76bb0', 'd55dea05d71d3bb787f5a3abce45c8b0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('25f2602a2f76920d89e08129d6ff07eb', '6f79a34b896e91cefe4a8dc396f76bb0', '16e3dc94b0a0b0ab3eb491d4e172295d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('2890bf77f99dea5dc15a560e07ea76b7', '6f79a34b896e91cefe4a8dc396f76bb0', 'e4dca1309de7f2d9efa1f09beb5ae34a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('2c039cd6a39ea6542176b4c3aa1e3a64', '6f79a34b896e91cefe4a8dc396f76bb0', '3f43a880c9e2259a1b1411efc9368bcd', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('2d9ab6c5596bdc7c180196f581355f8d', '6f79a34b896e91cefe4a8dc396f76bb0', 'f65a794f7aaa73d994048c53a928fbec', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('3090f908e4ac74da00cefd0a5b18cfbc', '6f79a34b896e91cefe4a8dc396f76bb0', 'a1cf74abe6ef8075975281c08bfe1895', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('3299b81f457edc6ae724b49fdee943c3', '6f79a34b896e91cefe4a8dc396f76bb0', 'a88286a2308fb14175868ded546953b7', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('384c17e634c07b707688828db7545d1b', '6f79a34b896e91cefe4a8dc396f76bb0', 'e1466d34d8c97f3b0cd10370d95ebea3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('38ecb3f02ce0d57da812d493abacee75', '6f79a34b896e91cefe4a8dc396f76bb0', '0186f5e32d71f917bd4cb4d693346cde', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('3ad4d95f2eec38fe81135df0649576f2', '6f79a34b896e91cefe4a8dc396f76bb0', '749fac6f70563b29de79b81e3ed50a00', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('3c2f623cb881b42efd0fe38c9889548c', '6f79a34b896e91cefe4a8dc396f76bb0', 'aa3f6a9f28e47f73ddde5e63c1aee822', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('3f3c0c7925e9f388162eb8dcde1dc33a', '6f79a34b896e91cefe4a8dc396f76bb0', 'b8b6a480d4487df8eea7edf88b1ec003', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('4c8f1035307fac5b630ddc4124c752aa', '6f79a34b896e91cefe4a8dc396f76bb0', 'bcd73e1ffe2c3698ca2bed4d1e835891', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('4e30dedce47d13b2990fb77eec674acd', '6f79a34b896e91cefe4a8dc396f76bb0', 'b72e17d19dd5372895c0b4969b292f1f', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('51bab98881ee21ed652bd0177710d581', '6f79a34b896e91cefe4a8dc396f76bb0', 'e18a1c6b71f13161ef1580f4fc2bd740', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('52c392cf430781760cc8106e02172891', '6f79a34b896e91cefe4a8dc396f76bb0', '77c5ef0c5e44e724f9c19f0cb20df4d9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('55c2087739690dede75845cc0842528b', '6f79a34b896e91cefe4a8dc396f76bb0', 'db75d07485333f3a052cac79b54b7c59', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('5975227ffdf7a05070580423a85ff96c', '6f79a34b896e91cefe4a8dc396f76bb0', 'b602e401093b6a40da8dc0101570bcf9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('616d2d5e70e0b0574ae7e3edf304ebbf', '6f79a34b896e91cefe4a8dc396f76bb0', '4316d4c947c23236d9c74bb8fa8448a1', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('63426b42a8e2563ca8c297958f35f2b7', '6f79a34b896e91cefe4a8dc396f76bb0', 'cd0f17b0d5ff3de2ddc974e07681f13e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('63ba609639607c87649f6260a43c2fc6', '6f79a34b896e91cefe4a8dc396f76bb0', '1298ab16f667edbece17a2b73cf51fc0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('66efd3dc6329bb999af562c1269a47d3', '6f79a34b896e91cefe4a8dc396f76bb0', '36c3988c159b20bc56f28f98f4b4aec9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('68a324d84f2775ce1e89fb40dbddddfc', '6f79a34b896e91cefe4a8dc396f76bb0', '8be50fe7341b636c2115ea7c9cf36854', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('69c658acfcca99c2935aac7e8d18bc3d', '6f79a34b896e91cefe4a8dc396f76bb0', '4a4596413a111e9e0c8944a6be62226a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('6bcf30b3898ae5f88917dd7fd750b31e', '6f79a34b896e91cefe4a8dc396f76bb0', '5b95542e87f4d9bf4c5b7eeffeb76bde', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('6c70f195b87f8a039490fa76f6826e73', '6f79a34b896e91cefe4a8dc396f76bb0', '895155a2550b1816cc01b21d13f9f5ce', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7081e5f9d4aa9f87ebc56276f72ee953', '6f79a34b896e91cefe4a8dc396f76bb0', 'c4c9727d9b18bf87531214b8c3b094a9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('70ce0747c67d39901c8bb0c75901f889', '6f79a34b896e91cefe4a8dc396f76bb0', '5ef83c1291ff3e1f435c3dffbf1be55a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('723c91f3df6dfc8aa943e825527f9477', '6f79a34b896e91cefe4a8dc396f76bb0', 'c74c6ede8a88856a513178bdc199f3cb', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7ce9b7c2d533f3ae2b0ac961897bb158', '6f79a34b896e91cefe4a8dc396f76bb0', 'b99d96ba9ce2526eba6ad9dabae9f2a0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7d1d6e77441fee3dc18f539dc36abb84', '6f79a34b896e91cefe4a8dc396f76bb0', '8037a655ebd77d22991e33871240bbb3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7d32c45e5f83631dee8247c348e80176', '6f79a34b896e91cefe4a8dc396f76bb0', '5d86554626f798497367444eb6ab4da3', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7d333bf3222da998e1a7e362e0f121b5', '6f79a34b896e91cefe4a8dc396f76bb0', 'f60544908c9665fe8a7bbc8718531f99', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7e520c2a037474348830e7e763a95275', '6f79a34b896e91cefe4a8dc396f76bb0', 'a3a7a0cf00f49ca90765dd869d5ce399', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('7ee3d137e7d8b108f909db12d9808d23', '6f79a34b896e91cefe4a8dc396f76bb0', 'c85f2e6539507a3aa594c9e0a08ded47', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('80a7fde9d1956c03454958e2dbe681c7', '6f79a34b896e91cefe4a8dc396f76bb0', '6dc6ec2b8519d3d4716f873428ab276c', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('892cd04d8137190b048f5eb2cabe2a0c', '6f79a34b896e91cefe4a8dc396f76bb0', '240307c217e98687e93b05c16a5424aa', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('894bc6bd4ab8347d559a7f156b719b5c', '6f79a34b896e91cefe4a8dc396f76bb0', 'ff635db54aeba2e9ca4968973347685e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('8a9564685412718a1b8e8c83c6d5fa04', '6f79a34b896e91cefe4a8dc396f76bb0', '3aac4a4b0b7284bc52b5308e776d14ea', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('8aeb53c4a38f5a657c2bd569be814851', '6f79a34b896e91cefe4a8dc396f76bb0', '9388193f467e661abefae3368414327a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('92b62af891d7b89329475f986c7023ec', '6f79a34b896e91cefe4a8dc396f76bb0', 'd228447e3e3650cedd98bd8c1724c866', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('95dd49684787c6d8cbc479effdee83c0', '6f79a34b896e91cefe4a8dc396f76bb0', 'ba5f42390d98d1ac546119627cec0b37', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('97dbd95db0af19a4baba971fa0cbb63c', '6f79a34b896e91cefe4a8dc396f76bb0', 'dae1346779d28f39a06d1e1ce3026781', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('9833efc742902a9a1851d497a484cc26', '6f79a34b896e91cefe4a8dc396f76bb0', '700e6284217d6743c74bc62efa508239', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('9a4468a9da8eea9a5d5bd5a8414e51cc', '6f79a34b896e91cefe4a8dc396f76bb0', '41b3f4ce407fa92523593c6edda2dab8', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('9ec244414c0807cb2cab87a872f57dfe', '6f79a34b896e91cefe4a8dc396f76bb0', '3d1d3a7f07e06ed44d8c451125b42963', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a0eb3d20c9e7a041fd9dcd7fc655b0eb', '6f79a34b896e91cefe4a8dc396f76bb0', '7e6e754ee2d1f2e0339b5a275a45acdd', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a368b31410755e8b5258d439213ed7aa', '6f79a34b896e91cefe4a8dc396f76bb0', 'ceb82099541da06df10c860a9f6e7d3f', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a39968220df05827739f10b4a5f8715b', '6f79a34b896e91cefe4a8dc396f76bb0', '30318a16f713de861d99a789e0caac77', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a48871d6dcc4922893472f9f11677855', '6f79a34b896e91cefe4a8dc396f76bb0', '4cfb361d436950364b6f99cf12501425', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a4b0ef12bf91d1624116e9cdea343712', '6f79a34b896e91cefe4a8dc396f76bb0', '5c98ab7391bde87525c3a41a4d0e72cf', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a60366601958003efc201c7ebb08c12d', '6f79a34b896e91cefe4a8dc396f76bb0', '6be6b8ccf9fba11ce330f2efaeb51c94', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a6be7184a2c3d613f4ebb27ca02ab2bb', '6f79a34b896e91cefe4a8dc396f76bb0', 'be190c8cd6238ea9119a0befa954cf7a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('a88e322c7b4ddeb4deff3b63cedc52d4', '6f79a34b896e91cefe4a8dc396f76bb0', 'efafe29a11921a7026b73d59dfcaa0b4', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('ab130ced4a29cb160e7dddd06cafd18b', '6f79a34b896e91cefe4a8dc396f76bb0', '51d34848ca84a9da5d6fdbe45afeb34f', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('acff6e101a836088a0a899ce03b92bb7', '6f79a34b896e91cefe4a8dc396f76bb0', 'af1ddec5003e789ea5d167e7b8b0a773', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('be6d795c3c48c49483805161e33cc2db', '6f79a34b896e91cefe4a8dc396f76bb0', '1417e20b26a17e778fb2c6356601e7d1', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('bf422a518e759befb86aebdb1525596f', '6f79a34b896e91cefe4a8dc396f76bb0', '440867f56dbf23944789d672b158bd32', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c18cf1e5b637897fadc0d5f30a8107d4', '6f79a34b896e91cefe4a8dc396f76bb0', '6abd125b6490c29c54739d9d0ca27ae9', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c61c2a6f1ec9d6c661d6542abe8b8cf7', '6f79a34b896e91cefe4a8dc396f76bb0', 'afef72e3ee28af35f2de19ba4c1dc88e', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c755f4bb7c71db0193af2dc1f8084d5c', '6f79a34b896e91cefe4a8dc396f76bb0', '285fca26989c2c49978d9853fb38197c', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('c98ca7d8795b57fba26335dc8f60869b', '6f79a34b896e91cefe4a8dc396f76bb0', '12c926c9e528aa15481a552b51c646e1', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d0324daf9335cde0b15cea8ea064d3ab', '6f79a34b896e91cefe4a8dc396f76bb0', 'cd428ecaf91f3747d4f75953ebab413d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d682dcd97d204ad3257bc57f39511e50', '6f79a34b896e91cefe4a8dc396f76bb0', '6491c0ddc37084a317b7bf0d5c4817cb', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d73f876a5bcba96ba88d759b1356fdde', '6f79a34b896e91cefe4a8dc396f76bb0', '85413b9370843cdb477dfbabf62bf6e5', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d74e85990c225cfb475d598163ee0651', '6f79a34b896e91cefe4a8dc396f76bb0', 'd112adfc4ba9caf20104609ec98712df', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('d8e2140ed5339008b258419b320f585a', '6f79a34b896e91cefe4a8dc396f76bb0', '6092364759758c2cd63808bdceba4405', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f2ca7f9ccfaf5463734ecdae18688e7d', '6f79a34b896e91cefe4a8dc396f76bb0', 'ed17d32a1f8a7173bcaeb4c84447fb98', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f39a3fa8fd081e2bf02b6004061f2aef', '6f79a34b896e91cefe4a8dc396f76bb0', '271bc0f236eaa1afabfec96a91d5cc5d', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f515563b17bef9d44c4c3507f04685ce', '6f79a34b896e91cefe4a8dc396f76bb0', '471c8a9a73d0e2bfa59eb40adffb3869', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('f6d535c656eabaa584a8d5e0e88f7d82', '6f79a34b896e91cefe4a8dc396f76bb0', '1f2da8d3b50d9ae744279bdeb3694df0', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('fb0d2cdc8e49f05595e13cd017753271', '6f79a34b896e91cefe4a8dc396f76bb0', '2d812ec5ddaedd70c93a2f78ab984334', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');
INSERT INTO `sys_role_menu` VALUES ('fc626b7dd82a663fd1ab40d52063ed85', '6f79a34b896e91cefe4a8dc396f76bb0', 'd839e3275f5fa4b708929c93f1bc531a', '2024-05-13 10:13:52', '2024-05-13 10:13:52', '0');

-- ----------------------------
-- Table structure for sys_todo
-- ----------------------------
DROP TABLE IF EXISTS `sys_todo`;
CREATE TABLE `sys_todo`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `done` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `text` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '备忘录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_todo
-- ----------------------------
INSERT INTO `sys_todo` VALUES ('1dcba433dcff57f17f82d1bbdabadfd4', '2022-11-29 17:01:56', '2023-08-07 09:46:12', '1', '网站备案');
INSERT INTO `sys_todo` VALUES ('7fbf8cdf5b49cf0f6362d4f64b55feb0', '2022-12-07 14:54:07', '2024-07-09 16:42:11', '0', '资源前台展示');
INSERT INTO `sys_todo` VALUES ('ad59208e42473b57794d69034f4869da', '2022-12-07 15:09:05', '2023-01-06 09:02:24', '1', '项目日志');
INSERT INTO `sys_todo` VALUES ('b894ce623f551d8495be0fdaea64bb01', '2022-12-07 14:51:55', '2024-05-13 18:06:41', '1', '时间线开发');
INSERT INTO `sys_todo` VALUES ('c4762dec027acc74786eafca0d50a494', '2022-12-08 14:24:41', '2022-12-08 15:12:04', '1', '优化资源页显示');
INSERT INTO `sys_todo` VALUES ('ecb4e59a419d74dd1c9cfc56e9eb60d7', '2022-12-07 14:51:15', '2022-12-08 14:23:21', '1', '折线图开发');
INSERT INTO `sys_todo` VALUES ('f84a1b530e4f7eac8f74709d7a057bf3', '2022-12-07 14:52:04', '2022-12-16 16:58:55', '1', '项目文档');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `head_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门id',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '岗位id',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openId',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态（1：正常 0：停用）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别（0：男 1：女 ）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('149dd22e7c4e8b0f1121ac1d3718dd93', 'admin', '96e79218965eb72c92a549dd5a330112', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2024-05-09 11:40:34', '2024-05-16 16:02:49', '0', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_admin_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_web_information
-- ----------------------------
DROP TABLE IF EXISTS `sys_web_information`;
CREATE TABLE `sys_web_information`  (
  `id` int(11) NOT NULL COMMENT 'id',
  `web_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站名称',
  `web_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网站详情',
  `web_avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信',
  `github` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'github',
  `gitee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'gitee',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `home_banner` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '首页背景图',
  `article_banner` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章背景图',
  `wechat_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信二维码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `comment_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论头像',
  `record_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备案号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网站基本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_web_information
-- ----------------------------
INSERT INTO `sys_web_information` VALUES (1, '漫漫前路', '<p>长路漫漫，吾将上下而求索。</p>', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/16/de051c412f2f46ba92de7817496cb605.jpg', '946232976', 'fr946232976', 'https://github.com/Jzjzzzz', 'https://gitee.com/jzjzz', '2022-07-28 20:16:13', '2023-01-06 15:30:49', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/09/a108c42fa21a43a4940c3e08cfd33c4f.jpg', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/10/9d26abe18ec84eadbaa8b9f180fefee6.jpg', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/08/f2330c187a6040ed8a74df5aae1cb763.jpg', '946232976@qq.com', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/19/1b0d32a2d72e4ed58b7cc6ede72621b0.png', '桂ICP备2022010904号-1');

SET FOREIGN_KEY_CHECKS = 1;
