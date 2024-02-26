-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ioio
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `student_calendar_comparisons`
--

DROP TABLE IF EXISTS `student_calendar_comparisons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_calendar_comparisons` (
  `id_stud` int NOT NULL,
  `id_offer` int NOT NULL,
  `value` float NOT NULL,
  PRIMARY KEY (`id_stud`,`id_offer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_calendar_comparisons`
--

LOCK TABLES `student_calendar_comparisons` WRITE;
/*!40000 ALTER TABLE `student_calendar_comparisons` DISABLE KEYS */;
INSERT INTO `student_calendar_comparisons` VALUES (0,1,0.0909091),(0,2,0),(0,3,0),(0,4,0),(0,5,0),(0,6,0),(0,7,0),(0,8,0),(0,9,0.069697),(0,10,0),(0,11,0.0462121),(0,12,0),(0,13,0),(0,14,0),(0,15,0),(0,16,0),(0,17,0),(0,18,0),(0,19,0.352273),(0,20,0),(0,21,0),(0,22,0.363636),(0,23,0),(0,24,0),(0,25,0),(1,1,0),(1,3,0),(1,5,0),(1,7,0),(1,9,0),(1,11,0),(1,15,0),(1,19,0),(1,21,0),(1,22,0),(1,25,0),(2,1,0),(2,2,0),(2,3,0),(2,4,0),(2,5,0),(2,6,0),(2,7,0),(2,8,0),(2,9,0),(2,10,0),(2,11,0),(2,12,0),(2,13,0),(2,14,0),(2,15,0),(2,16,0),(2,17,0),(2,18,0),(2,19,0),(2,20,0),(2,21,0),(2,22,0),(2,23,0),(2,24,0),(2,25,0),(3,1,0),(3,2,0),(3,3,0),(3,4,0),(3,5,0),(3,6,0),(3,7,0),(3,8,0),(3,9,0),(3,10,0),(3,11,0),(3,12,0),(3,13,0),(3,14,0),(3,15,0),(3,16,0),(3,17,0),(3,18,0),(3,19,0),(3,20,0),(3,21,0),(3,22,0),(3,23,0),(3,24,0),(3,25,0),(4,1,0.448148),(4,2,0),(4,3,0),(4,4,0),(4,5,0),(4,6,0),(4,7,0),(4,8,0),(4,9,0),(4,10,0),(4,11,0),(4,12,0),(4,13,0),(4,14,0),(4,15,0),(4,16,0),(4,17,0),(4,18,0),(4,19,0),(4,20,0),(4,21,0),(4,22,0.225926),(4,23,0),(4,24,0),(4,25,0.892593),(5,1,0),(5,2,0),(5,3,0),(5,4,0),(5,5,0),(5,6,0),(5,7,0),(5,8,0),(5,9,0.15082),(5,10,0),(5,11,0),(5,12,0),(5,13,0),(5,14,0),(5,15,0),(5,16,0),(5,17,0),(5,18,0),(5,19,0),(5,20,0),(5,21,0),(5,22,0),(5,23,0),(5,24,0),(5,25,0),(6,1,0),(6,3,0),(6,5,0),(6,7,0),(6,9,0),(6,11,0),(6,15,0),(6,19,0),(6,21,0),(6,22,0),(6,25,0),(7,1,0),(7,2,0),(7,3,0),(7,4,0),(7,5,0),(7,6,0),(7,7,0),(7,8,0),(7,9,0),(7,10,0),(7,11,0),(7,12,0),(7,13,0),(7,14,0),(7,15,0),(7,16,0),(7,17,0),(7,18,0),(7,19,1),(7,20,0),(7,21,0),(7,22,0),(7,23,0),(7,24,0),(7,25,0),(8,1,0),(8,2,0),(8,3,0),(8,4,0),(8,5,0),(8,6,0),(8,7,0),(8,8,0),(8,9,0),(8,10,0),(8,11,0),(8,12,0),(8,13,0),(8,14,0),(8,15,0),(8,16,0),(8,17,0),(8,18,0),(8,19,0),(8,20,0),(8,21,0),(8,22,0),(8,23,0),(8,24,0),(8,25,0),(9,1,0),(9,2,0),(9,3,0),(9,4,0),(9,5,0),(9,6,0),(9,7,0),(9,8,0),(9,9,0),(9,10,0),(9,11,0),(9,12,0),(9,13,0),(9,14,0),(9,15,0),(9,16,0),(9,17,0),(9,18,0),(9,19,0),(9,20,0),(9,21,0),(9,22,1),(9,23,0),(9,24,0),(9,25,0),(10,1,0),(10,3,0),(10,5,0),(10,7,0),(10,9,0),(10,11,0),(10,15,0),(10,19,0),(10,21,0),(10,22,0),(10,25,0),(11,1,0),(11,3,0),(11,5,0),(11,7,0),(11,9,0),(11,11,0),(11,15,0),(11,19,0),(11,21,0),(11,22,0),(11,25,0),(12,1,0),(12,3,0),(12,5,0),(12,7,0),(12,9,0),(12,11,0),(12,15,0),(12,19,0),(12,21,0),(12,22,0),(12,25,0),(13,1,0),(13,3,0),(13,5,0),(13,7,0),(13,9,0),(13,11,0),(13,15,0),(13,19,0),(13,21,0),(13,22,0),(13,25,0),(14,1,0),(14,3,0),(14,5,0),(14,7,0),(14,9,0),(14,11,0),(14,15,0),(14,19,0),(14,21,0),(14,22,0),(14,25,0),(15,1,0),(15,3,0),(15,5,0),(15,7,0),(15,9,0),(15,11,0),(15,15,0),(15,19,0),(15,21,0),(15,22,0),(15,25,0),(16,1,0),(16,3,0),(16,5,0),(16,7,0),(16,9,0),(16,11,0),(16,15,0),(16,19,0),(16,21,0),(16,22,0),(16,25,0),(17,1,0),(17,3,0),(17,5,0),(17,7,0),(17,9,0),(17,11,0),(17,15,0),(17,19,0),(17,21,0),(17,22,0),(17,25,0),(18,1,0),(18,3,0),(18,5,0),(18,7,0),(18,9,0),(18,11,0),(18,15,0),(18,19,0),(18,21,0),(18,22,0),(18,25,0),(19,1,0),(19,3,0),(19,5,0),(19,7,0),(19,9,0),(19,11,0),(19,15,0),(19,19,0),(19,21,0),(19,22,0),(19,25,0),(20,1,0),(20,3,0),(20,5,0),(20,7,0),(20,9,0),(20,11,0),(20,15,0),(20,19,0),(20,21,0),(20,22,0),(20,25,0),(21,1,0),(21,3,0),(21,5,0),(21,7,0),(21,9,0),(21,11,0),(21,15,0),(21,19,0),(21,21,0),(21,22,0),(21,25,0),(22,1,0),(22,3,0),(22,5,0),(22,7,0),(22,9,0),(22,11,0),(22,15,0),(22,19,0),(22,21,0),(22,22,0),(22,25,0),(23,1,0),(23,3,0),(23,5,0),(23,7,0),(23,9,0),(23,11,0),(23,15,0),(23,19,0),(23,21,0),(23,22,0),(23,25,0),(24,1,0),(24,3,0),(24,5,0),(24,7,0),(24,9,0),(24,11,0),(24,15,0),(24,19,0),(24,21,0),(24,22,0),(24,25,0),(25,25,0);
/*!40000 ALTER TABLE `student_calendar_comparisons` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-14 17:32:58