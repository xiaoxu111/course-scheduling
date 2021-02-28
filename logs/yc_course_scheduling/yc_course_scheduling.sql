/*
 Navicat Premium Data Transfer

 Source Server         : mysql主库
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : yc_course_scheduling

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 28/02/2021 20:14:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yc_admin
-- ----------------------------
DROP TABLE IF EXISTS `yc_admin`;
CREATE TABLE `yc_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `adminer_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员编号',
  `username` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `realname` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `user_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户类型',
  `job_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `license` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件照地址',
  `teach_subject` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教授科目',
  `telephone` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '管理员类型',
  `piority` int(2) NULL DEFAULT NULL COMMENT '优先级',
  `power` tinyint(1) NULL DEFAULT NULL COMMENT '1为管理员，0为超级管理员',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '账号状态',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '0 显示，1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_admin
-- ----------------------------
INSERT INTO `yc_admin` VALUES (1, '10011', 'lequal(梁老师)', '111111', '梁晓明', 1, '教务处副主任', NULL, '物理', '13677731235', 'teacher@guet.com', '广西', 32, NULL, '以身作则，教育好学生。', '务实', NULL, 1, 1, 0, 0, '2020-03-02 23:30:03', '2020-03-04 23:30:30');
INSERT INTO `yc_admin` VALUES (2, '10012', 'demo01', 'xu2021', '张三丰', 1, '教务处副主任', NULL, NULL, NULL, 'demo01@qq.com', '山东省寿光市', 23, NULL, '光线强的地方，影子也比较黑暗。', '零下1摄氏度', NULL, NULL, NULL, 0, 0, '2020-03-04 21:24:57', NULL);
INSERT INTO `yc_admin` VALUES (3, '10013', 'demo02', 'xu2021', '王五', 1, '教务处副主任', NULL, NULL, NULL, 'demo02@qq.com', '北京市海淀区', 45, NULL, '加油每一天', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for yc_class_info
-- ----------------------------
DROP TABLE IF EXISTS `yc_class_info`;
CREATE TABLE `yc_class_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id,班级表',
  `class_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级编号',
  `class_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `num` int(11) NOT NULL DEFAULT 0 COMMENT '班级人数',
  `teacher_id` int(11) NULL DEFAULT NULL COMMENT '班主任Id',
  `remark` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '直接用来做为年级编号的划分了',
  `deleted` tinyint(1) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_class_info
-- ----------------------------
INSERT INTO `yc_class_info` VALUES (1, '20200101', '20年高一1班', 0, 1, '01', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (2, '20200102', '20年高一2班', 10, 1, '01', 0, NULL, '2020-05-19 08:23:22');
INSERT INTO `yc_class_info` VALUES (3, '20200103', '20年高一3班', 20, 3, '01', 0, NULL, '2020-05-19 16:23:22');
INSERT INTO `yc_class_info` VALUES (4, '20200104', '20年高一4班', 50, 5, '01', 0, NULL, '2020-05-20 00:23:22');
INSERT INTO `yc_class_info` VALUES (5, '20200105', '20年高一5班', 20, 5, '01', 0, NULL, '2020-05-19 16:23:22');
INSERT INTO `yc_class_info` VALUES (6, '20200201', '20年高二1班', 0, 6, '02', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (7, '20200202', '20年高二2班', 0, 7, '02', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (8, '20200203', '20年高二3班', 0, 8, '02', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (9, '20200204', '20年高二4班', 0, 9, '02', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (10, '20200205', '20年高二5班', 0, 10, '02', 1, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (11, '20200301', '20年高三1班', 0, 11, '03', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (12, '20200302', '20年高三2班', 0, 12, '03', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (13, '20200303', '20年高三3班', 0, 13, '03', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (14, '20200304', '20年高三4班', 0, 14, '03', 1, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (15, '20200305', '20年高三5班', 0, 15, '03', 0, NULL, '2020-05-20 16:23:22');
INSERT INTO `yc_class_info` VALUES (24, '20200106', '高一6班-测试班级', 30, 41, '01', 0, '2020-06-11 00:43:07', '2021-01-23 07:07:27');
INSERT INTO `yc_class_info` VALUES (25, '202101102', '21年高一2班', 60, 7, '01', 0, '2021-02-13 08:59:39', NULL);
INSERT INTO `yc_class_info` VALUES (26, '202102103', '测试002', 0, 1, '01', 0, '2021-02-14 04:44:58', NULL);
INSERT INTO `yc_class_info` VALUES (27, '202103103', '测试003', 50, 7, '03', 0, '2021-02-13 12:49:52', '2021-02-13 20:50:09');

-- ----------------------------
-- Table structure for yc_class_room
-- ----------------------------
DROP TABLE IF EXISTS `yc_class_room`;
CREATE TABLE `yc_class_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教室id',
  `class_room_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室编号',
  `class_room_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教室名称',
  `teachbuild_no` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在教学楼编号',
  `capacity` int(11) NOT NULL COMMENT '教室人数容量',
  `attribute` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教室属性',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除（默认0显示，1删除）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教室表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_class_room
-- ----------------------------
INSERT INTO `yc_class_room` VALUES (1, '01-101', '01-101', '01', 50, '01', '备注', 0, NULL, '2020-04-10 14:21:48');
INSERT INTO `yc_class_room` VALUES (2, '01-102', '01-102', '01', 50, '01', '完美00000111', 0, NULL, '2021-02-28 09:30:55');
INSERT INTO `yc_class_room` VALUES (3, '01-103', '01-103', '01', 50, '01', '很开心0000', 0, NULL, '2021-02-28 09:31:04');
INSERT INTO `yc_class_room` VALUES (4, '01-104', '01-104', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (5, '01-105', '01-105', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (6, '01-201', '01-201', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (7, '01-202', '01-202', '01', 50, '01', '测试编辑功能', 0, NULL, '2021-02-27 06:25:46');
INSERT INTO `yc_class_room` VALUES (8, '01-203', '01-203', '01', 50, '01', '测试---', 0, NULL, '2021-02-28 09:30:25');
INSERT INTO `yc_class_room` VALUES (9, '01-204', '01-204', '01', 50, '01', '哈哈哈哈', 0, NULL, '2021-02-28 09:30:33');
INSERT INTO `yc_class_room` VALUES (10, '01-205', '01-205', '01', 50, '01', '准备齐活', 0, NULL, '2021-02-28 09:30:45');
INSERT INTO `yc_class_room` VALUES (11, '01-301', '01-301', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (12, '01-302', '01-302', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (13, '01-303', '01-303', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (14, '01-304', '01-304', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (15, '01-305', '01-305', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (16, '01-401', '01-401', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (17, '01-402', '01-402', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (18, '01-403', '01-403', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (19, '01-404', '01-404', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (20, '01-405', '01-405', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (21, '01-501', '01-501', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (22, '01-502', '01-502', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (23, '01-503', '01-503', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (24, '01-504', '01-504', '01', 50, '01', NULL, 0, '2020-03-19 12:32:18', '2020-03-12 12:32:21');
INSERT INTO `yc_class_room` VALUES (25, '01-505', '01-505', '01', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (26, '02-101', '02-101', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (27, '02-102', '02-102', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (28, '02-103', '02-103', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (29, '02-104', '02-104', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (30, '02-105', '02-105', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (31, '02-201', '02-202', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (32, '02-202', '02-202', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (33, '02-203', '02-203', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (34, '02-204', '02-204', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (35, '02-205', '02-205', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (36, '02-301', '02-301', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (37, '02-302', '02-302', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (38, '02-303', '02-303', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (39, '02-304', '02-304', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (40, '02-305', '02-305', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (41, '02-401', '02-401', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (42, '02-402', '02-402', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (43, '02-403', '02-403', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (44, '02-404', '02-404', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (45, '02-405', '02-405', '02', 60, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (46, '03-101', '03-101', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (47, '03-102', '03-102', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (48, '03-103', '03-103', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (49, '03-104', '03-104', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (50, '03-105', '03-105', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (51, '03-201', '03-201', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (52, '03-202', '03-202', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (53, '03-203', '03-203', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (54, '03-204', '03-204', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (55, '03-205', '03-205', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (56, '03-301', '03-301', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (57, '03-302', '03-302', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (58, '03-303', '03-303', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (59, '03-304', '03-304', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (60, '03-305', '03-305', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (61, '03-401', '03-401', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (62, '03-402', '03-402', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (63, '03-403', '03-403', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (64, '03-404', '03-404', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (65, '03-405', '03-405', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (66, '03-501', '03-501', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (67, '03-502', '03-502', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (68, '03-503', '03-503', '03', 50, '01', '', 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (69, '03-504', '03-504', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (70, '03-505', '03-505', '03', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (71, '04-101', '04-101', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (72, '04-102', '04-102', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (73, '04-103', '04-103', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (74, '04-104', '04-104', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (75, '04-105', '04-105', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (76, '04-201', '04-201', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (77, '04-202', '04-202', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (78, '04-203', '04-203', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (79, '04-204', '04-204', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (80, '04-205', '04-205', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (81, '04-301', '04-301', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (82, '04-302', '04-302', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (83, '04-303', '04-303', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (84, '04-304', '04-304', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (85, '04-305', '04-305', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (86, '04-401', '04-401', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (87, '04-402', '04-402', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (88, '04-403', '04-403', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (99, '04-404', '04-404', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (100, '04-405', '04-405', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (101, '04-501', '04-501', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (102, '04-502', '04-502', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (103, '04-503', '04-503', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (104, '04-504', '04-504', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (105, '04-505', '04-505', '04', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (106, '05-101', '05-101', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (107, '05-102', '05-102', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (108, '05-103', '05-103', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (109, '05-104', '05-104', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (110, '05-105', '05-105', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (111, '05-201', '05-201', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (112, '05-202', '05-202', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (113, '05-203', '05-203', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (114, '05-204', '05-204', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (115, '05-205', '05-205', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (116, '05-301', '05-301', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (117, '05-302', '05-302', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (118, '05-303', '05-303', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (119, '05-304', '05-304', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (120, '05-305', '05-305', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (121, '05-401', '05-401', '05', 50, '01', '', 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (122, '05-402', '05-402', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (123, '05-403', '05-403', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (124, '05-404', '05-404', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (125, '05-405', '05-405', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (126, '05-501', '05-501', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (127, '05-502', '05-502', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (128, '05-503', '05-503', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (129, '05-504', '05-504', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (130, '05-505', '05-505', '05', 50, '01', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (133, '12-101', '12-101', '12', 120, '04', '体育楼', 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (134, '12-102', '12-102', '12', 120, '04', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (135, '12-103', '12-103', '12', 120, '04', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (136, '12-104', '12-104', '12', 120, '04', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (137, '12-201', '12-201', '12', 120, '04', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (138, '12-202', '12-202', '12', 120, '04', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (139, '12-203', '12-203', '12', 120, '04', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (140, '12-204', '12-204', '12', 120, '04', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (141, '08-101', '08-101', '08', 50, '03', '实验楼', 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (142, '08-102', '08-102', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (143, '08-103', '08-103', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (144, '08-104', '08-104', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (145, '08-105', '08-105', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (146, '08-201', '08-201', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (147, '08-202', '08-202', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (148, '08-203', '08-203', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (149, '08-204', '08-204', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (150, '08-205', '08-205', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (151, '08-301', '08-301', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (152, '08-302', '08-302', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (153, '08-303', '08-303', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (154, '08-304', '08-304', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (155, '08-305', '08-305', '08', 50, '03', NULL, 0, NULL, NULL);
INSERT INTO `yc_class_room` VALUES (156, '111', '111', '01', 111, NULL, '111', 1, '2021-02-27 07:18:23', NULL);

-- ----------------------------
-- Table structure for yc_class_task
-- ----------------------------
DROP TABLE IF EXISTS `yc_class_task`;
CREATE TABLE `yc_class_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id，即将要上课的，需要进行排课的',
  `semester` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学期',
  `grade_no` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年级编号',
  `class_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级编号',
  `course_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `course_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `teacher_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师编号',
  `realname` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师姓名',
  `course_attr` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程属性',
  `student_num` int(11) NOT NULL COMMENT '学生人数',
  `weeks_sum` int(3) NOT NULL COMMENT '周数',
  `weeks_number` int(11) NOT NULL COMMENT '周学时',
  `is_fix` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否固定上课时间',
  `class_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定时间的话,2位为一个时间位置',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '0 显示，1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程计划表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_class_task
-- ----------------------------
INSERT INTO `yc_class_task` VALUES (1, '2019-2020-1', '01', '20200101', '100001', '高一语文必修1', '10010', '梁晓明', '01', 42, 20, 6, '1', NULL, 0, '2020-06-11 10:25:35', NULL);
INSERT INTO `yc_class_task` VALUES (2, '2019-2020-1', '01', '20200101', '100033', '高一数学必修1', '10012', '李雪雪', '01', 37, 20, 6, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (3, '2019-2020-1', '01', '20200101', '100056', '高一英语必修1', '10013', '王小芳', '01', 39, 20, 6, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (4, '2019-2020-1', '01', '20200101', '100004', '高一物理1', '10025', '张德良', '02', 42, 20, 4, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (5, '2019-2020-1', '01', '20200101', '100014', '高一化学必修1', '10033', '韩云', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (6, '2019-2020-1', '01', '20200101', '100041', '高一思想政治必修1', '10045', '江大波', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (7, '2019-2020-1', '01', '20200101', '100021', '高一历史必修1', '10044', '吴天盛', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (8, '2019-2020-1', '01', '20200101', '100007', '高一地理必修1', '10043', '王杰', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (9, '2019-2020-1', '01', '20200101', '100027', '高一生物必修1：分子与细胞', '10042', '谭咏麟', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (10, '2019-2020-1', '01', '20200101', '100051', '体育课', '10041', '张杰', '04', 40, 20, 2, '2', '14', 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (11, '2019-2020-1', '01', '20200101', '100066', '物理实验', '10025', '张德良', '03', 40, 20, 2, '2', '04', 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (12, '2019-2020-1', '01', '20200101', '100067', '化学实验', '10023', '张靓颖', '03', 40, 20, 2, '2', '15', 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (13, '2019-2020-1', '01', '20200102', '100001', '高一语文必修1', '10010', '梁晓明', '01', 42, 20, 6, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (14, '2019-2020-1', '01', '20200102', '100033', '高一数学必修1', '10012', '李雪雪', '01', 37, 20, 6, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (15, '2019-2020-1', '01', '20200102', '100056', '高一英语必修1', '10013', '王小芳', '01', 39, 20, 6, '1', NULL, 0, '2020-06-11 10:25:36', NULL);
INSERT INTO `yc_class_task` VALUES (16, '2019-2020-1', '01', '20200102', '100004', '高一物理1', '10025', '张德良', '02', 42, 20, 2, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (17, '2019-2020-1', '01', '20200102', '100014', '高一化学必修1', '10033', '韩云', '02', 40, 20, 2, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (18, '2019-2020-1', '01', '20200102', '100041', '高一思想政治必修1', '10045', '江大波', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (19, '2019-2020-1', '01', '20200102', '100021', '高一历史必修1', '10044', '吴天盛', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (20, '2019-2020-1', '01', '20200102', '100007', '高一地理必修1', '10043', '王杰', '02', 40, 20, 4, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (21, '2019-2020-1', '01', '20200102', '100027', '高一生物必修1：分子与细胞', '10042', '谭咏麟', '02', 40, 20, 2, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (22, '2019-2020-1', '01', '20200102', '100051', '体育课', '10041', '张杰', '04', 40, 20, 2, '2', '19', 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (23, '2019-2020-1', '01', '20200102', '100066', '物理实验', '10025', '张德良', '03', 40, 20, 2, '2', '09', 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (24, '2019-2020-1', '01', '20200102', '100067', '化学实验', '10023', '张靓颖', '03', 40, 20, 2, '2', '20', 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (25, '2019-2020-1', '01', '20200103', '100001', '高一语文必修1', '10034', '韦雪琪', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (26, '2019-2020-1', '01', '20200103', '100003', '高一数学1', '10035', '张三封', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (27, '2019-2020-1', '01', '20200103', '100056', '高一英语必修1', '10029', '郑小红', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (28, '2019-2020-1', '01', '20200103', '100004', '高一物理1', '10025', '张德良', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (29, '2019-2020-1', '01', '20200103', '100015', '高一化学必修2', '10037', '莫小新', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (30, '2019-2020-1', '01', '20200103', '100028', '高一生物必修2：遗传与进化', '10038', '甘楠', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (31, '2019-2020-1', '01', '20200103', '100022', '高一历史必修2', '10036', '胡小小', '02', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:37', NULL);
INSERT INTO `yc_class_task` VALUES (32, '2019-2020-1', '01', '20200103', '100008', '高一地理必修2', '10031', '张小龙', '02', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (33, '2019-2020-1', '01', '20200103', '100042', '高一思想政治必修2', '10040', '夏紫若', '02', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (34, '2019-2020-1', '01', '20200103', '100062', '信息与技术1', '10039', '江晓东', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (35, '2019-2020-1', '01', '20200103', '100051', '体育课', '10041', '张杰', '04', 45, 20, 2, '2', '15', 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (36, '2019-2020-1', '01', '20200103', '100066', '物理实验', '10025', '张德良', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (37, '2019-2020-1', '01', '20200103', '100067', '化学实验', '10023', '张靓颖', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (38, '2019-2020-1', '01', '20200104', '100004', '高一物理1', '10026', '张勇', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (39, '2019-2020-1', '01', '20200104', '100070', '语文', '10028', '马芸', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (40, '2019-2020-1', '01', '20200104', '100071', '数学', '10049', '王刚', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (41, '2019-2020-1', '01', '20200104', '100072', '英语', '10040', '夏紫若', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (42, '2019-2020-1', '01', '20200104', '100073', '化学', '10020', '胡冬梅', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (43, '2019-2020-1', '01', '20200104', '100074', '政治', '10019', '汪莉莉', '02', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (44, '2019-2020-1', '01', '20200104', '100075', '地理', '10024', '侯德南', '02', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (45, '2019-2020-1', '01', '20200104', '100076', '生物', '10018', '梁忠诚', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (46, '2019-2020-1', '01', '20200104', '100066', '物理实验', '10025', '张德良', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (47, '2019-2020-1', '01', '20200104', '100067', '化学实验', '10021', '林俊杰', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:38', NULL);
INSERT INTO `yc_class_task` VALUES (48, '2019-2020-1', '01', '20200104', '100051', '体育课', '10015', '孙振东', '04', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (49, '2019-2020-1', '01', '20200104', '100063', '信息技术2', '10032', '谭晓江', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (50, '2019-2020-1', '01', '20200105', '100070', '语文', '10028', '马芸', '01', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (51, '2019-2020-1', '01', '20200105', '100071', '数学', '10049', '王刚', '01', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (52, '2019-2020-1', '01', '20200105', '100072', '英语', '10040', '夏紫若', '01', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (53, '2019-2020-1', '01', '20200105', '100004', '高一物理1', '10026', '张勇', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (54, '2019-2020-1', '01', '20200105', '100073', '化学', '10020', '胡冬梅', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (55, '2019-2020-1', '01', '20200105', '100076', '生物', '10018', '梁忠诚', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (56, '2019-2020-1', '01', '20200105', '100066', '物理实验', '10025', '张德良', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (57, '2019-2020-1', '01', '20200105', '100067', '化学实验', '10021', '林俊杰', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (58, '2019-2020-1', '01', '20200105', '100051', '体育课', '10015', '孙振东', '04', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (59, '2019-2020-1', '01', '20200105', '100063', '信息技术2', '10032', '谭晓江', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (60, '2019-2020-1', '01', '20200105', '100074', '政治', '10019', '汪莉莉', '02', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (61, '2019-2020-1', '01', '20200105', '100075', '地理', '10024', '侯德南', '02', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (62, '2019-2020-1', '01', '20200105', '100076', '生物', '10018', '梁忠诚', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (63, '2019-2020-1', '01', '20200201', '100005', '高二语文必修5', '10019', '汪莉莉', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:39', NULL);
INSERT INTO `yc_class_task` VALUES (64, '2019-2020-1', '01', '20200201', '100017', '高二化学选修2', '10050', '黄三毛', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (65, '2019-2020-1', '01', '20200201', '100029', '高二生物必修3：稳态与环境', '10031', '张小龙', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (66, '2019-2020-1', '01', '20200201', '100035', '高二数学必修3', '10051', '燕双鹰', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (67, '2019-2020-1', '01', '20200201', '100058', '高二英语必修3', '10047', '马东锡', '01', 45, 20, 6, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (68, '2019-2020-1', '01', '20200201', '100066', '物理实验', '10025', '张德良', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (69, '2019-2020-1', '01', '20200201', '100067', '化学实验', '10021', '林俊杰', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (70, '2019-2020-1', '01', '20200201', '100051', '体育课', '10015', '孙振东', '04', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (71, '2019-2020-1', '01', '20200201', '100063', '信息技术2', '10032', '谭晓江', '03', 45, 20, 2, '1', NULL, 0, '2020-06-11 10:25:40', NULL);
INSERT INTO `yc_class_task` VALUES (72, '2019-2020-1', '01', '20200201', '100061', '高二物理2', '10024', '侯德南', '02', 45, 20, 4, '1', NULL, 0, '2020-06-11 10:25:40', NULL);

-- ----------------------------
-- Table structure for yc_course_info
-- ----------------------------
DROP TABLE IF EXISTS `yc_course_info`;
CREATE TABLE `yc_course_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `course_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `course_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `course_attr` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程属性',
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '课程状态',
  `piority` int(2) NULL DEFAULT NULL COMMENT '优先级',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0 显示, 1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_course_info
-- ----------------------------
INSERT INTO `yc_course_info` VALUES (1, '100001', '高一语文必修1', '01', '桂电出版社', NULL, NULL, '测试添加', 0, NULL, '2020-05-02 20:59:58');
INSERT INTO `yc_course_info` VALUES (2, '100002', '高一语文必修2', '01', '清华大学出版社', NULL, NULL, '衡水中学使用教材', 0, NULL, '2020-05-02 21:01:07');
INSERT INTO `yc_course_info` VALUES (3, '100003', '高一数学1', '01', '北京大学出版社', NULL, NULL, '谭老师授课', 0, NULL, '2020-05-02 21:03:13');
INSERT INTO `yc_course_info` VALUES (4, '100004', '高一物理1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (5, '100005', '高二语文必修5', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (6, '100006', '高三语文必修6', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (7, '100007', '高一地理必修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (8, '100008', '高一地理必修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (9, '100009', '高二地理必修3', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (10, '100010', '高三地理选修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (11, '100011', '高三地理选修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (12, '100012', '高三地理选修3', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (13, '100013', '高三地理选修4', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (14, '100014', '高一化学必修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (15, '100015', '高一化学必修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (16, '100016', '高二化学选修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (17, '100017', '高二化学选修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (18, '100018', '高三化学选修3', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (19, '100019', '高三化学选修4', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (20, '100020', '高三化学选修5', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (21, '100021', '高一历史必修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (22, '100022', '高一历史必修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (23, '100023', '高二历史必修3', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (24, '100024', '高二历史选修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (25, '100025', '高三历史选修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (26, '100026', '高三历史选修3', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (27, '100027', '高一生物必修1：分子与细胞', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (28, '100028', '高一生物必修2：遗传与进化', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (29, '100029', '高二生物必修3：稳态与环境', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (30, '100030', '高二生物选修1：生物技术与实践', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (31, '100031', '高三生物选修2：生物科学与社会', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (32, '100032', '高三生物选修3：现代生物科技专题', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (33, '100033', '高一数学必修1', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (34, '100034', '高一数学必修2', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (35, '100035', '高二数学必修3', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (36, '100036', '高二数学必须4', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (37, '100037', '高二数学选修1-2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (38, '100038', '高二数学选修2-1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (39, '100039', '高二数学选修2-2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (40, '100040 ', '高三数学必修5', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (41, '100041', '高一思想政治必修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (42, '100042', '高一思想政治必修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (43, '100043', '高二思想政治必修3', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (44, '100044', '高二思想政治必修4', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (45, '100045', '高三思想政治选修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (46, '100046', '高三思想政治选修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (47, '100047', '高三思想政治选修3', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (48, '100048', '高一物理必修1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (49, '100049', '高一物理必修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (50, '100050', '高二物理选修1-1', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (51, '100051', '体育课', '04', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (52, '100052 ', '高一物理必修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (53, '100053', '音乐课1', '05', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (54, '100054', '高一物理必修2', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (55, '100055', '舞蹈课1', '05', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (56, '100056', '高一英语必修1', '01', '高一出版社', NULL, NULL, NULL, 0, NULL, '2020-05-02 22:46:51');
INSERT INTO `yc_course_info` VALUES (57, '100057', '高一英语必修2', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (58, '100058', '高二英语必修3', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (59, '100059', '高二英语必修4', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (60, '100060', '高三英语选修1', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (61, '100061', '高三英语选修2', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (62, '100062', '信息与技术1', '03', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (63, '100063', '信息与技术2', '03', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (64, '100064', '音乐课2', '03', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (65, '100065', '舞蹈课2', '05', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (66, '100066', '物理实验', '03', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (67, '100067', '化学实验', '03', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (68, '100069', '测试课程', '02', '测试测试', NULL, NULL, '测试添加', 0, '2020-06-03 21:51:14', NULL);
INSERT INTO `yc_course_info` VALUES (69, '100070', '语文', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (70, '100071', '数学', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (71, '100072', '英语', '01', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (72, '100073', '化学', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (73, '100074', '政治', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (74, '100075', '地理', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);
INSERT INTO `yc_course_info` VALUES (75, '100076', '生物', '02', NULL, NULL, NULL, NULL, 0, NULL, NULL);

-- ----------------------------
-- Table structure for yc_course_plan
-- ----------------------------
DROP TABLE IF EXISTS `yc_course_plan`;
CREATE TABLE `yc_course_plan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `grade_no` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年级编号',
  `class_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级编号',
  `course_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程编号',
  `teacher_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '讲师编号',
  `class_room_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教室编号',
  `class_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上课时间',
  `weeks_sum` int(3) NULL DEFAULT NULL COMMENT '周数',
  `semester` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学期',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '0 显示，1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 128 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排课表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_course_plan
-- ----------------------------
INSERT INTO `yc_course_plan` VALUES (1, '01', '20200105', '100070', '10028', '01-205', '23', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (2, '01', '20200105', '100070', '10028', '01-502', '20', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (3, '01', '20200105', '100071', '10049', '01-304', '21', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (4, '01', '20200105', '100071', '10049', '01-503', '10', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (5, '01', '20200105', '100072', '10040', '01-503', '04', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (6, '01', '20200105', '100072', '10040', '01-301', '11', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (7, '01', '20200105', '100004', '10026', '01-402', '01', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (8, '01', '20200105', '100004', '10026', '01-103', '08', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (9, '01', '20200105', '100073', '10020', '01-405', '12', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (10, '01', '20200105', '100073', '10020', '01-105', '09', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (11, '01', '20200105', '100076', '10018', '01-505', '06', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (12, '01', '20200105', '100076', '10018', '01-203', '05', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (13, '01', '20200105', '100066', '10025', '08-301', '19', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (14, '01', '20200105', '100067', '10021', '08-301', '15', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (15, '01', '20200105', '100051', '10015', '12-204', '25', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (16, '01', '20200105', '100063', '10032', '08-304', '02', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (17, '01', '20200105', '100074', '10019', '01-302', '24', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (18, '01', '20200105', '100075', '10024', '01-202', '16', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (19, '01', '20200105', '100076', '10018', '01-404', '17', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (20, '01', '20200105', '100076', '10018', '01-202', '13', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (21, '01', '20200104', '100004', '10026', '01-303', '24', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (22, '01', '20200104', '100004', '10026', '01-101', '25', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (23, '01', '20200104', '100070', '10028', '01-401', '18', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (24, '01', '20200104', '100070', '10028', '01-304', '12', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (25, '01', '20200104', '100070', '10028', '01-302', '14', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (26, '01', '20200104', '100071', '10049', '01-503', '19', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (27, '01', '20200104', '100071', '10049', '01-103', '09', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (28, '01', '20200104', '100071', '10049', '01-401', '11', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (29, '01', '20200104', '100072', '10040', '01-502', '13', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (30, '01', '20200104', '100072', '10040', '01-504', '17', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (31, '01', '20200104', '100072', '10040', '01-205', '15', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (32, '01', '20200104', '100073', '10020', '01-405', '03', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (33, '01', '20200104', '100073', '10020', '01-403', '02', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (34, '01', '20200104', '100074', '10019', '01-204', '04', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (35, '01', '20200104', '100075', '10024', '01-404', '22', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (36, '01', '20200104', '100076', '10018', '01-204', '16', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (37, '01', '20200104', '100076', '10018', '01-205', '07', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (38, '01', '20200104', '100066', '10025', '08-305', '23', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (39, '01', '20200104', '100067', '10021', '08-304', '01', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (40, '01', '20200104', '100051', '10015', '12-103', '20', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (41, '01', '20200104', '100063', '10032', '08-201', '21', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (42, '01', '20200103', '100051', '10041', '12-201', '25', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (43, '01', '20200103', '100001', '10034', '01-103', '19', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (44, '01', '20200103', '100001', '10034', '01-405', '09', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (45, '01', '20200103', '100001', '10034', '01-205', '17', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (46, '01', '20200103', '100003', '10035', '01-102', '01', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (47, '01', '20200103', '100003', '10035', '01-402', '08', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (48, '01', '20200103', '100003', '10035', '01-402', '06', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (49, '01', '20200103', '100056', '10029', '01-501', '21', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (50, '01', '20200103', '100056', '10029', '01-404', '15', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (51, '01', '20200103', '100056', '10029', '01-304', '13', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (52, '01', '20200103', '100004', '10025', '01-104', '16', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (53, '01', '20200103', '100004', '10025', '01-205', '22', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (54, '01', '20200103', '100015', '10037', '01-405', '07', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (55, '01', '20200103', '100015', '10037', '01-103', '10', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (56, '01', '20200103', '100028', '10038', '01-204', '18', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (57, '01', '20200103', '100028', '10038', '01-305', '24', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (58, '01', '20200103', '100022', '10036', '01-205', '02', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (59, '01', '20200103', '100008', '10031', '01-202', '14', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (60, '01', '20200103', '100042', '10040', '01-503', '23', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (61, '01', '20200103', '100062', '10039', '08-203', '11', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (62, '01', '20200103', '100066', '10025', '08-304', '20', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (63, '01', '20200103', '100067', '10023', '08-205', '05', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (64, '01', '20200102', '100051', '10041', '12-201', '15', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (65, '01', '20200102', '100066', '10025', '08-303', '07', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (66, '01', '20200102', '100067', '10023', '08-304', '13', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (67, '01', '20200102', '100001', '10010', '01-202', '22', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (68, '01', '20200102', '100001', '10010', '01-505', '05', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (69, '01', '20200102', '100001', '10010', '01-503', '12', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (70, '01', '20200102', '100033', '10012', '01-402', '10', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (71, '01', '20200102', '100033', '10012', '01-503', '03', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (72, '01', '20200102', '100033', '10012', '01-505', '20', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (73, '01', '20200102', '100056', '10013', '01-202', '24', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (74, '01', '20200102', '100056', '10013', '01-304', '14', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (75, '01', '20200102', '100056', '10013', '01-303', '06', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (76, '01', '20200102', '100004', '10025', '01-302', '09', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (77, '01', '20200102', '100014', '10033', '01-302', '11', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (78, '01', '20200102', '100041', '10045', '01-304', '18', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (79, '01', '20200102', '100041', '10045', '01-402', '04', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (80, '01', '20200102', '100021', '10044', '01-105', '16', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (81, '01', '20200102', '100021', '10044', '01-404', '19', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (82, '01', '20200102', '100007', '10043', '01-204', '01', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (83, '01', '20200102', '100007', '10043', '01-105', '08', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (84, '01', '20200102', '100027', '10042', '01-502', '23', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (85, '01', '20200201', '100005', '10019', '01-402', '22', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (86, '01', '20200201', '100005', '10019', '01-203', '10', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (87, '01', '20200201', '100005', '10019', '01-504', '25', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (88, '01', '20200201', '100017', '10050', '01-102', '17', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (89, '01', '20200201', '100017', '10050', '01-103', '16', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (90, '01', '20200201', '100029', '10031', '01-303', '08', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (91, '01', '20200201', '100029', '10031', '01-302', '20', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (92, '01', '20200201', '100035', '10051', '01-201', '01', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (93, '01', '20200201', '100035', '10051', '01-505', '09', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (94, '01', '20200201', '100035', '10051', '01-202', '15', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (95, '01', '20200201', '100058', '10047', '01-401', '21', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (96, '01', '20200201', '100058', '10047', '01-305', '06', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (97, '01', '20200201', '100058', '10047', '01-504', '24', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (98, '01', '20200201', '100066', '10025', '08-301', '12', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (99, '01', '20200201', '100067', '10021', '08-104', '07', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (100, '01', '20200201', '100051', '10015', '12-103', '11', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (101, '01', '20200201', '100063', '10032', '08-201', '05', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (102, '01', '20200201', '100061', '10024', '01-502', '02', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (103, '01', '20200201', '100061', '10024', '01-403', '19', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (104, '01', '20200101', '100051', '10041', '12-204', '18', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (105, '01', '20200101', '100066', '10025', '08-305', '13', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (106, '01', '20200101', '100067', '10023', '08-305', '11', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (107, '01', '20200101', '100001', '10010', '01-203', '25', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (108, '01', '20200101', '100001', '10010', '01-402', '19', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (109, '01', '20200101', '100001', '10010', '01-103', '01', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (110, '01', '20200101', '100033', '10012', '01-504', '05', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (111, '01', '20200101', '100033', '10012', '01-103', '02', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (112, '01', '20200101', '100033', '10012', '01-101', '08', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (113, '01', '20200101', '100056', '10013', '01-504', '20', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (114, '01', '20200101', '100056', '10013', '01-305', '04', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (115, '01', '20200101', '100056', '10013', '01-402', '07', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (116, '01', '20200101', '100004', '10025', '01-303', '03', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (117, '01', '20200101', '100004', '10025', '01-204', '15', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (118, '01', '20200101', '100014', '10033', '01-401', '16', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (119, '01', '20200101', '100014', '10033', '01-401', '10', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (120, '01', '20200101', '100041', '10045', '01-402', '09', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (121, '01', '20200101', '100041', '10045', '01-302', '23', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (122, '01', '20200101', '100021', '10044', '01-203', '17', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (123, '01', '20200101', '100021', '10044', '01-504', '14', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (124, '01', '20200101', '100007', '10043', '01-505', '22', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (125, '01', '20200101', '100007', '10043', '01-201', '24', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (126, '01', '20200101', '100027', '10042', '01-202', '12', NULL, '2019-2020-1', 0, NULL, NULL);
INSERT INTO `yc_course_plan` VALUES (127, '01', '20200101', '100027', '10042', '01-201', '06', NULL, '2019-2020-1', 0, NULL, NULL);

-- ----------------------------
-- Table structure for yc_doc
-- ----------------------------
DROP TABLE IF EXISTS `yc_doc`;
CREATE TABLE `yc_doc`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'doc文件名',
  `file_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件实际名',
  `doc_url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件描述',
  `to_class_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标班级',
  `from_user_id` int(11) NULL DEFAULT NULL COMMENT '发布者id',
  `from_user_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布者名字',
  `from_user_type` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '2' COMMENT '来自的用户类型1:管理员。2：讲师',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `clicks` bigint(20) NULL DEFAULT NULL COMMENT '阅读次数',
  `expire` int(11) NULL DEFAULT NULL COMMENT '有效天数',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '0 显示，1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生文档信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_doc
-- ----------------------------
INSERT INTO `yc_doc` VALUES (7, '一寸照.jpg', '一寸照', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/一寸照.jpg', '照片参考样例', '20200101', 1, '梁主任', '1', NULL, 26, 0, 0, '2020-05-30 22:27:57', '2021-02-03 12:54:35');
INSERT INTO `yc_doc` VALUES (9, '3、软件需求说明书（GB856T——88）.doc', '需求说明', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/3、软件需求说明书（GB856T——88）.doc', '分析书', '20200201', 1, '梁主任', '1', NULL, 12, 0, 0, '2020-05-31 00:09:09', NULL);
INSERT INTO `yc_doc` VALUES (10, '进度计划表参考.docx', '进度计划参考表', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/进度计划表参考.docx', '进度计划的参考表格', '20200103', 1, '梁主任', '1', NULL, 13, 0, 0, '2020-05-31 00:09:46', NULL);
INSERT INTO `yc_doc` VALUES (11, '毕业设计独创性承诺书.docx', '独创性承诺', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/毕业设计独创性承诺书.docx', '测试文件', '20200105', 1, '梁主任', '1', NULL, 52, 0, 0, '2020-05-31 10:58:08', NULL);
INSERT INTO `yc_doc` VALUES (12, '20200519161528.jpg', '123', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/20200519161528.jpg', '123', '123', 1, '梁主任', '1', NULL, 111, 123123, 0, '2020-05-31 12:33:14', NULL);
INSERT INTO `yc_doc` VALUES (13, '467345634.docx', '123', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/467345634.docx', '3123', '12312', 1, '梁主任', '1', NULL, 100, 123123, 1, '2020-05-31 12:34:47', NULL);
INSERT INTO `yc_doc` VALUES (14, '翻译文献未定格式.docx', '参考文献', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/翻译文献未定格式.docx', '英文翻译示例', '20200101', 1, '梁主任', '1', NULL, 271, 0, 0, '2020-06-10 21:40:51', '2021-01-23 04:50:37');
INSERT INTO `yc_doc` VALUES (15, '桂林电子科技大学毕业设计（论文）任务书.docx', '任务书', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/桂林电子科技大学毕业设计（论文）任务书.docx', '排课系统任务书', '20200101', 1, '梁晓明', '2', NULL, 3504, 0, 0, '2020-06-10 21:48:23', '2021-01-23 12:45:55');

-- ----------------------------
-- Table structure for yc_grade_info
-- ----------------------------
DROP TABLE IF EXISTS `yc_grade_info`;
CREATE TABLE `yc_grade_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id,年级表',
  `grade_no` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级编号',
  `grade_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '年级信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_grade_info
-- ----------------------------
INSERT INTO `yc_grade_info` VALUES (1, '01', '高一');
INSERT INTO `yc_grade_info` VALUES (2, '02', '高二');
INSERT INTO `yc_grade_info` VALUES (3, '03', '高三');

-- ----------------------------
-- Table structure for yc_location_info
-- ----------------------------
DROP TABLE IF EXISTS `yc_location_info`;
CREATE TABLE `yc_location_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id,位置信息，高一在哪栋楼，高二在哪',
  `teach_build_no` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教学楼编号,放教学楼表中编号',
  `grade_no` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级编号,放年级表中的id',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0 显示， 1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '年级位置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_location_info
-- ----------------------------
INSERT INTO `yc_location_info` VALUES (1, '01', '01', 1, NULL, NULL);
INSERT INTO `yc_location_info` VALUES (2, '02', '02', 0, NULL, NULL);
INSERT INTO `yc_location_info` VALUES (15, '03', '03', 1, '2020-06-01 23:31:44', NULL);
INSERT INTO `yc_location_info` VALUES (16, '03', '03', 0, '2020-06-01 23:38:11', NULL);
INSERT INTO `yc_location_info` VALUES (17, '03', '03', 0, '2020-06-01 23:39:52', NULL);
INSERT INTO `yc_location_info` VALUES (18, '04', '01', 1, '2021-02-28 04:24:56', NULL);
INSERT INTO `yc_location_info` VALUES (19, '05', '01', 1, '2021-02-28 04:27:43', NULL);
INSERT INTO `yc_location_info` VALUES (20, '08', '01', 1, '2021-02-28 04:31:49', NULL);

-- ----------------------------
-- Table structure for yc_online_category
-- ----------------------------
DROP TABLE IF EXISTS `yc_online_category`;
CREATE TABLE `yc_online_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_no` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别编号，01-99',
  `category_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网课类别',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父级id，默认0为一级分类',
  `piority` int(3) NULL DEFAULT NULL COMMENT '优先级',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '0 显示，1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '网课类别表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_online_category
-- ----------------------------
INSERT INTO `yc_online_category` VALUES (1, '01', '语文', 0, NULL, '语文类别', 0, NULL, NULL);
INSERT INTO `yc_online_category` VALUES (2, '02', '数学', 0, NULL, NULL, 0, '2020-06-07 23:13:36', NULL);
INSERT INTO `yc_online_category` VALUES (3, '03', '英语', 0, NULL, NULL, 0, '2020-06-08 09:17:43', NULL);
INSERT INTO `yc_online_category` VALUES (4, '04', '物理', 0, NULL, NULL, 0, '2020-06-08 09:23:37', NULL);
INSERT INTO `yc_online_category` VALUES (5, '05', '化学', 0, NULL, NULL, 0, '2020-06-08 09:23:44', NULL);
INSERT INTO `yc_online_category` VALUES (6, '07', '生物', 0, NULL, NULL, 0, '2020-06-08 09:24:04', NULL);
INSERT INTO `yc_online_category` VALUES (9, '08', '高一语文', 1, NULL, NULL, 0, '2020-06-08 09:47:34', NULL);
INSERT INTO `yc_online_category` VALUES (10, '09', '高一数学', 2, NULL, NULL, 0, '2020-06-08 09:47:49', NULL);
INSERT INTO `yc_online_category` VALUES (11, '10', '高二语文', 1, NULL, NULL, 0, '2020-06-08 09:48:20', NULL);
INSERT INTO `yc_online_category` VALUES (12, '10', '高一英语', 3, NULL, NULL, 0, '2020-06-10 22:06:47', NULL);

-- ----------------------------
-- Table structure for yc_student
-- ----------------------------
DROP TABLE IF EXISTS `yc_student`;
CREATE TABLE `yc_student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生id',
  `student_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号，可以用于登录',
  `username` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称，可以用于登录',
  `password` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `realname` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `user_type` tinyint(1) NOT NULL DEFAULT 3 COMMENT '标记用户类型3',
  `grade` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年级',
  `class_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在班级',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前住址',
  `telephone` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '账号状态,0为正常，1为封禁',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_student
-- ----------------------------
INSERT INTO `yc_student` VALUES (1, '2020011234', 'lequal', '123456', '梁同学', 3, '高三', '20200101', 33, '河南省商丘市睢阳区', '15225200721', '1462638689@qq.com', NULL, '爱好打篮球', 0, 0, '2020-02-16 18:24:58', '2021-02-01 03:10:34');
INSERT INTO `yc_student` VALUES (2, '2019021541', 'litongxue', '123456', '李同学', 3, '高二', '20200203', 22, '广西桂林市', '15177989514', 'course@guet.com', NULL, '好好学习', 0, 0, '2020-03-10 20:51:26', '2020-04-06 14:32:22');
INSERT INTO `yc_student` VALUES (3, '2020031235', 'gantongxue', '123456', '甘同学', 3, '高三', '20200302', 19, '湖北省武汉市', '17007891233', 'course@guet.com', NULL, '天天向上', 0, 1, '2020-03-07 12:52:17', '2021-02-13 08:15:18');
INSERT INTO `yc_student` VALUES (4, '2020012589', 'xitongxue', '123456', '喜同学', 3, '高一', '20200103', 21, '广东省珠海市', '13677731456', 'course@guet.com', NULL, '找工作中', 0, 0, NULL, NULL);
INSERT INTO `yc_student` VALUES (5, '2020017895', 'huangtongxue', '123456', '黄同学', 3, '高一', '20200101', 20, '广西钦州市', '17689541452', 'course@guet.com', NULL, '技术强才是真的强', 0, 1, NULL, NULL);
INSERT INTO `yc_student` VALUES (6, '2020017836', 'caitongxue', '123456', '蔡同学', 3, '高一', '20200104', 18, '广西玉林', '18574562587', 'course@guet.com', NULL, '是时候好好学习了', 0, 0, NULL, '2021-02-27 05:37:51');
INSERT INTO `yc_student` VALUES (7, '2020021936', 'suntongxue', '123456', '孙同学', 3, '高二', '20200201', 17, '湖南长沙', '18648983826', 'course@guet.com', NULL, '加油咯', 0, 0, NULL, NULL);
INSERT INTO `yc_student` VALUES (8, '2020031245', 'hutongxue', '123456', '胡同学', 3, '高三', '20200301', 19, '湖北十堰', '17505127841', 'course@guet.com', NULL, '嘿嘿', 0, 0, NULL, NULL);
INSERT INTO `yc_student` VALUES (9, '2020031278', 'litongxue2', '123456', '黎同学', 3, '高三', '20200302', 17, '安徽省', '13412596654', 'course@guet.com', NULL, '做喜欢做的事', 0, 0, NULL, NULL);
INSERT INTO `yc_student` VALUES (10, '2020014596', 'shitongxue', '123456', '史同学', 3, '高一', '20200103', 18, '广西贵港', '13644527789', 'course@guet.com', NULL, '高一的同学', 0, 0, NULL, NULL);
INSERT INTO `yc_student` VALUES (11, '2020024567', 'xiaotongxue', '123456', '萧同学', 3, '高二', '20200202', 17, '广东珠海', '13677735445', 'course@guet.com', NULL, '高二同学', 0, 0, NULL, NULL);
INSERT INTO `yc_student` VALUES (12, '2020021456', 'student', '123456', '谭同学', 3, '高二', '20200204', 19, '广东汕头', '13677735559', 'course@guet.com', NULL, '高二8班', 0, 0, NULL, NULL);
INSERT INTO `yc_student` VALUES (13, '2020034123', 'studens', '123456', '王同学', 3, '高三', '20200302', 22, '广东深圳', '17007895623', 'course@guet.com', NULL, NULL, 0, 0, NULL, '2020-05-02 13:44:02');
INSERT INTO `yc_student` VALUES (14, '2020035468', 'liangyike', '123456', '梁同学', 3, '高三', '20200301', 20, '广西桂林市', '13677731235', 'course@guet.com', NULL, NULL, 0, 0, '2020-03-26 22:57:04', '2020-04-06 14:52:37');
INSERT INTO `yc_student` VALUES (15, '2020016788', 'xiewutong', '123456', '谢童鞋', 3, '高一', '20200104', 16, '湖南省衡阳市', '15177959816', 'course@guet.com', NULL, NULL, 0, 0, '2020-03-26 23:15:06', NULL);
INSERT INTO `yc_student` VALUES (16, '2020024182', 'qintongxue', '123456', '覃同学', 3, '高二', '20200203', 17, '广西桂平市', '18565412563', 'course@guet.com', NULL, NULL, 0, 0, '2020-03-26 23:18:02', NULL);
INSERT INTO `yc_student` VALUES (17, '2020028242', 'lian', '123456', '梁先生', 3, '高二', '20200201', 17, '广东中山', '17585968745', 'course@guet.com', NULL, NULL, 0, 0, '2020-03-26 23:20:29', '2020-05-02 21:44:19');
INSERT INTO `yc_student` VALUES (18, '2020038300', 'jiangtongxue', '123456', '蒋同学', 3, '高三', '20200305', 18, '广东省佛山市', '13596857412', 'jiang@guet.com', NULL, NULL, 0, 0, '2020-03-26 23:25:11', '2020-04-06 14:49:22');
INSERT INTO `yc_student` VALUES (19, '2020027623', 'ganxiansheng', '123456', '甘先生', 3, '高二', '20200205', 17, '广西百色', '17015789654', 'course@guet.com', NULL, NULL, 0, 0, '2020-03-26 23:26:20', NULL);
INSERT INTO `yc_student` VALUES (20, '2020027807', '2020027623', '123456', '王总', 3, '高二', '20200205', 17, '上海市', '15678415241', 'course@guet.com', NULL, NULL, 0, 0, '2020-03-26 23:27:40', '2020-06-01 10:20:42');
INSERT INTO `yc_student` VALUES (21, '2020022351', '小幸运', '123456', '陈奕迅', 3, '高二', '20200101', 18, '广东省珠海市金湾区', '15177959814', '12222@qq.com', NULL, NULL, 0, 0, '2020-05-20 09:11:25', '2021-02-09 07:10:31');
INSERT INTO `yc_student` VALUES (22, '2020035084', '马同学', '123456', '马东良', 3, '高三', '20200301', 19, '广西贵港市', '15177959814', 'ma.dl@qq.com', NULL, NULL, 0, 0, '2020-06-01 02:40:30', '2020-06-01 04:25:22');
INSERT INTO `yc_student` VALUES (23, '2020011062', '黄华斌', 'aizai2015', '黄华', 3, '高一', '20200102', 33, '广西贵港市', '15678675545', '222@qq.com', NULL, '努力挣钱', 0, 0, '2020-06-01 02:54:42', '2021-02-09 07:12:32');
INSERT INTO `yc_student` VALUES (24, '2020021158', '梁丙光', 'aizai2015', '梁丙光', 3, '高二', '20200204', 23, '广西', '15177959814', '1576070851@qq.com', NULL, NULL, 0, 0, '2020-05-31 18:57:45', '2021-02-08 23:02:34');
INSERT INTO `yc_student` VALUES (25, '2020014949', 'registerTest', '123456', '我是测试用户', 3, '高一', '20200101', 33, '桂电', '13677731234', 'test@qq.com', NULL, '努力奋斗', 0, 0, '2020-06-06 10:16:27', '2021-02-09 07:11:25');
INSERT INTO `yc_student` VALUES (26, '2021034884', 'xuyy19', 'ssssssss', '徐亚远', 3, '高三', NULL, NULL, '河南省商丘市睢阳区', '15225200721', '1462638689@qq.com', NULL, NULL, 1, 0, '2021-02-02 21:43:40', '2021-02-04 06:25:47');
INSERT INTO `yc_student` VALUES (27, '2021025581', 'xuyy', '123456', '徐亚远', 3, '高三', '20200301', 19, '河南省', '15225200721', '1462638689@qq.com', NULL, NULL, 1, 0, '2021-01-27 16:06:59', '2021-01-28 00:14:11');
INSERT INTO `yc_student` VALUES (28, '2021028248', 'zwm', 'zwm123', '赵文苗', 3, '高三', '20200305', 22, '河南', '13083776023', '1462638689', NULL, NULL, 1, 0, '2021-01-26 07:30:58', '2021-01-30 12:16:27');

-- ----------------------------
-- Table structure for yc_teach_build_info
-- ----------------------------
DROP TABLE IF EXISTS `yc_teach_build_info`;
CREATE TABLE `yc_teach_build_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id,教学楼信息表',
  `teach_build_no` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教学楼编号',
  `teach_build_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教学楼名称',
  `teach_build_location` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教学楼位置',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0 显示，1 删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教学楼信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_teach_build_info
-- ----------------------------
INSERT INTO `yc_teach_build_info` VALUES (1, '01', '第1教学楼', '东校区', 0, NULL, '2020-04-09 21:51:09');
INSERT INTO `yc_teach_build_info` VALUES (2, '02', '2号教学楼', '1校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (3, '03', '3号教学楼', '1校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (4, '04', '4号教学楼', '1校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (5, '05', '5号教学楼', '1校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (6, '06', '音乐楼', '1校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (7, '07', '美术楼', '1校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (8, '08', '实验楼1', '1校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (9, '09', '实验楼2', '西南校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (10, '10', '逸夫楼1', '东南校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (11, '11', '逸夫楼2', '北校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (12, '12', '体育楼', '南校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (13, '13', '化生楼', '西校区', 0, NULL, NULL);
INSERT INTO `yc_teach_build_info` VALUES (14, '14', '14号教学楼', '2校区', 0, '2020-03-23 00:05:03', NULL);
INSERT INTO `yc_teach_build_info` VALUES (15, '20', '测试楼', '花江校区', 0, '2020-06-02 11:58:16', NULL);
INSERT INTO `yc_teach_build_info` VALUES (16, '19', 's', 's', 1, '2021-02-27 06:18:42', NULL);

-- ----------------------------
-- Table structure for yc_teacher
-- ----------------------------
DROP TABLE IF EXISTS `yc_teacher`;
CREATE TABLE `yc_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id，讲师表',
  `teacher_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师编号',
  `username` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称（用户名）',
  `password` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `realname` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `user_type` tinyint(1) NOT NULL DEFAULT 2 COMMENT '用户类型',
  `job_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `grade_no` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属年级',
  `license` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件照(地址)',
  `teach_subject` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教授科目',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `telephone` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述（签名）',
  `power` tinyint(1) NULL DEFAULT 1 COMMENT '操作权限',
  `piority` int(2) NULL DEFAULT NULL COMMENT '优先级',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '账号状态',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除 0(默认)显示，1删除 ',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of yc_teacher
-- ----------------------------
INSERT INTO `yc_teacher` VALUES (1, '10010', 'lequal(梁老师)', '111222', '梁晓明', 2, '教务处副主任', '01', NULL, '物理', 32, '13677731235', 'teacher@guet.com', '广西', NULL, '以身作则，教育好学生。', 1, 1, 0, 0, '2020-03-03 07:30:03', '2020-03-05 07:30:30');
INSERT INTO `yc_teacher` VALUES (3, '10012', 'msLi', 'lixuexue', '李雪雪', 2, '高级讲师', '01', NULL, '语文', 29, '13677731235', 'teacher@guet.com', '广西桂林市桂林电子科技大学', NULL, '做人民的好教师', 1, 2, 0, 0, '2020-03-06 23:39:39', '2020-03-06 23:39:39');
INSERT INTO `yc_teacher` VALUES (5, '10013', 'mswang', '123456', '王小芳', 2, '初级讲师', '01', NULL, '英语', 25, '13677731235', 'teacher@guet.com', '湖南省', NULL, '过好每一天', 1, 3, 0, 0, '2020-03-04 19:45:44', '2020-03-08 19:45:51');
INSERT INTO `yc_teacher` VALUES (6, '10014', 'mssun', '123456', '孙晓明', 2, '中级讲师', '01', NULL, '数学', 28, '13677731235', 'teacher@guet.com', '湖北省', NULL, '加油', 1, 2, 0, 0, '2020-03-06 19:47:11', '2020-03-30 19:47:14');
INSERT INTO `yc_teacher` VALUES (7, '10015', 'msming', '123456', '孙振东', 2, '实习生', '01', NULL, '化学', 22, '13677731235', 'teacher@guet.com', '江苏省', NULL, 'welcome', 1, 4, 0, 0, '2020-03-05 19:48:40', '2020-03-06 19:48:45');
INSERT INTO `yc_teacher` VALUES (8, '10016', 'mstan', '123456', '谭老师', 2, '初级讲师', '01', NULL, '英语', 23, '13677731235', 'teacher@guet.com', '广西', NULL, 'come on', 1, 3, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (9, '10017', 'msliang', '123456', '梁老师', 2, '高级讲师', '01', NULL, '语文', 32, '13677731235', 'teacher@guet.com', '河北', NULL, '做更好的自己', 1, 1, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (10, '10018', 'mrliang', '123456', '梁忠诚', 2, '高级讲师', '01', NULL, '数学', 35, '13677731235', 'teacher@guet.com', '湖北', NULL, '数学好', 1, 1, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (11, '10019', 'mrwang', '123456', '汪莉莉', 2, '中级讲师', '01', NULL, '地理', 33, '13677731235', 'teacher@guet.com', '河北', NULL, '地理好', 1, 2, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (12, '10020', 'mshu', '123456', '胡冬梅', 2, '中级讲师', '01', NULL, '化学', 46, '13677731235', 'teacher@guet.com', '搜索', NULL, '是', 1, 2, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (13, '10021', 'mrlin', '123456', '林俊杰', 2, '初级讲师', '01', NULL, '生物', 41, '13677731235', 'teacher@guet.com', '试试', NULL, '就', 1, 3, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (14, '10022', 'mrzhou', '123456', '周杰伦', 2, '高级讲师', '01', NULL, '生物', 39, '13677731235', 'teacher@guet.com', '看看', NULL, '看', 1, 1, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (15, '10023', 'mrwang', '123456', '张靓颖', 2, '初级讲师', '01', NULL, '历史', 33, '13677731235', 'teacher@guet.com', '55', NULL, '555', 1, 3, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (16, '10024', 'mrhou', '123456', '侯德南', 2, '高级讲师', '01', NULL, '政治', 37, '13677731235', 'teacher@guet.com', '54546', NULL, '8878878', 1, 1, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (17, '10025', 'mrzhang', '123456', '张德良', 2, '高级讲师', '01', NULL, '物理', 34, '13677731235', 'teacher@guet.com', '78788', '', '878755', 1, 1, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (18, '10026', 'mrzhang', '123456', '张勇', 2, '中级讲师', '02', NULL, '数学', 45, '13677731235', 'teacher@guet.com', '湖南', NULL, '565675', 1, 2, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (19, '10027', '马老师', '123456', '马晓东', 2, '初级讲师', '02', NULL, '语文', 28, '13677731235', 'teacher@guet.com', '海南', NULL, '78688787', 1, 3, 0, 0, NULL, '2020-04-11 14:33:58');
INSERT INTO `yc_teacher` VALUES (20, '10028', '马老师', '123456', '马芸', 2, '中级讲师', '02', NULL, '英语', 29, '13677731235', 'teacher@guet.com', '河北省邢台市', NULL, '5654', 1, 2, 0, 0, NULL, '2020-04-05 21:10:11');
INSERT INTO `yc_teacher` VALUES (21, '10029', '郑老师', '123456', '郑小红', 2, '高级讲师', '02', NULL, '生物', 32, '13677731235', 'teacher@guet.com', '河南', NULL, '768567', 1, 1, 0, 0, NULL, '2020-04-11 14:33:47');
INSERT INTO `yc_teacher` VALUES (22, '10030', '韦老师', '123456', '韦小龙', 2, '中级讲师', '02', NULL, '物理', 33, '13677731235', 'teacher@guet.com', '江苏', NULL, '6875675', 1, 2, 0, 0, NULL, '2020-04-11 14:30:24');
INSERT INTO `yc_teacher` VALUES (23, '10031', '张老师', '123456', '张小龙', 2, '高级讲师', '02', NULL, '化学', 35, '13677731235', 'teacher@guet.com', '福建', NULL, '6785675', 1, 1, 0, 0, NULL, '2020-04-11 14:29:48');
INSERT INTO `yc_teacher` VALUES (24, '10032', '谭老师', '123456', '谭晓江', 2, '高级讲师', '02', NULL, '历史', 33, '13677731235', 'teacher@guet.com', '贵州', NULL, '78678', 1, 1, 0, 0, NULL, '2020-04-11 14:29:29');
INSERT INTO `yc_teacher` VALUES (25, '10033', '韩老师', '123456', '韩云', 2, '高级讲师', '02', NULL, '政治', 32, '13677731235', 'teacher@guet.com', '新疆', NULL, '67767', 1, 1, 0, 0, NULL, '2020-04-11 14:29:20');
INSERT INTO `yc_teacher` VALUES (26, '10034', '韦老师', '123456', '韦雪琪', 2, '中级讲师', '02', NULL, '历史', 28, '13677731235', 'teacher@guet.com', '贵州省贵阳市', NULL, NULL, 1, NULL, 0, 0, NULL, '2020-04-05 21:00:36');
INSERT INTO `yc_teacher` VALUES (27, '10046', '黄老师', '123456', '黄继光', 2, '高级讲师', '02', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/timg.jfif', '地理', 31, '13677731235', 'huang@guet.com', '西藏', NULL, '6756', 1, 1, 0, 0, NULL, '2020-04-11 14:29:00');
INSERT INTO `yc_teacher` VALUES (28, '10035', '张老师', '123456', '张三封', 2, '高级讲师', '03', NULL, '语文', 33, '13677731235', 'teacher@guet.com', '甘肃', NULL, '7567', 1, 1, 0, 0, NULL, '2020-04-11 14:28:44');
INSERT INTO `yc_teacher` VALUES (29, '10036', '胡老师', '123456', '胡小小', 2, '高级讲师', '03', NULL, '数学', 33, '13677731235', 'teacher@guet.com', '广西', NULL, '5675467', 1, 1, 0, 0, NULL, '2020-04-11 14:28:16');
INSERT INTO `yc_teacher` VALUES (30, '10037', '莫老师', '123456', '莫小新', 2, '高级讲师', '03', NULL, '英语', 33, '13677731235', 'teacher@guet.com', '河北石家庄市', NULL, '7867', 1, 1, 0, 0, NULL, '2020-04-11 14:26:18');
INSERT INTO `yc_teacher` VALUES (31, '10038', '甘老师', '123456', '甘楠', 2, '高级讲师', '03', NULL, '物理', 33, '13677731235', 'teacher@guet.com', '北京', NULL, '5644', 1, 1, 0, 0, NULL, '2020-04-05 20:59:08');
INSERT INTO `yc_teacher` VALUES (32, '10039', '江老师', '123456', '江晓东', 2, '高级讲师', '03', NULL, '化学', 40, '13677731235', 'teacher@guet.com', '广东省中山市', NULL, '22222', 1, 1, 0, 0, NULL, '2020-04-05 20:26:13');
INSERT INTO `yc_teacher` VALUES (33, '10040', '夏老师', '123456', '夏紫若', 2, '高级讲师', '03', NULL, '生物', 33, '13677731235', 'teacher@guet.com', '广东省深圳市', NULL, '6758', 1, 1, 0, 0, NULL, '2020-04-05 20:22:34');
INSERT INTO `yc_teacher` VALUES (34, '10041', '张老师', '123456', '张杰', 2, '高级讲师', '03', NULL, '政治', 31, '13677731235', 'teacher@guet.com', '上海', NULL, '999999', 1, 1, 0, 0, NULL, '2020-04-05 18:16:19');
INSERT INTO `yc_teacher` VALUES (35, '10042', '谭老师', '123456', '谭咏麟', 2, '高级讲师', '03', NULL, '历史', 32, '13677731235', 'teacher@guet.com', '天津', NULL, '5353', 1, 1, 0, 0, NULL, '2020-04-05 18:04:37');
INSERT INTO `yc_teacher` VALUES (36, '10043', '王老师', '123456', '王杰', 2, '高级讲师', '03', NULL, '地理', 33, '13677731235', 'teacher@guet.com', '湖北省武汉市', NULL, '543453', 1, 1, 0, 0, NULL, '2020-04-05 18:04:09');
INSERT INTO `yc_teacher` VALUES (38, '10044', '吴老师', '123456', '吴天盛', 2, '高级讲师', '03', NULL, '物理', 32, '13677731235', 'teacher@guet.com', '福建省福州市', NULL, NULL, 1, NULL, 0, 0, '2020-05-18 21:56:41', NULL);
INSERT INTO `yc_teacher` VALUES (39, '10045', '江老师', '123456', '江小陆', 2, '中级讲师', '03', 'https://arrange.oss-cn-shenzhen.aliyuncs.com/timg.gif', '数学', 37, '13677731235', 'teacher@guet.com', '甘肃省兰州市', NULL, NULL, 1, NULL, 0, 0, '2020-05-18 22:02:25', '2020-05-19 09:19:05');
INSERT INTO `yc_teacher` VALUES (40, '10047', '马东锡', '123456', '马东锡', 2, '高级讲师', NULL, 'https://arrange.oss-cn-shenzhen.aliyuncs.com/timg.gif', '物理', 35, '13677731234', 'madx@guet.com', '广西河池市', NULL, NULL, 1, NULL, 0, 0, '2020-06-01 10:35:18', '2020-06-01 10:39:05');
INSERT INTO `yc_teacher` VALUES (41, '10048', '黄讲师', '123456', '黄桐', 2, '初级讲师', NULL, 'https://arrange.oss-cn-shenzhen.aliyuncs.com/timg (2).jfif', '数学', 32, '13677731235', 'tong@qq.com', '广西贵港市', NULL, NULL, 1, NULL, 0, 0, '2020-06-01 14:38:57', '2020-06-01 14:49:25');
INSERT INTO `yc_teacher` VALUES (42, '10049', '王刚', '123456', '王刚', 2, '高级讲师', NULL, 'https://arrange.oss-cn-shenzhen.aliyuncs.com/timg (1).jfif', '化学', 36, '18890786676', 'gang.w@qq.com', '广西壮族自治区贺州市', NULL, NULL, 1, NULL, 0, 0, '2020-06-01 14:53:56', '2020-06-05 15:30:43');
INSERT INTO `yc_teacher` VALUES (43, '10050', '黄三毛', '123456', '黄三毛', 2, '高级讲师', NULL, NULL, '化学', 34, '15876765634', 'huang@guet.com', '广东省汕头市', NULL, NULL, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `yc_teacher` VALUES (44, '10051', '燕双赢', '123456', '燕双鹰', 2, '中级讲师', NULL, NULL, '数学', 30, '13454349878', 'yan@guet.com', '山东省青岛市', NULL, NULL, 1, NULL, 0, 0, NULL, '2021-02-12 09:09:27');
INSERT INTO `yc_teacher` VALUES (45, '', 'xuyy', '123456', '许亚云', 2, '高级讲师', NULL, NULL, '化学', 23, '15225200721', '1462638689@qq.com', '河南省商丘市', NULL, NULL, 1, NULL, 0, 0, '2021-02-14 03:31:06', NULL);
INSERT INTO `yc_teacher` VALUES (46, '10052', 'xuyy', '123456', '徐亚远', 2, '高级讲师', NULL, NULL, '物理', 30, '13403701002a', '111@qqc.om', '河南省', NULL, NULL, 1, NULL, 0, 0, '2021-02-13 11:52:22', '2021-02-20 20:24:07');

SET FOREIGN_KEY_CHECKS = 1;
