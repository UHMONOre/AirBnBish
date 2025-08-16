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
  `Email` varchar(100) NOT NULL,
  `Id` int NOT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Username` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('mporasalex@gmail.com',0,'123','uhmono');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homebookings`
--

DROP TABLE IF EXISTS `homebookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homebookings` (
  `BookingId` int NOT NULL AUTO_INCREMENT,
  `HomeId` int NOT NULL,
  `BookingDate` date NOT NULL,
  `CustomerId` int NOT NULL,
  PRIMARY KEY (`BookingId`),
  KEY `HomeId` (`HomeId`),
  KEY `CustomerId` (`CustomerId`),
  CONSTRAINT `homebookings_ibfk_1` FOREIGN KEY (`HomeId`) REFERENCES `homes` (`Id`),
  CONSTRAINT `homebookings_ibfk_2` FOREIGN KEY (`CustomerId`) REFERENCES `customers` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homebookings`
--

LOCK TABLES `homebookings` WRITE;
/*!40000 ALTER TABLE `homebookings` DISABLE KEYS */;
INSERT INTO `homebookings` VALUES (1,25,'2025-07-19',0),(2,25,'2025-07-20',0),(3,25,'2025-07-21',0),(4,4,'2025-07-25',0),(5,4,'2025-07-26',0),(6,4,'2025-07-27',0),(7,4,'2025-07-28',0),(8,4,'2025-07-29',0),(9,4,'2025-07-30',0),(10,4,'2025-07-31',0),(11,4,'2025-08-01',0),(12,4,'2025-08-02',0),(13,4,'2025-08-03',0),(14,4,'2025-08-04',0),(15,4,'2025-08-05',0),(16,4,'2025-08-06',0),(17,40,'2025-07-29',0),(18,40,'2025-07-30',0),(19,40,'2025-07-31',0),(20,40,'2025-08-01',0),(21,33,'2025-07-21',0),(22,33,'2025-07-22',0);
/*!40000 ALTER TABLE `homebookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homes`
--

DROP TABLE IF EXISTS `homes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homes` (
  `Id` int NOT NULL,
  `Title` varchar(100) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `Capacity` int NOT NULL,
  `Country` varchar(100) NOT NULL,
  `City` varchar(100) NOT NULL,
  `Price` double NOT NULL,
  `Image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homes`
--

LOCK TABLES `homes` WRITE;
/*!40000 ALTER TABLE `homes` DISABLE KEYS */;
INSERT INTO `homes` VALUES (0,'Eiffel Studio','12 Rue Cler',1,'France','Paris',90,'https://picsum.photos/185/130?random=0'),(1,'Montmartre Loft','7 Rue Lepic',2,'France','Paris',110,'https://picsum.photos/185/130?random=1'),(2,'Champs Elysees Suite','55 Avenue des Champs-Élysées',3,'France','Paris',150,'https://picsum.photos/185/130?random=2'),(3,'Latin Quarter Flat','23 Rue Mouffetard',4,'France','Paris',180,'https://picsum.photos/185/130?random=3'),(4,'Seine River View','18 Quai Voltaire',5,'France','Paris',200,'https://picsum.photos/185/130?random=4'),(5,'Opera Apartment','9 Rue de la Paix',6,'France','Paris',220,'https://picsum.photos/185/130?random=5'),(6,'Louvre Penthouse','3 Rue de Rivoli',7,'France','Paris',250,'https://picsum.photos/185/130?random=6'),(7,'Elegant Saint-Germain','15 Boulevard Saint-Germain',8,'France','Paris',300,'https://picsum.photos/185/130?random=7'),(8,'Old Port Studio','4 Quai du Port',1,'France','Marseille',80,'https://picsum.photos/185/130?random=8'),(9,'Panier District Loft','17 Rue du Panier',2,'France','Marseille',100,'https://picsum.photos/185/130?random=9'),(10,'Seaside Apartment','88 Corniche Kennedy',3,'France','Marseille',130,'https://picsum.photos/185/130?random=10'),(11,'Notre-Dame View','25 Boulevard André Aune',4,'France','Marseille',150,'https://picsum.photos/185/130?random=11'),(12,'Modern Vieux-Port Flat','12 Rue Sainte',5,'France','Marseille',170,'https://picsum.photos/185/130?random=12'),(13,'La Plaine Maisonette','5 Cours Julien',6,'France','Marseille',190,'https://picsum.photos/185/130?random=13'),(14,'Canebière Residence','103 La Canebière',7,'France','Marseille',220,'https://picsum.photos/185/130?random=14'),(15,'Elegant Prado Villa','19 Avenue du Prado',8,'France','Marseille',250,'https://picsum.photos/185/130?random=15'),(16,'White Tower Studio','1 Leoforos Nikis',1,'Greece','Thessaloniki',60,'https://picsum.photos/185/130?random=16'),(17,'Aristotelous Loft','10 Aristotelous Square',2,'Greece','Thessaloniki',75,'https://picsum.photos/185/130?random=17'),(18,'Rotunda Apartment','55 Egnatia Street',3,'Greece','Thessaloniki',90,'https://picsum.photos/185/130?random=18'),(19,'Ano Poli House','8 Epimenidou Street',4,'Greece','Thessaloniki',110,'https://picsum.photos/185/130?random=19'),(20,'Seaside Flat','20 Makedonia Street',5,'Greece','Thessaloniki',130,'https://picsum.photos/185/130?random=20'),(21,'City Center Residence','14 Tsimiski Street',6,'Greece','Thessaloniki',150,'https://picsum.photos/185/130?random=21'),(22,'Modern Panorama Home','33 Panorama Avenue',7,'Greece','Thessaloniki',170,'https://picsum.photos/185/130?random=22'),(23,'Elegant Kalamaria Villa','77 Kalamaria Road',8,'Greece','Thessaloniki',200,'https://picsum.photos/185/130?random=23'),(24,'Portside Studio','1 Poros Port Road',1,'Greece','Poros',55,'https://picsum.photos/185/130?random=24'),(25,'Seaview Loft','8 Askeli Beach Road',2,'Greece','Poros',65,'https://picsum.photos/185/130?random=25'),(26,'Traditional House','3 Poros Old Town',3,'Greece','Poros',75,'https://picsum.photos/185/130?random=26'),(27,'Beachside Apartment','12 Kanali Road',4,'Greece','Poros',90,'https://picsum.photos/185/130?random=27'),(28,'Harbor View Flat','18 Neorio Road',5,'Greece','Poros',110,'https://picsum.photos/185/130?random=28'),(29,'Island Retreat','6 Galatas Street',6,'Greece','Poros',130,'https://picsum.photos/185/130?random=29'),(30,'Elegant Island Villa','25 Lemonodassos Street',7,'Greece','Poros',150,'https://picsum.photos/185/130?random=30'),(31,'Luxury Seaside Home','14 Monastiri Beach Road',8,'Greece','Poros',170,'https://picsum.photos/185/130?random=31'),(32,'Notting Hill Studio','22 Portobello Road',1,'England','London',100,'https://picsum.photos/185/130?random=32'),(33,'Soho Loft','17 Dean Street',2,'England','London',120,'https://picsum.photos/185/130?random=33'),(34,'Covent Garden Apartment','8 Floral Street',3,'England','London',150,'https://picsum.photos/185/130?random=34'),(35,'Camden Town Flat','99 Camden High Street',4,'England','London',170,'https://picsum.photos/185/130?random=35'),(36,'Thames View Residence','5 Southbank Walk',5,'England','London',200,'https://picsum.photos/185/130?random=36'),(37,'Westminster Suite','10 Parliament Square',6,'England','London',230,'https://picsum.photos/185/130?random=37'),(38,'Chelsea Penthouse','44 Kings Road',7,'England','London',260,'https://picsum.photos/185/130?random=38'),(39,'Elegant Mayfair Home','3 Park Lane',8,'England','London',300,'https://picsum.photos/185/130?random=39'),(40,'Diogo Jota Forever Studio','20 Anfield Dock',1,'England','Liverpool',70,'https://picsum.photos/185/130?random=40'),(41,'Cavern Quarter Loft','10 Mathew Street',2,'England','Liverpool',85,'https://picsum.photos/185/130?random=41'),(42,'Anfield Apartment','25 Anfield Road',3,'England','Liverpool',100,'https://picsum.photos/185/130?random=42'),(43,'Hope Street Flat','8 Hope Street',4,'England','Liverpool',120,'https://picsum.photos/185/130?random=43'),(44,'Sefton Park View','55 Lark Lane',5,'England','Liverpool',140,'https://picsum.photos/185/130?random=44'),(45,'Baltic Triangle Residence','14 Jamaica Street',6,'England','Liverpool',160,'https://picsum.photos/185/130?random=45'),(46,'Georgian Quarter Maisonette','3 Rodney Street',7,'England','Liverpool',180,'https://picsum.photos/185/130?random=46'),(47,'Luxury Waterfront Home','18 Princes Dock',8,'England','Liverpool',200,'https://picsum.photos/185/130?random=47'),(48,'Jordaan Studio','5 Elandsgracht',1,'Netherlands','Amsterdam',95,'https://picsum.photos/185/130?random=48'),(49,'De Pijp Loft','22 Albert Cuypstraat',2,'Netherlands','Amsterdam',110,'https://picsum.photos/185/130?random=49'),(50,'Canal View Apartment','77 Prinsengracht',3,'Netherlands','Amsterdam',130,'https://picsum.photos/185/130?random=50'),(51,'Museum Quarter Flat','14 Van Baerlestraat',4,'Netherlands','Amsterdam',150,'https://picsum.photos/185/130?random=51'),(52,'Central Residence','12 Damrak',5,'Netherlands','Amsterdam',180,'https://picsum.photos/185/130?random=52'),(53,'Vondelpark Suite','3 Vondelstraat',6,'Netherlands','Amsterdam',210,'https://picsum.photos/185/130?random=53'),(54,'Eastern Docklands Penthouse','8 Java Island Road',7,'Netherlands','Amsterdam',240,'https://picsum.photos/185/130?random=54'),(55,'Elegant Zuid Villa','10 Beethovenstraat',8,'Netherlands','Amsterdam',270,'https://picsum.photos/185/130?random=55'),(56,'Cube House Studio','1 Overblaak',1,'Netherlands','Rotterdam',80,'https://picsum.photos/185/130?random=56'),(57,'Witte de With Loft','45 Witte de Withstraat',2,'Netherlands','Rotterdam',95,'https://picsum.photos/185/130?random=57'),(58,'Erasmus Bridge Apartment','88 Erasmus Bridge Street',3,'Netherlands','Rotterdam',115,'https://picsum.photos/185/130?random=58'),(59,'Markthal Flat','7 Binnenrotte',4,'Netherlands','Rotterdam',135,'https://picsum.photos/185/130?random=59'),(60,'Central Station Residence','18 Stationsplein',5,'Netherlands','Rotterdam',160,'https://picsum.photos/185/130?random=60'),(61,'Kop van Zuid Suite','12 Wilhelminakade',6,'Netherlands','Rotterdam',185,'https://picsum.photos/185/130?random=61'),(62,'Delfshaven Penthouse','33 Voorhaven',7,'Netherlands','Rotterdam',210,'https://picsum.photos/185/130?random=62'),(63,'Elegant Kralingen Villa','14 Lusthofstraat',8,'Netherlands','Rotterdam',240,'https://picsum.photos/185/130?random=63');
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

-- Dump completed on 2025-08-17  1:59:49
