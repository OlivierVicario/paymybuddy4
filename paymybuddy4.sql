-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema paymybuddy4
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `paymybuddy4` ;

-- -----------------------------------------------------
-- Schema paymybuddy4
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `paymybuddy4` DEFAULT CHARACTER SET utf8 ;
USE `paymybuddy4` ;

-- -----------------------------------------------------
-- Table `paymybuddy4`.`utilisateur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymybuddy4`.`utilisateur` ;

CREATE TABLE IF NOT EXISTS `paymybuddy4`.`utilisateur` (
  `idutilisateur` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(100) NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `balance` DECIMAL(10,2) NULL,
  PRIMARY KEY (`idutilisateur`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy4`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymybuddy4`.`transaction` ;

CREATE TABLE IF NOT EXISTS `paymybuddy4`.`transaction` (
  `idtransaction` INT NOT NULL AUTO_INCREMENT,
  `utilisateur_idutilisateur` INT NOT NULL,
  `utilisateur_idutilisateur1` INT NOT NULL,
  `montant` DECIMAL(10,2) NULL,
  PRIMARY KEY (`idtransaction`, `utilisateur_idutilisateur`, `utilisateur_idutilisateur1`),
  INDEX `fk_transaction_utilisateur1_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
  INDEX `fk_transaction_utilisateur2_idx` (`utilisateur_idutilisateur1` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `paymybuddy4`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_utilisateur2`
    FOREIGN KEY (`utilisateur_idutilisateur1`)
    REFERENCES `paymybuddy4`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy4`.`utilisateur_has_utilisateur`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymybuddy4`.`utilisateur_has_utilisateur` ;

CREATE TABLE IF NOT EXISTS `paymybuddy4`.`utilisateur_has_utilisateur` (
  `utilisateur_idutilisateur` INT NOT NULL,
  `utilisateur_idutilisateur1` INT NOT NULL,
  PRIMARY KEY (`utilisateur_idutilisateur`, `utilisateur_idutilisateur1`),
  INDEX `fk_utilisateur_has_utilisateur_utilisateur1_idx` (`utilisateur_idutilisateur1` ASC) VISIBLE,
  INDEX `fk_utilisateur_has_utilisateur_utilisateur_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
  CONSTRAINT `fk_utilisateur_has_utilisateur_utilisateur`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `paymybuddy4`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_utilisateur_has_utilisateur_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur1`)
    REFERENCES `paymybuddy4`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy4`.`compte_bancaire`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymybuddy4`.`compte_bancaire` ;

CREATE TABLE IF NOT EXISTS `paymybuddy4`.`compte_bancaire` (
  `idcompte_bancaire` INT NOT NULL AUTO_INCREMENT,
  `banque` VARCHAR(100) NULL,
  `intitule` VARCHAR(255) NULL,
  `iban` VARCHAR(27) NULL,
  `bic` VARCHAR(11) NULL,
  `utilisateur_idutilisateur` INT NOT NULL,
  PRIMARY KEY (`idcompte_bancaire`, `utilisateur_idutilisateur`),
  INDEX `fk_compte_bancaire_utilisateur1_idx` (`utilisateur_idutilisateur` ASC) VISIBLE,
  CONSTRAINT `fk_compte_bancaire_utilisateur1`
    FOREIGN KEY (`utilisateur_idutilisateur`)
    REFERENCES `paymybuddy4`.`utilisateur` (`idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `paymybuddy4`.`virement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymybuddy4`.`virement` ;

CREATE TABLE IF NOT EXISTS `paymybuddy4`.`virement` (
  `idvirement` INT NOT NULL AUTO_INCREMENT,
  `montant` DECIMAL(10,2) NULL,
  `compte_bancaire_idcompte_bancaire` INT NOT NULL,
  `compte_bancaire_utilisateur_idutilisateur` INT NOT NULL,
  PRIMARY KEY (`idvirement`, `compte_bancaire_idcompte_bancaire`, `compte_bancaire_utilisateur_idutilisateur`),
  INDEX `fk_virement_compte_bancaire1_idx` (`compte_bancaire_idcompte_bancaire` ASC, `compte_bancaire_utilisateur_idutilisateur` ASC) VISIBLE,
  CONSTRAINT `fk_virement_compte_bancaire1`
    FOREIGN KEY (`compte_bancaire_idcompte_bancaire` , `compte_bancaire_utilisateur_idutilisateur`)
    REFERENCES `paymybuddy4`.`compte_bancaire` (`idcompte_bancaire` , `utilisateur_idutilisateur`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
