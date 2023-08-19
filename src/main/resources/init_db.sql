CREATE SCHEMA IF NOT EXISTS `rental_car_service` DEFAULT CHARACTER SET utf8;
USE `rental_car_service`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `renters`;
CREATE TABLE `renters`  (
                            `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(255) NOT NULL,
                            `login` VARCHAR(255) NOT NULL,
                            `password` VARCHAR(255) NOT NULL,
                            `driver_license_number` VARCHAR(255) NOT NULL,
                            `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
                            PRIMARY KEY (`id`) USING BTREE
);

DROP TABLE IF EXISTS `manufacturers`;
CREATE TABLE `manufacturers`  (
                                  `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `name` VARCHAR(255) NOT NULL,
                                  `country` VARCHAR(255) NOT NULL,
                                  `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
                                  PRIMARY KEY (`id`) USING BTREE
);

DROP TABLE IF EXISTS `rental_cars`;
CREATE TABLE `rental_cars`  (
                         `id` BIGINT(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `model` VARCHAR(255) NOT NULL,
                         `manufacturer_id` BIGINT(0) UNSIGNED NOT NULL,
                         `is_deleted` BIT(1) NOT NULL DEFAULT b'0',
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `FK_manufacturer_id`(`manufacturer_id`) USING BTREE,
                         CONSTRAINT `FK_manufacturer_id` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

DROP TABLE IF EXISTS `rental_cars_renters`;
CREATE TABLE `rental_cars_renters`  (
                                 `rental_car_id` BIGINT(0) UNSIGNED NOT NULL,
                                 `renter_id` BIGINT(0) UNSIGNED NOT NULL,
                                 PRIMARY KEY (`rental_car_id`, `renter_id`) USING BTREE,
                                 INDEX `renter_id`(`renter_id`) USING BTREE,
                                 INDEX `rental_car_id`(`rental_car_id`) USING BTREE,
                                 CONSTRAINT `rental_car_id` FOREIGN KEY (`rental_car_id`) REFERENCES `rental_cars` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                 CONSTRAINT `renter_id` FOREIGN KEY (`renter_id`) REFERENCES `renters` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

SET FOREIGN_KEY_CHECKS = 1;