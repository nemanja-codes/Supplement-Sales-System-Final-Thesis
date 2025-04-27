/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`suplementi` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `suplementi`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator` VALUES 
(1,'Nemanja','Markovic','nemanja','nemanja');



DROP TABLE IF EXISTS `Kategorija`;

CREATE TABLE `Kategorija` (
  `KategorijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`KategorijaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Kategorija` VALUES 
(1,'Protein'),
(2,'Kreatin'),
(3,'Vitamini, minerali, imunitet');


DROP TABLE IF EXISTS `JedinicaMere`;

CREATE TABLE `JedinicaMere` (
  `JedinicaMereID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`JedinicaMereID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `JedinicaMere` VALUES 
(1,'kg'),
(2,'g'),
(3,'mg'),
(4,'kapsule od 450mg'),
(5,'kapsule od 200mg');


DROP TABLE IF EXISTS `Sastojak`;

CREATE TABLE `Sastojak` (
  `SastojakID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  `JedinicaMereID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`SastojakID`),
  CONSTRAINT `fk_jm_id` FOREIGN KEY (`JedinicaMereID`) REFERENCES `JedinicaMere` (`JedinicaMereID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Sastojak` VALUES 
(1,'Bromelain',3),
(2,'Papain',3),
(3,'Collagen',2),
(4,'Vitamin C',2),
(5,'Kreatin',2),
(6,'Ashwagandha',3),
(7,'Beta karoten',2);



DROP TABLE IF EXISTS `Suplement`;

CREATE TABLE `Suplement` (
  `SuplementID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(20) NOT NULL,
  `Kolicina` DECIMAL(10,2) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  `KategorijaID` BIGINT(10) UNSIGNED NOT NULL,
  `JedinicaMereID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`SuplementID`),
  CONSTRAINT `fk_kategorija_id` FOREIGN KEY (`KategorijaID`) REFERENCES `Kategorija` (`KategorijaID`),
  CONSTRAINT `fk_jm_id2` FOREIGN KEY (`JedinicaMereID`) REFERENCES `JedinicaMere` (`JedinicaMereID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Suplement` VALUES 
(1,'100% Premium Azgard Whey', 2.27, 6000, '73% CFM proteina surutke doprinosi rastu i održavanju mišićne mase i održavanju normalnog stanja kostiju sa digestivnim enzimima, za bolje varenje. Naš Instant CFM WHEY PROTEIN „Mikrofiltracija unakrsnog protoka“ proizvodni proces je jedan od najboljih oblika procesa proizvodnje, kada je whey protein u pitanju.',1,1),
(2,'Collagen', 0.3, 3000, 'Kolagen je strukturni i najzastupljeniji protein koji ulazi u sastav preko 25% svih proteina organizma sisara, preko 30% proteina čoveka i preko 70% proteina ljudske kože.',1,1),
(3,'Creatine Monohydrate', 1.0, 5000, 'Kreatin monohidrat je najpoznatiji i najispitaniji sportski suplement svih vremena.',2,1),
(4,'Crea Lean Powder', 0.5, 2500, 'Kreatin je jedinjenje koje ljudski organizam sam proizvodi i koji snabdeva mišiće energijom.',2,1),
(5,'Ashwagandha', 90, 2400, 'Ašvanganda (Withania somnifera) je biljka koja pripada familiji Solanaceae i ima široku primenu u indijskoj tradicionalnoj medicini Ajurvedi.',3,4),
(6,'Beta Karoten', 50, 2000, 'Njegov unos smanjuje rizik od pojave dijabetesa i tolerancije na glukozu, jačanje imuno-sistema, boreći se protiv infekcije i virusa.',3,5);




DROP TABLE IF EXISTS `StavkaSuplementa`;

CREATE TABLE `StavkaSuplementa` (
  `SuplementID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `Kolicina` DECIMAL(10,2) NOT NULL,
  `SastojakID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`SuplementID`,`Rb`),
  CONSTRAINT `fk_suplement_id` FOREIGN KEY (`SuplementID`) REFERENCES `Suplement` (`SuplementID`) ON DELETE CASCADE,
  CONSTRAINT `fk_sastojak_id` FOREIGN KEY (`SastojakID`) REFERENCES `Sastojak` (`SastojakID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `StavkaSuplementa` VALUES 
(1,1,5.00,1),
(1,2,5.00,2),
(2,1,9.5,3),
(2,2,80.00,4),
(3,1,1000.00,5),
(4,1,500.00,5),
(5,1,450.00,6),
(5,2,40.00,4),
(6,1,200.00,7);



DROP TABLE IF EXISTS `Racun`;

CREATE TABLE `Racun` (
  `RacunID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `UkupanIznos` DECIMAL(10,2) NOT NULL,
  `PoreskaStopa` DECIMAL(10,2) NOT NULL,
  `KonacanIznos` DECIMAL(10,2) NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RacunID`),
  CONSTRAINT `fk_administrator_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT INTO `Racun` VALUES 
(1,'2024-01-25 18:51:32', 5000, 20.00, 6000, 1);



DROP TABLE IF EXISTS `StavkaRacuna`;

CREATE TABLE `StavkaRacuna` (
  `RacunID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `Kolicina` INT(7) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `SuplementID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RacunID`,`Rb`),
  CONSTRAINT `fk_racun_id` FOREIGN KEY (`RacunID`) REFERENCES `Racun` (`RacunID`),
  CONSTRAINT `fk_suplement_id2` FOREIGN KEY (`SuplementID`) REFERENCES `Suplement` (`SuplementID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `StavkaRacuna` VALUES 
(1,1,1,3000,2),
(1,2,1,2000,6);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
