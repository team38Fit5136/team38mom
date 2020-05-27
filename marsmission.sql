-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: localhost    Database: marsmission
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `cargo_id` int(11) NOT NULL AUTO_INCREMENT,
  `cargo_journey` json DEFAULT NULL,
  `cargo_mission` json DEFAULT NULL,
  `cargo_other_mission` json DEFAULT NULL,
  PRIMARY KEY (`cargo_id`)
) 
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `country_id` int(11) NOT NULL,
  `country_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_name_UNIQUE` (`country_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_title` varchar(45) DEFAULT NULL,
  `emp_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_title` varchar(45) DEFAULT NULL,
  `job_no` int(11) DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_north` varchar(45) DEFAULT NULL,
  `location_east` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission_details`
--

DROP TABLE IF EXISTS `mission_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission_details` (
  `mission_id` int(11) NOT NULL,
  `Mission_name` varchar(45) DEFAULT NULL,
  `country_origin` int(11) DEFAULT NULL,
  `country_allowed` json DEFAULT NULL,
  `mission_details` text,
  `coordinator_id` int(11) NOT NULL,
  `launch_date` date DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `cargo_id` int(11) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  `job_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT '1',
  `shuttle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`mission_id`),
  UNIQUE KEY `Mission_name_UNIQUE` (`Mission_name`),
  UNIQUE KEY `country_origin_UNIQUE` (`country_origin`),
  KEY `coordinator_id_idx` (`coordinator_id`),
  KEY `location_idx` (`location_id`),
  KEY `status_idx` (`status_id`),
  KEY `emp_id_idx` (`emp_id`),
  KEY `cargo_id_idx` (`cargo_id`),
  KEY `job_idx` (`job_id`),
  KEY `shuttle_id_idx` (`shuttle_id`),
  CONSTRAINT `cargo_id` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`cargo_id`) ON UPDATE CASCADE,
  CONSTRAINT `coordinator_id` FOREIGN KEY (`coordinator_id`) REFERENCES `user_info` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `country_origin` FOREIGN KEY (`country_origin`) REFERENCES `country` (`country_id`) ON UPDATE CASCADE,
  CONSTRAINT `emp_id` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON UPDATE CASCADE,
  CONSTRAINT `job_id` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`) ON UPDATE CASCADE,
  CONSTRAINT `location_id` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON UPDATE CASCADE,
  CONSTRAINT `shuttle_id` FOREIGN KEY (`shuttle_id`) REFERENCES `shuttle` (`shuttle_id`) ON UPDATE CASCADE,
  CONSTRAINT `status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission_details`
--

LOCK TABLES `mission_details` WRITE;
/*!40000 ALTER TABLE `mission_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `mission_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle`
--

DROP TABLE IF EXISTS `shuttle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shuttle` (
  `shuttle_id` int(11) NOT NULL,
  `shuttle_name` varchar(45) DEFAULT NULL,
  `manfacture_year` date DEFAULT NULL,
  `fuel_capacity` int(11) DEFAULT NULL,
  `passenger_capacity` int(11) DEFAULT NULL,
  `cargo_capacity` int(11) DEFAULT NULL,
  `travel_speed` int(11) DEFAULT NULL,
  PRIMARY KEY (`shuttle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle`
--

LOCK TABLES `shuttle` WRITE;
/*!40000 ALTER TABLE `shuttle` DISABLE KEYS */;
/*!40000 ALTER TABLE `shuttle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `status_name_UNIQUE` (`status_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  `user_password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `User_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-29 12:50:29
