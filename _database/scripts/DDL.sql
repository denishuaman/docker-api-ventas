CREATE DATABASE IF NOT EXISTS ventas CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ventas` ;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS persona;
DROP TABLE IF EXISTS venta;
DROP TABLE IF EXISTS detalle_venta;

SET foreign_key_checks = 1;

CREATE TABLE producto (
  `id_producto` BIGINT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `marca` VARCHAR(100) NOT NULL,
  `precio` DOUBLE NOT NULL,
  PRIMARY KEY (`id_producto`))
ENGINE = InnoDB;

CREATE TABLE persona (
  `id_persona` BIGINT NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(8) NOT NULL,
  `nombres` VARCHAR(100) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_persona`) ,
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC)  )
ENGINE = InnoDB;

CREATE TABLE venta (
  `id_venta` BIGINT NOT NULL AUTO_INCREMENT,
  `id_persona` BIGINT NOT NULL,
  `importe` DOUBLE NOT NULL,
  `fecha` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id_venta`) ,
  INDEX `fk_venta_persona_idx` (`id_persona` ASC) ,
  CONSTRAINT `fk_venta_persona`
    FOREIGN KEY (`id_persona`)
    REFERENCES `ventas`.`persona` (`id_persona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS detalle_venta (
  `id_detalle_venta` BIGINT NOT NULL AUTO_INCREMENT,
  `id_venta` BIGINT NOT NULL,
  `id_producto` BIGINT NOT NULL,
  `cantidad` INT NULL,
  INDEX `fk_venta_has_producto_producto1_idx` (`id_producto` ASC) ,
  INDEX `fk_venta_has_producto_venta1_idx` (`id_venta` ASC) ,
  PRIMARY KEY (`id_detalle_venta`) ,
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
