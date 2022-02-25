-- MySQL dump 10.13  Distrib 8.0.23, for osx10.16 (x86_64)
--
-- Host: localhost    Database: test1_db
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `order`
--

CREATE DATABASE `test1_db`;
USE DATABASE `test1_db`;

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `patientID` varchar(8) NOT NULL,
  `doctorID` varchar(8) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`patientID`),
  KEY `order_userDtl_id_fk` (`doctorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('123','123','2021-01-08 01:30:35'),('12345','678','2021-01-08 01:31:29');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `orderdtl`
--

DROP TABLE IF EXISTS `orderdtl`;

-- failed on view `orderdtl`: CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `orderdtl` AS select `patientdtl`.`ID` AS `ID`,`patientdtl`.`name` AS `patientName`,`patientdtl`.`phone` AS `phone`,`userdtl`.`name` AS `doctorName`,`order`.`date` AS `date` from ((`order` join `patientdtl`) join `userdtl`) where ((`order`.`patientID` = `patientdtl`.`ID`) and (`order`.`doctorID` = `userdtl`.`id`))


--
-- Table structure for table `productDtl`
--

DROP TABLE IF EXISTS `productDtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productDtl` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `picture` varchar(50) DEFAULT NULL,
  `owner` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productDtl`
--

LOCK TABLES `productDtl` WRITE;
/*!40000 ALTER TABLE `productDtl` DISABLE KEYS */;
INSERT INTO `productDtl` VALUES ('2343546','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg','123'),('23435469','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg','123'),('acsef','fas','aergaasgvd','232','dsvg','/assets/images/商品页/tf.jpg','123'),('afsf-1098248881','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg','123'),('afsf-1574822549','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg','123'),('afsf1085826129','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg','123'),('afsf1474511698','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg','123'),('afsf1929508780','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg',NULL),('afsf56782641','afsf','uasfgasegfkashfhaskghlashghaskrhgasrgkshlgsjkhskjahjksdfklj','csD','zsdszdzs','assets/images/商品页/tf.jpg',NULL),('awf','fawe','awef',NULL,'ewaf','assets/images/商品页/tf.jpg',NULL),('fawe-2016299748','fawe','awef',NULL,'ewaf','assets/images/商品页/tf.jpg','123'),('sdvCzsdv','ssda','sdfasfsafadfasfdasdsagfasg','2345','sfaa','/assets/images/商品页/tf.jpg/3243',NULL);
/*!40000 ALTER TABLE `productDtl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` varchar(50) NOT NULL,
  `productID` varchar(50) DEFAULT NULL,
  `userID` varchar(50) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES ('sdzfcs','afsf-1574822549','123','ghcfjvklm;jbhgtdrfh');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userDtl`
--

DROP TABLE IF EXISTS `userDtl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userDtl` (
  `id` varchar(8) NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `phone2` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(18) DEFAULT NULL,
  `loginState` tinyint(1) DEFAULT '0',
  `inService` tinyint(1) DEFAULT '1',
  `admin` tinyint(1) DEFAULT '0',
  `sessionId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userDtl`
--

LOCK TABLES `userDtl` WRITE;
/*!40000 ALTER TABLE `userDtl` DISABLE KEYS */;
INSERT INTO `userDtl` VALUES ('','',NULL,'','',NULL,'',NULL,0,1,0,NULL),('123','hhh',NULL,NULL,'34567',NULL,NULL,'123',1,1,0,'83163DDA27157FAF086D39EE1DB6F64D'),('1234','',NULL,'','',NULL,'','1234',1,1,1,'D408AB67899130DCE8086B278390E243'),('3123','zx ',NULL,'man','3245',NULL,'wzx00051111@gmail.com',NULL,0,1,0,NULL),('3we456','ggg',NULL,'woman','7686797',NULL,'123456@12345678',NULL,0,1,0,NULL),('5876','咕咕咕',NULL,'woman','77987',NULL,'cbh1274797657@163.com',NULL,0,1,0,NULL),('678','zx w',NULL,NULL,'23456789',NULL,'12324@54',NULL,0,1,0,NULL),('6780','zx w',NULL,NULL,'19850839376',NULL,'sdfghj@g',NULL,0,1,0,NULL),('67800','zx w',NULL,NULL,'19850839376',NULL,'sdfghj@g',NULL,0,1,0,NULL),('678000','zx w',NULL,NULL,'19850839376',NULL,'sdfghj@g',NULL,0,1,0,NULL),('6780000','zx w',NULL,NULL,'19850839376',NULL,'sdfghj@g',NULL,0,1,0,NULL),('6780001','zx w',NULL,NULL,'19850839376',NULL,'sdfghj@g',NULL,0,1,0,NULL),('6780002','zx w',NULL,'woman','19850839376',NULL,'sdfghj@g',NULL,0,1,0,NULL),('765986','gg',NULL,'man','69778',NULL,'sdfghj@g',NULL,0,1,0,NULL),('765987','gg',NULL,'man','69778',NULL,'sdfghj@g',NULL,0,1,0,NULL),('76598769','咕咕咕',NULL,'woman','767567558',NULL,'cbh1274797657@163.com',NULL,0,1,0,NULL),('faef','',NULL,'','',NULL,'',NULL,0,1,0,NULL),('m,','kj',NULL,'man','nk',NULL,'kj',NULL,0,1,0,NULL);
/*!40000 ALTER TABLE `userDtl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verificationCode`
--

DROP TABLE IF EXISTS `verificationCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verificationCode` (
  `verificationCode` varchar(5) NOT NULL,
  `sessionId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`verificationCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationCode`
--

LOCK TABLES `verificationCode` WRITE;
/*!40000 ALTER TABLE `verificationCode` DISABLE KEYS */;
INSERT INTO `verificationCode` VALUES ('4tyyh','D408AB67899130DCE8086B278390E243'),('989ha','83163DDA27157FAF086D39EE1DB6F64D');
/*!40000 ALTER TABLE `verificationCode` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-04 12:59:24
