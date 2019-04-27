CREATE DATABASE bookie;

USE bookie;

DROP TABLE IF EXISTS book_library;
DROP TABLE IF EXISTS book_type;

DROP TABLE IF EXISTS s_role_permission;
DROP TABLE IF EXISTS s_user_role;
DROP TABLE IF EXISTS s_user;
DROP TABLE IF EXISTS s_role;
DROP TABLE IF EXISTS s_permission;



CREATE TABLE book_type
(
	`type_name` VARCHAR(10),
	PRIMARY KEY (type_name)
);

CREATE TABLE book_library
(
	`title` VARCHAR(40),
	`author` VARCHAR(30),
	`type` VARCHAR(10),
	`description` VARCHAR(200),
	`storage` INTEGER,
	`cover_url` VARCHAR(70),
	`isbn` VARCHAR(15),
	`price` DECIMAL(8, 2),
	`coupon_price` DECIMAL(8, 2),
	PRIMARY KEY (`isbn`),
	FOREIGN KEY (`type`) REFERENCES book_type (`type_name`) ON DELETE SET NULL
);

CREATE TABLE s_user
(
	`user_id` INTEGER auto_increment,
	`username` VARCHAR(40),
	`password` VARCHAR(40),
	PRIMARY KEY (`user_id`)
);

CREATE TABLE s_role
(
	`role_id` INTEGER auto_increment,
	`role` VARCHAR(32),
	PRIMARY KEY (`role_id`)
);

CREATE TABLE s_permission
(
	`perm_id` INTEGER auto_increment,
	`permission` VARCHAR(32),
	`url` VARCHAR(32),
	PRIMARY KEY (`perm_id`)
);

CREATE TABLE s_role_permission
(
	`role_id` INTEGER,
	`perm_id` INTEGER,
	FOREIGN KEY (`role_id`) REFERENCES s_role(`role_id`) ON DELETE SET NULL,
	FOREIGN KEY (`perm_id`) REFERENCES s_permission(`perm_id`) ON DELETE SET NULL
);

CREATE TABLE s_user_role
(
	`user_id` INTEGER,
	`role_id` INTEGER,
	FOREIGN KEY (`user_id`) REFERENCES s_user(`user_id`) ON DELETE SET NULL,
	FOREIGN KEY (`role_id`) REFERENCES s_role(`role_id`) ON DELETE SET NULL
);