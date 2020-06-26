DROP DATABASE IF EXISTS `im_database`;
	
CREATE DATABASE `im_database`;
USE `im_database`;
CREATE TABLE IF NOT EXISTS `USER` (
	`CREATED` TIMESTAMP NOT NULL,
	`MODIFIED` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`USER_ID` VARCHAR(20) NOT NULL, -- 用户账号
	`USERNAME` VARCHAR(100) NOT NULL UNIQUE, -- 用户昵称
	`PASSWORD` VARCHAR(128) NOT NULL, -- 基于MD5加密
	`BIRTH_DAY` TIMESTAMP,
	`GENDER` ENUM('MEN', 'LADY', 'UNKNOWN', 'OTHER') DEFAULT 'UNKNOWN',
	`ADDRESS` VARCHAR(200),
	`USER_PHOTO` VARCHAR(200), -- 用户头像
	`USER_PHOTO_BIG` VARCHAR(200) -- 用户头像（大）
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `CONTACT` (
	`CREATED` TIMESTAMP NOT NULL,
	`MODIFIED` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`ONE_USER_ID` BIGINT NOT NULL,
	`OTHER_USER_ID` BIGINT NOT NULL,
	`CATECORY_ID` BIGINT NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `CONTACT_CATEGORY` (
	`CREATED` TIMESTAMP NOT NULL,
	`MODIFIED` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`USER_ID` BIGINT NOT NULL UNIQUE,
	`CATEGORY_NAME` VARCHAR(20) NOT NULL,
	`CATEGORY_NUM` INT NOT NULL,
	INDEX(`USER_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `MESSAGE` (
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`TYPE` ENUM('PLAIN_TEXT', 'URL', 'HYBRID'),
	`CONTENT` VARCHAR(1000) NOT NULL,
	`SENDER_ID` BIGINT NOT NULL,
	`RECEIVER_ID` BIGINT NOT NULL,
	`SENDING_TIME` TIMESTAMP NOT NULL,
	INDEX(`SENDER_ID`, `RECEIVER_ID`)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `MESSAGE_INDEX` (
	`CREATED` TIMESTAMP NOT NULL,
	`MODIFIED` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`ONE_USER_ID` BIGINT NOT NULL,
	`OTHER_USER_ID` BIGINT NOT NULL,
	`MESSAGE_ID` BIGINT NOT NULL,
	`RECEIVED` ENUM('0', '1')
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `GROUP` (
	`CREATED` TIMESTAMP NOT NULL,
	`MODIFIED` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`GROUP_NAME` VARCHAR(20) NOT NULL,
	`GROUP_OWNER_ID` BIGINT  NOT NULL,
	`GROUP_PHOTO` VARCHAR(200)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `GROUP_MEMBER` (
	`JOIN` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`GROUP_ID` BIGINT NOT NULL,
	`MEMBER_ID` BIGINT NOT NULL,
	`TYPE` ENUM('OWNER', 'ADMIN', 'MEMBER') NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `GROUP_RESOURCE` (
	`CREATED` TIMESTAMP NOT NULL,
	`MODIFIED` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`GROUP_ID` BIGINT NOT NULL,
	`PUBLISHED_ID` BIGINT NOT NULL,
	`TYPE` ENUM('NOTICE', 'FILE', 'URL') NOT NULL,
	`CONTENT` VARCHAR(200) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `GROUP_MESSAGE` (
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`GROUP_ID` BIGINT NOT NULL,
	`SENDER_ID` BIGINT NOT NULL,
	`SENDING_TIME` TIMESTAMP NOT NULL,
	`TYPE` ENUM('PLAIN_TEXT', 'URL', 'HYBRID'),
	`CONTENT` VARCHAR(1000) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `GROUP_MESSAGE_INDEX` (
	`CREATED` TIMESTAMP NOT NULL,
	`ID` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`GROUP_ID` BIGINT NOT NULL,
	`SENDER_ID` BIGINT NOT NULL,
	`RECEIVER_ID` BIGINT NOT NULL,
	`MESSAGE_ID` BIGINT NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=UTF8;
