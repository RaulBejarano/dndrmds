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
-- Table `Dandremid_Base`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dandremid_Base` ;

CREATE  TABLE IF NOT EXISTS `Dandremid_Base` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `Element1_id` INT(11) NOT NULL ,
  `Element2_id` INT(11) NOT NULL ,
  `strength` INT(11) NOT NULL ,
  `defense` INT(11) NOT NULL ,
  `speed` INT(11) NOT NULL ,
  `maxFeed` INT(11) NOT NULL ,
  `maxLife` INT(11) NOT NULL ,
  `description` VARCHAR(250) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Dandremid_Base_Element1` (`Element1_id` ASC) ,
  INDEX `fk_Dandremid_Base_Element2` (`Element2_id` ASC) ,
  CONSTRAINT `fk_Dandremid_Base_Element1`
    FOREIGN KEY (`Element1_id` )
    REFERENCES `Element` (`id` ),
  CONSTRAINT `fk_Dandremid_Base_Element2`
    FOREIGN KEY (`Element2_id` )
    REFERENCES `Element` (`id` ));


-- -----------------------------------------------------
-- Table `Dandremid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dandremid` ;

CREATE  TABLE IF NOT EXISTS `Dandremid` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `level` INT(11) NOT NULL ,
  `exp` INT(11) NOT NULL ,
  `expNextLevel` INT(11) NOT NULL ,
  `selected` INT(11) NOT NULL ,
  `strength` INT(11) NOT NULL ,
  `defense` INT(11) NOT NULL ,
  `speed` INT(11) NOT NULL ,
  `feed` INT(11) NOT NULL ,
  `maxFeed` INT(11) NOT NULL ,
  `life` INT(11) NOT NULL ,
  `maxLife` INT(11) NOT NULL ,
  `happiness` INT(11) NOT NULL ,
  `Dandremid_Base_id` INT NOT NULL ,
  `User_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Dandremid_User` (`User_id` ASC) ,
  INDEX `fk_Dandremid_Dandremid_Base1` (`Dandremid_Base_id` ASC) ,
  CONSTRAINT `fk_Dandremid_User`
    FOREIGN KEY (`User_id` )
    REFERENCES `User` (`id` ),
  CONSTRAINT `fk_Dandremid_Dandremid_Base1`
    FOREIGN KEY (`Dandremid_Base_id` )
    REFERENCES `Dandremid_Base` (`id` ));


-- -----------------------------------------------------
-- Table `Attack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Attack` ;

CREATE  TABLE IF NOT EXISTS `Attack` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `Element_id` INT(11) NOT NULL ,
  `strike` INT(11) NOT NULL ,
  `heal` INT(11) NOT NULL ,
  `minimumLevel` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,
  INDEX `fk_Attack_Element1` (`Element_id` ASC) ,
  CONSTRAINT `fk_Attack_Element1`
    FOREIGN KEY (`Element_id` )
    REFERENCES `Element` (`id` ));


-- -----------------------------------------------------
-- Table `Dandremid_Attack`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Dandremid_Attack` ;

CREATE  TABLE IF NOT EXISTS `Dandremid_Attack` (
  `Attack_id` INT(11) NOT NULL ,
  `Dandremid_id` INT(11) NOT NULL ,
  `level` INT(11) NOT NULL ,
  `uses` INT(11) NOT NULL ,
  `nextLevelUses` INT(11) NOT NULL ,
  PRIMARY KEY (`Attack_id`, `Dandremid_id`) ,
  INDEX `fk_Attack_has_Dandremid_Dandremid1` (`Dandremid_id` ASC) ,
  INDEX `fk_Attack_has_Creature_Attack1` (`Attack_id` ASC) ,
  CONSTRAINT `fk_Attack_has_Dandremid_Attack1`
    FOREIGN KEY (`Attack_id` )
    REFERENCES `Attack` (`id` ),
  CONSTRAINT `fk_Attack_has_Dandremid_Dandremid1`
    FOREIGN KEY (`Dandremid_id` )
    REFERENCES `Dandremid` (`id` ));


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
  `Attack_id` INT(11) NOT NULL ,
  PRIMARY KEY (`State_id`, `Attack_id`) ,
  INDEX `fk_State_has_Attack_Attack1` (`Attack_id` ASC) ,
  INDEX `fk_State_has_Attack_State1` (`State_id` ASC) ,
  CONSTRAINT `fk_State_has_Attack_State1`
    FOREIGN KEY (`State_id` )
    REFERENCES `State` (`id` ),
  CONSTRAINT `fk_State_has_Attack_Attack1`
    FOREIGN KEY (`Attack_id` )
    REFERENCES `Attack` (`id` ));


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
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_State_has_Dandremid_Dandremid1`
    FOREIGN KEY (`Dandremid_id` )
    REFERENCES `Dandremid` (`id` ));


-- -----------------------------------------------------
-- Table `Element_Element`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Element_Element` ;

CREATE  TABLE IF NOT EXISTS `Element_Element` (
  `Element_id_1` INT(11) NOT NULL ,
  `Element_id_2` INT(11) NOT NULL ,
  `power` INT NOT NULL ,
  PRIMARY KEY (`Element_id_1`, `Element_id_2`) ,
  INDEX `fk_Element_Element_Element2` (`Element_id_2` ASC) ,
  CONSTRAINT `fk_Element_Element_Element1`
    FOREIGN KEY (`Element_id_1` )
    REFERENCES `Element` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Element_Element_Element2`
    FOREIGN KEY (`Element_id_2` )
    REFERENCES `Element` (`id` );


-- -----------------------------------------------------
-- Table `CombatObject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CombatObject` ;

CREATE  TABLE IF NOT EXISTS `CombatObject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL ,
  `strength` INT NOT NULL ,
  `defense` INT NOT NULL ,
  `speed` INT NOT NULL ,
  `life` INT NOT NULL ,
  `trap` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) );


-- -----------------------------------------------------
-- Table `CombatObject_User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CombatObject_User` ;

CREATE  TABLE IF NOT EXISTS `CombatObject_User` (
  `CombatObject_id` INT NOT NULL ,
  `User_id` INT NOT NULL ,
  `quantity` INT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`CombatObject_id`, `User_id`) ,
  INDEX `fk_CombatObject_has_User_User1` (`User_id` ASC) ,
  INDEX `fk_CombatObject_has_User_CombatObject1` (`CombatObject_id` ASC) ,
  CONSTRAINT `fk_CombatObject_has_User_CombatObject1`
    FOREIGN KEY (`CombatObject_id` )
    REFERENCES `CombatObject` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CombatObject_has_User_User1`
    FOREIGN KEY (`User_id` )
    REFERENCES `User` (`id` );


-- -----------------------------------------------------
-- Table `CareObject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CareObject` ;

CREATE  TABLE IF NOT EXISTS `CareObject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` INT NOT NULL ,
  `life` INT NOT NULL ,
  `feed` INT NOT NULL ,
  `happiness` INT NOT NULL ,
  PRIMARY KEY (`id`) );


-- -----------------------------------------------------
-- Table `CareObject_User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `CareObject_User` ;

CREATE  TABLE IF NOT EXISTS `CareObject_User` (
  `CareObject_id` INT NOT NULL ,
  `User_id` INT NOT NULL ,
  `quantity` INT NOT NULL ,
  PRIMARY KEY (`CareObject_id`, `User_id`) ,
  INDEX `fk_CareObject_has_User_User1` (`User_id` ASC) ,
  INDEX `fk_CareObject_has_User_CareObject1` (`CareObject_id` ASC) ,
  CONSTRAINT `fk_CareObject_has_User_CareObject1`
    FOREIGN KEY (`CareObject_id` )
    REFERENCES `CareObject` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CareObject_has_User_User1`
    FOREIGN KEY (`User_id` )
    REFERENCES `User` (`id` );

SET FOREIGN_KEY_CHECKS=1;

