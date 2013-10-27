SET FOREIGN_KEY_CHECKS=0;

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User` ;

CREATE  TABLE IF NOT EXISTS `User` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `playerName` VARCHAR(60) NOT NULL ,
  `name` VARCHAR(60) NOT NULL ,
  `password` VARCHAR(256) NOT NULL ,
  `email` VARCHAR(250) NOT NULL ,
  `surname` VARCHAR(45) NOT NULL ,
  `birth` VARCHAR(45) NOT NULL ,
  `gender` VARCHAR(45) NOT NULL ,
  `level` INT(11) NOT NULL ,
  `exp` INT(11) NOT NULL ,
  `expNextLevel` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  UNIQUE INDEX `playerName_UNIQUE` (`playerName` ASC) );


-- -----------------------------------------------------
-- Table `Element`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Element` ;

CREATE  TABLE IF NOT EXISTS `Element` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) );


-- -----------------------------------------------------
-- Table `Creature_Base`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Creature_Base` ;

CREATE  TABLE IF NOT EXISTS `Creature_Base` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL ,
  `Element1_id` INT(11) NOT NULL ,
  `Element2_id` INT(11) NOT NULL ,
  `strength` INT(11) NOT NULL ,
  `defense` INT(11) NOT NULL ,
  `speed` INT(11) NOT NULL ,
  `feed` INT(11) NOT NULL ,
  `maxFeed` INT(11) NOT NULL ,
  `starveSpeed` INT(11) NOT NULL ,
  `maxLife` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Creature_Base_Element1` (`Element1_id` ASC) ,
  INDEX `fk_Creature_Base_Element2` (`Element2_id` ASC) ,
  CONSTRAINT `fk_Creature_Base_Element1`
    FOREIGN KEY (`Element1_id` )
    REFERENCES `Element` (`id` ),
  CONSTRAINT `fk_Creature_Base_Element2`
    FOREIGN KEY (`Element2_id` )
    REFERENCES `Element` (`id` ));


-- -----------------------------------------------------
-- Table `Creature`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Creature` ;

CREATE  TABLE IF NOT EXISTS `Creature` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL ,
  `level` INT(11) NOT NULL ,
  `exp` INT(11) NOT NULL ,
  `expNextLevel` INT(11) NOT NULL ,
  `selected` TINYINT(1) NOT NULL ,
  `strength` INT(11) NOT NULL ,
  `defense` INT(11) NOT NULL ,
  `speed` INT(11) NOT NULL ,
  `feed` INT(11) NOT NULL ,
  `maxFeed` INT(11) NOT NULL ,
  `starveSpeed` INT(11) NOT NULL ,
  `life` INT(11) NOT NULL ,
  `maxLife` INT(11) NOT NULL ,
  `happiness` INT(11) NOT NULL ,
  `User_id` INT NOT NULL ,
  `Creature_Base_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Creature_User` (`User_id` ASC) ,
  INDEX `fk_Creature_Creature_Base1` (`Creature_Base_id` ASC) ,
  CONSTRAINT `fk_Creature_User`
    FOREIGN KEY (`User_id` )
    REFERENCES `User` (`id` ),
  CONSTRAINT `fk_Creature_Creature_Base1`
    FOREIGN KEY (`Creature_Base_id` )
    REFERENCES `Creature_Base` (`id` ));


-- -----------------------------------------------------
-- Table `Atack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Atack` ;

CREATE  TABLE IF NOT EXISTS `Atack` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `Element_id` INT(11) NOT NULL ,
  `strike` INT(11) NOT NULL ,
  `heal` INT(11) NOT NULL ,
  `minimumLevel` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_Atack_Element1` (`Element_id` ASC) ,
  CONSTRAINT `fk_Atack_Element1`
    FOREIGN KEY (`Element_id` )
    REFERENCES `Element` (`id` ));


-- -----------------------------------------------------
-- Table `Creature_Atack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Creature_Atack` ;

CREATE  TABLE IF NOT EXISTS `Creature_Atack` (
  `Atack_id` INT(11) NOT NULL ,
  `Creature_id` INT(11) NOT NULL ,
  `level` INT(11) NOT NULL ,
  `uses` INT(11) NOT NULL ,
  `nextLevelUses` INT(11) NOT NULL ,
  PRIMARY KEY (`Atack_id`, `Creature_id`) ,
  INDEX `fk_Atack_has_Creature_Creature1` (`Creature_id` ASC) ,
  INDEX `fk_Atack_has_Creature_Atack1` (`Atack_id` ASC) ,
  CONSTRAINT `fk_Atack_has_Creature_Atack1`
    FOREIGN KEY (`Atack_id` )
    REFERENCES `Atack` (`id` ),
  CONSTRAINT `fk_Atack_has_Creature_Creature1`
    FOREIGN KEY (`Creature_id` )
    REFERENCES `Creature` (`id` ));


-- -----------------------------------------------------
-- Table `State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `State` ;

CREATE  TABLE IF NOT EXISTS `State` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL ,
  `abreviation` VARCHAR(3) NOT NULL ,
  PRIMARY KEY (`id`) );


-- -----------------------------------------------------
-- Table `Atack_State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Atack_State` ;

CREATE  TABLE IF NOT EXISTS `Atack_State` (
  `State_id` INT NOT NULL ,
  `Atack_id` INT(11) NOT NULL ,
  PRIMARY KEY (`State_id`, `Atack_id`) ,
  INDEX `fk_State_has_Atack_Atack1` (`Atack_id` ASC) ,
  INDEX `fk_State_has_Atack_State1` (`State_id` ASC) ,
  CONSTRAINT `fk_State_has_Atack_State1`
    FOREIGN KEY (`State_id` )
    REFERENCES `State` (`id` ),
  CONSTRAINT `fk_State_has_Atack_Atack1`
    FOREIGN KEY (`Atack_id` )
    REFERENCES `Atack` (`id` ));


-- -----------------------------------------------------
-- Table `Creature_State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Creature_State` ;

CREATE  TABLE IF NOT EXISTS `Creature_State` (
  `State_id` INT NOT NULL ,
  `Creature_id` INT NOT NULL ,
  PRIMARY KEY (`State_id`, `Creature_id`) ,
  INDEX `fk_State_has_Creature_Creature1` (`Creature_id` ASC) ,
  INDEX `fk_State_has_Creature_State1` (`State_id` ASC) ,
  CONSTRAINT `fk_State_has_Creature_State1`
    FOREIGN KEY (`State_id` )
    REFERENCES `State` (`id` ),
  CONSTRAINT `fk_State_has_Creature_Creature1`
    FOREIGN KEY (`Creature_id` )
    REFERENCES `Creature` (`id` ));

SET FOREIGN_KEY_CHECKS=1;
