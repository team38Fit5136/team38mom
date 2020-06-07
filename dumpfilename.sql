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
  `cargo` json DEFAULT NULL,
  `mission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cargo_id`),
  KEY `fk_cargo_1` (`mission_id`),
  CONSTRAINT `fk_cargo_1` FOREIGN KEY (`mission_id`) REFERENCES `mission_details` (`mission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (0,'{\"cargo_journey\": \"no cargo\"}',NULL),(12,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',37),(13,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',38),(14,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',40),(15,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',42),(16,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',44),(17,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',46),(18,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',47),(19,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',52),(20,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',53),(21,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',54),(22,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',55),(23,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',57),(24,'{\"cargoForJourney\": [{\"soup\": \"10\"}, {\"wood\": \"1555\"}, {\"dnlfl\": \"1205\"}, {\"sfnlf\": \"1205\"}], \"cargoForMission\": [], \"cargoForOtherMission\": []}',59);
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_name` varchar(45) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_name_UNIQUE` (`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (3,'America'),(6,'Austria'),(9,'India'),(1,'No country'),(10,'North Russia');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country_allowed`
--

DROP TABLE IF EXISTS `country_allowed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country_allowed` (
  `country_id` int(11) DEFAULT NULL,
  `mission_id` int(11) DEFAULT NULL,
  KEY `fk_country_allowed_1_idx` (`country_id`),
  KEY `fk_country_allowed_2_idx` (`mission_id`),
  CONSTRAINT `fk_country_allowed_1` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_country_allowed_2` FOREIGN KEY (`mission_id`) REFERENCES `mission_details` (`mission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country_allowed`
--

LOCK TABLES `country_allowed` WRITE;
/*!40000 ALTER TABLE `country_allowed` DISABLE KEYS */;
INSERT INTO `country_allowed` VALUES (3,38),(6,38),(3,40),(6,40),(3,42),(6,42),(3,44),(6,44),(3,46),(6,46),(3,47),(6,47),(3,48),(6,48),(3,50),(6,50),(3,52),(6,52),(3,53),(6,53);
/*!40000 ALTER TABLE `country_allowed` ENABLE KEYS */;
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
  `job_id` int(11) NOT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `fk_employee_1_idx` (`job_id`),
  CONSTRAINT `fk_employee_1` FOREIGN KEY (`job_id`) REFERENCES `job` (`job_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,'civil',23,7),(3,'architect',10,7),(4,'labour',2,7),(5,'civil',23,15);
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
  `job_desc` varchar(500) DEFAULT NULL,
  `mission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`job_id`),
  KEY `fk_job_1_idx` (`mission_id`),
  CONSTRAINT `fk_job_1` FOREIGN KEY (`mission_id`) REFERENCES `mission_details` (`mission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (2,'Infrastructure','To build roads',NULL),(3,'Infrastructure','To build roads',6),(4,'Infrastructure','To build roads',6),(5,'Infrastructure','To build roads',NULL),(6,'Infrastructure','To build roads',NULL),(7,'Infrastructure','To build roads',NULL),(8,'Infrastructure','To build roads',NULL),(9,'Infrastructure','To build roads',NULL),(10,'Infrastructure','To build roads',NULL),(11,'Infrastructure','To build roads',NULL),(12,'Infrastructure','To build roads',NULL),(13,'Infrastructure','To build roads',53),(14,'Infrastructure','To build roads',53),(15,'Infrastructure','To build roads',53);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'no location','no location'),(2,'112.4','121.5'),(3,'11.34','145.67');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission_details`
--

DROP TABLE IF EXISTS `mission_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission_details` (
  `mission_id` int(11) NOT NULL AUTO_INCREMENT,
  `mission_name` varchar(45) DEFAULT NULL,
  `country_origin` int(11) DEFAULT NULL,
  `mission_details` text,
  `coordinator_id` int(11) NOT NULL,
  `launch_date` date DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT '1',
  PRIMARY KEY (`mission_id`),
  UNIQUE KEY `Mission_name_UNIQUE` (`mission_name`),
  KEY `status_idx` (`status_id`),
  KEY `coordinator_id_idx` (`coordinator_id`),
  KEY `country_origin_idx` (`country_origin`),
  CONSTRAINT `coordinator_id` FOREIGN KEY (`coordinator_id`) REFERENCES `user_info` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `country_origin` FOREIGN KEY (`country_origin`) REFERENCES `country` (`country_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission_details`
--

LOCK TABLES `mission_details` WRITE;
/*!40000 ALTER TABLE `mission_details` DISABLE KEYS */;
INSERT INTO `mission_details` VALUES (6,'tester',NULL,'atesting',15,NULL,NULL,1),(37,'anything testing',3,'sddsd',15,NULL,0,2),(38,'testing',3,'sddsd',15,'1999-10-09',0,2),(40,'testing12',3,'sddsd',15,'0011-10-09',0,2),(42,'how testing',3,'sddsd',15,'2018-04-15',0,2),(44,'creste testing',3,'sddsd',15,'2018-04-15',56,2),(46,'test',3,'sddsd',15,'2018-04-15',56,2),(47,'done test',3,'sddsd',15,'2018-04-15',56,2),(48,'mission testing',3,'sddsd',15,'2018-04-15',56,2),(50,'mission for testing',3,'sddsd',15,'2018-04-15',56,2),(52,'mission a for testing',3,'sddsd',15,'2018-04-15',56,2),(53,'mission is testing',3,'sddsd',15,'2018-04-15',56,2),(54,'you test',1,'sddsd',15,'1999-12-12',0,1),(55,'testing 1234',1,'tyuiyu',1,'2020-06-13',0,1),(57,'checking',1,'tyuiyu',15,'2020-06-13',0,1),(59,'checked',1,'tyuiyu',15,'2020-06-13',0,3);
/*!40000 ALTER TABLE `mission_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission_location`
--

DROP TABLE IF EXISTS `mission_location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission_location` (
  `mission_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  KEY `fk_mission_location_1_idx` (`location_id`),
  KEY `fk_mission_location_2_idx` (`mission_id`),
  CONSTRAINT `fk_mission_location_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `fk_mission_location_2` FOREIGN KEY (`mission_id`) REFERENCES `mission_details` (`mission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission_location`
--

LOCK TABLES `mission_location` WRITE;
/*!40000 ALTER TABLE `mission_location` DISABLE KEYS */;
INSERT INTO `mission_location` VALUES (38,2),(40,2),(42,2),(44,2),(46,2),(47,2),(48,2),(50,2),(52,2),(53,2),(54,1),(55,1),(57,1),(59,1);
/*!40000 ALTER TABLE `mission_location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shuttle`
--

DROP TABLE IF EXISTS `shuttle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shuttle` (
  `shuttle_id` int(11) NOT NULL AUTO_INCREMENT,
  `shuttle_name` varchar(45) DEFAULT NULL,
  `manfacture_year` year(4) DEFAULT NULL,
  `fuel_capacity` int(11) DEFAULT NULL,
  `passenger_capacity` int(11) DEFAULT NULL,
  `cargo_capacity` int(11) DEFAULT NULL,
  `travel_speed` int(11) DEFAULT NULL,
  `mission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shuttle_id`),
  UNIQUE KEY `mission_id_UNIQUE` (`mission_id`),
  CONSTRAINT `fk_shuttle_1` FOREIGN KEY (`mission_id`) REFERENCES `mission_details` (`mission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shuttle`
--

LOCK TABLES `shuttle` WRITE;
/*!40000 ALTER TABLE `shuttle` DISABLE KEYS */;
INSERT INTO `shuttle` VALUES (1,'no shuttle',NULL,NULL,NULL,NULL,NULL,NULL),(2,'magna. Sed',2018,977899900,249,502,31837,NULL),(3,'accumsan convallis',2011,578248695,248,1054,34503,NULL),(4,'dui augue',2019,904495444,214,673,31972,NULL),(5,'eget laoreet',2017,917563297,262,1053,29991,NULL);
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
INSERT INTO `status` VALUES (2,'Departed Earth'),(3,'Landed on Mars'),(6,'Mission Completed'),(4,'Mission in Progess'),(1,'Planning phase'),(5,'Returned to Earth');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(25) NOT NULL,
  `user_email` varchar(45) DEFAULT NULL,
  `user_password` varchar(45) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `gender` set('Male','Female','Other') DEFAULT NULL,
  `user_role` set('admin','coordinator','candidate') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `User_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'Bakul Mittal','admin@gmail.com','YWRtaW4=',NULL,NULL,NULL,NULL,'admin'),(11,'Deepak Kumar','abc@gmail.com','YWRtaW4=',NULL,NULL,NULL,'Male','coordinator'),(13,'siddhart','abc1@gmail.com','YWRtaW4x','1993-01-06',NULL,NULL,NULL,'admin'),(15,'Flloyd','abcd1@gmail.com','YWRtaW4x','1995-06-15',NULL,'India',NULL,'candidate');
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

-- Dump completed on 2020-06-07 22:17:43
