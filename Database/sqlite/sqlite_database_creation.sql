CREATE  TABLE IF NOT EXISTS User ( 
					  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , 
					  playerName VARCHAR(60) NOT NULL , 
					  name VARCHAR(60) NOT NULL , 
					  password VARCHAR(256) NOT NULL , 
					  email VARCHAR(250) NOT NULL UNIQUE , 
					  surname VARCHAR(45) NOT NULL UNIQUE,
					  birth VARCHAR(45) NOT NULL , 
					  gender VARCHAR(45) NOT NULL,
					  level INTEGER NOT NULL , 
					  exp INTEGER NOT NULL , 
					  expNextLevel INTEGER NOT NULL );		
		
		
		CREATE  TABLE IF NOT EXISTS Element ( 
					 id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , 
					 name VARCHAR(45) NOT NULL UNIQUE ) ;
		
		CREATE  TABLE IF NOT EXISTS Creature_Base ( 
					  id INTEGER NOT NULL , 
					  name VARCHAR(45) NOT NULL , 
					  Element1_id INTEGER NOT NULL , 
					  Element2_id INTEGER NOT NULL , 
					  strength INTEGER NOT NULL , 
					  defense INTEGER NOT NULL , 
					  speed INTEGER NOT NULL , 
					  feed INTEGER NOT NULL , 
					  maxFeed INTEGER NOT NULL , 
					  starveSpeed INTEGER NOT NULL , 
					  maxLife INTEGER NOT NULL , 
					  CONSTRAINT fk_Creature_Base_Element1 
					    FOREIGN KEY (Element1_id ) 
					    REFERENCES Element (id ), 
					  CONSTRAINT fk_Creature_Base_Element2 
					    FOREIGN KEY (Element2_id ) 
					    REFERENCES Element (id ));
		
		CREATE  TABLE IF NOT EXISTS Creature ( 
					  id INTEGER NOT NULL , 
					  name VARCHAR(45) NOT NULL , 
					  level INTEGER NOT NULL , 
					  exp INTEGER NOT NULL , 
					  expNextLevel INTEGER NOT NULL , 
					  selected TINYINT(1) NOT NULL , 
					  strength INTEGER NOT NULL , 
					  defense INTEGER NOT NULL , 
					  speed INTEGER NOT NULL , 
					  feed INTEGER NOT NULL , 
					  maxFeed INTEGER NOT NULL , 
					  starveSpeed INTEGER NOT NULL , 
					  life INTEGER NOT NULL , 
					  maxLife INTEGER NOT NULL , 
					  happiness INTEGER NOT NULL , 
					  User_id INTEGER NOT NULL , 
					  Creature_Base_id INTEGER NOT NULL , 
					  CONSTRAINT fk_Creature_User 
					    FOREIGN KEY (User_id ) 
					    REFERENCES User (id ), 
					  CONSTRAINT fk_Creature_Creature_Base1 
					    FOREIGN KEY (Creature_Base_id ) 
					    REFERENCES Creature_Base (id ));
		
		CREATE  TABLE IF NOT EXISTS Attack ( 
					  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , 
					  name VARCHAR(45) NOT NULL UNIQUE , 
					  Element_id INTEGER NOT NULL , 
					  strike INTEGER NOT NULL , 
					  heal INTEGER NOT NULL , 
					  CONSTRAINT fk_Attack_Element1 
					    FOREIGN KEY (Element_id ) 
					    REFERENCES Element (id ));
		
		CREATE  TABLE IF NOT EXISTS Creature_Attack (  
					  Attack_id INTEGER NOT NULL , 
					  Creature_id INTEGER NOT NULL , 
					  level INTEGER NOT NULL , 
					  uses INTEGER NOT NULL , 
					  nextLevelUses INTEGER NOT NULL , 
					  CONSTRAINT fk_Attack_has_Creature_Attack1 
					    FOREIGN KEY (Attack_id ) 
					    REFERENCES Attack (id ), 
					  CONSTRAINT fk_Attack_has_Creature_Creature1 
					    FOREIGN KEY (Creature_id ) 
					    REFERENCES Creature (id ));
		
		CREATE  TABLE IF NOT EXISTS State ( 
					  id INTEGER NOT NULL , 
					  name VARCHAR(45) NOT NULL , 
					  abreviation VARCHAR(3) NOT NULL ); 
		
		CREATE  TABLE IF NOT EXISTS Attack_State ( 
					  State_id INTEGER NOT NULL , 
					  Attack_id INTEGER NOT NULL , 
					  PRIMARY KEY (State_id, Attack_id) , 
					 CONSTRAINT fk_State_has_Attack_State1 
					    FOREIGN KEY (State_id ) 
					    REFERENCES State (id ), 
					  CONSTRAINT fk_State_has_Attack_Attack1 
					    FOREIGN KEY (Attack_id ) 
					    REFERENCES Attack (id ));
		
		CREATE  TABLE IF NOT EXISTS Creature_State ( 
					  State_id INTEGER NOT NULL , 
					  Creature_id INTEGER NOT NULL , 
					  PRIMARY KEY (State_id, Creature_id) , 
					  CONSTRAINT fk_State_has_Creature_State1 
					    FOREIGN KEY (State_id ) 
					    REFERENCES State (id ), 
					  CONSTRAINT fk_State_has_Creature_Creature1 
					    FOREIGN KEY (Creature_id ) 
					    REFERENCES Creature (id ));
