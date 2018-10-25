/*
Navicat MySQL Data Transfer

Source Server         : gaoge
Source Server Version : 50718
Source Host           : rm-bp1w9jt8l75n7643pro.mysql.rds.aliyuncs.com:3306
Source Database       : cbk

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-02-26 14:05:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_file
-- ----------------------------
DROP TABLE IF EXISTS `system_file`;
CREATE TABLE `system_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_origin_name` varchar(50) DEFAULT NULL COMMENT '原文件名称',
  `file_new_name` varchar(50) DEFAULT NULL COMMENT '新的文件名称',
  `file_mime` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint(13) DEFAULT NULL COMMENT '文件大小',
  `file_url` varchar(500) DEFAULT NULL COMMENT '文件签名url',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_file
-- ----------------------------
INSERT INTO `system_file` VALUES ('1', 'Jellyfish.jpg', '1510819312495.jpg', 'image/jpeg', '775702', null, '2017-11-16 16:01:53', '1');
INSERT INTO `system_file` VALUES ('2', 'Desert.jpg', '1510823483216.jpg', 'image/jpeg', '845941', 'http://cbk92.oss-cn-hangzhou.aliyuncs.com/1510823483216.jpg?Expires=1510827090&OSSAccessKeyId=LTAI4Q94TPxG1SKm&Signature=c1YJZvZsvW4NycKdmgp1QCZFECQ%3D', '2017-11-16 17:12:23', '1');
INSERT INTO `system_file` VALUES ('3', 'Jellyfish.jpg', '1510824923205.jpg', 'image/jpeg', '775702', 'http://cbk92.oss-cn-hangzhou.aliyuncs.com/1510824923205.jpg?Expires=1510891912&OSSAccessKeyId=TMP.AQF7p8Pue4jMeLxNTIG2LRk-926iO1Wmiwmuebo-BfBdwModRY5f7LsTRT5MMC4CFQDIQFhJIQel5VcGGMOCmD36j7ChXQIVAPFMVvMvsU6Gev60fsPvm7gX4QRI&Signature&Signature=S8Hi3j%2Bxi4SRXWSuzRT%2FFjAlex8%3D', '2017-11-16 17:35:25', '1');
INSERT INTO `system_file` VALUES ('4', 'Desert.jpg', '1510903715448.jpg', 'image/jpeg', '845941', null, '2017-11-17 15:28:37', '1');
INSERT INTO `system_file` VALUES ('5', 'Koala.jpg', '1510904191853.jpg', 'image/jpeg', '780831', null, '2017-11-17 15:36:32', '1');
INSERT INTO `system_file` VALUES ('6', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510991227724.jpg', 'image/jpeg', '11810', null, '2017-11-18 15:47:08', '1');
INSERT INTO `system_file` VALUES ('7', 'IMG_20161212_121454.jpg', '1510995299522.jpg', 'image/jpeg', '1865154', null, '2017-11-18 16:55:01', '1');
INSERT INTO `system_file` VALUES ('8', 'IMG_20170623_203731.jpg', '1510995437816.jpg', 'image/jpeg', '3047988', null, '2017-11-18 16:57:20', '1');
INSERT INTO `system_file` VALUES ('9', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510995697548.jpg', 'image/jpeg', '11810', null, '2017-11-18 17:01:38', '1');
INSERT INTO `system_file` VALUES ('10', '2.png', '1510995969606.png', 'image/png', '8212', null, '2017-11-18 17:06:10', '1');
INSERT INTO `system_file` VALUES ('11', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510998026317.jpg', 'image/jpeg', '11810', null, '2017-11-18 17:40:26', '1');
INSERT INTO `system_file` VALUES ('12', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510998255152.jpg', 'image/jpeg', '11810', null, '2017-11-18 17:44:15', '1');
INSERT INTO `system_file` VALUES ('13', '2.png', '1510998397155.png', 'image/png', '8212', null, '2017-11-18 17:46:37', '1');
INSERT INTO `system_file` VALUES ('14', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510998712336.jpg', 'image/jpeg', '11810', null, '2017-11-18 17:51:52', '1');
INSERT INTO `system_file` VALUES ('15', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510998758833.jpg', 'image/jpeg', '11810', null, '2017-11-18 17:52:39', '1');
INSERT INTO `system_file` VALUES ('16', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510999106055.jpg', 'image/jpeg', '11810', null, '2017-11-18 17:58:26', '1');
INSERT INTO `system_file` VALUES ('17', 'IMG_20161212_121454.jpg', '1510999224962.jpg', 'image/jpeg', '1865154', null, '2017-11-18 18:00:26', '1');
INSERT INTO `system_file` VALUES ('18', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1510999787220.jpg', 'image/jpeg', '11810', null, '2017-11-18 18:09:47', '1');
INSERT INTO `system_file` VALUES ('19', 'logo-chinese.jpg', '1511080385215.jpg', 'image/jpeg', '32145', null, '2017-11-19 16:33:05', '1');
INSERT INTO `system_file` VALUES ('20', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1511081193468.jpg', 'image/jpeg', '11810', null, '2017-11-19 16:46:34', '1');
INSERT INTO `system_file` VALUES ('21', '2.png', '1511083080155.png', 'image/png', '8212', null, '2017-11-19 17:18:00', '1');
INSERT INTO `system_file` VALUES ('22', '2.png', '1511083213601.png', 'image/png', '8212', null, '2017-11-19 17:20:14', '1');
INSERT INTO `system_file` VALUES ('23', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1511083341158.jpg', 'image/jpeg', '11810', null, '2017-11-19 17:22:21', '1');
INSERT INTO `system_file` VALUES ('24', 'logo-chinese.jpg', '1511085722907.jpg', 'image/jpeg', '32145', null, '2017-11-19 18:02:03', '1');
INSERT INTO `system_file` VALUES ('25', '267f9e2f070828385e512605b199a9014c08f147.jpg', '1511167489745.jpg', 'image/jpeg', '98834', null, '2017-11-20 16:44:50', '23');
INSERT INTO `system_file` VALUES ('26', 'u=1402786300,2528196698&fm=21&gp=0.jpg', '1511494915545.jpg', 'image/jpeg', '11810', null, '2017-11-24 11:41:56', '1');
INSERT INTO `system_file` VALUES ('27', 'Tulips.jpg', '1511495792239.jpg', 'image/jpeg', '620888', null, '2017-11-24 11:56:33', '25');
INSERT INTO `system_file` VALUES ('28', 'Desert.jpg', '1511502763140.jpg', 'image/jpeg', '845941', null, '2017-11-24 13:52:45', '1');
INSERT INTO `system_file` VALUES ('29', 'Chrysanthemum.jpg', '1511503560415.jpg', 'image/jpeg', '879394', null, '2017-11-24 14:06:04', '1');
INSERT INTO `system_file` VALUES ('30', 'Desert.jpg', '1511503591384.jpg', 'image/jpeg', '845941', null, '2017-11-24 14:06:33', '1');
INSERT INTO `system_file` VALUES ('31', 'ludashi.jpg', '1511529509825.jpg', 'image/jpeg', '104210', null, '2017-11-24 21:18:30', '1');
INSERT INTO `system_file` VALUES ('32', 'ludashi.jpg', '1511529682302.jpg', 'image/jpeg', '104210', null, '2017-11-24 21:21:22', '1');
INSERT INTO `system_file` VALUES ('33', 'ludashi.jpg', '1513265653588.jpg', 'image/jpeg', '104210', null, '2017-12-14 23:34:14', '1');
INSERT INTO `system_file` VALUES ('34', '1511494915545.jpg', '1513302951756.jpg', 'image/jpeg', '11810', null, '2017-12-15 09:55:52', '1');
INSERT INTO `system_file` VALUES ('35', '1511494915545.jpg', '1513304140881.jpg', 'image/jpeg', '11810', null, '2017-12-15 10:15:41', '1');
INSERT INTO `system_file` VALUES ('36', 'Koala.jpg', '1513304504904.jpg', 'image/jpeg', '780831', null, '2017-12-15 10:21:46', '1');
INSERT INTO `system_file` VALUES ('37', '1511494915545.jpg', '1513309721792.jpg', 'image/jpeg', '11810', null, '2017-12-15 11:48:42', '1');
INSERT INTO `system_file` VALUES ('38', '1511080385215.jpg', '1514024782552.jpg', 'image/jpeg', '32145', null, '2017-12-23 18:26:23', '1');
INSERT INTO `system_file` VALUES ('39', '1511080385215.jpg', '1514025057198.jpg', 'image/jpeg', '32145', null, '2017-12-23 18:30:57', '1');
INSERT INTO `system_file` VALUES ('40', '1511080385215.jpg', '1514025265504.jpg', 'image/jpeg', '32145', null, '2017-12-23 18:34:26', '1');
INSERT INTO `system_file` VALUES ('41', '1511494915545.jpg', '1514025677581.jpg', 'image/jpeg', '11810', null, '2017-12-23 18:41:18', '1');
INSERT INTO `system_file` VALUES ('42', '1511080385215.jpg', '1514547261006.jpg', 'image/jpeg', '32145', null, '2017-12-29 19:34:21', '1');
INSERT INTO `system_file` VALUES ('43', '1511494915545.jpg', '1514547273274.jpg', 'image/jpeg', '11810', null, '2017-12-29 19:34:33', '1');
INSERT INTO `system_file` VALUES ('44', 'Desert.jpg-ee330bc4aac04f87802958bd480b8bbd.jpg', '1515054128515.jpg-ee330bc4aac04f87802958bd480b8bbd', 'image/jpeg', '845941', null, '2018-01-04 16:22:10', '1');
INSERT INTO `system_file` VALUES ('45', 'Hydrangeas.jpg', '1515054766549.jpg', 'image/jpeg', '595284', null, '2018-01-04 16:32:51', '1');
INSERT INTO `system_file` VALUES ('46', 'Desert.jpg', '1515058404426.jpg', 'image/jpeg', '845941', null, '2018-01-04 17:33:26', '1');
INSERT INTO `system_file` VALUES ('47', '1511494915545.jpg', '1515072590732.jpg', 'image/jpeg', '11810', null, '2018-01-04 21:29:51', '1');

-- ----------------------------
-- Table structure for system_icon
-- ----------------------------
DROP TABLE IF EXISTS `system_icon`;
CREATE TABLE `system_icon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '图标名称',
  `unicode` varchar(50) DEFAULT NULL COMMENT 'unicode字符',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `weight` int(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='图标表';

-- ----------------------------
-- Records of system_icon
-- ----------------------------
INSERT INTO `system_icon` VALUES ('1', '设置', '&#xe620;', null, '10');
INSERT INTO `system_icon` VALUES ('2', '好友请求', '&#xe612;', null, '20');
INSERT INTO `system_icon` VALUES ('3', '文件夹', '&#xe622;', null, '30');
INSERT INTO `system_icon` VALUES ('4', '我的好友', '&#xe613;', null, '40');

-- ----------------------------
-- Table structure for system_menu
-- ----------------------------
DROP TABLE IF EXISTS `system_menu`;
CREATE TABLE `system_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `icon_id` int(11) DEFAULT NULL COMMENT '图标id',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `module` varchar(50) DEFAULT NULL COMMENT '模块',
  `weight` int(11) DEFAULT NULL COMMENT '排序',
  `pid` int(11) DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_menu
-- ----------------------------
INSERT INTO `system_menu` VALUES ('1', '系统模块', 'javascript:;', null, '1', 'system', '10', '0');
INSERT INTO `system_menu` VALUES ('2', '系统管理', 'javascript:;', '1', '2', 'system', '20', '1');
INSERT INTO `system_menu` VALUES ('3', '用户管理', '/user/query', '2', '3', 'system', '10', '2');
INSERT INTO `system_menu` VALUES ('7', '菜单管理', '/systemmenu/query', '3', '3', 'system', '20', '2');
INSERT INTO `system_menu` VALUES ('14', '角色管理', '/systemrole/query', '4', '3', 'system', '30', '2');
INSERT INTO `system_menu` VALUES ('27', '短信模块', 'javascript:;', null, '1', 'duanxin', '20', '0');
INSERT INTO `system_menu` VALUES ('32', '短信管理', 'javascript:;', '3', '2', 'duanxin', '10', '27');
INSERT INTO `system_menu` VALUES ('33', '发送短信', '/sms/toSms', '3', '3', 'duanxin', '10', '32');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `field` varchar(50) DEFAULT NULL COMMENT '角色域',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `weight` int(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', '系统管理员', 'adminstor', null, '10');
INSERT INTO `system_role` VALUES ('6', '设计部经理', 'designer_manager', '232345', '12');
INSERT INTO `system_role` VALUES ('7', '设计师', 'designer', '123', '100');
INSERT INTO `system_role` VALUES ('8', '质检员', 'qualitor', 'hello', '30');
INSERT INTO `system_role` VALUES ('9', '项目经理', 'project_manager', null, '40');
INSERT INTO `system_role` VALUES ('10', '营销顾问', 'salesman', null, '50');
INSERT INTO `system_role` VALUES ('11', '营销经理', 'salesman_manager', null, '60');
INSERT INTO `system_role` VALUES ('12', '财务人员', 'finance', null, '70');
INSERT INTO `system_role` VALUES ('13', '客服经理', 'customer_service_manager', null, '80');
INSERT INTO `system_role` VALUES ('14', '客服', 'customer_service', null, '45');
INSERT INTO `system_role` VALUES ('15', '材料部经理', 'material_manager', null, '100');

-- ----------------------------
-- Table structure for system_sms_api
-- ----------------------------
DROP TABLE IF EXISTS `system_sms_api`;
CREATE TABLE `system_sms_api` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `api_name` varchar(50) DEFAULT NULL COMMENT 'api名称',
  `api_key` varchar(255) DEFAULT NULL,
  `api_signature` varchar(50) DEFAULT NULL COMMENT '签名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_sms_api
-- ----------------------------
INSERT INTO `system_sms_api` VALUES ('1', 'LuosimaoApi', '4ea66b9ea07273a78216493d29684918', '【河豚科技】');

-- ----------------------------
-- Table structure for system_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `system_sms_log`;
CREATE TABLE `system_sms_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `content` varchar(255) DEFAULT NULL COMMENT '发送内容',
  `api` varchar(50) DEFAULT NULL,
  `token` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '状态 0 未发送 1 已发送 2 发送成功 -1 发送失败',
  `response` varchar(4096) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 COMMENT='短信发送日志';

-- ----------------------------
-- Records of system_sms_log
-- ----------------------------
INSERT INTO `system_sms_log` VALUES ('154', '18362686001', 'abc', 'LUOSIMAO', '6988fdf71cc04fe28c0d7c2e3649d6fe', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-C828A4AA-4CB5-DC1C-3274-61F40E23A998\"}', null, '2018-01-09 22:52:21', '2018-01-10 10:42:23');
INSERT INTO `system_sms_log` VALUES ('155', '13770878094', '高压锅高压锅', 'LUOSIMAO', 'c55b9212225c4cc589e9d69287e1aa52', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-C42F27D5-CE14-B5BA-B70A-C69BF30E6CD2\"}', null, '2018-01-10 10:49:02', '2018-01-10 10:49:03');
INSERT INTO `system_sms_log` VALUES ('156', '18362686001', 'test', 'LUOSIMAO', '49230950647a4c5d85cb12e3ef15a5cd', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-4AF04B68-697B-E215-D2BA-B28E8C1FA508\"}', null, '2018-01-10 14:35:40', '2018-01-10 14:35:45');
INSERT INTO `system_sms_log` VALUES ('157', '13362895692', 'test', 'LUOSIMAO', '49230950647a4c5d85cb12e3ef15a5cd', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-81524F17-7AF4-C2FC-1A9C-6FFAD9C3C00F\"}', null, '2018-01-10 14:35:40', '2018-01-10 14:35:45');
INSERT INTO `system_sms_log` VALUES ('158', '18362686001', 'hello world', 'LUOSIMAO', 'f2c88d4b9e5d4163a7d8036a3afe6d22', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-1F466D3F-F35B-20CC-8ABF-AA071593C342\"}', null, '2018-01-15 16:49:45', '2018-01-15 16:49:48');
INSERT INTO `system_sms_log` VALUES ('159', '18362686001', 'hello', 'LUOSIMAO', '0fe4dc0861b04ffc90409be9e394002c', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-9CD5B609-4C8F-AC5B-D18A-6671725F8AA8\"}', null, '2018-01-16 20:46:12', '2018-01-16 20:46:13');
INSERT INTO `system_sms_log` VALUES ('160', '18362686001', 'hello', 'LUOSIMAO', '6797d921004341468bb6eb75803deada', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-37832D37-023C-B2AD-178F-0078ED97AED9\"}', null, '2018-01-16 20:46:12', '2018-01-16 20:46:13');
INSERT INTO `system_sms_log` VALUES ('161', '18362686001', 'hello', 'LUOSIMAO', '5a298f38b65b41ce8b80046660301602', '1', '{\"error\":0,\"msg\":\"ok\",\"batch_id\":\"03-FBA4CD87-3812-6A10-B4BF-650ADE763B89\"}', null, '2018-01-16 20:48:18', '2018-01-16 20:48:23');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `realname` varchar(50) DEFAULT NULL COMMENT '真是姓名',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `img` int(11) DEFAULT NULL COMMENT '头像',
  `addr` varchar(255) DEFAULT NULL COMMENT '地址',
  `birthdate` varchar(20) DEFAULT NULL COMMENT '生日',
  `status` int(11) DEFAULT NULL COMMENT '状态 （0 启用 1 禁用）',
  `deleted` int(11) DEFAULT NULL COMMENT '是否删除  0 未删除  1 已删除 逻辑删除  暂时不用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'cbk', '123456', '陈炳坤', '18362686001', '47', '天鸿香榭里1', '1992-09-28', '0', null, '2017-10-25 09:00:00', '2018-01-04 21:29:52');
INSERT INTO `system_user` VALUES ('3', 'huangzijian', '234', '黄子剑', null, null, null, null, '1', null, '2017-10-30 11:45:30', '2017-12-06 22:19:36');
INSERT INTO `system_user` VALUES ('4', 'jinhui', '123456', '金老师', '15897877878', null, '南京大厂', '1992-10-01', '0', null, '2017-10-31 11:22:04', '2018-01-04 21:35:47');
INSERT INTO `system_user` VALUES ('7', 'huxiaopeng', '123456', '胡晓鹏', '15267556788', null, null, '2017-10-16', '0', null, '2017-10-31 13:44:31', '2017-10-31 15:54:10');
INSERT INTO `system_user` VALUES ('8', 'jiangquan', 'jq123456', '江佺', '15867898766', null, null, '2017-10-08', '1', null, '2017-10-31 15:55:57', '2017-12-29 19:31:38');
INSERT INTO `system_user` VALUES ('9', 'heyidong', '123456', '何一东', '15866778899', null, null, '2017-10-09', '1', null, '2017-10-31 16:22:06', '2017-12-07 22:11:49');
INSERT INTO `system_user` VALUES ('21', 'yedan', '123456', '叶丹', '18262664743', null, null, '2017-10-01', '1', null, '2017-10-31 17:38:55', '2017-12-06 22:10:26');
INSERT INTO `system_user` VALUES ('22', 'lizhiyuan', '123456', '李志远', '15895816708', null, null, '2017-11-08', '1', null, '2017-11-01 11:16:39', '2017-12-07 11:43:51');
INSERT INTO `system_user` VALUES ('23', 'chenergou', '123456', '陈二狗', '13770878091', '25', '12312312', '2017-11-21', '0', null, '2017-11-20 13:37:58', '2018-01-04 17:48:04');

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('13', '34', '1');
INSERT INTO `system_user_role` VALUES ('14', '23', '1');
INSERT INTO `system_user_role` VALUES ('16', '1', '1');
INSERT INTO `system_user_role` VALUES ('17', '8', '1');
INSERT INTO `system_user_role` VALUES ('18', '4', '1');
