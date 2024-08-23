/*
 Navicat Premium Data Transfer

 Source Server         : jdcenter
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : 117.72.47.88:3306
 Source Schema         : vue-system

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 23/08/2024 16:52:06
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
                              `gen_export` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否生成导出(0-不生成,1-生成)',
                              PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

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
                                     `is_export` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否生成导出(0-不生成,1-生成)',
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
) ENGINE = InnoDB AUTO_INCREMENT = 328 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------

-- ----------------------------
-- Table structure for oa_process
-- ----------------------------
DROP TABLE IF EXISTS `oa_process`;
CREATE TABLE `oa_process`  (
                               `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
                               `process_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '审批code',
                               `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '用户id',
                               `process_template_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批模板id',
                               `process_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批类型',
                               `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
                               `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                               `form_values` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '表单值',
                               `process_instance_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程实例id',
                               `current_auditor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前审批人',
                               `status` tinyint(3) NULL DEFAULT NULL COMMENT '状态（0：默认 1：审批中 2：审批通过 -1：驳回）',
                               `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '删除标记（0:不可用 1:可用）',
                               `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                               `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
                               `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
                               `process_template_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批模板名称',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批类型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oa_process
-- ----------------------------

-- ----------------------------
-- Table structure for oa_process_record
-- ----------------------------
DROP TABLE IF EXISTS `oa_process_record`;
CREATE TABLE `oa_process_record`  (
                                      `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
                                      `process_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '审批流程id',
                                      `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批描述',
                                      `status` tinyint(3) NULL DEFAULT 0 COMMENT '状态',
                                      `operate_user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '操作用户id',
                                      `operate_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
                                      `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `is_deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '删除标记（0:不可用 1:可用）',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oa_process_record
-- ----------------------------

-- ----------------------------
-- Table structure for oa_process_template
-- ----------------------------
DROP TABLE IF EXISTS `oa_process_template`;
CREATE TABLE `oa_process_template`  (
                                        `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色id',
                                        `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '模板名称',
                                        `icon_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标路径',
                                        `process_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审批类别',
                                        `form_props` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '表单属性',
                                        `form_options` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '表单选项',
                                        `process_definition_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程定义key',
                                        `process_definition_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程定义上传路径',
                                        `process_model_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '流程定义模型id',
                                        `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                                        `status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '状态',
                                        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `is_deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '删除标记（0:不可用 1:可用）',
                                        `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                        `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审批模板' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oa_process_template
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
) ENGINE = InnoDB AUTO_INCREMENT = 179 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `sys_dict_data` VALUES (169, 0, '正常', '1', 'currency_status', NULL, 'primary', 'N', '0', 'admin', '2024-07-18 16:34:31', 'admin', '2024-07-24 17:09:52', '正常');
INSERT INTO `sys_dict_data` VALUES (170, 0, '停用', '0', 'currency_status', NULL, 'danger', 'N', '0', 'admin', '2024-07-18 16:34:48', 'admin', '2024-07-24 17:10:02', '停用');
INSERT INTO `sys_dict_data` VALUES (171, 0, '财务', '0', 'sys_oa_type', NULL, 'primary', 'N', '0', 'admin', '2024-07-30 10:06:49', '', NULL, '财务');
INSERT INTO `sys_dict_data` VALUES (172, 0, '人事', '1', 'sys_oa_type', NULL, 'primary', 'N', '0', 'admin', '2024-07-30 10:07:08', 'admin', '2024-07-30 10:07:50', '人事');
INSERT INTO `sys_dict_data` VALUES (173, 0, '出勤', '2', 'sys_oa_type', NULL, 'primary', 'N', '0', 'admin', '2024-07-30 10:07:32', 'admin', '2024-07-30 10:07:56', '出勤');
INSERT INTO `sys_dict_data` VALUES (174, 0, '初始', '0', 'oa_template_status', NULL, 'info', 'N', '0', 'admin', '2024-08-01 14:36:39', '', NULL, '初始');
INSERT INTO `sys_dict_data` VALUES (175, 0, '发布', '1', 'oa_template_status', NULL, 'success', 'N', '0', 'admin', '2024-08-01 14:36:54', 'admin', '2024-08-01 14:37:00', '发布');
INSERT INTO `sys_dict_data` VALUES (176, 0, '驳回', '-1', 'oa_approval_status', NULL, 'danger', 'N', '0', 'admin', '2024-08-01 15:19:43', 'admin', '2024-08-01 15:20:46', '驳回');
INSERT INTO `sys_dict_data` VALUES (177, 0, '进行中', '1', 'oa_approval_status', NULL, 'primary', 'N', '0', 'admin', '2024-08-01 15:20:19', 'admin', '2024-08-01 15:20:53', '进行中');
INSERT INTO `sys_dict_data` VALUES (178, 0, '已完成', '2', 'oa_approval_status', NULL, 'success', 'N', '0', 'admin', '2024-08-01 15:20:35', 'admin', '2024-08-01 15:20:58', '已完成');

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
) ENGINE = InnoDB AUTO_INCREMENT = 114 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `sys_dict_type` VALUES (110, '通用状态', 'currency_status', '0', 'admin', '2024-07-18 16:34:13', '', NULL, '通用状态');
INSERT INTO `sys_dict_type` VALUES (111, '审批类型', 'sys_oa_type', '0', 'admin', '2024-07-30 10:06:17', '', NULL, '审批类型');
INSERT INTO `sys_dict_type` VALUES (112, '审批模板状态', 'oa_template_status', '0', 'admin', '2024-08-01 14:36:05', '', NULL, '审批模板状态');
INSERT INTO `sys_dict_type` VALUES (113, '审批状态', 'oa_approval_status', '0', 'admin', '2024-08-01 15:18:29', '', NULL, '审批状态');

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
                             `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                             `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
                             PRIMARY KEY (`id`) USING BTREE,
                             INDEX `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0186f5e32d71f917bd4cb4d693346cde', '8be50fe7341b636c2115ea7c9cf36854', '列表', 2, '', '', 'btn.config.list', '', 1, '1', '2024-05-11 17:50:49', '2024-05-11 17:50:49', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('05fa1dd782ad6b196769e9d9585ff6f3', '1c2d6c42822884c157897af502ca251e', '编辑', 2, '/tool/gen-edit/index/:tableId', 'tool/gen/editTable', 'btn.gen.edit', '', 1, '1', '2024-07-18 16:36:06', '2024-07-19 17:02:42', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('0c2f7bcab57cd177ddce93fa5057c993', '1c2d6c42822884c157897af502ca251e', '创建表', 2, '', '', 'btn.gen.sql', '', 1, '1', '2024-07-19 17:10:09', '2024-07-19 17:10:09', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('1298ab16f667edbece17a2b73cf51fc0', '8be50fe7341b636c2115ea7c9cf36854', '删除', 2, '', '', 'btn.config.del', '', 1, '1', '2024-05-11 17:50:33', '2024-05-11 17:50:33', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('16e3dc94b0a0b0ab3eb491d4e172295d', 'be190c8cd6238ea9119a0befa954cf7a', '网站信息', 1, 'information', 'system/information/index', '', 'el-icon-s-platform', 8, '1', '2024-05-08 10:42:56', '2024-05-08 10:46:20', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('1c2d6c42822884c157897af502ca251e', 'be190c8cd6238ea9119a0befa954cf7a', '代码生成', 1, 'gen', 'tool/gen/index', '', 'el-icon-s-flag', 2, '1', '2024-07-18 16:32:30', '2024-07-24 11:15:39', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('240307c217e98687e93b05c16a5424aa', '8be50fe7341b636c2115ea7c9cf36854', '修改', 2, '', '', 'btn.config.edit', '', 1, '1', '2024-05-11 17:50:21', '2024-05-11 17:50:21', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('2d812ec5ddaedd70c93a2f78ab984334', '5ef83c1291ff3e1f435c3dffbf1be55a', '删除', 2, '', '', 'btn.notice.del', '', 1, '1', '2024-05-11 17:34:43', '2024-05-11 17:34:43', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('30318a16f713de861d99a789e0caac77', '85413b9370843cdb477dfbabf62bf6e5', '删除', 2, '', '', 'btn.dict.del', '', 1, '1', '2024-05-11 17:39:28', '2024-05-11 17:39:28', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('3aac4a4b0b7284bc52b5308e776d14ea', '440867f56dbf23944789d672b158bd32', '删除', 2, '', '', 'btn.role.del', '', 1, '1', '2024-05-11 17:14:33', '2024-05-11 17:14:33', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('3d1d3a7f07e06ed44d8c451125b42963', 'e4dca1309de7f2d9efa1f09beb5ae34a', '删除', 2, '', '', 'btn.user.del', '', 1, '1', '2024-05-11 17:59:49', '2024-05-11 17:59:49', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('40249e60756abe740b657da8c214aecd', '84ee2d5d26c4179de53924559120e588', '审批列表', 1, 'approval', 'oa/work/approval', '', 'el-icon-s-flag', 1, '1', '2024-08-05 16:05:37', '2024-08-06 10:25:51', '0', 'admin', 'lisi');
INSERT INTO `sys_menu` VALUES ('41b3f4ce407fa92523593c6edda2dab8', 'e4dca1309de7f2d9efa1f09beb5ae34a', '修改', 2, '', '', 'btn.user.edit', '', 1, '1', '2024-05-11 17:59:35', '2024-05-11 17:59:35', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('440867f56dbf23944789d672b158bd32', 'be190c8cd6238ea9119a0befa954cf7a', '角色管理', 1, 'role', 'system/role/index', '', 'el-icon-s-help', 2, '1', '2024-05-08 10:39:34', '2024-05-08 10:39:45', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('471c8a9a73d0e2bfa59eb40adffb3869', 'be190c8cd6238ea9119a0befa954cf7a', '菜单管理', 1, 'menu', 'system/menu/index', '', 'el-icon-menu', 3, '1', '2024-05-08 10:40:21', '2024-05-08 10:40:21', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('4cfb361d436950364b6f99cf12501425', 'b602e401093b6a40da8dc0101570bcf9', '列表', 2, '', '', 'btn.job.list', '', 1, '1', '2024-05-13 09:23:09', '2024-05-13 09:23:09', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5c98ab7391bde87525c3a41a4d0e72cf', 'e4dca1309de7f2d9efa1f09beb5ae34a', '列表', 2, '', '', 'btn.user.list', '', 1, '1', '2024-05-11 18:00:01', '2024-05-11 18:00:01', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5e2fd2434fd811ef84f5e8808875d329', '83678ff8550841aa9d24dc830f8a4008', '审批管理', 1, 'process', 'oa/process/index', '', 'el-icon-tickets', 1, '1', '2024-08-01 15:33:36', '2024-08-05 15:43:47', '0', NULL, 'admin');
INSERT INTO `sys_menu` VALUES ('5e307b174fd811ef84f5e8808875d329', '5e2fd2434fd811ef84f5e8808875d329', '导出', 2, '', '', 'oa.process.export', '', 1, '1', '2024-08-01 15:33:36', '2024-08-01 15:33:36', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5e30aba64fd811ef84f5e8808875d329', '5e2fd2434fd811ef84f5e8808875d329', '删除', 2, '', '', 'oa.process.remove', '', 1, '1', '2024-08-01 15:33:36', '2024-08-01 15:33:36', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5e30ddc14fd811ef84f5e8808875d329', '5e2fd2434fd811ef84f5e8808875d329', '修改', 2, '', '', 'oa.process.edit', '', 1, '1', '2024-08-01 15:33:36', '2024-08-01 15:33:36', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5e3112f64fd811ef84f5e8808875d329', '5e2fd2434fd811ef84f5e8808875d329', '新增', 2, '', '', 'oa.process.add', '', 1, '1', '2024-08-01 15:33:36', '2024-08-01 15:33:36', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5e3140234fd811ef84f5e8808875d329', '5e2fd2434fd811ef84f5e8808875d329', '列表', 2, '', '', 'oa.process.list', '', 1, '1', '2024-08-01 15:33:36', '2024-08-01 15:33:36', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5e567f2c0646723f318be3290811da6b', '440867f56dbf23944789d672b158bd32', '新增', 2, '', '', 'btn.role.add', '', 1, '1', '2024-05-11 17:14:04', '2024-05-11 17:14:04', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5ea2d48af011f309547f007d5cabbd27', '471c8a9a73d0e2bfa59eb40adffb3869', '列表', 2, '', '', 'btn.menu.list', '', 1, '1', '2024-05-11 17:28:42', '2024-05-11 17:28:42', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('5ef83c1291ff3e1f435c3dffbf1be55a', 'be190c8cd6238ea9119a0befa954cf7a', '公告管理', 1, 'notice', 'system/notice/index', '', 'el-icon-warning-outline', 6, '1', '2024-05-08 10:43:22', '2024-05-08 10:46:06', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('6092364759758c2cd63808bdceba4405', '471c8a9a73d0e2bfa59eb40adffb3869', '删除', 2, '', '', 'btn.menu.del', '', 1, '1', '2024-05-11 17:28:29', '2024-05-11 17:28:29', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('6491c0ddc37084a317b7bf0d5c4817cb', '8be50fe7341b636c2115ea7c9cf36854', '新增', 2, '', '', 'btn.config.add', '', 1, '1', '2024-05-11 17:49:55', '2024-05-11 17:49:55', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('6be6b8ccf9fba11ce330f2efaeb51c94', 'b602e401093b6a40da8dc0101570bcf9', '新增', 2, '', '', 'btn.job.add', '', 1, '1', '2024-05-13 09:22:21', '2024-05-13 09:22:21', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('702458974e1911efbe46e8808875d329', '83678ff8550841aa9d24dc830f8a4008', '审批模板', 1, 'template', 'oa/template/index', '', 'el-icon-tickets', 2, '1', '2024-07-30 10:14:23', '2024-08-06 15:02:43', '0', NULL, 'admin');
INSERT INTO `sys_menu` VALUES ('7024a1c24e1911efbe46e8808875d329', '702458974e1911efbe46e8808875d329', '导出', 2, '', '', 'oa.template.export', '', 1, '1', '2024-07-30 10:14:23', '2024-07-30 10:14:23', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('7024c8ae4e1911efbe46e8808875d329', '702458974e1911efbe46e8808875d329', '删除', 2, '', '', 'oa.template.remove', '', 1, '1', '2024-07-30 10:14:23', '2024-07-30 10:14:23', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('7024fe154e1911efbe46e8808875d329', '702458974e1911efbe46e8808875d329', '修改', 2, '', '', 'oa.template.edit', '', 1, '1', '2024-07-30 10:14:23', '2024-07-30 10:14:23', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('702523e64e1911efbe46e8808875d329', '702458974e1911efbe46e8808875d329', '新增', 2, 'templateSet', 'oa/template/templateSet', 'oa.template.add', '', 1, '1', '2024-07-30 10:14:23', '2024-07-30 10:35:09', '0', NULL, 'admin');
INSERT INTO `sys_menu` VALUES ('70254cfb4e1911efbe46e8808875d329', '702458974e1911efbe46e8808875d329', '列表', 2, '', '', 'oa.template.list', '', 1, '1', '2024-07-30 10:14:23', '2024-07-30 10:14:23', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('77b30e9d5b7f261153c67947ae97963e', '471c8a9a73d0e2bfa59eb40adffb3869', '新增', 2, '', '', 'btn.menu.add', '', 1, '1', '2024-05-11 17:27:57', '2024-05-11 17:27:57', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('7e6e754ee2d1f2e0339b5a275a45acdd', 'dae1346779d28f39a06d1e1ce3026781', '列表', 2, '', '', 'btn.operlog.list', '', 1, '1', '2024-05-13 09:29:56', '2024-05-13 09:29:56', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('808ad31a8b9f1e3ebf6fb9b07e5448b5', '702458974e1911efbe46e8808875d329', '发布', 2, '', '', 'oa.template.publish', '', 1, '1', '2024-08-05 10:01:58', '2024-08-05 10:01:58', '0', 'admin', NULL);
INSERT INTO `sys_menu` VALUES ('83678ff8550841aa9d24dc830f8a4008', '0', '流程管理', 0, 'oa/', 'Layout', '', 'el-icon-menu', 1, '1', '2024-07-26 14:07:42', '2024-08-06 15:01:58', '0', 'admin', 'admin');
INSERT INTO `sys_menu` VALUES ('84ee2d5d26c4179de53924559120e588', '0', '流程申请', 0, 'work/', 'Layout', '', 'el-icon-document-remove', 2, '1', '2024-08-05 15:58:30', '2024-08-06 15:02:17', '0', 'admin', 'admin');
INSERT INTO `sys_menu` VALUES ('85413b9370843cdb477dfbabf62bf6e5', 'be190c8cd6238ea9119a0befa954cf7a', '字典分类', 1, 'dict', 'system/dict/index', '', 'el-icon-tickets', 4, '1', '2024-05-08 10:40:46', '2024-05-08 10:45:53', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('8be50fe7341b636c2115ea7c9cf36854', 'be190c8cd6238ea9119a0befa954cf7a', '参数设置', 1, 'config', 'system/config/index', '', 'el-icon-setting', 7, '1', '2024-05-08 10:41:52', '2024-05-08 10:46:10', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('90afc646c4b5570f592038959f1e654a', 'e4dca1309de7f2d9efa1f09beb5ae34a', '强制下线', 2, '', '', 'btn.user.off', '', 1, '1', '2024-07-26 14:11:42', '2024-07-26 14:11:42', '0', 'admin', NULL);
INSERT INTO `sys_menu` VALUES ('a14e227cf950c10bc96bfc9061487a87', '1c2d6c42822884c157897af502ca251e', '生成代码', 2, '', '', 'btn.gen.gen', '', 1, '1', '2024-07-19 17:07:46', '2024-07-19 17:07:46', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('a1cf74abe6ef8075975281c08bfe1895', '85413b9370843cdb477dfbabf62bf6e5', '字典数据', 2, 'data/:id(\\d+)', 'system/dict/data', 'btn.dict.data', '', 1, '1', '2024-05-08 10:41:13', '2024-05-08 15:52:27', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('a2cad0fca07077b15c41e7bb2cfc967c', 'df5894ce47de7c972d3507865a47e99d', '申请', 2, '', '', 'oa.work.apply', '', 1, '1', '2024-08-05 16:03:26', '2024-08-05 16:03:26', '0', 'admin', NULL);
INSERT INTO `sys_menu` VALUES ('a3a7a0cf00f49ca90765dd869d5ce399', '85413b9370843cdb477dfbabf62bf6e5', '列表', 2, '', '', 'btn.dict.list', '', 1, '1', '2024-05-11 17:39:55', '2024-05-11 17:39:55', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('a88286a2308fb14175868ded546953b7', 'dae1346779d28f39a06d1e1ce3026781', '删除', 2, '', '', 'btn.operlog.del', '', 1, '1', '2024-05-13 09:29:37', '2024-05-13 09:29:37', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('af19e30c6aa4f2fa195918528273aa6d', '5ef83c1291ff3e1f435c3dffbf1be55a', '列表', 2, '', '', 'btn.notice.list', '', 1, '1', '2024-05-11 17:35:01', '2024-05-11 17:35:01', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('af1ddec5003e789ea5d167e7b8b0a773', '16e3dc94b0a0b0ab3eb491d4e172295d', '修改', 2, '', '', 'btn.information.edit', '', 1, '1', '2024-05-11 17:11:14', '2024-05-11 17:11:14', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('afef72e3ee28af35f2de19ba4c1dc88e', 'b602e401093b6a40da8dc0101570bcf9', '修改', 2, '', '', 'btn.job.edit', '', 1, '1', '2024-05-13 09:22:44', '2024-05-13 09:22:44', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('b602e401093b6a40da8dc0101570bcf9', 'be190c8cd6238ea9119a0befa954cf7a', '定时任务', 1, 'job', 'system/job/index', '', 'el-icon-s-unfold', 5, '1', '2024-05-08 10:42:17', '2024-05-08 10:45:59', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('b9d414c8de0b6ba6e2c44ab59e0ce8a9', '1c2d6c42822884c157897af502ca251e', '删除', 2, '', '', 'btn.gen.del', '', 1, '1', '2024-07-19 17:04:43', '2024-07-19 17:04:43', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('ba5f42390d98d1ac546119627cec0b37', '5ef83c1291ff3e1f435c3dffbf1be55a', '新增', 2, '', '', 'btn.notice.add', '', 1, '1', '2024-05-11 17:34:10', '2024-05-11 17:34:10', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('be190c8cd6238ea9119a0befa954cf7a', '0', '系统管理', 0, 'system/', 'Layout', '', 'el-icon-s-tools', 6, '1', '2024-05-08 10:33:07', '2024-07-24 11:16:28', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('cd0f17b0d5ff3de2ddc974e07681f13e', '85413b9370843cdb477dfbabf62bf6e5', '修改', 2, '', '', 'btn.dict.edit', '', 1, '1', '2024-05-11 17:39:15', '2024-05-11 17:39:15', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('d68167181026ce9241d1c26c0dbf0720', 'df5894ce47de7c972d3507865a47e99d', '列表', 2, '', '', 'oa.work.list', '', 1, '1', '2024-08-05 16:03:05', '2024-08-05 16:03:05', '0', 'admin', NULL);
INSERT INTO `sys_menu` VALUES ('d839e3275f5fa4b708929c93f1bc531a', '5ef83c1291ff3e1f435c3dffbf1be55a', '修改', 2, '', '', 'btn.notice.edit', '', 1, '1', '2024-05-11 17:34:34', '2024-05-11 17:34:34', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('dae1346779d28f39a06d1e1ce3026781', 'be190c8cd6238ea9119a0befa954cf7a', '日志管理', 1, 'operlog', 'monitor/operlog/index', '', 'el-icon-edit', 9, '1', '2024-05-08 10:43:53', '2024-05-08 10:46:26', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('db75d07485333f3a052cac79b54b7c59', 'e4dca1309de7f2d9efa1f09beb5ae34a', '新增', 2, '', '', 'btn.user.add', '', 1, '1', '2024-05-11 17:59:17', '2024-05-11 17:59:17', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('df5894ce47de7c972d3507865a47e99d', '84ee2d5d26c4179de53924559120e588', '流程列表', 1, 'work', 'oa/work/index', '', 'el-icon-tickets', 2, '1', '2024-08-05 16:02:31', '2024-08-06 15:03:18', '0', 'admin', 'admin');
INSERT INTO `sys_menu` VALUES ('e1466d34d8c97f3b0cd10370d95ebea3', '471c8a9a73d0e2bfa59eb40adffb3869', '修改', 2, '', '', 'btn.menu.edit', '', 1, '1', '2024-05-11 17:28:12', '2024-05-11 17:28:12', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('e18a1c6b71f13161ef1580f4fc2bd740', '440867f56dbf23944789d672b158bd32', '列表', 2, '', '', 'btn.role.list', '', 1, '1', '2024-05-11 17:14:55', '2024-05-11 17:14:55', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('e4dca1309de7f2d9efa1f09beb5ae34a', 'be190c8cd6238ea9119a0befa954cf7a', '用户管理', 1, 'user', 'system/user/index', '', 'el-icon-s-custom', 1, '1', '2024-05-08 10:36:58', '2024-05-08 10:36:58', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('efafe29a11921a7026b73d59dfcaa0b4', '440867f56dbf23944789d672b158bd32', '修改', 2, '', '', 'btn.role.edit', '', 1, '1', '2024-05-11 17:14:19', '2024-05-11 17:14:19', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('f0c2e6792c806012822fcef73c0b44c3', '1c2d6c42822884c157897af502ca251e', '同步', 2, '', '', 'btn.gen.sync', '', 1, '1', '2024-07-19 17:08:05', '2024-07-19 17:08:05', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('f60544908c9665fe8a7bbc8718531f99', 'b602e401093b6a40da8dc0101570bcf9', '删除', 2, '', '', 'btn.job.del', '', 1, '1', '2024-05-13 09:22:57', '2024-05-13 09:22:57', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('f61f9dc267e64d6fd6b509030ef9b756', '1c2d6c42822884c157897af502ca251e', '列表', 2, '', '', 'btn.gen.list', '', 1, '1', '2024-07-19 17:03:03', '2024-07-19 17:03:03', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('f65a794f7aaa73d994048c53a928fbec', '16e3dc94b0a0b0ab3eb491d4e172295d', '列表', 2, '', '', 'btn.information.list', '', 1, '1', '2024-05-11 17:11:37', '2024-05-11 17:11:37', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('f8bd0f8742c68ddfae6acaa2a26cef69', '1c2d6c42822884c157897af502ca251e', '导入', 2, '', '', 'btn.gen.import', '', 1, '1', '2024-07-19 17:03:54', '2024-07-19 17:04:20', '0', NULL, NULL);
INSERT INTO `sys_menu` VALUES ('ff635db54aeba2e9ca4968973347685e', '85413b9370843cdb477dfbabf62bf6e5', '新增', 2, '', '', 'btn.dict.add', '', 1, '1', '2024-05-11 17:38:57', '2024-05-11 17:38:57', '0', NULL, NULL);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
                               `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
                               `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
                               `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
                               `notice_content` longblob NULL COMMENT '公告内容',
                               `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
                               `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
                               `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                               `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
                               `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                               `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

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
                             `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
                             `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

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
                             `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                             `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
                             `is_super` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '是否是超级管理员',
                             PRIMARY KEY (`id`) USING BTREE,
                             UNIQUE INDEX `idx_username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('149dd22e7c4e8b0f1121ac1d3718dd93', 'admin', '96e79218965eb72c92a549dd5a330112', 'mmcl', '', NULL, NULL, NULL, NULL, NULL, '1', '2024-05-09 11:40:34', '2024-07-26 17:45:36', '0', '0', '', 'admin', '1');

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
-- Table structure for sys_todo
-- ----------------------------
DROP TABLE IF EXISTS `sys_todo`;
CREATE TABLE `sys_todo`  (
                             `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
                             `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
                             `done` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
                             `text` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
                             `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '备忘录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_todo
-- ----------------------------
INSERT INTO `sys_todo` VALUES ('1dcba433dcff57f17f82d1bbdabadfd4', '2022-11-29 17:01:56', '2023-08-07 09:46:12', '1', '网站备案', NULL);
INSERT INTO `sys_todo` VALUES ('7fbf8cdf5b49cf0f6362d4f64b55feb0', '2022-12-07 14:54:07', '2024-07-09 16:42:11', '0', '资源前台展示', NULL);
INSERT INTO `sys_todo` VALUES ('ad59208e42473b57794d69034f4869da', '2022-12-07 15:09:05', '2023-01-06 09:02:24', '1', '项目日志', NULL);
INSERT INTO `sys_todo` VALUES ('b894ce623f551d8495be0fdaea64bb01', '2022-12-07 14:51:55', '2024-05-13 18:06:41', '1', '时间线开发', NULL);
INSERT INTO `sys_todo` VALUES ('c4762dec027acc74786eafca0d50a494', '2022-12-08 14:24:41', '2022-12-08 15:12:04', '1', '优化资源页显示', NULL);
INSERT INTO `sys_todo` VALUES ('ecb4e59a419d74dd1c9cfc56e9eb60d7', '2022-12-07 14:51:15', '2022-12-08 14:23:21', '1', '折线图开发', NULL);
INSERT INTO `sys_todo` VALUES ('f84a1b530e4f7eac8f74709d7a057bf3', '2022-12-07 14:52:04', '2022-12-16 16:58:55', '1', '项目文档', NULL);


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
                                        `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '网站基本信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_web_information
-- ----------------------------
INSERT INTO `sys_web_information` VALUES (1, '漫漫前路', '<p>长路漫漫，吾将上下而求索。</p>', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/16/de051c412f2f46ba92de7817496cb605.jpg', '946232976', 'fr946232976', 'https://github.com/Jzjzzzz', 'https://gitee.com/jzjzz', '2022-07-28 20:16:13', '2023-01-06 15:30:49', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/09/a108c42fa21a43a4940c3e08cfd33c4f.jpg', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/10/9d26abe18ec84eadbaa8b9f180fefee6.jpg', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/08/f2330c187a6040ed8a74df5aae1cb763.jpg', '946232976@qq.com', 'https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/09/19/1b0d32a2d72e4ed58b7cc6ede72621b0.png', '桂ICP备2022010904号-1', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
