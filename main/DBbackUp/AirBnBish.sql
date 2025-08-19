-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: homebookingapp
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Username` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'mporasalex@gmail.com','123','uhmono');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homebookings`
--

DROP TABLE IF EXISTS `homebookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homebookings` (
  `BookingDate` date NOT NULL,
  `BookingId` int NOT NULL AUTO_INCREMENT,
  `CustomerId` int NOT NULL,
  `HomeId` int NOT NULL,
  PRIMARY KEY (`BookingId`),
  KEY `CustomerId` (`CustomerId`),
  KEY `HomeId` (`HomeId`),
  CONSTRAINT `homebookings_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `homebookings_ibfk_2` FOREIGN KEY (`HomeId`) REFERENCES `homes` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homebookings`
--

LOCK TABLES `homebookings` WRITE;
/*!40000 ALTER TABLE `homebookings` DISABLE KEYS */;
/*!40000 ALTER TABLE `homebookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homes`
--

DROP TABLE IF EXISTS `homes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homes` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `Capacity` int NOT NULL,
  `Country` varchar(100) NOT NULL,
  `City` varchar(100) NOT NULL,
  `Price` double NOT NULL,
  `Image` varchar(500) DEFAULT NULL,
  `CustomerId` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id`),
  KEY `customerId_fk` (`CustomerId`),
  CONSTRAINT `customerId_fk` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homes`
--

LOCK TABLES `homes` WRITE;
/*!40000 ALTER TABLE `homes` DISABLE KEYS */;
INSERT INTO `homes` VALUES (1,'Eiffel Studio','12 Rue Cler',1,'France','Paris',90,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(2,'Montmartre Loft','7 Rue Lepic',2,'France','Paris',110,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(3,'Champs Elysees Suite','55 Avenue des Champs-Élysées',3,'France','Paris',150,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(4,'Latin Quarter Flat','23 Rue Mouffetard',4,'France','Paris',180,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(5,'Seine River View','18 Quai Voltaire',5,'France','Paris',200,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(6,'Opera Apartment','9 Rue de la Paix',6,'France','Paris',220,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(7,'Louvre Penthouse','3 Rue de Rivoli',7,'France','Paris',250,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(8,'Elegant Saint-Germain','15 Boulevard Saint-Germain',8,'France','Paris',300,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(9,'Old Port Studio','4 Quai du Port',1,'France','Marseille',80,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(10,'Panier District Loft','17 Rue du Panier',2,'France','Marseille',100,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(11,'Seaside Apartment','88 Corniche Kennedy',3,'France','Marseille',130,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(12,'Notre-Dame View','25 Boulevard André Aune',4,'France','Marseille',150,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(13,'Modern Vieux-Port Flat','12 Rue Sainte',5,'France','Marseille',170,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(14,'La Plaine Maisonette','5 Cours Julien',6,'France','Marseille',190,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(15,'Canebière Residence','103 La Canebière',7,'France','Marseille',220,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(16,'Elegant Prado Villa','19 Avenue du Prado',8,'France','Marseille',250,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(17,'White Tower Studio','1 Leoforos Nikis',1,'Greece','Thessaloniki',60,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(18,'Aristotelous Loft','10 Aristotelous Square',2,'Greece','Thessaloniki',75,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(19,'Rotunda Apartment','55 Egnatia Street',3,'Greece','Thessaloniki',90,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(20,'Ano Poli House','8 Epimenidou Street',4,'Greece','Thessaloniki',110,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(21,'Seaside Flat','20 Makedonia Street',5,'Greece','Thessaloniki',130,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(22,'City Center Residence','14 Tsimiski Street',6,'Greece','Thessaloniki',150,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(23,'Modern Panorama Home','33 Panorama Avenue',7,'Greece','Thessaloniki',170,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(24,'Elegant Kalamaria Villa','77 Kalamaria Road',8,'Greece','Thessaloniki',200,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(25,'Portside Studio','1 Poros Port Road',1,'Greece','Poros',55,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(26,'Seaview Loft','8 Askeli Beach Road',2,'Greece','Poros',65,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(27,'Traditional House','3 Poros Old Town',3,'Greece','Poros',75,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(28,'Beachside Apartment','12 Kanali Road',4,'Greece','Poros',90,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(29,'Harbor View Flat','18 Neorio Road',5,'Greece','Poros',110,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(30,'Island Retreat','6 Galatas Street',6,'Greece','Poros',130,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(31,'Elegant Island Villa','25 Lemonodassos Street',7,'Greece','Poros',150,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(32,'Luxury Seaside Home','14 Monastiri Beach Road',8,'Greece','Poros',170,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(33,'Notting Hill Studio','22 Portobello Road',1,'England','London',100,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(34,'Soho Loft','17 Dean Street',2,'England','London',120,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(35,'Covent Garden Apartment','8 Floral Street',3,'England','London',150,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(36,'Camden Town Flat','99 Camden High Street',4,'England','London',170,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(37,'Thames View Residence','5 Southbank Walk',5,'England','London',200,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(38,'Westminster Suite','10 Parliament Square',6,'England','London',230,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(39,'Chelsea Penthouse','44 Kings Road',7,'England','London',260,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(40,'Elegant Mayfair Home','3 Park Lane',8,'England','London',300,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(41,'Diogo Jota Forever Studio','20 Anfield Dock',1,'England','Liverpool',70,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(42,'Cavern Quarter Loft','10 Mathew Street',2,'England','Liverpool',85,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(43,'Anfield Apartment','25 Anfield Road',3,'England','Liverpool',100,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(44,'Hope Street Flat','8 Hope Street',4,'England','Liverpool',120,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(45,'Sefton Park View','55 Lark Lane',5,'England','Liverpool',140,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(46,'Baltic Triangle Residence','14 Jamaica Street',6,'England','Liverpool',160,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(47,'Georgian Quarter Maisonette','3 Rodney Street',7,'England','Liverpool',180,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(48,'Luxury Waterfront Home','18 Princes Dock',8,'England','Liverpool',200,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(49,'Jordaan Studio','5 Elandsgracht',1,'Netherlands','Amsterdam',95,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(50,'De Pijp Loft','22 Albert Cuypstraat',2,'Netherlands','Amsterdam',110,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(51,'Canal View Apartment','77 Prinsengracht',3,'Netherlands','Amsterdam',130,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(52,'Museum Quarter Flat','14 Van Baerlestraat',4,'Netherlands','Amsterdam',150,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(53,'Central Residence','12 Damrak',5,'Netherlands','Amsterdam',180,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(54,'Vondelpark Suite','3 Vondelstraat',6,'Netherlands','Amsterdam',210,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(55,'Eastern Docklands Penthouse','8 Java Island Road',7,'Netherlands','Amsterdam',240,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(56,'Elegant Zuid Villa','10 Beethovenstraat',8,'Netherlands','Amsterdam',270,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(57,'Cube House Studio','1 Overblaak',1,'Netherlands','Rotterdam',80,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(58,'Witte de With Loft','45 Witte de Withstraat',2,'Netherlands','Rotterdam',95,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(59,'Erasmus Bridge Apartment','88 Erasmus Bridge Street',3,'Netherlands','Rotterdam',115,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(60,'Markthal Flat','7 Binnenrotte',4,'Netherlands','Rotterdam',135,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(61,'Central Station Residence','18 Stationsplein',5,'Netherlands','Rotterdam',160,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(62,'Kop van Zuid Suite','12 Wilhelminakade',6,'Netherlands','Rotterdam',185,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(63,'Delfshaven Penthouse','33 Voorhaven',7,'Netherlands','Rotterdam',210,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1),(64,'Elegant Kralingen Villa','14 Lusthofstraat',8,'Netherlands','Rotterdam',240,'https://static01.nyt.com/images/2022/11/07/multimedia/07liverpool2-1-4e75/07liverpool2-1-4e75-superJumbo.jpg?quality=75&auto=webp',1);
/*!40000 ALTER TABLE `homes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-19 17:56:07
