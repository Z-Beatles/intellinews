/*
Navicat MySQL Data Transfer

Source Server         : 郑州服务器
Source Server Version : 50720
Source Host           : 119.31.210.76:3306
Source Database       : intellinews

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-12-04 12:32:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oi_advertise
-- ----------------------------
DROP TABLE IF EXISTS `oi_advertise`;
CREATE TABLE `oi_advertise` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `title` varchar(255) DEFAULT NULL COMMENT '广告标题',
  `url` varchar(255) DEFAULT NULL COMMENT '广告图片',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '广告缩略图',
  `is_active` tinyint(1) unsigned DEFAULT NULL COMMENT '广告状态(上架/下架)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_article
-- ----------------------------
DROP TABLE IF EXISTS `oi_article`;
CREATE TABLE `oi_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `source` varchar(30) DEFAULT NULL COMMENT '文章来源',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '缩略图',
  `content` text COMMENT '文章内容',
  `keywords` json DEFAULT NULL COMMENT '关键字json序列',
  `section` json DEFAULT NULL COMMENT '条目json序列',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_article_channel
-- ----------------------------
DROP TABLE IF EXISTS `oi_article_channel`;
CREATE TABLE `oi_article_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `channel_id` bigint(20) unsigned DEFAULT NULL COMMENT '频道id',
  `article_id` bigint(20) unsigned DEFAULT NULL COMMENT '文章id',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_article_count
-- ----------------------------
DROP TABLE IF EXISTS `oi_article_count`;
CREATE TABLE `oi_article_count` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '统计id',
  `article_id` bigint(20) unsigned NOT NULL COMMENT '文章id',
  `like_count` int(10) unsigned DEFAULT '0' COMMENT '赞数量',
  `dislike_count` int(10) unsigned DEFAULT '0' COMMENT '踩数量',
  `view_count` int(10) unsigned DEFAULT '0' COMMENT '浏览数量',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_atlas
-- ----------------------------
DROP TABLE IF EXISTS `oi_atlas`;
CREATE TABLE `oi_atlas` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `section_id` bigint(20) unsigned DEFAULT NULL COMMENT '条目id',
  `relation_id` bigint(20) unsigned DEFAULT NULL COMMENT '图谱关联id',
  `relation_type` enum('section','article') DEFAULT 'section' COMMENT '图谱类型',
  `relation_degree` smallint(3) unsigned DEFAULT NULL COMMENT '相关度（1~375）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_channel
-- ----------------------------
DROP TABLE IF EXISTS `oi_channel`;
CREATE TABLE `oi_channel` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '频道id',
  `name` varchar(20) DEFAULT NULL COMMENT '频道名称（英文）',
  `name_zh` varchar(8) DEFAULT NULL COMMENT '频道名称（中文）',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_column
-- ----------------------------
DROP TABLE IF EXISTS `oi_column`;
CREATE TABLE `oi_column` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '专栏id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_comment
-- ----------------------------
DROP TABLE IF EXISTS `oi_comment`;
CREATE TABLE `oi_comment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `article_id` bigint(20) unsigned DEFAULT NULL COMMENT '文章id',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `like_count` int(10) unsigned DEFAULT '0' COMMENT '赞数量',
  `dislike_count` int(10) unsigned DEFAULT '0' COMMENT '踩数量',
  `is_delete` tinyint(1) unsigned DEFAULT '0' COMMENT '删除评论',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_footmark
-- ----------------------------
DROP TABLE IF EXISTS `oi_footmark`;
CREATE TABLE `oi_footmark` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `content_id` bigint(20) unsigned DEFAULT NULL COMMENT '浏览对象的id',
  `content_type` varchar(20) DEFAULT NULL COMMENT '浏览对象的类型',
  `source` varchar(255) DEFAULT NULL COMMENT '足迹来源',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=728 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_keyword
-- ----------------------------
DROP TABLE IF EXISTS `oi_keyword`;
CREATE TABLE `oi_keyword` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '关键字id',
  `name` varchar(50) DEFAULT NULL COMMENT '关键字名称',
  `count` int(10) unsigned DEFAULT '0' COMMENT '关键字搜索统计',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_section
-- ----------------------------
DROP TABLE IF EXISTS `oi_section`;
CREATE TABLE `oi_section` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '条目id',
  `logo` varchar(255) DEFAULT NULL COMMENT '条目logo',
  `name` varchar(50) DEFAULT NULL COMMENT '条目名称',
  `create_by` varchar(20) DEFAULT NULL COMMENT '创建人姓名',
  `update_by` varchar(20) DEFAULT NULL COMMENT '修改人姓名',
  `introduction` text COMMENT '条目简介',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_section_alias
-- ----------------------------
DROP TABLE IF EXISTS `oi_section_alias`;
CREATE TABLE `oi_section_alias` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `section_id` bigint(20) unsigned NOT NULL COMMENT '条目id',
  `alias` varchar(255) NOT NULL COMMENT '条目别名',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=538 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_section_count
-- ----------------------------
DROP TABLE IF EXISTS `oi_section_count`;
CREATE TABLE `oi_section_count` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '段落id',
  `section_id` bigint(20) unsigned NOT NULL COMMENT '条目id',
  `view_count` int(10) unsigned DEFAULT '0' COMMENT '条目浏览量',
  `share_count` int(10) unsigned DEFAULT '0' COMMENT '条目分享数量',
  `collect_count` int(10) unsigned DEFAULT '0' COMMENT '条目收藏数量',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_section_item
-- ----------------------------
DROP TABLE IF EXISTS `oi_section_item`;
CREATE TABLE `oi_section_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '条目id',
  `section_id` bigint(20) unsigned DEFAULT NULL COMMENT '条目id',
  `item_info` json DEFAULT NULL COMMENT '条目扩展信息',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_user_article
-- ----------------------------
DROP TABLE IF EXISTS `oi_user_article`;
CREATE TABLE `oi_user_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `resource_id` bigint(20) unsigned DEFAULT NULL COMMENT '收藏资源id',
  `resource_type` varchar(10) DEFAULT NULL COMMENT '收藏资源类型(article、column)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_user_config
-- ----------------------------
DROP TABLE IF EXISTS `oi_user_config`;
CREATE TABLE `oi_user_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `channel_config` json DEFAULT NULL COMMENT '个人新闻首页频道配置',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_user_info
-- ----------------------------
DROP TABLE IF EXISTS `oi_user_info`;
CREATE TABLE `oi_user_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(15) DEFAULT NULL COMMENT '用户名',
  `weixin_id` varchar(255) DEFAULT NULL COMMENT '微信账号',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) DEFAULT NULL COMMENT '电话',
  `gmt_create` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_user_keyword
-- ----------------------------
DROP TABLE IF EXISTS `oi_user_keyword`;
CREATE TABLE `oi_user_keyword` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `keyword` varchar(10) DEFAULT NULL COMMENT '关键字',
  `attention` int(255) DEFAULT '1' COMMENT '关注度',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_user_login
-- ----------------------------
DROP TABLE IF EXISTS `oi_user_login`;
CREATE TABLE `oi_user_login` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `password_hash` varchar(100) DEFAULT NULL COMMENT '密码',
  `password_salt` varchar(100) DEFAULT NULL COMMENT '密码盐值',
  `password_algo` varchar(20) NOT NULL COMMENT '加密方式',
  `password_iteration` int(11) unsigned DEFAULT NULL COMMENT '加密次数',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oi_user_section
-- ----------------------------
DROP TABLE IF EXISTS `oi_user_section`;
CREATE TABLE `oi_user_section` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `section_id` bigint(20) unsigned DEFAULT NULL COMMENT '条目id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
