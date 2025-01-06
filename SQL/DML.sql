-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: warung_makan_samudra
-- ------------------------------------------------------
-- Server version	9.0.1

--
-- Dumping data for table `bill_details`
--

LOCK TABLES `bill_details` WRITE;
INSERT INTO `bill_details` VALUES (1,3,'P001',2),(2,3,'P002',1),(3,4,'P001',2),(4,4,'P002',1),(5,5,'P001',2),(6,5,'P002',2);
UNLOCK TABLES;

--
-- Dumping data for table `branches`
--

LOCK TABLES `branches` WRITE;
INSERT INTO `branches` VALUES (14,'B001','Main 1','1234 Main St','1234567890'),(15,'B002','Jakarta Selatan','Kebayoran Lama','1234567890'),(17,'BR001','Updated Branch Name','123 Main Street','123-456-7890');
UNLOCK TABLES;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
INSERT INTO `products` VALUES (1,'P001','Product A',10000.00,14),(3,'P002','Product B',10000.00,14);
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
INSERT INTO `transactions` VALUES (1,'ONLINE','123456','2025-01-05',30000.00,'B002'),(3,'ONLINE','10001','2025-01-05',30000.00,'B002'),(4,'TAKE_AWAY','10002','2025-01-05',30000.00,'B002'),(5,'EAT_IN','10003','2025-01-05',40000.00,'B001');
UNLOCK TABLES;



-- Dump completed on 2025-01-06 12:09:40
