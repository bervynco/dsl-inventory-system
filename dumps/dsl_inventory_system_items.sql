-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: dsl_inventory_system
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) DEFAULT NULL,
  `product_name` varchar(45) DEFAULT NULL,
  `information` varchar(45) DEFAULT NULL,
  `ip_rate` varchar(45) DEFAULT NULL,
  `kelvin` varchar(45) DEFAULT NULL,
  `beam_angle` varchar(45) DEFAULT NULL,
  `wattage` varchar(45) DEFAULT NULL,
  `color_temp` varchar(45) DEFAULT NULL,
  `batch_no` varchar(45) DEFAULT NULL,
  `row_no` varchar(45) DEFAULT NULL,
  `rack_no` varchar(45) DEFAULT NULL,
  `location_no` varchar(45) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `threshold` int(11) DEFAULT NULL,
  `production_date` datetime DEFAULT NULL,
  `lumens` varchar(45) DEFAULT NULL,
  `cri` varchar(45) DEFAULT NULL,
  `power` varchar(45) DEFAULT NULL,
  `size` varchar(45) DEFAULT NULL,
  `ac` varchar(45) DEFAULT NULL,
  `dc` varchar(45) DEFAULT NULL,
  `remark` text,
  `image` varchar(45) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `limit` tinyint(4) DEFAULT '0',
  `status` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id_UNIQUE` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (2,332123,'Lamp','This is a sample','100','100','200','5','100','2','2','1','1',1000,1000,'2017-05-01 00:00:00','100','200','300','40','1','1','',NULL,'2017-06-26 14:10:50',0,0),(3,999,'Laptop','This is a laptop','111','222','333','10','20','30','1','2','2',1500,1500,'2017-06-01 00:00:00','100','200','300','10','5','5','Nothing to note.',NULL,'2017-06-29 04:16:02',0,0),(5,222,'Desk Lamp','Green Desk Lamp','100','200','12','20','30','1','1','1','1',5000,200,'2017-07-02 00:00:00','1','1','2','3','2','2','This is a sample item',NULL,'2017-07-02 15:37:37',0,0);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-04 12:29:29
