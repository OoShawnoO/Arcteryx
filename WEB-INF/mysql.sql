#建数据库
CREATE DATABASE record

#建表
CREATE TABLE `record`(
    `id` INT UNSIGNED AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `uploader` VARCHAR(40) NOT NULL,
    `price` FLOAT NOT NULL,
    `cost` FLOAT NOT NULL,
    PRIMARY KEY ( `id` )
    )