
CREATE TABLE User(
  id INTEGER PRIMARY KEY  NOT NULL,
  playerName VARCHAR(60) NOT NULL,
  name VARCHAR(60) NOT NULL,
  password VARCHAR(256) NOT NULL,
  email VARCHAR(250) NOT NULL,
  surname VARCHAR(45) NOT NULL,
  birth VARCHAR(45) NOT NULL,
  gender VARCHAR(45) NOT NULL,
  level INTEGER NOT NULL,
  exp INTEGER NOT NULL,
  expNextLevel INTEGER NOT NULL,
  gold INTEGER NOT NULL,
  fighting BOOLEAN NOT NULL,
  CONSTRAINT email_UNIQUE
    UNIQUE(email),
  CONSTRAINT playerName_UNIQUE
    UNIQUE(playerName)
);
CREATE TABLE Element(
  id INTEGER PRIMARY KEY  NOT NULL,
  name VARCHAR(45) NOT NULL,
  CONSTRAINT name_UNIQUE
    UNIQUE(name)
);
CREATE TABLE Dandremid_Base(
  id INTEGER PRIMARY KEY  NOT NULL,
  name VARCHAR(45) NOT NULL,
  Element1_id INTEGER NOT NULL,
  Element2_id INTEGER NOT NULL,
  strength INTEGER NOT NULL,
  defense INTEGER NOT NULL,
  speed INTEGER NOT NULL,
  maxFeed INTEGER NOT NULL,
  maxLife INTEGER NOT NULL,
  description VARCHAR(250) NOT NULL,
  CONSTRAINT fk_Dandremid_Base_Element1
    FOREIGN KEY(Element1_id)
    REFERENCES Element(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Dandremid_Base_Element2
    FOREIGN KEY(Element2_id)
    REFERENCES Element(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Dandremid(
  id INTEGER PRIMARY KEY  NOT NULL,
  name VARCHAR(45) NOT NULL,
  level INTEGER NOT NULL,
  exp INTEGER NOT NULL,
  expNextLevel INTEGER NOT NULL,
  selected INTEGER NOT NULL,
  strength INTEGER NOT NULL,
  defense INTEGER NOT NULL,
  speed INTEGER NOT NULL,
  feed INTEGER NOT NULL,
  maxFeed INTEGER NOT NULL,
  life INTEGER NOT NULL,
  maxLife INTEGER NOT NULL,
  happiness INTEGER NOT NULL,
  Dandremid_Base_id INTEGER NOT NULL,
  User_id INTEGER NOT NULL,
  CONSTRAINT fk_Dandremid_User
    FOREIGN KEY(User_id)
    REFERENCES User(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Dandremid_Dandremid_Base1
    FOREIGN KEY(Dandremid_Base_id)
    REFERENCES Dandremid_Base(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Attack(
  id INTEGER PRIMARY KEY  NOT NULL,
  name VARCHAR(45) NOT NULL,
  Element_id INTEGER NOT NULL,
  strike INTEGER NOT NULL,
  heal INTEGER NOT NULL,
  minimumLevel INTEGER NOT NULL,
  CONSTRAINT name_UNIQUE
    UNIQUE(name),
  CONSTRAINT fk_Attack_Element1
    FOREIGN KEY(Element_id)
    REFERENCES Element(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Dandremid_Attack(
  Attack_id INTEGER NOT NULL,
  Dandremid_id INTEGER NOT NULL,
  level INTEGER NOT NULL,
  uses INTEGER NOT NULL,
  nextLevelUses INTEGER NOT NULL,
  PRIMARY KEY(Attack_id,Dandremid_id),
  CONSTRAINT fk_Attack_has_Dandremid_Attack1
    FOREIGN KEY(Attack_id)
    REFERENCES Attack(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Attack_has_Dandremid_Dandremid1
    FOREIGN KEY(Dandremid_id)
    REFERENCES Dandremid(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE State(
  id INTEGER PRIMARY KEY  NOT NULL,
  name VARCHAR(45) NOT NULL,
  abreviation VARCHAR(3) NOT NULL
);
CREATE TABLE Attack_State(
  State_id INTEGER NOT NULL,
  Attack_id INTEGER NOT NULL,
  PRIMARY KEY(State_id,Attack_id),
  CONSTRAINT fk_State_has_Attack_State1
    FOREIGN KEY(State_id)
    REFERENCES State(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_State_has_Attack_Attack1
    FOREIGN KEY(Attack_id)
    REFERENCES Attack(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Dandremid_State(
  State_id INTEGER NOT NULL,
  Dandremid_id INTEGER NOT NULL,
  PRIMARY KEY(State_id,Dandremid_id),
  CONSTRAINT fk_State_has_Dandremid_State1
    FOREIGN KEY(State_id)
    REFERENCES State(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_State_has_Dandremid_Dandremid1
    FOREIGN KEY(Dandremid_id)
    REFERENCES Dandremid(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Element_Element(
  Element_id_1 INTEGER NOT NULL,
  Element_id_2 INTEGER NOT NULL,
  power DOUBLE NOT NULL,
  PRIMARY KEY(Element_id_1,Element_id_2),
  CONSTRAINT fk_Element_Element_Element1
    FOREIGN KEY(Element_id_1)
    REFERENCES Element(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Element_Element_Element2
    FOREIGN KEY(Element_id_2)
    REFERENCES Element(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Object(
  id INTEGER PRIMARY KEY  NOT NULL,
  name VARCHAR(45) NOT NULL,
  strength INTEGER NOT NULL,
  defense INTEGER NOT NULL,
  speed INTEGER NOT NULL,
  life INTEGER NOT NULL,
  feed INTEGER NOT NULL,
  happiness INTEGER NOT NULL,
  trap BOOLEAN NOT NULL,
  type VARCHAR(45) NOT NULL,
  price INTEGER NOT NULL
);

CREATE TABLE User_Object(
  User_id INTEGER NOT NULL,
  Object_id INTEGER NOT NULL,
  quantity VARCHAR(45) NOT NULL,
  PRIMARY KEY(User_id,Object_id),
  CONSTRAINT fk_User_has_Object_User1
    FOREIGN KEY(User_id)
    REFERENCES User(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_User_has_Object_Object1
    FOREIGN KEY(Object_id)
    REFERENCES Object(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE Object_State(
  Object_id INTEGER NOT NULL,
  State_id INTEGER NOT NULL,
  PRIMARY KEY(Object_id,State_id),
  CONSTRAINT fk_Object_has_State_Object1
    FOREIGN KEY(Object_id)
    REFERENCES Object(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Object_has_State_State1
    FOREIGN KEY(State_id)
    REFERENCES State(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE League(
  id INTEGER PRIMARY KEY  NOT NULL,
  name VARCHAR(45) NOT NULL,
  rounds INTEGER NOT NULL,
  status VARCHAR(45) NOT NULL,
  code VARCHAR(256) NOT NULL
);

CREATE TABLE User_League(
  User_id INTEGER NOT NULL,
  League_id INTEGER NOT NULL,
  points INTEGER NOT NULL,
  PRIMARY KEY(User_id,League_id),
  CONSTRAINT fk_User_has_League_User1
    FOREIGN KEY(User_id)
    REFERENCES User(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_User_has_League_League1
    FOREIGN KEY(League_id)
    REFERENCES League(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE Combat(
  League_id INTEGER NOT NULL,
  id INTEGER NOT NULL,
  User_1_id INTEGER NOT NULL,
  User_2_id INTEGER NOT NULL,
  active BOOLEAN NOT NULL,
  PRIMARY KEY(League_id,id),
  CONSTRAINT fk_Combat_User_has_League1
    FOREIGN KEY(League_id)
    REFERENCES User_League(League_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Combat_User_has_League2
    FOREIGN KEY(User_1_id)
    REFERENCES User_League(User_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Combat_User_has_League3
    FOREIGN KEY(User_2_id)
    REFERENCES User_League(User_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


CREATE TABLE Turn(
  League_id INTEGER NOT NULL,
  Combat_id INTEGER NOT NULL,
  id INTEGER NOT NULL,
  User_id INTEGER NOT NULL,
  action VARCHAR(45) NOT NULL,
  value VARCHAR(45) NOT NULL,
  date DATE NOT NULL,
  PRIMARY KEY(id,League_id,Combat_id),
  CONSTRAINT fk_Turn_Combat1
    FOREIGN KEY(League_id,Combat_id)
    REFERENCES Combat(League_id,id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Turn_Combat2
    FOREIGN KEY(User_id)
    REFERENCES Combat(User_1_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Turn_Combat3
    FOREIGN KEY(User_id)
    REFERENCES Combat(User_2_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

