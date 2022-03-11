-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: paymybuddy4
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `compte_bancaire`
--

DROP TABLE IF EXISTS `compte_bancaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compte_bancaire` (
  `idcompte_bancaire` int NOT NULL AUTO_INCREMENT,
  `banque` varchar(100) DEFAULT NULL,
  `intitule` varchar(255) DEFAULT NULL,
  `iban` varchar(27) DEFAULT NULL,
  `bic` varchar(11) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  `utilisateur_idutilisateur` int NOT NULL,
  PRIMARY KEY (`idcompte_bancaire`,`utilisateur_idutilisateur`),
  KEY `fk_compte_bancaire_utilisateur1_idx` (`utilisateur_idutilisateur`),
  CONSTRAINT `fk_compte_bancaire_utilisateur1` FOREIGN KEY (`utilisateur_idutilisateur`) REFERENCES `utilisateur` (`idutilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compte_bancaire`
--

LOCK TABLES `compte_bancaire` WRITE;
/*!40000 ALTER TABLE `compte_bancaire` DISABLE KEYS */;
INSERT INTO `compte_bancaire` VALUES (40,'unknow','unknow','unknow','unknow',865.00,154),(41,'unknow','unknow','unknow','unknow',1000.00,155),(42,'unknow','unknow','unknow','unknow',1000.00,156),(43,'unknow','unknow','unknow','unknow',1000.00,158),(44,'unknow','unknow','unknow','unknow',1000.00,159);
/*!40000 ALTER TABLE `compte_bancaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `idtransaction` int NOT NULL AUTO_INCREMENT,
  `utilisateur_idutilisateur` int NOT NULL,
  `utilisateur_idutilisateur1` int NOT NULL,
  `montant` decimal(10,2) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idtransaction`,`utilisateur_idutilisateur`,`utilisateur_idutilisateur1`),
  KEY `fk_transaction_utilisateur1_idx` (`utilisateur_idutilisateur`),
  KEY `fk_transaction_utilisateur2_idx` (`utilisateur_idutilisateur1`),
  CONSTRAINT `fk_transaction_utilisateur1` FOREIGN KEY (`utilisateur_idutilisateur`) REFERENCES `utilisateur` (`idutilisateur`),
  CONSTRAINT `fk_transaction_utilisateur2` FOREIGN KEY (`utilisateur_idutilisateur1`) REFERENCES `utilisateur` (`idutilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (20,154,155,10.00,'to twoo 10€'),(21,154,156,10.00,'to three 10€'),(22,154,158,400.00,'to four 400'),(23,154,156,30.00,'to three 30€');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `idutilisateur` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idutilisateur`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (154,'uone@oc.com','$2a$10$NU2ChIWj91y7c0rboUMdC.To/GVL0JT8dmuxKt4PZi13xvuzjF3KS','user','one',185.00),(155,'utwo@oc.com','$2a$10$NvfHcZxAU.Fq53A3/0HoZuaDNwDhlLwbyqaYpRpnsfx/worolzGqW','user','two',500.00),(156,'uthree@oc.com','$2a$10$7KrW/QwTEfTvNX65hn1ttuGb9baHJ1JjTAJlX3MopSdbCBqUwP5Be','user','three',530.00),(158,'ufour@oc.com','$2a$10$8aBiI0rm8MLJ7Wj4kuPriuGxWNeQyJciA860S7/xZCUJd7mG94kXy','user','four',500.00),(159,'ufive@oc.com','$2a$10$X325YsZypeD8dDZbLjxOy.yUrgwN6U9jUx4ZNNFrsoS2Y0X.WAkLu','user','five',500.00);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur_has_utilisateur`
--

DROP TABLE IF EXISTS `utilisateur_has_utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur_has_utilisateur` (
  `utilisateur_idutilisateur` int NOT NULL,
  `utilisateur_idutilisateur1` int NOT NULL,
  PRIMARY KEY (`utilisateur_idutilisateur`,`utilisateur_idutilisateur1`),
  KEY `fk_utilisateur_has_utilisateur_utilisateur1_idx` (`utilisateur_idutilisateur1`),
  KEY `fk_utilisateur_has_utilisateur_utilisateur_idx` (`utilisateur_idutilisateur`),
  CONSTRAINT `fk_utilisateur_has_utilisateur_utilisateur` FOREIGN KEY (`utilisateur_idutilisateur`) REFERENCES `utilisateur` (`idutilisateur`),
  CONSTRAINT `fk_utilisateur_has_utilisateur_utilisateur1` FOREIGN KEY (`utilisateur_idutilisateur1`) REFERENCES `utilisateur` (`idutilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur_has_utilisateur`
--

LOCK TABLES `utilisateur_has_utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur_has_utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur_has_utilisateur` VALUES (154,155),(154,156),(154,158),(154,159);
/*!40000 ALTER TABLE `utilisateur_has_utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `virement`
--

DROP TABLE IF EXISTS `virement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `virement` (
  `idvirement` int NOT NULL AUTO_INCREMENT,
  `montant` decimal(10,2) DEFAULT NULL,
  `compte_bancaire_idcompte_bancaire` int DEFAULT NULL,
  `compte_bancaire_utilisateur_idutilisateur` int NOT NULL,
  PRIMARY KEY (`idvirement`,`compte_bancaire_utilisateur_idutilisateur`),
  KEY `fk_virement_compte_bancaire1_idx` (`compte_bancaire_utilisateur_idutilisateur`),
  CONSTRAINT `fk_virement_compte_bancaire1` FOREIGN KEY (`compte_bancaire_utilisateur_idutilisateur`) REFERENCES `compte_bancaire` (`utilisateur_idutilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `virement`
--

LOCK TABLES `virement` WRITE;
/*!40000 ALTER TABLE `virement` DISABLE KEYS */;
INSERT INTO `virement` VALUES (1,15.00,NULL,154);
/*!40000 ALTER TABLE `virement` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-03 16:13:48
