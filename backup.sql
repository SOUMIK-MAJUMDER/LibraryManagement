-- MySQL dump 10.13  Distrib 8.4.3, for Win64 (x86_64)
--
-- Host: localhost    Database: librarymanagementsystem
-- ------------------------------------------------------
-- Server version	8.4.3

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
  `AdminID` int NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `contactno` varchar(20) DEFAULT NULL,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  PRIMARY KEY (`AdminID`),
  UNIQUE KEY `UserName` (`UserName`),
  UNIQUE KEY `Password` (`Password`),
  UNIQUE KEY `AdminID` (`AdminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (841,'Koustav','Chakrobarty','9123099687','Ko@78','mN$23');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `AuthorID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`AuthorID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Rabindranath','Tagore'),(2,'Kazi','Nazrul Islam'),(3,'Sarat Chandra','Chattopadhyay'),(4,'Bankim Chandra','Chattopadhyay'),(5,'Bibhutibhushan','Bandyopadhyay'),(6,'Manik','Bandyopadhyay'),(7,'Jibanananda','Das'),(8,'Sunil','Gangopadhyay');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookborrowed`
--

DROP TABLE IF EXISTS `bookborrowed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookborrowed` (
  `BorrowerID` int NOT NULL,
  `BookID` int DEFAULT NULL,
  `IssueDate` date DEFAULT NULL,
  `SubmissionDate` date DEFAULT NULL,
  PRIMARY KEY (`BorrowerID`),
  KEY `BookID` (`BookID`),
  CONSTRAINT `bookborrowed_ibfk_1` FOREIGN KEY (`BorrowerID`) REFERENCES `member` (`BorrowerID`),
  CONSTRAINT `bookborrowed_ibfk_2` FOREIGN KEY (`BookID`) REFERENCES `books` (`BookID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookborrowed`
--

LOCK TABLES `bookborrowed` WRITE;
/*!40000 ALTER TABLE `bookborrowed` DISABLE KEYS */;
INSERT INTO `bookborrowed` VALUES (1,3,'2024-02-20','2024-03-21'),(2,5,'2024-04-16','2024-05-16'),(3,2,'2024-11-18','2024-12-18'),(4,12,'2024-05-10','2024-06-09'),(5,20,'2024-09-30','2024-10-30'),(6,8,'2024-03-22','2024-04-21'),(7,16,'2024-08-11','2024-09-10'),(8,22,'2024-12-01','2024-12-31');
/*!40000 ALTER TABLE `bookborrowed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `BookID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `AuthorID` int DEFAULT NULL,
  `Genre` varchar(100) DEFAULT NULL,
  `PublicationYear` int DEFAULT NULL,
  `ISBN` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`BookID`),
  KEY `AuthorID` (`AuthorID`),
  CONSTRAINT `books_ibfk_1` FOREIGN KEY (`AuthorID`) REFERENCES `author` (`AuthorID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Gitanjali',1,'Poetry',1910,'978-8175810146'),(2,'Chokher Bali',1,'Novel',1903,'978-8170807281'),(3,'Shesher Kobita',1,'Novel',1929,'978-8178236811'),(4,'Bidrohi',2,'Poetry',1922,'978-8171226819'),(5,'Raktakarabi',2,'Play',1926,'978-8178281513'),(6,'Agniveena',2,'Poetry',1922,'978-8172203237'),(7,'Devdas',3,'Novel',1917,'978-0143103990'),(8,'Parineeta',3,'Novel',1914,'978-0143032349'),(9,'Choritrohin',3,'Novel',1917,'978-8187100516'),(10,'Anandamath',4,'Historical Fiction',1882,'978-0143032349'),(11,'Durgeshnandini',4,'Novel',1865,'978-8170237414'),(12,'Kapalkundala',4,'Novel',1866,'978-8170222724'),(13,'Pather Panchali',5,'Novel',1929,'978-9383145601'),(14,'Aparajito',5,'Novel',1932,'978-9383145793'),(15,'Ichhamati',5,'Novel',1936,'978-8185109232'),(16,'Padma Nadir Majhi',6,'Novel',1951,'978-8172235844'),(17,'Kumudini',6,'Novel',1945,'978-8171674775'),(18,'Mahal',6,'Novel',1955,'978-8179934043'),(19,'Banalata Sen',7,'Poetry',1934,'978-8172242441'),(20,'Rupasi Bangla',7,'Poetry',1936,'978-8179921237'),(21,'Jibanananda Rachanabali',7,'Poetry/Prose',1971,'978-8178290300'),(22,'Sei Samay',8,'Historical Fiction',1985,'978-8170238887'),(23,'Pratham Alo',8,'Novel',1989,'978-8170239938'),(24,'Arjun',8,'Novel',1997,'978-8177114681');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `BorrowerID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(100) DEFAULT NULL,
  `LastName` varchar(100) DEFAULT NULL,
  `PhoneNumber` varchar(15) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `SubscriptionType` varchar(50) DEFAULT NULL,
  `SubscriptionAmount` int DEFAULT NULL,
  `FineDue` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`BorrowerID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'Santunu','Roy','9876543210','123, Main Street','Yearly',1000,0.00),(2,'Ananya','Banerjee','9876543211','45, Green Avenue','Monthly',110,0.00),(3,'Ravi','Sharma','9876543212','78, Red Road','Yearly',1000,0.00),(4,'Soma','Ghosh','9876543213','12, Blue Lane','Yearly',1000,0.00),(5,'Ankush','Saha','9876543214','34, Lake View','Monthly',110,0.00),(6,'Ritu','Roy','9876543215','56, Park Street','Yearly',1000,0.00),(7,'Arpita','Dey','9876543216','89, Riverbank','Monthly',110,0.00),(8,'Subhajit','Mukherjee','9876543217','101, City Center','Monthly',110,0.00);
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

-- Dump completed on 2025-04-13 11:20:03
