-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
-- test
-- Host: localhost    Database: warung_makan_samudra
-- ------------------------------------------------------
-- Server version	9.0.1
-- Table structure for table `bill_details`
--

DROP TABLE IF EXISTS `bill_details`;

CREATE TABLE `bill_details` (
  `bill_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_id` bigint DEFAULT NULL,
  `product_code` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`bill_detail_id`),
  KEY `transaction_id` (`transaction_id`),
  KEY `product_code` (`product_code`),
  CONSTRAINT `bill_details_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`),
  CONSTRAINT `bill_details_ibfk_2` FOREIGN KEY (`product_code`) REFERENCES `products` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `branches`
--

DROP TABLE IF EXISTS `branches`;

CREATE TABLE `branches` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch_code` varchar(255) DEFAULT NULL,
  `branch_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `branch_code` (`branch_code`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_code` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `branch_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_code` (`product_code`),
  KEY `branch_id` (`branch_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`branch_id`) REFERENCES `branches` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;

CREATE TABLE `transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_type` enum('EAT_IN','TAKE_AWAY','ONLINE') NOT NULL,
  `receipt_number` varchar(255) NOT NULL,
  `transaction_date` date NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `branch_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `receipt_number` (`receipt_number`),
  KEY `fk_branch_code` (`branch_code`),
  CONSTRAINT `fk_branch_code` FOREIGN KEY (`branch_code`) REFERENCES `branches` (`branch_code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Dump completed on 2025-01-06 12:08:52
