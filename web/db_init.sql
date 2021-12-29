#建数据库
CREATE DATABASE record

#建数据表
CREATE TABLE `record`(
    `id` INT UNSIGNED AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `uploader` VARCHAR(40) NOT NULL,
    `price` FLOAT NOT NULL,
    `cost` FLOAT NOT NULL,
    `intro` VARCHAR(400) NOT NULL,
    `imgsrc` VARCHAR(100) NOT NULL,
    PRIMARY KEY ( `id` )
    )
#删除记录表
CREATE TABLE `delete_history`(
     `id` INT UNSIGNED AUTO_INCREMENT,
     `name` VARCHAR(100) NOT NULL,
     `deleter` VARCHAR(40) NOT NULL,
     `price` FLOAT NOT NULL,
     `cost` FLOAT NOT NULL,
     PRIMARY KEY ( `id` )
)
#更新记录表
CREATE TABLE `update_history`(
     `id` INT UNSIGNED AUTO_INCREMENT,
     `new_name` VARCHAR(100) NOT NULL,
     `updater` VARCHAR(40) NOT NULL,
     `new_price` FLOAT NOT NULL,
     `new_cost` FLOAT NOT NULL,
     `old_name` VARCHAR(100) NOT NULL,
     `old_price` FLOAT NOT NULL,
     `old_cost` FLOAT NOT NULL,
     `datetime` DATE NOT NULL,
     PRIMARY KEY ( `id` )
)
#用户表
CREATE TABLE `users`(
    `id` INT UNSIGNED AUTO_INCREMENT,
    `username` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
)

#爬虫数据表
CREATE TABLE `scrapy`(
    `id` INT UNSIGNED AUTO_INCREMENT,
    `uid` VARCHAR(100) NOT NULL,
    `userid` VARCHAR(100) NOT NULL,
    `username` VARCHAR(100) NOT NULL,
    `price` float not null,
    `painwear` VARCHAR(100) NOT NULL,
    PRIMARY KEY ( `id` )
)