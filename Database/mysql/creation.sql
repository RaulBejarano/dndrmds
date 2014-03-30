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
  `level` INT NOT NULL ,
  `exp` INT NOT NULL ,
  `expNextLevel` INT NOT NULL ,
  `gold` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  UNIQUE INDEX `playerName_UNIQUE` (`playerName` ASC) );


-- -----------------------------------------------------
-- Table `Element`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Element` ;

CREATE  TABLE IF NOT EXISTS `Element` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) );


-- -----------------------------------------------------
-- Table `Dandremid_Base`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dandremid_Base` ;

CREATE  TABLE IF NOT EXISTS `Dandremid_Base` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `Element1_id` INT NOT NULL ,
  `Element2_id` INT NOT NULL ,
  `strength` INT NOT NULL ,
  `defense` INT NOT NULL ,
  `speed` INT NOT NULL ,
  `maxFeed` INT NOT NULL ,
  `maxLife` INT NOT NULL ,
  `description` VARCHAR(250) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Dandremid_Base_Element1` (`Element1_id` ASC) ,
  INDEX `fk_Dandremid_Base_Element2` (`Element2_id` ASC) ,
  CONSTRAINT `fk_Dandremid_Base_Element1`
    FOREIGN KEY (`Element1_id` )
    REFERENCES `Element` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Dandremid_Base_Element2`
    FOREIGN KEY (`Element2_id` )
    REFERENCES `Element` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `Dandremid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dandremid` ;

CREATE  TABLE IF NOT EXISTS `Dandremid` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `level` INT NOT NULL ,
  `exp` INT NOT NULL ,
  `expNextLevel` INT NOT NULL ,
  `selected` INT NOT NULL ,
  `strength` INT NOT NULL ,
  `defense` INT NOT NULL ,
  `speed` INT NOT NULL ,
  `feed` INT NOT NULL ,
  `maxFeed` INT NOT NULL ,
  `life` INT NOT NULL ,
  `maxLife` INT NOT NULL ,
  `happiness` INT NOT NULL ,
  `Dandremid_Base_id` INT NOT NULL ,
  `User_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Dandremid_User` (`User_id` ASC) ,
  INDEX `fk_Dandremid_Dandremid_Base1` (`Dandremid_Base_id` ASC) ,
  CONSTRAINT `fk_Dandremid_User`
    FOREIGN KEY (`User_id` )
    REFERENCES `User` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Dandremid_Dandremid_Base1`
    FOREIGN KEY (`Dandremid_Base_id` )
    REFERENCES `Dandremid_Base` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `Attack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Attack` ;

CREATE  TABLE IF NOT EXISTS `Attack` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `Element_id` INT NOT NULL ,
  `strike` INT NOT NULL ,
  `heal` INT NOT NULL ,
  `minimumLevel` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_Attack_Element1` (`Element_id` ASC) ,
  CONSTRAINT `fk_Attack_Element1`
    FOREIGN KEY (`Element_id` )
    REFERENCES `Element` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `Dandremid_Attack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dandremid_Attack` ;

CREATE  TABLE IF NOT EXISTS `Dandremid_Attack` (
  `Attack_id` INT NOT NULL ,
  `Dandremid_id` INT NOT NULL ,
  `level` INT NOT NULL ,
  `uses` INT NOT NULL ,
  `nextLevelUses` INT NOT NULL ,
  PRIMARY KEY (`Attack_id`, `Dandremid_id`) ,
  INDEX `fk_Attack_has_Dandremid_Dandremid1` (`Dandremid_id` ASC) ,
  INDEX `fk_Attack_has_Creature_Attack1` (`Attack_id` ASC) ,
  CONSTRAINT `fk_Attack_has_Dandremid_Attack1`
    FOREIGN KEY (`Attack_id` )
    REFERENCES `Attack` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Attack_has_Dandremid_Dandremid1`
    FOREIGN KEY (`Dandremid_id` )
    REFERENCES `Dandremid` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `State` ;

CREATE  TABLE IF NOT EXISTS `State` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `abreviation` VARCHAR(3) NOT NULL ,
  PRIMARY KEY (`id`) );


-- -----------------------------------------------------
-- Table `Attack_State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Attack_State` ;

CREATE  TABLE IF NOT EXISTS `Attack_State` (
  `State_id` INT NOT NULL ,
  `Attack_id` INT NOT NULL ,
  PRIMARY KEY (`State_id`, `Attack_id`) ,
  INDEX `fk_State_has_Attack_Attack1` (`Attack_id` ASC) ,
  INDEX `fk_State_has_Attack_State1` (`State_id` ASC) ,
  CONSTRAINT `fk_State_has_Attack_State1`
    FOREIGN KEY (`State_id` )
    REFERENCES `State` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_State_has_Attack_Attack1`
    FOREIGN KEY (`Attack_id` )
    REFERENCES `Attack` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `Dandremid_State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dandremid_State` ;

CREATE  TABLE IF NOT EXISTS `Dandremid_State` (
  `State_id` INT NOT NULL ,
  `Dandremid_id` INT NOT NULL ,
  PRIMARY KEY (`State_id`, `Dandremid_id`) ,
  INDEX `fk_State_has_Dandremid_Dandremid1` (`Dandremid_id` ASC) ,
  INDEX `fk_State_has_Creature_State1` (`State_id` ASC) ,
  CONSTRAINT `fk_State_has_Dandremid_State1`
    FOREIGN KEY (`State_id` )
    REFERENCES `State` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_State_has_Dandremid_Dandremid1`
    FOREIGN KEY (`Dandremid_id` )
    REFERENCES `Dandremid` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `Element_Element`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Element_Element` ;

CREATE  TABLE IF NOT EXISTS `Element_Element` (
  `Element_id_1` INT NOT NULL ,
  `Element_id_2` INT NOT NULL ,
  `power` DOUBLE NOT NULL ,
  PRIMARY KEY (`Element_id_1`, `Element_id_2`) ,
  INDEX `fk_Element_Element_Element2` (`Element_id_2` ASC) ,
  CONSTRAINT `fk_Element_Element_Element1`
    FOREIGN KEY (`Element_id_1` )
    REFERENCES `Element` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Element_Element_Element2`
    FOREIGN KEY (`Element_id_2` )
    REFERENCES `Element` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Object`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Object` ;

CREATE  TABLE IF NOT EXISTS `Object` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `strength` INT NOT NULL ,
  `defense` INT NOT NULL ,
  `speed` INT NOT NULL ,
  `life` INT NOT NULL ,
  `feed` INT NOT NULL ,
  `happiness` INT NOT NULL ,
  `trap` TINYINT(1) NOT NULL ,
  `type` VARCHAR(45) NOT NULL ,
  `price` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `User_Object`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User_Object` ;

CREATE  TABLE IF NOT EXISTS `User_Object` (
  `User_id` INT NOT NULL ,
  `Object_id` INT NOT NULL ,
  `quantity` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`User_id`, `Object_id`) ,
  INDEX `fk_User_has_Object_Object1` (`Object_id` ASC) ,
  INDEX `fk_User_has_Object_User1` (`User_id` ASC) ,
  CONSTRAINT `fk_User_has_Object_User1`
    FOREIGN KEY (`User_id` )
    REFERENCES `User` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_User_has_Object_Object1`
    FOREIGN KEY (`Object_id` )
    REFERENCES `Object` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `Object_State`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Object_State` ;

CREATE  TABLE IF NOT EXISTS `Object_State` (
  `Object_id` INT NOT NULL ,
  `State_id` INT NOT NULL ,
  PRIMARY KEY (`Object_id`, `State_id`) ,
  INDEX `fk_Object_has_State_State1` (`State_id` ASC) ,
  INDEX `fk_Object_has_State_Object1` (`Object_id` ASC) ,
  CONSTRAINT `fk_Object_has_State_Object1`
    FOREIGN KEY (`Object_id` )
    REFERENCES `Object` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Object_has_State_State1`
    FOREIGN KEY (`State_id` )
    REFERENCES `State` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `League`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `League` ;

CREATE  TABLE IF NOT EXISTS `League` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `rounds` INT NOT NULL ,
  `tag` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `User_League`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User_League` ;

CREATE  TABLE IF NOT EXISTS `User_League` (
  `User_id` INT NOT NULL ,
  `League_id` INT NOT NULL ,
  `points` INT NOT NULL ,
  PRIMARY KEY (`User_id`, `League_id`) ,
  INDEX `fk_User_has_League_League1` (`League_id` ASC) ,
  INDEX `fk_User_has_League_User1` (`User_id` ASC) ,
  CONSTRAINT `fk_User_League_User1`
    FOREIGN KEY (`User_id` )
    REFERENCES `User` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_User_League_League1`
    FOREIGN KEY (`League_id` )
    REFERENCES `League` (`id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `Combat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Combat` ;

CREATE  TABLE IF NOT EXISTS `Combat` (
  `League_id` INT NOT NULL ,
  `id` INT NOT NULL ,
  `User_1_id` INT NOT NULL ,
  `User_2_id` INT NOT NULL ,
  PRIMARY KEY (`League_id`, `id`) ,
  INDEX `fk_Combat_User_has_League1` (`League_id` ASC) ,
  INDEX `fk_Combat_User_has_League2` (`User_1_id` ASC) ,
  INDEX `fk_Combat_User_has_League3` (`User_2_id` ASC) ,
  CONSTRAINT `fk_Combat_User_has_League1`
    FOREIGN KEY (`League_id` )
    REFERENCES `User_League` (`League_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Combat_User_has_League2`
    FOREIGN KEY (`User_1_id` )
    REFERENCES `User_League` (`User_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Combat_User_has_League3`
    FOREIGN KEY (`User_2_id` )
    REFERENCES `User_League` (`User_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Turn`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Turn` ;

CREATE  TABLE IF NOT EXISTS `Turn` (
  `League_id` INT NOT NULL ,
  `Combat_id` INT NOT NULL ,
  `id` INT NOT NULL AUTO_INCREMENT ,
  `User_id` INT NOT NULL ,
  `action` VARCHAR(45) NOT NULL ,
  `value` VARCHAR(45) NOT NULL ,
  `date` DATE NOT NULL ,
  PRIMARY KEY (`id`, `League_id`, `Combat_id`) ,
  INDEX `fk_Turn_Combat2` (`User_id` ASC) ,
  CONSTRAINT `fk_Turn_Combat1`
    FOREIGN KEY (`League_id` , `Combat_id` )
    REFERENCES `Combat` (`League_id` , `id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Turn_Combat2`
    FOREIGN KEY (`User_id` )
    REFERENCES `Combat` (`User_1_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Turn_Combat3`
    FOREIGN KEY (`User_id` )
    REFERENCES `Combat` (`User_2_id` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS=1;
