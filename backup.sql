-- MySQL dump 10.13  Distrib 8.4.3, for Win64 (x86_64)
--
-- Host: localhost    Database: librarymanagementsystem
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `contact_no` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'9517538264','Apurba','Sen','123','A1'),(2,'9874561238','Biren','Saha','456','B2');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `author_name` varchar(150) NOT NULL,
  `genre` varchar(100) DEFAULT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  `publication_year` int DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `borrowed_date` date DEFAULT NULL,
  `is_borrowed` bit(1) NOT NULL,
  `return_date` date DEFAULT NULL,
  `borrowed_by_id` int DEFAULT NULL,
  `extension_count` int NOT NULL,
  `fine_amount` int NOT NULL,
  PRIMARY KEY (`book_id`),
  KEY `FKryd40ht43klaww6reav2s2c7e` (`borrowed_by_id`),
  CONSTRAINT `FKryd40ht43klaww6reav2s2c7e` FOREIGN KEY (`borrowed_by_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Shukumar Roy','Poetry','9781387687244',1923,'Abol Tabol','2025-04-15',_binary '','2025-04-22',4,0,0),(6,'Rabindranath Tagore','Poem','9780333422175',1910,'Gitanjali',NULL,_binary '\0',NULL,NULL,0,0),(12,'Bibhutibhushan Bandyopadhyay','Novel','9788175251160',1929,'Pather Panchali',NULL,_binary '\0',NULL,NULL,0,0),(13,'Sarat Chandra Chattopadhyay','Novel','9788189265309',1917,'Charitraheen',NULL,_binary '\0',NULL,NULL,0,0),(14,'Sarat Chandra Chattopadhyay','Novel','9788171671764',1917,'Srikanta',NULL,_binary '\0',NULL,NULL,0,0),(15,'Sarat Chandra Chattopadhyay','Romantic Novel','9788121507744',1917,'Devdas',NULL,_binary '\0',NULL,NULL,0,0),(16,'Bibhutibhushan Bandyopadhyay','Novel','9788175251306',1939,'Aranyak',NULL,_binary '\0',NULL,NULL,0,0),(17,'Bankim Chandra Chattopadhyay','Historical Novel','9788129121579',1882,'Anandamath',NULL,_binary '\0',NULL,NULL,0,0),(18,'Satyajit Ray','Detective Fiction','9788129127144',1965,'Feluda Samagra',NULL,_binary '\0',NULL,NULL,0,0),(19,'Upendrakishore Ray Chowdhury','Children\'s Literature','9788186204371',1915,'Goopy Gyne Bagha Byne',NULL,_binary '\0',NULL,NULL,0,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `contact_no` varchar(15) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL, 
  `subscription_amount` int NOT NULL,
  `subscription_type` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `pending_fine` int NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'KOL-455','9123099685','Soumik','Majumder',1000,'Yearly','Am@123','Sm/555477',0),(2,'jkl/88','9990396844','FGG','LKI',110,'Monthly','lk*89','Shjd',0),(4,'JK-98','7894561238','Raj','Saha',1000,'Yearly','123','R1',45),(5,'Delhi-88','9125863221','Mainak','Dhar',1000,'Y','912','M@7',0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-29 20:52:57
