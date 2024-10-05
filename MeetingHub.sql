-- MySQL Script generated by MySQL Workbench
-- Thu Oct  3 18:36:08 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Sala` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `capacidade` VARCHAR(45) NULL,
  `recursos_disponiveis` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reserva`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reserva` (
  `id` INT NOT NULL,
  `data_hora_inicio` VARCHAR(45) NULL,
  `data_hora_fim` VARCHAR(45) NULL,
  `Sala_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Sala_id`),
  INDEX `fk_Reserva_Sala1_idx` (`Sala_id` ASC) VISIBLE,
  CONSTRAINT `fk_Reserva_Sala1`
    FOREIGN KEY (`Sala_id`)
    REFERENCES `mydb`.`Sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Funcionario` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `cargo` VARCHAR(45) NULL,
  `Reserva_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Reserva_id`),
  INDEX `fk_Funcionario_Reserva_idx` (`Reserva_id` ASC) VISIBLE,
  CONSTRAINT `fk_Funcionario_Reserva`
    FOREIGN KEY (`Reserva_id`)
    REFERENCES `mydb`.`Reserva` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Recurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Recurso` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  `quantidade` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Sala_has_Recurso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Sala_has_Recurso` (
  `Sala_id` INT NOT NULL,
  `Recurso_id` INT NOT NULL,
  PRIMARY KEY (`Sala_id`, `Recurso_id`),
  INDEX `fk_Sala_has_Recurso_Recurso1_idx` (`Recurso_id` ASC) VISIBLE,
  INDEX `fk_Sala_has_Recurso_Sala1_idx` (`Sala_id` ASC) VISIBLE,
  CONSTRAINT `fk_Sala_has_Recurso_Sala1`
    FOREIGN KEY (`Sala_id`)
    REFERENCES `mydb`.`Sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sala_has_Recurso_Recurso1`
    FOREIGN KEY (`Recurso_id`)
    REFERENCES `mydb`.`Recurso` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
