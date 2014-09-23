-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: SS_mysite
-- ------------------------------------------------------
-- Server version	5.5.35-0+wheezy1

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
-- Table structure for table `ContentCategory`
--

DROP TABLE IF EXISTS `ContentCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContentCategory` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('ContentCategory') DEFAULT 'ContentCategory',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Title` varchar(50) DEFAULT NULL,
  `MainCategoriesID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `MainCategoriesID` (`MainCategoriesID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContentCategory`
--

LOCK TABLES `ContentCategory` WRITE;
/*!40000 ALTER TABLE `ContentCategory` DISABLE KEYS */;
INSERT INTO `ContentCategory` VALUES (7,'ContentCategory','2014-09-17 12:00:55','2014-09-17 12:00:55','Test',1);
/*!40000 ALTER TABLE `ContentCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ContentField`
--

DROP TABLE IF EXISTS `ContentField`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ContentField` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('ContentField') DEFAULT 'ContentField',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Notes` mediumtext,
  `ContentCategoryID` int(11) NOT NULL DEFAULT '0',
  `ImageID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ContentCategoryID` (`ContentCategoryID`),
  KEY `ImageID` (`ImageID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ContentField`
--

LOCK TABLES `ContentField` WRITE;
/*!40000 ALTER TABLE `ContentField` DISABLE KEYS */;
INSERT INTO `ContentField` VALUES (1,'ContentField','2014-07-27 20:37:50','2014-07-27 20:37:50','This is the information for the slide',1,14),(4,'ContentField','2014-07-28 12:10:58','2014-07-28 12:11:28','another note',2,17),(5,'ContentField','2014-07-30 14:49:46','2014-08-11 18:10:40','Another note',1,19),(6,'ContentField','2014-07-30 16:00:00','2014-07-30 16:00:00','asdasdasdasd',1,20),(7,'ContentField','2014-08-11 18:13:37','2014-08-11 18:13:37','asdfafasfasdfasfasf',1,22),(8,'ContentField','2014-08-11 18:33:54','2014-08-11 18:33:54','Test ContentField',4,23),(9,'ContentField','2014-09-17 12:02:11','2014-09-17 12:02:11','Test',7,25);
/*!40000 ALTER TABLE `ContentField` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ErrorPage`
--

DROP TABLE IF EXISTS `ErrorPage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ErrorPage` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ErrorCode` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ErrorPage`
--

LOCK TABLES `ErrorPage` WRITE;
/*!40000 ALTER TABLE `ErrorPage` DISABLE KEYS */;
INSERT INTO `ErrorPage` VALUES (4,404),(5,500);
/*!40000 ALTER TABLE `ErrorPage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ErrorPage_Live`
--

DROP TABLE IF EXISTS `ErrorPage_Live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ErrorPage_Live` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ErrorCode` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ErrorPage_Live`
--

LOCK TABLES `ErrorPage_Live` WRITE;
/*!40000 ALTER TABLE `ErrorPage_Live` DISABLE KEYS */;
INSERT INTO `ErrorPage_Live` VALUES (4,404),(5,500);
/*!40000 ALTER TABLE `ErrorPage_Live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ErrorPage_versions`
--

DROP TABLE IF EXISTS `ErrorPage_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ErrorPage_versions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RecordID` int(11) NOT NULL DEFAULT '0',
  `Version` int(11) NOT NULL DEFAULT '0',
  `ErrorCode` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `RecordID_Version` (`RecordID`,`Version`),
  KEY `RecordID` (`RecordID`),
  KEY `Version` (`Version`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ErrorPage_versions`
--

LOCK TABLES `ErrorPage_versions` WRITE;
/*!40000 ALTER TABLE `ErrorPage_versions` DISABLE KEYS */;
INSERT INTO `ErrorPage_versions` VALUES (1,4,1,404),(2,5,1,500);
/*!40000 ALTER TABLE `ErrorPage_versions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `File`
--

DROP TABLE IF EXISTS `File`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `File` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('File','Folder','Image','Image_Cached') DEFAULT 'File',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Filename` mediumtext,
  `Content` mediumtext,
  `ShowInSearch` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `ParentID` int(11) NOT NULL DEFAULT '0',
  `OwnerID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ParentID` (`ParentID`),
  KEY `OwnerID` (`OwnerID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `File`
--

LOCK TABLES `File` WRITE;
/*!40000 ALTER TABLE `File` DISABLE KEYS */;
INSERT INTO `File` VALUES (1,'Folder','2014-03-25 13:00:54','2014-03-25 13:00:54','Uploads','Uploads','assets/Uploads/',NULL,1,0,0),(2,'Image','2014-03-25 13:00:54','2014-03-25 13:00:54','SilverStripeLogo.png','SilverStripeLogo.png','assets/Uploads/SilverStripeLogo.png',NULL,1,1,0),(3,'File','2014-03-25 13:00:54','2014-03-25 13:00:54','error-404.html','error-404.html','assets/error-404.html',NULL,1,0,0),(4,'File','2014-03-25 13:00:54','2014-03-25 13:00:54','error-500.html','error-500.html','assets/error-500.html',NULL,1,0,0),(6,'Image','2014-07-27 16:42:10','2014-07-27 16:42:10','10300987-10201965735936716-1608455035784666364-n.jpg','10300987 10201965735936716 1608455035784666364 n','assets/Uploads/10300987-10201965735936716-1608455035784666364-n.jpg',NULL,1,1,1),(7,'Image','2014-07-27 19:37:41','2014-07-27 19:37:41','s.jpg','s','assets/Uploads/s.jpg',NULL,1,1,1),(8,'Image','2014-07-27 19:44:21','2014-07-27 19:44:21','s.jpg','s','assets/Uploads/s.jpg',NULL,1,1,1),(9,'Image','2014-07-27 19:46:31','2014-07-27 19:46:31','s.jpg','s','assets/Uploads/s.jpg',NULL,1,1,1),(10,'Image','2014-07-27 19:49:29','2014-07-27 19:49:29','s.jpg','s','assets/Uploads/s.jpg',NULL,1,1,1),(11,'Image','2014-07-27 19:52:31','2014-07-27 19:52:31','s.jpg','s','assets/Uploads/s.jpg',NULL,1,1,1),(12,'Image','2014-07-27 20:20:59','2014-07-27 20:20:59','R.jpg','R','assets/Uploads/R.jpg',NULL,1,1,1),(13,'Image','2014-07-27 20:24:02','2014-07-27 20:24:02','R.jpg','R','assets/Uploads/R.jpg',NULL,1,1,1),(14,'Image','2014-07-27 20:37:48','2014-07-27 20:37:48','slide1.jpg','slide1','assets/Uploads/slide1.jpg',NULL,1,1,1),(15,'Image','2014-07-27 20:38:36','2014-07-27 20:38:36','ModelImage.jpg','ModelImage','assets/Uploads/ModelImage.jpg',NULL,1,1,1),(16,'Image','2014-07-28 12:09:54','2014-07-28 12:09:54','android11.jpg','android11','assets/Uploads/android11.jpg',NULL,1,1,1),(17,'Image','2014-07-28 12:10:55','2014-07-28 12:10:55','android11.jpg','android11','assets/Uploads/android11.jpg',NULL,1,1,1),(18,'Image','2014-07-28 12:13:05','2014-07-28 12:13:05','android11.jpg','android11','assets/Uploads/android11.jpg',NULL,1,1,1),(19,'Image','2014-07-30 14:49:40','2014-07-30 14:49:40','Another-Warning-on-installation.PNG','Another Warning on installation','assets/Uploads/Another-Warning-on-installation.PNG',NULL,1,1,1),(20,'Image','2014-07-30 15:59:56','2014-07-30 15:59:56','10492509-1444644582473357-1998683549721059691-n.jpg','10492509 1444644582473357 1998683549721059691 n','assets/Uploads/10492509-1444644582473357-1998683549721059691-n.jpg',NULL,1,1,1),(21,'Image','2014-08-11 14:56:01','2014-08-11 14:56:01','androidstudio.jpg','androidstudio','assets/Uploads/androidstudio.jpg',NULL,1,1,1),(22,'Image','2014-08-11 18:13:34','2014-08-11 18:13:34','asdas.png','asdas','assets/Uploads/asdas.png',NULL,1,1,1),(23,'Image','2014-08-11 18:33:51','2014-08-11 18:33:51','iso-64x64-outside-0007.png','iso 64x64 outside 0007','assets/Uploads/iso-64x64-outside-0007.png',NULL,1,1,1),(25,'Image','2014-09-17 12:01:28','2014-09-17 12:01:28','New-Project-Eclipse.PNG','New Project Eclipse','assets/Uploads/New-Project-Eclipse.PNG',NULL,1,1,3),(26,'Image','2014-09-17 12:22:34','2014-09-17 12:22:34','GUI-Creation.PNG','GUI Creation','assets/Uploads/GUI-Creation.PNG',NULL,1,1,3);
/*!40000 ALTER TABLE `File` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Group`
--

DROP TABLE IF EXISTS `Group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Group` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('Group') DEFAULT 'Group',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Description` mediumtext,
  `Code` varchar(255) DEFAULT NULL,
  `Locked` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `Sort` int(11) NOT NULL DEFAULT '0',
  `HtmlEditorConfig` mediumtext,
  `ParentID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ParentID` (`ParentID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Group`
--

LOCK TABLES `Group` WRITE;
/*!40000 ALTER TABLE `Group` DISABLE KEYS */;
INSERT INTO `Group` VALUES (1,'Group','2014-03-25 13:00:52','2014-09-01 12:37:14','Content Authors',NULL,'content-authors',0,1,NULL,0),(2,'Group','2014-03-25 13:00:52','2014-03-25 13:00:52','Administrators',NULL,'administrators',0,0,NULL,0),(3,'Group','2014-09-01 12:23:07','2014-09-01 13:08:05','Lecturer',NULL,'lecturer',0,0,NULL,0);
/*!40000 ALTER TABLE `Group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Group_Members`
--

DROP TABLE IF EXISTS `Group_Members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Group_Members` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupID` int(11) NOT NULL DEFAULT '0',
  `MemberID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `GroupID` (`GroupID`),
  KEY `MemberID` (`MemberID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Group_Members`
--

LOCK TABLES `Group_Members` WRITE;
/*!40000 ALTER TABLE `Group_Members` DISABLE KEYS */;
INSERT INTO `Group_Members` VALUES (1,2,1),(2,2,2),(3,3,3);
/*!40000 ALTER TABLE `Group_Members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Group_Roles`
--

DROP TABLE IF EXISTS `Group_Roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Group_Roles` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupID` int(11) NOT NULL DEFAULT '0',
  `PermissionRoleID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `GroupID` (`GroupID`),
  KEY `PermissionRoleID` (`PermissionRoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Group_Roles`
--

LOCK TABLES `Group_Roles` WRITE;
/*!40000 ALTER TABLE `Group_Roles` DISABLE KEYS */;
INSERT INTO `Group_Roles` VALUES (2,3,1);
/*!40000 ALTER TABLE `Group_Roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('Item') DEFAULT 'Item',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `startID` int(11) NOT NULL DEFAULT '0',
  `MenuID` int(11) NOT NULL DEFAULT '0',
  `University` varchar(50) DEFAULT NULL,
  `CategoryID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `MenuID` (`MenuID`),
  KEY `ClassName` (`ClassName`),
  KEY `CategoryID` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
INSERT INTO `Item` VALUES (1,'Item','2014-03-27 18:37:12','2014-03-27 18:37:12','Item a',2,22,NULL,0);
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LoginAttempt`
--

DROP TABLE IF EXISTS `LoginAttempt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LoginAttempt` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('LoginAttempt') DEFAULT 'LoginAttempt',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Status` enum('Success','Failure') DEFAULT 'Success',
  `IP` varchar(255) DEFAULT NULL,
  `MemberID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `MemberID` (`MemberID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LoginAttempt`
--

LOCK TABLES `LoginAttempt` WRITE;
/*!40000 ALTER TABLE `LoginAttempt` DISABLE KEYS */;
/*!40000 ALTER TABLE `LoginAttempt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MainCategories`
--

DROP TABLE IF EXISTS `MainCategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MainCategories` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('MainCategories') DEFAULT 'MainCategories',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MainCategories`
--

LOCK TABLES `MainCategories` WRITE;
/*!40000 ALTER TABLE `MainCategories` DISABLE KEYS */;
INSERT INTO `MainCategories` VALUES (1,'MainCategories','2014-08-27 19:30:39','2014-08-27 19:30:39','Fetal Skull'),(2,'MainCategories','2014-08-27 19:31:22','2014-08-27 19:31:22','Maternal Pelvis'),(3,'MainCategories','2014-08-27 19:31:41','2014-08-27 19:31:41','Mechanism of Labor');
/*!40000 ALTER TABLE `MainCategories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member`
--

DROP TABLE IF EXISTS `Member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Member` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('Member') DEFAULT 'Member',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `Surname` varchar(50) DEFAULT NULL,
  `Email` varchar(256) DEFAULT NULL,
  `Password` varchar(160) DEFAULT NULL,
  `RememberLoginToken` varchar(160) DEFAULT NULL,
  `NumVisit` int(11) NOT NULL DEFAULT '0',
  `LastVisited` datetime DEFAULT NULL,
  `AutoLoginHash` varchar(160) DEFAULT NULL,
  `AutoLoginExpired` datetime DEFAULT NULL,
  `PasswordEncryption` varchar(50) DEFAULT NULL,
  `Salt` varchar(50) DEFAULT NULL,
  `PasswordExpiry` date DEFAULT NULL,
  `LockedOutUntil` datetime DEFAULT NULL,
  `Locale` varchar(6) DEFAULT NULL,
  `FailedLoginCount` int(11) NOT NULL DEFAULT '0',
  `DateFormat` varchar(30) DEFAULT NULL,
  `TimeFormat` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Email` (`Email`(255)),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` VALUES (1,'Member','2014-03-25 13:00:52','2014-09-22 14:07:46','Default Admin',NULL,'admin','$2y$10$7228a179a0ab0d6c066caurkWUU6sIS3mFneIlw.uetubkag.n2O.',NULL,57,'2014-09-22 14:07:46',NULL,NULL,'blowfish','10$7228a179a0ab0d6c066ca8',NULL,NULL,'en_US',0,NULL,NULL),(2,'Member','2014-03-25 14:25:06','2014-03-25 14:25:06','Jay','Steele','steeljm1@student.op.ac.nz','$2y$10$e991dadda1427ce13b614unu5NXFjZsUC1HHCOn1s1WWHYQHZ6T26',NULL,0,NULL,NULL,NULL,'blowfish','10$e991dadda1427ce13b6147',NULL,NULL,'en_US',0,'MMM d, y','h:mm:ss a'),(3,'Member','2014-09-01 12:25:39','2014-09-22 14:08:00','Suzane','MIller','Lecturer','$2y$10$4cbaed8105a4cc6c7a17dOmtfY/zIyl4AQUgnp8jJTQcTs8bBnSSe',NULL,12,'2014-09-22 14:33:04',NULL,NULL,'blowfish','10$4cbaed8105a4cc6c7a17dc',NULL,NULL,'en_US',0,'MMM d, y','h:mm:ss a');
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MemberPassword`
--

DROP TABLE IF EXISTS `MemberPassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MemberPassword` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('MemberPassword') DEFAULT 'MemberPassword',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Password` varchar(160) DEFAULT NULL,
  `Salt` varchar(50) DEFAULT NULL,
  `PasswordEncryption` varchar(50) DEFAULT NULL,
  `MemberID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `MemberID` (`MemberID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MemberPassword`
--

LOCK TABLES `MemberPassword` WRITE;
/*!40000 ALTER TABLE `MemberPassword` DISABLE KEYS */;
INSERT INTO `MemberPassword` VALUES (1,'MemberPassword','2014-03-25 13:00:54','2014-03-25 13:00:54','$2y$10$7228a179a0ab0d6c066caurkWUU6sIS3mFneIlw.uetubkag.n2O.','10$7228a179a0ab0d6c066ca8','blowfish',1),(2,'MemberPassword','2014-03-25 14:25:06','2014-03-25 14:25:06','$2y$10$e991dadda1427ce13b614unu5NXFjZsUC1HHCOn1s1WWHYQHZ6T26','10$e991dadda1427ce13b6147','blowfish',2),(3,'MemberPassword','2014-07-27 15:17:25','2014-07-27 15:17:25','$2y$10$7228a179a0ab0d6c066caurkWUU6sIS3mFneIlw.uetubkag.n2O.','10$7228a179a0ab0d6c066ca8','blowfish',1),(4,'MemberPassword','2014-09-01 12:25:39','2014-09-01 12:25:39','$2y$10$4cbaed8105a4cc6c7a17dOmtfY/zIyl4AQUgnp8jJTQcTs8bBnSSe','10$4cbaed8105a4cc6c7a17dc','blowfish',3);
/*!40000 ALTER TABLE `MemberPassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ModelColour`
--

DROP TABLE IF EXISTS `ModelColour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ModelColour` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('ModelColour') DEFAULT 'ModelColour',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `ModelViewID` int(11) NOT NULL DEFAULT '0',
  `BgColor` mediumtext,
  PRIMARY KEY (`ID`),
  KEY `ModelViewID` (`ModelViewID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ModelColour`
--

LOCK TABLES `ModelColour` WRITE;
/*!40000 ALTER TABLE `ModelColour` DISABLE KEYS */;
INSERT INTO `ModelColour` VALUES (3,'ModelColour','2014-07-30 14:35:24','2014-07-30 14:35:24','Information for colour',1,'5a25cc');
/*!40000 ALTER TABLE `ModelColour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ModelView`
--

DROP TABLE IF EXISTS `ModelView`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ModelView` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('ModelView') DEFAULT 'ModelView',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Title` varchar(50) DEFAULT NULL,
  `Step` int(11) NOT NULL DEFAULT '0',
  `MainCategoriesID` int(11) NOT NULL DEFAULT '0',
  `ImageID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `MainCategoriesID` (`MainCategoriesID`),
  KEY `ImageID` (`ImageID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ModelView`
--

LOCK TABLES `ModelView` WRITE;
/*!40000 ALTER TABLE `ModelView` DISABLE KEYS */;
INSERT INTO `ModelView` VALUES (2,'ModelView','2014-09-17 12:22:36','2014-09-17 12:22:36','Model 1',0,1,26);
/*!40000 ALTER TABLE `ModelView` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Permission`
--

DROP TABLE IF EXISTS `Permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('Permission') DEFAULT 'Permission',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Code` varchar(50) DEFAULT NULL,
  `Arg` int(11) NOT NULL DEFAULT '0',
  `Type` int(11) NOT NULL DEFAULT '1',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `GroupID` (`GroupID`),
  KEY `Code` (`Code`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Permission`
--

LOCK TABLES `Permission` WRITE;
/*!40000 ALTER TABLE `Permission` DISABLE KEYS */;
INSERT INTO `Permission` VALUES (5,'Permission','2014-03-25 13:00:52','2014-03-25 13:00:52','ADMIN',0,1,2),(53,'Permission','2014-09-01 12:37:14','2014-09-01 12:37:14','CMS_ACCESS_CMSMain',0,1,1),(54,'Permission','2014-09-01 12:37:14','2014-09-01 12:37:14','CMS_ACCESS_AssetAdmin',0,1,1),(55,'Permission','2014-09-01 12:37:14','2014-09-01 12:37:14','CMS_ACCESS_ReportAdmin',0,1,1),(56,'Permission','2014-09-01 12:37:14','2014-09-01 12:37:14','SITETREE_REORGANISE',0,1,1),(100,'Permission','2014-09-01 13:08:05','2014-09-01 13:08:05','CMS_ACCESS_CMSMain',0,1,3),(101,'Permission','2014-09-01 13:08:05','2014-09-01 13:08:05','CMS_ACCESS_CategoryModelAdminExtension',0,1,3);
/*!40000 ALTER TABLE `Permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PermissionRole`
--

DROP TABLE IF EXISTS `PermissionRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PermissionRole` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('PermissionRole') DEFAULT 'PermissionRole',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Title` varchar(50) DEFAULT NULL,
  `OnlyAdminCanApply` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermissionRole`
--

LOCK TABLES `PermissionRole` WRITE;
/*!40000 ALTER TABLE `PermissionRole` DISABLE KEYS */;
/*!40000 ALTER TABLE `PermissionRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PermissionRoleCode`
--

DROP TABLE IF EXISTS `PermissionRoleCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PermissionRoleCode` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('PermissionRoleCode') DEFAULT 'PermissionRoleCode',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Code` varchar(50) DEFAULT NULL,
  `RoleID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `RoleID` (`RoleID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PermissionRoleCode`
--

LOCK TABLES `PermissionRoleCode` WRITE;
/*!40000 ALTER TABLE `PermissionRoleCode` DISABLE KEYS */;
/*!40000 ALTER TABLE `PermissionRoleCode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RedirectorPage`
--

DROP TABLE IF EXISTS `RedirectorPage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RedirectorPage` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RedirectionType` enum('Internal','External') DEFAULT 'Internal',
  `ExternalURL` varchar(2083) DEFAULT NULL,
  `LinkToID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `LinkToID` (`LinkToID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RedirectorPage`
--

LOCK TABLES `RedirectorPage` WRITE;
/*!40000 ALTER TABLE `RedirectorPage` DISABLE KEYS */;
/*!40000 ALTER TABLE `RedirectorPage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RedirectorPage_Live`
--

DROP TABLE IF EXISTS `RedirectorPage_Live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RedirectorPage_Live` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RedirectionType` enum('Internal','External') DEFAULT 'Internal',
  `ExternalURL` varchar(2083) DEFAULT NULL,
  `LinkToID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `LinkToID` (`LinkToID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RedirectorPage_Live`
--

LOCK TABLES `RedirectorPage_Live` WRITE;
/*!40000 ALTER TABLE `RedirectorPage_Live` DISABLE KEYS */;
/*!40000 ALTER TABLE `RedirectorPage_Live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RedirectorPage_versions`
--

DROP TABLE IF EXISTS `RedirectorPage_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RedirectorPage_versions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RecordID` int(11) NOT NULL DEFAULT '0',
  `Version` int(11) NOT NULL DEFAULT '0',
  `RedirectionType` enum('Internal','External') DEFAULT 'Internal',
  `ExternalURL` varchar(2083) DEFAULT NULL,
  `LinkToID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `RecordID_Version` (`RecordID`,`Version`),
  KEY `RecordID` (`RecordID`),
  KEY `Version` (`Version`),
  KEY `LinkToID` (`LinkToID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RedirectorPage_versions`
--

LOCK TABLES `RedirectorPage_versions` WRITE;
/*!40000 ALTER TABLE `RedirectorPage_versions` DISABLE KEYS */;
/*!40000 ALTER TABLE `RedirectorPage_versions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteConfig`
--

DROP TABLE IF EXISTS `SiteConfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteConfig` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('SiteConfig') DEFAULT 'SiteConfig',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Tagline` varchar(255) DEFAULT NULL,
  `Theme` varchar(255) DEFAULT NULL,
  `CanViewType` enum('Anyone','LoggedInUsers','OnlyTheseUsers') DEFAULT 'Anyone',
  `CanEditType` enum('LoggedInUsers','OnlyTheseUsers') DEFAULT 'LoggedInUsers',
  `CanCreateTopLevelType` enum('LoggedInUsers','OnlyTheseUsers') DEFAULT 'LoggedInUsers',
  PRIMARY KEY (`ID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteConfig`
--

LOCK TABLES `SiteConfig` WRITE;
/*!40000 ALTER TABLE `SiteConfig` DISABLE KEYS */;
INSERT INTO `SiteConfig` VALUES (1,'SiteConfig','2014-03-25 13:00:52','2014-03-25 16:11:32','Visual Midwifery','Administration Login',NULL,'Anyone','LoggedInUsers','LoggedInUsers');
/*!40000 ALTER TABLE `SiteConfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteConfig_CreateTopLevelGroups`
--

DROP TABLE IF EXISTS `SiteConfig_CreateTopLevelGroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteConfig_CreateTopLevelGroups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SiteConfigID` int(11) NOT NULL DEFAULT '0',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `SiteConfigID` (`SiteConfigID`),
  KEY `GroupID` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteConfig_CreateTopLevelGroups`
--

LOCK TABLES `SiteConfig_CreateTopLevelGroups` WRITE;
/*!40000 ALTER TABLE `SiteConfig_CreateTopLevelGroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteConfig_CreateTopLevelGroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteConfig_EditorGroups`
--

DROP TABLE IF EXISTS `SiteConfig_EditorGroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteConfig_EditorGroups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SiteConfigID` int(11) NOT NULL DEFAULT '0',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `SiteConfigID` (`SiteConfigID`),
  KEY `GroupID` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteConfig_EditorGroups`
--

LOCK TABLES `SiteConfig_EditorGroups` WRITE;
/*!40000 ALTER TABLE `SiteConfig_EditorGroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteConfig_EditorGroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteConfig_ViewerGroups`
--

DROP TABLE IF EXISTS `SiteConfig_ViewerGroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteConfig_ViewerGroups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SiteConfigID` int(11) NOT NULL DEFAULT '0',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `SiteConfigID` (`SiteConfigID`),
  KEY `GroupID` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteConfig_ViewerGroups`
--

LOCK TABLES `SiteConfig_ViewerGroups` WRITE;
/*!40000 ALTER TABLE `SiteConfig_ViewerGroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteConfig_ViewerGroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteTree`
--

DROP TABLE IF EXISTS `SiteTree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteTree` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('Page','ErrorPage','mainCategory','SiteTree','RedirectorPage','VirtualPage') DEFAULT 'Page',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `URLSegment` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `MenuTitle` varchar(100) DEFAULT NULL,
  `Content` mediumtext,
  `MetaDescription` mediumtext,
  `ExtraMeta` mediumtext,
  `ShowInMenus` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ShowInSearch` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `Sort` int(11) NOT NULL DEFAULT '0',
  `HasBrokenFile` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `HasBrokenLink` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ReportClass` varchar(50) DEFAULT NULL,
  `CanViewType` enum('Anyone','LoggedInUsers','OnlyTheseUsers','Inherit') DEFAULT 'Inherit',
  `CanEditType` enum('LoggedInUsers','OnlyTheseUsers','Inherit') DEFAULT 'Inherit',
  `Version` int(11) NOT NULL DEFAULT '0',
  `ParentID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ParentID` (`ParentID`),
  KEY `URLSegment` (`URLSegment`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteTree`
--

LOCK TABLES `SiteTree` WRITE;
/*!40000 ALTER TABLE `SiteTree` DISABLE KEYS */;
INSERT INTO `SiteTree` VALUES (1,'Page','2014-03-25 13:00:52','2014-07-28 12:21:58','home','Home',NULL,'<p>Welcome to SilverStripe! This is the default homepage. You can now access the <a href=\"http://doc.silverstripe.org\">developer documentation</a>, or begin <a href=\"http://doc.silverstripe.org/doku.php?id=tutorials\">the tutorials.</a> vasdasdased</p>',NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',3,0),(2,'Page','2014-03-25 13:00:52','2014-07-27 15:17:25','about-us','About Us',NULL,'<p>You can fill this page out with your own content, or delete it and create your own pages.<br /></p>',NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',1,0),(3,'Page','2014-03-25 13:00:52','2014-07-27 15:17:25','contact-us','Contact Us',NULL,'<p>You can fill this page out with your own content, or delete it and create your own pages.<br /></p>',NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',1,0),(4,'ErrorPage','2014-03-25 13:00:53','2014-03-25 13:00:54','page-not-found','Page not found',NULL,'<p>Sorry, it seems you were trying to access a page that doesn\'t exist.</p><p>Please check the spelling of the URL you were trying to access and try again.</p>',NULL,NULL,0,0,4,0,0,NULL,'Inherit','Inherit',1,0),(5,'ErrorPage','2014-03-25 13:00:53','2014-03-25 13:00:54','server-error','Server error',NULL,'<p>Sorry, there was a problem with handling your request.</p>',NULL,NULL,0,0,5,0,0,NULL,'Inherit','Inherit',1,0),(49,'mainCategory','2014-07-02 13:48:24','2014-07-02 13:48:53','mechanism-of-labor-','Mechanism of Labor ',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',2,46);
/*!40000 ALTER TABLE `SiteTree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteTree_EditorGroups`
--

DROP TABLE IF EXISTS `SiteTree_EditorGroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteTree_EditorGroups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SiteTreeID` int(11) NOT NULL DEFAULT '0',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `SiteTreeID` (`SiteTreeID`),
  KEY `GroupID` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteTree_EditorGroups`
--

LOCK TABLES `SiteTree_EditorGroups` WRITE;
/*!40000 ALTER TABLE `SiteTree_EditorGroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteTree_EditorGroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteTree_ImageTracking`
--

DROP TABLE IF EXISTS `SiteTree_ImageTracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteTree_ImageTracking` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SiteTreeID` int(11) NOT NULL DEFAULT '0',
  `FileID` int(11) NOT NULL DEFAULT '0',
  `FieldName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SiteTreeID` (`SiteTreeID`),
  KEY `FileID` (`FileID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteTree_ImageTracking`
--

LOCK TABLES `SiteTree_ImageTracking` WRITE;
/*!40000 ALTER TABLE `SiteTree_ImageTracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteTree_ImageTracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteTree_LinkTracking`
--

DROP TABLE IF EXISTS `SiteTree_LinkTracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteTree_LinkTracking` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SiteTreeID` int(11) NOT NULL DEFAULT '0',
  `ChildID` int(11) NOT NULL DEFAULT '0',
  `FieldName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `SiteTreeID` (`SiteTreeID`),
  KEY `ChildID` (`ChildID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteTree_LinkTracking`
--

LOCK TABLES `SiteTree_LinkTracking` WRITE;
/*!40000 ALTER TABLE `SiteTree_LinkTracking` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteTree_LinkTracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteTree_Live`
--

DROP TABLE IF EXISTS `SiteTree_Live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteTree_Live` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('Page','ErrorPage','mainCategory','SiteTree','RedirectorPage','VirtualPage') DEFAULT 'Page',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `URLSegment` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `MenuTitle` varchar(100) DEFAULT NULL,
  `Content` mediumtext,
  `MetaDescription` mediumtext,
  `ExtraMeta` mediumtext,
  `ShowInMenus` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ShowInSearch` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `Sort` int(11) NOT NULL DEFAULT '0',
  `HasBrokenFile` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `HasBrokenLink` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ReportClass` varchar(50) DEFAULT NULL,
  `CanViewType` enum('Anyone','LoggedInUsers','OnlyTheseUsers','Inherit') DEFAULT 'Inherit',
  `CanEditType` enum('LoggedInUsers','OnlyTheseUsers','Inherit') DEFAULT 'Inherit',
  `Version` int(11) NOT NULL DEFAULT '0',
  `ParentID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ParentID` (`ParentID`),
  KEY `URLSegment` (`URLSegment`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteTree_Live`
--

LOCK TABLES `SiteTree_Live` WRITE;
/*!40000 ALTER TABLE `SiteTree_Live` DISABLE KEYS */;
INSERT INTO `SiteTree_Live` VALUES (1,'Page','2014-03-25 13:00:52','2014-07-28 12:21:58','home','Home',NULL,'<p>Welcome to SilverStripe! This is the default homepage. You can now access the <a href=\"http://doc.silverstripe.org\">developer documentation</a>, or begin <a href=\"http://doc.silverstripe.org/doku.php?id=tutorials\">the tutorials.</a> vasdasdased</p>',NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',3,0),(2,'Page','2014-03-25 13:00:52','2014-03-27 12:04:43','about-us','About Us',NULL,'<p>You can fill this page out with your own content, or delete it and create your own pages.<br /></p>',NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',1,0),(3,'Page','2014-03-25 13:00:52','2014-03-27 12:04:43','contact-us','Contact Us',NULL,'<p>You can fill this page out with your own content, or delete it and create your own pages.<br /></p>',NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',1,0),(4,'ErrorPage','2014-03-25 13:00:53','2014-07-27 15:17:30','page-not-found','Page not found',NULL,'<p>Sorry, it seems you were trying to access a page that doesn\'t exist.</p><p>Please check the spelling of the URL you were trying to access and try again.</p>',NULL,NULL,0,0,4,0,0,NULL,'Inherit','Inherit',1,0),(5,'ErrorPage','2014-03-25 13:00:53','2014-03-27 12:04:43','server-error','Server error',NULL,'<p>Sorry, there was a problem with handling your request.</p>',NULL,NULL,0,0,5,0,0,NULL,'Inherit','Inherit',1,0);
/*!40000 ALTER TABLE `SiteTree_Live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteTree_ViewerGroups`
--

DROP TABLE IF EXISTS `SiteTree_ViewerGroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteTree_ViewerGroups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SiteTreeID` int(11) NOT NULL DEFAULT '0',
  `GroupID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `SiteTreeID` (`SiteTreeID`),
  KEY `GroupID` (`GroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteTree_ViewerGroups`
--

LOCK TABLES `SiteTree_ViewerGroups` WRITE;
/*!40000 ALTER TABLE `SiteTree_ViewerGroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `SiteTree_ViewerGroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SiteTree_versions`
--

DROP TABLE IF EXISTS `SiteTree_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SiteTree_versions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RecordID` int(11) NOT NULL DEFAULT '0',
  `Version` int(11) NOT NULL DEFAULT '0',
  `WasPublished` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `AuthorID` int(11) NOT NULL DEFAULT '0',
  `PublisherID` int(11) NOT NULL DEFAULT '0',
  `ClassName` enum('Page','ErrorPage','mainCategory','SiteTree','RedirectorPage','VirtualPage') DEFAULT 'Page',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `URLSegment` varchar(255) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `MenuTitle` varchar(100) DEFAULT NULL,
  `Content` mediumtext,
  `MetaDescription` mediumtext,
  `ExtraMeta` mediumtext,
  `ShowInMenus` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ShowInSearch` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `Sort` int(11) NOT NULL DEFAULT '0',
  `HasBrokenFile` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `HasBrokenLink` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `ReportClass` varchar(50) DEFAULT NULL,
  `CanViewType` enum('Anyone','LoggedInUsers','OnlyTheseUsers','Inherit') DEFAULT 'Inherit',
  `CanEditType` enum('LoggedInUsers','OnlyTheseUsers','Inherit') DEFAULT 'Inherit',
  `ParentID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `RecordID_Version` (`RecordID`,`Version`),
  KEY `RecordID` (`RecordID`),
  KEY `Version` (`Version`),
  KEY `AuthorID` (`AuthorID`),
  KEY `PublisherID` (`PublisherID`),
  KEY `ParentID` (`ParentID`),
  KEY `URLSegment` (`URLSegment`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SiteTree_versions`
--

LOCK TABLES `SiteTree_versions` WRITE;
/*!40000 ALTER TABLE `SiteTree_versions` DISABLE KEYS */;
INSERT INTO `SiteTree_versions` VALUES (1,1,1,1,0,0,'Page','2014-03-25 13:00:52','2014-03-25 13:00:52','home','Home',NULL,'<p>Welcome to SilverStripe! This is the default homepage. You can edit this page by opening <a href=\"admin/\">the CMS</a>. You can now access the <a href=\"http://doc.silverstripe.org\">developer documentation</a>, or begin <a href=\"http://doc.silverstripe.org/doku.php?id=tutorials\">the tutorials.</a></p>',NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',0),(2,2,1,1,0,0,'Page','2014-03-25 13:00:52','2014-03-25 13:00:52','about-us','About Us',NULL,'<p>You can fill this page out with your own content, or delete it and create your own pages.<br /></p>',NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',0),(3,3,1,1,0,0,'Page','2014-03-25 13:00:52','2014-03-25 13:00:52','contact-us','Contact Us',NULL,'<p>You can fill this page out with your own content, or delete it and create your own pages.<br /></p>',NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',0),(4,4,1,1,0,0,'ErrorPage','2014-03-25 13:00:53','2014-03-25 13:00:53','page-not-found','Page not found',NULL,'<p>Sorry, it seems you were trying to access a page that doesn\'t exist.</p><p>Please check the spelling of the URL you were trying to access and try again.</p>',NULL,NULL,0,0,4,0,0,NULL,'Inherit','Inherit',0),(5,5,1,1,0,0,'ErrorPage','2014-03-25 13:00:53','2014-03-25 13:00:53','server-error','Server error',NULL,'<p>Sorry, there was a problem with handling your request.</p>',NULL,NULL,0,0,5,0,0,NULL,'Inherit','Inherit',0),(6,1,2,1,1,1,'Page','2014-03-25 13:00:52','2014-03-25 14:00:01','home','Home',NULL,'<p>Welcome to SilverStripe! This is the default homepage. You can now access the <a href=\"http://doc.silverstripe.org\">developer documentation</a>, or begin <a href=\"http://doc.silverstripe.org/doku.php?id=tutorials\">the tutorials.</a></p>',NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',0),(7,6,1,0,1,0,'Page','2014-03-27 08:31:46','2014-03-27 08:31:46','new-page','New Page',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(8,7,1,0,1,0,'VirtualPage','2014-03-27 09:04:37','2014-03-27 09:04:37','new-virtual-page','New Virtual Page',NULL,NULL,NULL,NULL,1,1,1,0,1,NULL,'Inherit','Inherit',3),(9,7,2,0,1,0,'VirtualPage','2014-03-27 09:04:37','2014-03-27 09:05:42','new-virtual-page','New Virtual Page',NULL,NULL,NULL,NULL,1,1,1,0,1,NULL,'Inherit','Inherit',3),(10,8,1,0,1,0,'Page','2014-03-27 12:05:01','2014-03-27 12:05:01','new-page','New Page',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(11,9,1,0,1,0,'Page','2014-03-27 12:22:22','2014-03-27 12:22:22','new-page','New Page',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(12,10,1,0,1,0,'','2014-03-27 12:30:21','2014-03-27 12:30:21','new-project','New Project',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(13,11,1,0,1,0,'','2014-03-27 12:35:18','2014-03-27 12:35:18','new-projects-holder','New Projects Holder',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(14,11,2,1,1,1,'','2014-03-27 12:35:18','2014-03-27 12:36:16','projects','Projects',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(15,12,1,0,1,0,'','2014-03-27 12:51:58','2014-03-27 12:51:58','new-project','New Project',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',11),(16,12,2,1,1,1,'','2014-03-27 12:51:58','2014-03-27 12:52:34','developer-toolbar','Developer Toolbar',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',11),(17,13,1,1,1,1,'','2014-03-27 14:14:57','2014-03-27 14:14:57','new-project','New Project',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',11),(18,13,2,1,1,1,'','2014-03-27 14:14:57','2014-03-27 14:20:26','developer-toolbar','Developer Toolbar',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',11),(19,14,1,0,1,0,'','2014-03-27 14:20:40','2014-03-27 14:20:40','new-project','New Project',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',11),(20,14,2,1,1,1,'','2014-03-27 14:20:40','2014-03-27 14:21:06','behavior-testing','Behavior Testing',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',11),(21,15,1,0,1,0,'','2014-03-27 14:21:25','2014-03-27 14:21:25','new-project','New Project',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',11),(22,15,2,1,1,1,'','2014-03-27 14:21:25','2014-03-27 14:23:25','content-personalization-','Content Personalization    ',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',11),(23,16,1,0,1,0,'','2014-03-27 14:23:41','2014-03-27 14:23:41','new-project','New Project',NULL,NULL,NULL,NULL,1,1,4,0,0,NULL,'Inherit','Inherit',11),(24,16,2,1,1,1,'','2014-03-27 14:23:41','2014-03-27 14:24:31','module-management-','Module Management ',NULL,NULL,NULL,NULL,1,1,4,0,0,NULL,'Inherit','Inherit',11),(25,17,1,0,1,0,'','2014-03-27 14:33:37','2014-03-27 14:33:37','new-project','New Project',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',13),(26,18,1,1,1,1,'','2014-03-27 14:36:14','2014-03-27 14:36:14','new-project','New Project',NULL,NULL,NULL,NULL,1,1,7,0,0,NULL,'Inherit','Inherit',0),(27,19,1,0,1,0,'','2014-03-27 14:37:02','2014-03-27 14:37:02','new-projects-holder','New Projects Holder',NULL,NULL,NULL,NULL,1,1,7,0,0,NULL,'Inherit','Inherit',0),(28,19,2,1,1,1,'','2014-03-27 14:37:02','2014-03-27 14:37:10','projects-2','Projects',NULL,NULL,NULL,NULL,1,1,7,0,0,NULL,'Inherit','Inherit',0),(29,16,3,0,1,0,'','2014-03-27 14:23:41','2014-03-27 14:37:21','module-management-','Module Management ',NULL,NULL,NULL,NULL,1,1,4,0,0,NULL,'Inherit','Inherit',19),(30,16,4,1,1,1,'','2014-03-27 14:23:41','2014-03-27 14:37:21','module-management-','Module Management ',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',19),(31,15,3,0,1,0,'','2014-03-27 14:21:25','2014-03-27 14:37:22','content-personalization-','Content Personalization    ',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',19),(32,15,4,1,1,1,'','2014-03-27 14:21:25','2014-03-27 14:37:22','content-personalization-','Content Personalization    ',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',19),(33,13,3,0,1,0,'','2014-03-27 14:14:57','2014-03-27 14:37:24','developer-toolbar','Developer Toolbar',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',0),(34,13,4,0,1,0,'','2014-03-27 14:14:57','2014-03-27 14:37:25','developer-toolbar','Developer Toolbar',NULL,NULL,NULL,NULL,1,1,7,0,0,NULL,'Inherit','Inherit',0),(35,13,5,0,1,0,'','2014-03-27 14:14:57','2014-03-27 14:37:27','developer-toolbar','Developer Toolbar',NULL,NULL,NULL,NULL,1,1,7,0,0,NULL,'Inherit','Inherit',19),(36,13,6,0,1,0,'','2014-03-27 14:14:57','2014-03-27 14:37:27','developer-toolbar','Developer Toolbar',NULL,NULL,NULL,NULL,1,1,4,0,0,NULL,'Inherit','Inherit',19),(37,20,1,1,1,1,'','2014-03-27 14:37:41','2014-03-27 14:37:41','new-project','New Project',NULL,NULL,NULL,NULL,1,1,5,0,0,NULL,'Inherit','Inherit',19),(38,14,3,0,1,0,'','2014-03-27 14:20:40','2014-03-27 14:38:38','behavior-testing','Behavior Testing',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',19),(39,14,4,1,1,1,'','2014-03-27 14:20:40','2014-03-27 14:38:38','behavior-testing','Behavior Testing',NULL,NULL,NULL,NULL,1,1,4,0,0,NULL,'Inherit','Inherit',19),(40,13,7,1,1,1,'','2014-03-27 14:14:57','2014-03-27 14:42:54','developer-toolbar','Developer Toolbar',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',19),(41,21,1,0,1,0,'','2014-03-27 18:13:28','2014-03-27 18:13:28','new-item-holder','New Item Holder',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(42,21,2,1,1,1,'','2014-03-27 18:13:28','2014-03-27 18:14:23','app-menus','App Menus',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(43,22,1,1,1,1,'','2014-03-27 18:14:33','2014-03-27 18:14:33','new-menu','New Menu',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',21),(44,21,3,1,1,1,'SiteTree','2014-03-27 18:13:28','2014-04-07 12:14:53','app-menus','App Menus',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(45,23,1,0,1,0,'Page','2014-04-07 12:15:29','2014-04-07 12:15:29','new-page','New Page',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',1),(46,24,1,0,1,0,'','2014-04-07 12:18:48','2014-04-07 12:18:48','new-category-holder','New Category Holder',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(47,24,2,1,1,1,'','2014-04-07 12:18:48','2014-04-07 12:19:02','categorys','Categorys',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(48,25,1,0,1,0,'','2014-04-07 12:19:20','2014-04-07 12:19:20','new-category','New Category',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',24),(49,25,2,1,1,1,'','2014-04-07 12:19:20','2014-04-07 12:19:52','fetal-skull','Fetal Skull',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',24),(50,26,1,0,1,0,'','2014-04-07 12:20:06','2014-04-07 12:20:06','new-category','New Category',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',24),(51,26,2,1,1,1,'','2014-04-07 12:20:06','2014-04-07 12:20:18','new-category','Maternal Pelvis',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',24),(52,27,1,0,1,0,'','2014-04-07 12:20:33','2014-04-07 12:20:33','new-category-2','New Category',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',24),(53,27,2,1,1,1,'','2014-04-07 12:20:33','2014-04-07 12:21:10','mechanism-of-labor','Mechanism of Labor',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',24),(54,28,1,0,1,0,'','2014-04-07 12:59:32','2014-04-07 12:59:32','new-category-holder','New Category Holder',NULL,NULL,NULL,NULL,1,1,10,0,0,NULL,'Inherit','Inherit',0),(55,29,1,0,1,0,'','2014-04-07 12:59:57','2014-04-07 12:59:57','new-sub-category-holder','New Sub Category Holder',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',25),(56,30,1,0,1,0,'','2014-04-07 13:00:12','2014-04-07 13:00:12','new-sub-category','New Sub Category',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',29),(57,31,1,0,1,0,'','2014-04-07 13:03:15','2014-04-07 13:03:15','new-sub-category-holder','New Sub Category Holder',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',25),(58,31,2,1,1,1,'','2014-04-07 13:03:15','2014-04-07 13:03:45','fetal-skull-slides-category-1','Fetal Skull slides category 1',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',25),(59,32,1,0,1,0,'','2014-04-07 13:04:14','2014-04-07 13:04:14','new-sub-category','New Sub Category',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',31),(60,33,1,0,1,0,'','2014-04-07 13:30:46','2014-04-07 13:30:46','new-sub-catagory-holder','New Sub Catagory Holder',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',24),(61,33,2,1,1,1,'','2014-04-07 13:30:46','2014-04-07 13:31:03','fetal-skull','Fetal Skull',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',24),(62,34,1,0,1,0,'','2014-04-07 13:31:19','2014-04-07 13:31:19','new-sub-catagory','New Sub Catagory',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',33),(63,35,1,0,1,0,'','2014-04-07 13:32:33','2014-04-07 13:32:33','new-sub-catagory-2','New Sub Catagory',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',33),(64,36,1,0,1,0,'','2014-04-07 13:38:07','2014-04-07 13:38:07','new-sub-catagory-3','New Sub Catagory',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',33),(65,24,3,1,1,1,'SiteTree','2014-04-07 12:18:48','2014-04-07 13:41:38','categorys','Categorys',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(66,37,1,0,1,0,'','2014-04-07 13:42:40','2014-04-07 13:42:40','new-catagory-holder','New Catagory Holder',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',1),(67,37,2,1,1,1,'','2014-04-07 13:42:40','2014-04-07 13:42:52','catagorys','Catagorys',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',1),(68,38,1,0,1,0,'','2014-04-07 13:43:32','2014-04-07 13:43:32','new-catagory-holder','New Catagory Holder',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(69,38,2,1,1,1,'','2014-04-07 13:43:32','2014-04-07 13:43:51','catagorys','Catagorys',NULL,NULL,NULL,NULL,1,1,9,0,0,NULL,'Inherit','Inherit',0),(70,39,1,0,1,0,'','2014-04-07 13:44:02','2014-04-07 13:44:02','new-sub-catagory-holder','New Sub Catagory Holder',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',38),(71,39,2,1,1,1,'','2014-04-07 13:44:02','2014-04-07 13:44:16','fetal-skull','Fetal Skull',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',38),(72,40,1,0,1,0,'','2014-04-07 13:44:30','2014-04-07 13:44:30','new-sub-catagory','New Sub Catagory',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',39),(73,41,1,0,1,0,'','2014-04-07 13:46:59','2014-04-07 13:46:59','new-sub-catagory-2','New Sub Catagory',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',39),(74,41,2,1,1,1,'','2014-04-07 13:46:59','2014-04-07 13:47:29','slideset1','Slide Set 1',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',39),(75,42,1,0,1,0,'','2014-04-07 13:48:03','2014-04-07 13:48:03','new-sub-catagory-holder','New Sub Catagory Holder',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',38),(76,42,2,1,1,1,'','2014-04-07 13:48:03','2014-04-07 13:48:19','new-sub-catagory-holder','Mechanism of labor',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',38),(77,43,1,0,1,0,'','2014-04-07 13:48:33','2014-04-07 13:48:33','new-sub-catagory-holder-2','New Sub Catagory Holder',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',38),(78,43,2,1,1,1,'','2014-04-07 13:48:33','2014-04-07 13:48:54','maternal-pelvis','Maternal Pelvis',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',38),(79,44,1,0,1,0,'','2014-04-22 13:31:26','2014-04-22 13:31:26','new-sub-catagory','New Sub Catagory',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',42),(80,44,2,1,1,1,'','2014-04-22 13:31:26','2014-04-22 13:31:52','slide-set-1','Slide set 1',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',42),(81,45,1,0,1,0,'','2014-05-09 15:19:38','2014-05-09 15:19:38','new-sub-catagory','New Sub Catagory',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',39),(82,45,2,1,1,1,'','2014-05-09 15:19:38','2014-05-09 15:19:48','slide-set-2','Slide Set 2',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',39),(83,15,5,1,1,1,'SiteTree','2014-03-27 14:21:25','2014-07-02 13:35:27','content-personalization-','Content Personalization    ',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',19),(84,14,5,1,1,1,'SiteTree','2014-03-27 14:20:40','2014-07-02 13:35:40','behavior-testing','Behavior Testing',NULL,NULL,NULL,NULL,1,1,4,0,0,NULL,'Inherit','Inherit',19),(85,46,1,0,1,0,'','2014-07-02 13:36:29','2014-07-02 13:36:29','new-main-category-holder','New Main Category Holder',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(86,46,2,1,1,1,'','2014-07-02 13:36:29','2014-07-02 13:36:49','categories','Categories',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(87,47,1,0,1,0,'mainCategory','2014-07-02 13:44:46','2014-07-02 13:44:46','new-main-category','New Main Category',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',46),(88,47,2,1,1,1,'mainCategory','2014-07-02 13:44:46','2014-07-02 13:45:13','fetal-skull','Fetal Skull',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',46),(89,48,1,0,1,0,'mainCategory','2014-07-02 13:46:32','2014-07-02 13:46:32','new-main-category','New Main Category',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',46),(90,47,3,1,1,1,'mainCategory','2014-07-02 13:44:46','2014-07-02 13:47:00','maternal-pelvis','Maternal Pelvis',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',46),(91,47,4,1,1,1,'mainCategory','2014-07-02 13:44:46','2014-07-02 13:47:25','fetal-skull','Fetal Skull',NULL,NULL,NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',46),(92,48,2,1,1,1,'mainCategory','2014-07-02 13:46:32','2014-07-02 13:48:05','maternal-pelvis','Maternal Pelvis',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',46),(93,49,1,0,1,0,'mainCategory','2014-07-02 13:48:24','2014-07-02 13:48:24','new-main-category','New Main Category',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',46),(94,49,2,1,1,1,'mainCategory','2014-07-02 13:48:24','2014-07-02 13:48:50','mechanism-of-labor-','Mechanism of Labor ',NULL,NULL,NULL,NULL,1,1,3,0,0,NULL,'Inherit','Inherit',46),(95,46,3,0,1,0,'SiteTree','2014-07-02 13:36:29','2014-07-27 13:28:36','categories','Categories',NULL,NULL,NULL,NULL,1,1,6,0,0,NULL,'Inherit','Inherit',0),(96,48,3,1,1,1,'SiteTree','2014-07-02 13:46:32','2014-07-27 13:44:53','maternal-pelvis','Maternal Pelvis',NULL,NULL,NULL,NULL,1,1,2,0,0,NULL,'Inherit','Inherit',46),(97,1,3,1,1,1,'Page','2014-03-25 13:00:52','2014-07-28 12:21:57','home','Home',NULL,'<p>Welcome to SilverStripe! This is the default homepage. You can now access the <a href=\"http://doc.silverstripe.org\">developer documentation</a>, or begin <a href=\"http://doc.silverstripe.org/doku.php?id=tutorials\">the tutorials.</a> vasdasdased</p>',NULL,NULL,1,1,1,0,0,NULL,'Inherit','Inherit',0);
/*!40000 ALTER TABLE `SiteTree_versions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UpdateLog`
--

DROP TABLE IF EXISTS `UpdateLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UpdateLog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ClassName` enum('UpdateLog') DEFAULT 'UpdateLog',
  `Created` datetime DEFAULT NULL,
  `LastEdited` datetime DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `MainCategoriesID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `MainCategoriesID` (`MainCategoriesID`),
  KEY `ClassName` (`ClassName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UpdateLog`
--

LOCK TABLES `UpdateLog` WRITE;
/*!40000 ALTER TABLE `UpdateLog` DISABLE KEYS */;
INSERT INTO `UpdateLog` VALUES (1,'UpdateLog','2014-09-17 12:44:37','2014-09-17 12:44:37','Added new Models',0),(2,'UpdateLog','2014-09-17 12:51:12','2014-09-17 12:51:12','Added Stuff',0),(3,'UpdateLog','2014-09-22 14:08:16','2014-09-22 14:08:16','Test update',0),(4,'UpdateLog','2014-09-22 14:30:31','2014-09-22 14:30:31','New Update',0);
/*!40000 ALTER TABLE `UpdateLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VirtualPage`
--

DROP TABLE IF EXISTS `VirtualPage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VirtualPage` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VersionID` int(11) NOT NULL DEFAULT '0',
  `CopyContentFromID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `CopyContentFromID` (`CopyContentFromID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VirtualPage`
--

LOCK TABLES `VirtualPage` WRITE;
/*!40000 ALTER TABLE `VirtualPage` DISABLE KEYS */;
/*!40000 ALTER TABLE `VirtualPage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VirtualPage_Live`
--

DROP TABLE IF EXISTS `VirtualPage_Live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VirtualPage_Live` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VersionID` int(11) NOT NULL DEFAULT '0',
  `CopyContentFromID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `CopyContentFromID` (`CopyContentFromID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VirtualPage_Live`
--

LOCK TABLES `VirtualPage_Live` WRITE;
/*!40000 ALTER TABLE `VirtualPage_Live` DISABLE KEYS */;
/*!40000 ALTER TABLE `VirtualPage_Live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VirtualPage_versions`
--

DROP TABLE IF EXISTS `VirtualPage_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VirtualPage_versions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RecordID` int(11) NOT NULL DEFAULT '0',
  `Version` int(11) NOT NULL DEFAULT '0',
  `VersionID` int(11) NOT NULL DEFAULT '0',
  `CopyContentFromID` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `RecordID_Version` (`RecordID`,`Version`),
  KEY `RecordID` (`RecordID`),
  KEY `Version` (`Version`),
  KEY `CopyContentFromID` (`CopyContentFromID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VirtualPage_versions`
--

LOCK TABLES `VirtualPage_versions` WRITE;
/*!40000 ALTER TABLE `VirtualPage_versions` DISABLE KEYS */;
INSERT INTO `VirtualPage_versions` VALUES (1,7,1,0,0),(2,7,2,0,0);
/*!40000 ALTER TABLE `VirtualPage_versions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-23  8:56:34
