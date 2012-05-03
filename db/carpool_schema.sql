SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `carpool` ;
CREATE SCHEMA IF NOT EXISTS `carpool` DEFAULT CHARACTER SET utf8 ;
USE `carpool` ;

-- -----------------------------------------------------
-- Table `carpool`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carpool`.`role` ;

CREATE  TABLE IF NOT EXISTS `carpool`.`role` (
  `idRole` INT(11) NOT NULL AUTO_INCREMENT ,
  `roleType` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idRole`) ,
  UNIQUE INDEX `idRole_UNIQUE` (`idRole` ASC) ,
  UNIQUE INDEX `roleType_UNIQUE` (`roleType` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `carpool`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carpool`.`event` ;

CREATE  TABLE IF NOT EXISTS `carpool`.`event` (
  `idEvent` INT(11) NOT NULL ,
  `idMemberGroup` INT(11) NOT NULL ,
  `idRole` INT(11) NOT NULL ,
  `eventDate` DATETIME NOT NULL ,
  PRIMARY KEY (`idEvent`, `idMemberGroup`) ,
  INDEX `fk_idRole` (`idRole` ASC) ,
  CONSTRAINT `fk_idRole`
    FOREIGN KEY (`idRole` )
    REFERENCES `carpool`.`role` (`idRole` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `carpool`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carpool`.`group` ;

CREATE  TABLE IF NOT EXISTS `carpool`.`group` (
  `idGroup` INT(11) NOT NULL AUTO_INCREMENT ,
  `nickname` VARCHAR(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`idGroup`) ,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `carpool`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carpool`.`member` ;

CREATE  TABLE IF NOT EXISTS `carpool`.`member` (
  `idMember` INT(11) NOT NULL AUTO_INCREMENT ,
  `memberfirstname` VARCHAR(45) NOT NULL ,
  `memberlastname` VARCHAR(45) NULL DEFAULT NULL ,
  `memberphonenumber` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`idMember`) ,
  UNIQUE INDEX `memberphonenumber_UNIQUE` (`memberphonenumber` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COMMENT = 'Save member details';


-- -----------------------------------------------------
-- Table `carpool`.`membergroups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carpool`.`membergroups` ;

CREATE  TABLE IF NOT EXISTS `carpool`.`membergroups` (
  `idMemberGroup` INT(11) NOT NULL AUTO_INCREMENT ,
  `idGroup` INT(11) NOT NULL ,
  `idMember` INT(11) NOT NULL ,
  PRIMARY KEY (`idMemberGroup`) ,
  INDEX `fk_group` (`idGroup` ASC) ,
  INDEX `fk_member` (`idMember` ASC) ,
  INDEX `fk_idMember` (`idMember` ASC) ,
  INDEX `fk_idGroup` (`idGroup` ASC) ,
  CONSTRAINT `fk_idGroup`
    FOREIGN KEY (`idGroup` )
    REFERENCES `carpool`.`group` (`idGroup` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_idMember`
    FOREIGN KEY (`idMember` )
    REFERENCES `carpool`.`member` (`idMember` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `carpool`.`membertokens`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carpool`.`membertokens` ;

CREATE  TABLE IF NOT EXISTS `carpool`.`membertokens` (
  `idMemberGroup` INT(11) NOT NULL ,
  `totalTokens` INT(11) NULL DEFAULT '0' ,
  PRIMARY KEY (`idMemberGroup`) ,
  INDEX `fk_idMemberGroup` (`idMemberGroup` ASC) ,
  CONSTRAINT `fk_idMemberGroup`
    FOREIGN KEY (`idMemberGroup` )
    REFERENCES `carpool`.`membergroups` (`idMemberGroup` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `carpool`.`tokens`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `carpool`.`tokens` ;

CREATE  TABLE IF NOT EXISTS `carpool`.`tokens` (
  `idToken` INT(11) NOT NULL AUTO_INCREMENT ,
  `idEvent` INT(11) NULL DEFAULT NULL ,
  `idMemberGroup` INT(11) NULL DEFAULT NULL ,
  `earnedToken` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`idToken`) ,
  UNIQUE INDEX `idToken_UNIQUE` (`idToken` ASC) ,
  INDEX `fk_idEvent` (`idEvent` ASC) ,
  CONSTRAINT `fk_idEvent`
    FOREIGN KEY (`idEvent` )
    REFERENCES `carpool`.`event` (`idEvent` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
