CREATE DATABASE  IF NOT EXISTS `assignment2_2022` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `assignment2_2022`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: assignment2_2022
-- ------------------------------------------------------
-- Server version	5.5.28

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
-- Table structure for table `parts`
--

DROP TABLE IF EXISTS `parts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parts` (
  `pid` int(11) NOT NULL,
  `pname` varchar(30) DEFAULT NULL,
  `color` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parts`
--

LOCK TABLES `parts` WRITE;
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
INSERT INTO `parts` VALUES (101,'Battery','red'),(102,'Wiper Blades','black'),(201,'Catalytic Converter','black'),(202,'Oxygen Sensor','black'),(301,'Ignition Coil','black'),(302,'Brake Pads','green'),(401,'Brake Rotors','green'),(402,'Light','red'),(501,'Serpentine Belt','blue'),(703,'Spark Plugs','red'),(704,'Shock Absorbers','red'),(800,'Battery','black'),(801,'Wiper Blades','green'),(802,'Catalytic Converter','green'),(803,'Oxygen Sensor','green'),(804,'Ignition Coil','green'),(805,'Brake Pads','black'),(806,'Brake Rotors','black'),(807,'Light','green'),(808,'Serpentine Belt','black'),(809,'Spark Plugs','black'),(810,'Shock Absorbers','black'),(811,'Battery','green'),(812,'Wiper Blades','brown'),(813,'Catalytic Converter','pink'),(814,'Oxygen Sensor','white'),(815,'Ignition Coil','blue'),(816,'Brake Pads','red'),(817,'Brake Rotors','red'),(818,'Light','yellow'),(819,'Serpentine Belt','green'),(820,'Air Filter','black'),(821,'Engine Oil','black'),(822,'Transmission Fluid','black'),(823,'Air Pressure Sensor','black'),(824,'Wheel Bearing','black'),(825,'Air Filter','green'),(826,'Engine Oil','green'),(827,'Transmission Fluid','green'),(828,'Air Pressure Sensor','green'),(829,'Wheel Bearing','green'),(830,'Air Filter','white'),(831,'Engine Oil','white'),(832,'Transmission Fluid','white'),(833,'Air Pressure Sensor','white'),(834,'Wheel Bearing','white'),(835,'Air Filter','red'),(836,'Engine Oil','red'),(837,'Transmission Fluid','red'),(838,'Air Pressure Sensor','red'),(839,'Wheel Bearing','red');
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers` (
  `sid` int(11) NOT NULL,
  `sname` varchar(30) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'Amazon','Ames'),(2,'AutoZone','Boone'),(3,'Advance Auto Parts','Ankeny'),(4,'O Reilly','Bondurant'),(5,'Walmart','Des Moines'),(6,'Ford','Ames'),(7,'Wilson Toyota','Boone'),(8,'Lithia Honda','Nevada'),(9,'George Chevy','Clive'),(10,'Deerly Bro','West Des Moines'),(11,'Dewey Ford','Ankeny'),(12,'Huxley Nissan','Huxley'),(13,'Story City Dodge','Story City'),(14,'Iowa Auto','Iowa City'),(15,'Seth Auto','Davenport'),('16','Valve Auto','');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalog` (
  `sid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `cost` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`sid`,`pid`),
  KEY `pid_idx` (`pid`),
  CONSTRAINT `pid` FOREIGN KEY (`pid`) REFERENCES `parts` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sid` FOREIGN KEY (`sid`) REFERENCES `suppliers` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (1,102,100),(1,202,50),(1,302,40),(1,402,35),(1,800,76),(1,801,74),(1,802,22),(1,803,3),(1,804,60),(1,805,50),(1,806,87),(1,807,45),(1,808,94),(1,809,53),(1,810,28),(1,811,24),(1,812,51),(1,813,3),(1,814,60),(1,815,90),(1,816,8),(1,817,86),(1,818,66),(1,819,26),(1,820,59),(1,821,37),(1,822,81),(1,823,5),(1,824,6),(1,825,60),(1,826,36),(1,827,14),(1,828,59),(1,829,55),(1,830,95),(1,831,9),(1,832,58),(1,833,55),(1,834,59),(1,835,17),(1,836,41),(1,837,7),(1,838,9),(1,839,96),(2,301,30),(2,401,35),(2,703,45),(2,704,120),(2,800,18),(2,801,1),(2,802,63),(2,803,25),(2,804,85),(2,805,49),(2,806,74),(2,807,12),(2,808,73),(2,809,68),(2,810,89),(2,811,86),(2,812,15),(2,813,76),(2,814,60),(2,815,86),(2,816,9),(2,817,65),(2,818,32),(2,819,85),(2,820,49),(2,821,63),(2,822,3),(2,823,96),(2,824,29),(2,825,48),(2,826,77),(2,827,15),(2,828,27),(2,829,77),(2,830,22),(2,831,83),(2,832,2),(2,833,18),(2,834,9),(2,835,63),(2,836,99),(2,837,95),(2,838,97),(2,839,39),(3,101,65),(3,201,55),(3,703,85),(3,704,70),(3,800,37),(3,801,63),(3,802,47),(3,803,81),(3,804,4),(3,805,25),(3,806,80),(3,807,38),(3,808,88),(3,809,3),(3,810,20),(3,811,47),(3,812,19),(3,813,2),(3,814,6),(3,815,70),(3,816,94),(3,817,29),(3,818,60),(3,819,66),(3,820,27),(3,821,34),(3,822,96),(3,823,3),(3,824,46),(3,825,93),(3,826,89),(3,827,41),(3,828,5),(3,829,44),(3,830,97),(3,831,29),(3,832,80),(3,833,83),(3,834,99),(3,835,64),(3,836,2),(3,837,36),(3,838,7),(3,839,63),(4,301,60),(4,302,75),(4,401,80),(4,402,95),(4,800,66),(4,801,95),(4,802,3),(4,803,37),(4,804,88),(4,805,99),(4,806,94),(4,807,98),(4,808,47),(4,809,49),(4,810,68),(4,811,35),(4,812,71),(4,813,93),(4,814,98),(4,815,20),(4,816,3),(4,817,77),(4,818,74),(4,819,35),(4,820,60),(4,821,31),(4,822,6),(4,823,87),(4,824,29),(4,825,39),(4,826,47),(4,827,93),(4,828,22),(4,829,56),(4,830,59),(4,831,83),(4,832,59),(4,833,39),(4,834,35),(4,835,94),(4,836,85),(4,837,31),(4,838,92),(4,839,65),(5,102,60),(5,201,45),(5,202,65),(5,301,90),(5,800,14),(5,801,78),(5,802,93),(5,803,34),(5,804,80),(5,805,15),(5,806,12),(5,807,82),(5,808,100),(5,809,1),(5,810,43),(5,811,79),(5,812,9),(5,813,70),(5,814,17),(5,815,31),(5,816,83),(5,817,1),(5,818,40),(5,819,45),(5,820,1),(5,821,75),(5,822,30),(5,823,89),(5,824,4),(5,825,30),(5,826,95),(5,827,46),(5,828,27),(5,829,3),(5,830,18),(5,831,32),(5,832,26),(5,833,66),(5,834,64),(5,835,4),(5,836,76),(5,837,52),(5,838,73),(5,839,31),(6,800,20),(6,801,61),(6,802,87),(6,803,40),(6,804,56),(6,805,29),(6,806,30),(6,807,61),(6,808,25),(6,809,5),(6,810,42),(6,811,2),(6,812,37),(6,813,88),(6,814,95),(6,815,34),(6,816,5),(6,817,66),(6,818,85),(6,819,89),(6,820,51),(6,821,49),(6,822,29),(6,823,62),(6,824,96),(6,825,4),(6,826,77),(6,827,88),(6,828,7),(6,829,34),(6,830,18),(6,831,82),(6,832,89),(6,833,24),(6,834,8),(6,835,27),(6,836,92),(6,837,15),(6,838,21),(6,839,32),(7,800,100),(7,801,71),(7,802,46),(7,803,68),(7,804,51),(7,805,40),(7,806,97),(7,807,15),(7,808,42),(7,809,76),(7,810,47),(7,811,27),(7,812,45),(7,813,88),(7,814,91),(7,815,93),(7,816,32),(7,817,26),(7,818,99),(7,819,96),(7,820,32),(7,821,51),(7,822,19),(7,823,33),(7,824,53),(7,825,31),(7,826,35),(7,827,70),(7,828,28),(7,829,86),(7,830,95),(7,831,68),(7,832,14),(7,833,79),(7,834,68),(7,835,50),(7,836,81),(7,837,44),(7,838,76),(7,839,96),(8,800,90),(8,801,85),(8,802,74),(8,803,81),(8,804,76),(8,805,15),(8,806,82),(8,807,24),(8,808,15),(8,809,86),(8,810,27),(8,811,64),(8,812,96),(8,813,48),(8,814,6),(8,815,60),(8,816,89),(8,817,70),(8,818,73),(8,819,69),(8,820,2),(8,821,24),(8,822,52),(8,823,35),(8,824,35),(8,825,48),(8,826,8),(8,827,85),(8,828,38),(8,829,44),(8,830,62),(8,831,50),(8,832,12),(8,833,67),(8,834,98),(8,835,22),(8,836,73),(8,837,43),(8,838,25),(8,839,7),(9,800,17),(9,801,31),(9,802,2),(9,803,75),(9,804,64),(9,805,84),(9,806,40),(9,807,81),(9,808,17),(9,809,78),(9,810,9),(9,811,60),(9,812,55),(9,813,89),(9,814,89),(9,815,64),(9,816,9),(9,817,38),(9,818,30),(9,819,51),(9,820,82),(9,821,1),(9,822,22),(9,823,54),(9,824,20),(9,825,59),(9,826,42),(9,827,39),(9,828,51),(9,829,8),(9,830,43),(9,831,93),(9,832,45),(9,833,2),(9,834,97),(9,835,28),(9,836,51),(9,837,34),(9,838,94),(9,839,60),(10,800,34),(10,801,22),(10,802,65),(10,803,31),(10,804,1),(10,805,92),(10,806,31),(10,807,64),(10,808,76),(10,809,28),(10,810,35),(10,811,1),(10,812,93),(10,813,38),(10,814,26),(10,815,94),(10,816,78),(10,817,35),(10,818,21),(10,819,98),(10,820,12),(10,821,19),(10,822,75),(10,823,42),(10,824,65),(10,825,48),(10,826,26),(10,827,88),(10,828,30),(10,829,56),(10,830,98),(10,831,90),(10,832,89),(10,833,43),(10,834,14),(10,835,37),(10,836,92),(10,837,40),(10,838,6),(10,839,47),(11,800,10),(11,801,45),(11,802,62),(11,803,68),(11,804,86),(11,805,95),(11,806,24),(11,807,16),(11,808,41),(11,809,40),(11,810,4),(11,811,71),(11,812,64),(11,813,56),(11,814,91),(11,815,29),(11,816,12),(11,817,63),(11,818,45),(11,819,62),(11,820,78),(11,821,1),(11,822,73),(11,823,72),(11,824,4),(11,825,62),(11,826,40),(11,827,91),(11,828,14),(11,829,56),(11,830,16),(11,831,85),(11,832,93),(11,833,68),(11,834,17),(11,835,5),(11,836,85),(11,837,23),(11,838,65),(11,839,74),(12,800,37),(12,801,56),(12,802,87),(12,803,61),(12,804,9),(12,805,67),(12,806,25),(12,807,78),(12,808,55),(12,809,93),(12,810,90),(12,811,75),(12,812,38),(12,813,22),(12,814,18),(12,815,81),(12,816,93),(12,817,71),(12,818,72),(12,819,86),(12,820,33),(12,821,78),(12,822,46),(12,823,40),(12,824,98),(12,825,25),(12,826,49),(12,827,33),(12,828,96),(12,829,63),(12,830,81),(12,831,35),(12,832,20),(12,833,47),(12,834,64),(12,835,15),(12,836,18),(12,837,16),(12,838,54),(12,839,48),(13,800,73),(13,801,12),(13,802,1),(13,803,65),(13,804,7),(13,805,91),(13,806,90),(13,807,96),(13,808,28),(13,809,8),(13,810,65),(13,811,4),(13,812,2),(13,813,16),(13,814,10),(13,815,58),(13,816,16),(13,817,59),(13,818,69),(13,819,65),(13,820,14),(13,821,10),(13,822,69),(13,823,96),(13,824,89),(13,825,58),(13,826,46),(13,827,48),(13,828,17),(13,829,61),(13,830,77),(13,831,36),(13,832,23),(13,833,76),(13,834,17),(13,835,71),(13,836,23),(13,837,47),(13,838,39),(13,839,10),(14,800,93),(14,801,49),(14,802,42),(14,803,10),(14,804,70),(14,805,22),(14,806,33),(14,807,6),(14,808,40),(14,809,46),(14,810,39),(14,811,35),(14,812,36),(14,813,37),(14,814,29),(14,815,23),(14,816,59),(14,817,39),(14,818,75),(14,819,78),(14,820,79),(14,821,54),(14,822,80),(14,823,78),(14,824,22),(14,825,7),(14,826,50),(14,827,56),(14,828,90),(14,829,63),(14,830,37),(14,831,47),(14,832,48),(14,833,93),(14,834,88),(14,835,3),(14,836,69),(14,837,74),(14,838,66),(14,839,6),(15,800,96),(15,801,99),(15,802,49),(15,803,27),(15,804,23),(15,805,86),(15,806,88),(15,807,61),(15,808,88),(15,809,96),(15,810,46),(15,811,17),(15,812,28),(15,813,67),(15,814,17),(15,815,3),(15,816,47),(15,817,64),(15,818,45),(15,819,71),(15,820,51),(15,821,28),(15,822,92),(15,823,77),(15,824,36),(15,825,79),(15,826,16),(15,827,55),(15,828,65),(15,829,78),(15,830,3),(15,831,46),(15,832,80),(15,833,43),(15,834,83),(15,835,97),(15,836,81),(15,837,89),(15,838,58),(15,839,69),('16','302','5');
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-28 13:00:10
