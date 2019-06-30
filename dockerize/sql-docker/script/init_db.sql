SET GLOBAL time_zone = "+8:00";
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS bookie;
CREATE DATABASE bookie;

USE bookie;

DROP TABLE IF EXISTS book_library;
DROP TABLE IF EXISTS book_type;

DROP TABLE IF EXISTS s_role_permission;
DROP TABLE IF EXISTS s_user_role;
DROP TABLE IF EXISTS s_user;
DROP TABLE IF EXISTS s_role;
DROP TABLE IF EXISTS s_permission;
DROP TABLE IF EXISTS bills;
DROP TABLE IF EXISTS comments;

CREATE TABLE book_type
(
	`type_name` VARCHAR(10),
	PRIMARY KEY (type_name)
);

CREATE TABLE book_library
(
	`disabled` BOOLEAN,
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
	`available` BOOLEAN,
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
	FOREIGN KEY (`fk_role_id`) REFERENCES s_role(`id`) ON DELETE RESTRICT,
	FOREIGN KEY (`fk_permission_id`) REFERENCES s_permission(`id`) ON DELETE RESTRICT
);

CREATE TABLE s_user_role
(
	`fk_user_id` INTEGER,
	`fk_role_id` INTEGER,
	FOREIGN KEY (`fk_user_id`) REFERENCES s_user(`id`) ON DELETE RESTRICT,
	FOREIGN KEY (`fk_role_id`) REFERENCES s_role(`id`) ON DELETE RESTRICT
);

CREATE TABLE paid_orders
(
	`bill_uuid` VARCHAR(36),
	`user_id` INTEGER,
	`time` DATETIME,
	`receiver` VARCHAR(10),
	`phone_no` VARCHAR(20),
	`address` VARCHAR(81),
    PRIMARY KEY (`bill_uuid`),
    FOREIGN KEY (`user_id`) REFERENCES s_user (`id`) ON DELETE RESTRICT
);

CREATE TABLE order_items (
    `bill_uuid` VARCHAR(36),
    `count` INTEGER,
    `isbn` VARCHAR(15),
    PRIMARY KEY (`isbn` , `bill_uuid`),
    FOREIGN KEY (`bill_uuid`)
        REFERENCES paid_orders (`bill_uuid`),
    FOREIGN KEY (`isbn`)
        REFERENCES book_library (`isbn`)
);


CREATE TABLE unpaid_carts
(
	`user_id` INTEGER,
	`count` INTEGER,
	`isbn` VARCHAR(15),
    PRIMARY KEY (`isbn`, `user_id`),
    FOREIGN KEY (`isbn`) REFERENCES book_library (`isbn`) ON DELETE RESTRICT,
    FOREIGN KEY (`user_id`) REFERENCES s_user (`id`) ON DELETE RESTRICT
);

CREATE TABLE comments
(
	`comm_uuid` VARCHAR(36),
	`user_id` INTEGER,
	`time` DATETIME,
	`purchased` BOOLEAN,
	`isbn` VARCHAR(15),
	`comment_content` VARCHAR(1024),
	PRIMARY KEY (`comm_uuid`),
	FOREIGN KEY (`user_id`) REFERENCES s_user (`id`) ON DELETE RESTRICT,
	FOREIGN KEY (`isbn`) REFERENCES book_library (`isbn`) ON DELETE RESTRICT
);