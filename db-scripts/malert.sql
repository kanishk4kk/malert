-- MySQL dump 10.13  Distrib 5.5.22, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: malert
-- ------------------------------------------------------
-- Server version	5.5.22-0ubuntu1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_option`
--

DROP TABLE IF EXISTS `app_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_option` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(254) COLLATE utf8_bin DEFAULT NULL,
  `value` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_option`
--

LOCK TABLES `app_option` WRITE;
/*!40000 ALTER TABLE `app_option` DISABLE KEYS */;
INSERT INTO `app_option` VALUES (1,'28b970383f753a241e689bba10799332','b326b5062b2f0e69046810717534cb09');
/*!40000 ALTER TABLE `app_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `fname` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `lname` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  `type` enum('admin','regular') COLLATE utf8_bin NOT NULL DEFAULT 'regular',
  `updated_by` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `U_email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Pradeep','Sharma','a','a','admin',1),(2,'Kanishk','Choraria','Kanishk.Choraria@gmail.com','password','regular',0),(8,'aa','aa','aa','aa','regular',8),(14,'sdsada','asads','a@asdfa.com','asda','regular',1),(15,'Deepak','Sharma','deepak.sharma2@maruti.co.in','207799','regular',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visit_observation`
--

DROP TABLE IF EXISTS `visit_observation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visit_observation` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `visit_date` date NOT NULL,
  `observation` text COLLATE utf8_bin,
  `action_plan` text COLLATE utf8_bin,
  `target_date` date DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('implemented','pending') COLLATE utf8_bin NOT NULL,
  `isSent` tinyint(1) DEFAULT '0',
  `workshop_id` int(10) NOT NULL,
  `visited_by` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit_observation`
--

LOCK TABLES `visit_observation` WRITE;
/*!40000 ALTER TABLE `visit_observation` DISABLE KEYS */;
INSERT INTO `visit_observation` VALUES (1,'2012-05-05','Alignment machine not working properly.Alignment machine not working properly.Alignment machine not working properly.Alignment machine not working properly.Alignment machine not working properly.Alignment machine not working properly.\r\n\r\nAlignment machine not working properly.\r\nAlignment machine not working properly.Alignment machine not working properly.\r\n\r\n\r\nAlignment machine not working properly.','Needs to be checked by technician. Needs to be checked by technician.\r\nNeeds to be checked by technician.\r\n\r\nNeeds to be checked by technician.Needs to be checked by technician.','2012-02-02','2012-05-20 15:19:21','pending',1,1,1),(2,'2012-05-06','Test1','Test1','2012-02-02','2012-05-20 15:19:21','implemented',0,1,1),(3,'2012-05-06','Test2','Test2','2012-02-02','2012-05-20 15:19:21','implemented',0,1,1),(4,'2012-05-06','Test3','Test3','2012-02-02','2012-05-20 15:19:21','implemented',0,1,1),(10,'2012-12-12','a','a','2012-02-02','2012-05-20 15:19:21','pending',1,2,1),(7,'2012-12-02','ddfszfsdf','dsfsdf','2012-02-02','2012-05-20 15:19:21','implemented',0,2,8),(8,'2012-12-02','ajdkljfdajfd','fjkjgkffjkljgs','2012-02-02','2012-05-20 15:19:21','pending',1,2,8),(9,'2012-05-15','Wheel alignment machine not working','a new machine to be implemented','2012-02-02','2012-05-20 15:19:21','pending',1,2,8);
/*!40000 ALTER TABLE `visit_observation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workshop`
--

DROP TABLE IF EXISTS `workshop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workshop` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `location` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code` varchar(64) COLLATE utf8_bin NOT NULL,
  `tsm_name` varchar(128) COLLATE utf8_bin NOT NULL,
  `tsm_email` varchar(128) COLLATE utf8_bin NOT NULL,
  `active` tinyint(1) DEFAULT '1',
  `updated_by` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `NewIndex1` (`code`,`active`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workshop`
--

LOCK TABLES `workshop` WRITE;
/*!40000 ALTER TABLE `workshop` DISABLE KEYS */;
INSERT INTO `workshop` VALUES (1,'K P Automotive Pvt Ltd','C 17,Sawai Jaisingh Highway,Bani Park, Jaipur.,\r\nJaipur, Rajasthan, 302016','kpauto','Pradeep s','pr12gold@gmail.com',1,1),(2,'Prem Motors Pvt Ltd.','CORPORATE PARK, NEAR AJMER PULIA, GOPALBARI, AJMER ROAD,JAIPUR,\r\nJaipur, Rajasthan, 302 006','VPL','Kanishk','pr12gold@gmail.com',1,1),(19,'Vipul Motors Ltd','S 10 Shyam Nagar, Jaipur,\r\nJaipur, Rajasthan, 302015','vipul_jpr','Raj','pr12gold@gmail.com',1,1),(18,'SANGA AUTOMOBILES PVT LTD','A-1, PUSPH ENCLAVE, SECTOR-5, PRATAP NAGAR, SANGANER, JAIPUR,\r\nJaipur, Rajasthan, 202033','1111-1','Sidharth','pr12gold@gmail.com',1,1);
/*!40000 ALTER TABLE `workshop` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-20 23:07:26
