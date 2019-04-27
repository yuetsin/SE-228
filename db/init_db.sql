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
	`id` INTEGER auto_increment,
	`name` VARCHAR(40),
	`password` VARCHAR(40),
	PRIMARY KEY (`id`)
);

CREATE TABLE s_role
(
	`id` INTEGER auto_increment,
	`role` VARCHAR(32),
	PRIMARY KEY (`id`)
);

CREATE TABLE s_permission
(
	`id` INTEGER auto_increment,
	`permission` VARCHAR(32),
	`url` VARCHAR(32),
	PRIMARY KEY (`id`)
);

CREATE TABLE s_role_permission
(
	`fk_role_id` INTEGER,
	`fk_permission_id` INTEGER,
	FOREIGN KEY (`fk_role_id`) REFERENCES s_role(`id`) ON DELETE SET NULL,
	FOREIGN KEY (`fk_permission_id`) REFERENCES s_permission(`id`) ON DELETE SET NULL
);

CREATE TABLE s_user_role
(
	`fk_user_id` INTEGER,
	`fk_role_id` INTEGER,
	FOREIGN KEY (`fk_user_id`) REFERENCES s_user(`id`) ON DELETE SET NULL,
	FOREIGN KEY (`fk_role_id`) REFERENCES s_role(`id`) ON DELETE SET NULL
);