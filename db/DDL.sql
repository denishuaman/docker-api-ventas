-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ventas
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ventas` ;

-- -----------------------------------------------------
-- Schema ventas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ventas` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ventas` ;

-- -----------------------------------------------------
-- Table `ventas`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ventas`.`producto` ;

CREATE TABLE IF NOT EXISTS `ventas`.`producto` (
  `id_producto` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `nombre` VARCHAR(100) NOT NULL COMMENT '',
  `marca` VARCHAR(100) NOT NULL COMMENT '',
  `precio` DOUBLE NOT NULL COMMENT '',
  PRIMARY KEY (`id_producto`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ventas`.`persona` ;

CREATE TABLE IF NOT EXISTS `ventas`.`persona` (
  `id_persona` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `nombres` VARCHAR(100) NOT NULL COMMENT '',
  `apellidos` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`id_persona`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas`.`venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ventas`.`venta` ;

CREATE TABLE IF NOT EXISTS `ventas`.`venta` (
  `id_venta` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_persona` BIGINT NOT NULL COMMENT '',
  `importe` DOUBLE NOT NULL COMMENT '',
  `fecha` TIMESTAMP NOT NULL COMMENT '',
  PRIMARY KEY (`id_venta`)  COMMENT '',
  INDEX `fk_venta_persona_idx` (`id_persona` ASC)  COMMENT '',
  CONSTRAINT `fk_venta_persona`
    FOREIGN KEY (`id_persona`)
    REFERENCES `ventas`.`persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ventas`.`detalle_venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `ventas`.`detalle_venta` ;

CREATE TABLE IF NOT EXISTS `ventas`.`detalle_venta` (
  `id_detalle_venta` BIGINT NOT NULL AUTO_INCREMENT COMMENT '',
  `id_venta` BIGINT NOT NULL COMMENT '',
  `id_producto` BIGINT NOT NULL COMMENT '',
  `cantidad` INT NULL COMMENT '',
  INDEX `fk_venta_has_producto_producto1_idx` (`id_producto` ASC)  COMMENT '',
  INDEX `fk_venta_has_producto_venta1_idx` (`id_venta` ASC)  COMMENT '',
  PRIMARY KEY (`id_detalle_venta`)  COMMENT '',
  CONSTRAINT `fk_venta_has_producto_venta1`
    FOREIGN KEY (`id_venta`)
    REFERENCES `ventas`.`venta` (`id_venta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venta_has_producto_producto1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `ventas`.`producto` (`id_producto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
