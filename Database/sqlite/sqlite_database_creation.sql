		CREATE  TABLE IF NOT EXISTS User ( 
					  id INTEGER NOT NULL PRIMARY KEY, 
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
					 id INTEGER NOT NULL PRIMARY KEY, 
					 name VARCHAR(45) NOT NULL UNIQUE ) ;
		
		CREATE  TABLE IF NOT EXISTS Dandremid_Base ( 
					  id INTEGER NOT NULL , 
					  name VARCHAR(45) NOT NULL , 
					  Element1_id INTEGER NOT NULL , 
					  Element2_id INTEGER NOT NULL , 
					  strength INTEGER NOT NULL , 
					  defense INTEGER NOT NULL , 
					  speed INTEGER NOT NULL ,
					  maxFeed INTEGER NOT NULL ,
					  maxLife INTEGER NOT NULL , 
					  description VARCHAR(250) NOT NULL ,
					  CONSTRAINT fk_Dandremid_Base_Element1 
					    FOREIGN KEY (Element1_id ) 
					    REFERENCES Element (id ), 
					  CONSTRAINT fk_Dandremid_Base_Element2 
					    FOREIGN KEY (Element2_id ) 
					    REFERENCES Element (id ));
		
		CREATE  TABLE IF NOT EXISTS Dandremid ( 
					  id INTEGER NOT NULL , 
					  name VARCHAR(45) NOT NULL , 
					  level INTEGER NOT NULL , 
					  exp INTEGER NOT NULL , 
					  expNextLevel INTEGER NOT NULL , 
					  selected INTEGER NOT NULL , 
					  strength INTEGER NOT NULL , 
					  defense INTEGER NOT NULL , 
					  speed INTEGER NOT NULL , 
					  feed INTEGER NOT NULL , 
					  maxFeed INTEGER NOT NULL , 
					  life INTEGER NOT NULL , 
					  maxLife INTEGER NOT NULL , 
					  happiness INTEGER NOT NULL , 
					  User_id INTEGER NOT NULL , 
					  Dandremid_Base_id INTEGER NOT NULL , 
					  CONSTRAINT fk_Dandremid_User 
					    FOREIGN KEY (User_id ) 
					    REFERENCES User (id ), 
					  CONSTRAINT fk_Dandremid_Dandremid_Base1 
					    FOREIGN KEY (Dandremid_Base_id ) 
					    REFERENCES Dandremid_Base (id ));
		
		CREATE  TABLE IF NOT EXISTS Attack ( 
					  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , 
					  name VARCHAR(45) NOT NULL UNIQUE , 
					  Element_id INTEGER NOT NULL , 
					  strike INTEGER NOT NULL , 
					  heal INTEGER NOT NULL , 
					  minimumLevel INTEGER NOT NULL,
					  CONSTRAINT fk_Attack_Element1 
					    FOREIGN KEY (Element_id ) 
					    REFERENCES Element (id ));
		
		CREATE  TABLE IF NOT EXISTS Dandremid_Attack (  
					  Attack_id INTEGER NOT NULL , 
					  Dandremid_id INTEGER NOT NULL , 
					  level INTEGER NOT NULL , 
					  uses INTEGER NOT NULL , 
					  nextLevelUses INTEGER NOT NULL , 
					  CONSTRAINT fk_Attack_has_Dandremid_Attack1 
					    FOREIGN KEY (Attack_id ) 
					    REFERENCES Attack (id ), 
					  CONSTRAINT fk_Attack_has_Dandremid_Dandremid1 
					    FOREIGN KEY (Dandremid_id ) 
					    REFERENCES Dandremid (id ));
		
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
		
		CREATE  TABLE IF NOT EXISTS Dandremid_State ( 
					  State_id INTEGER NOT NULL , 
					  Dandremid_id INTEGER NOT NULL , 
					  PRIMARY KEY (State_id, Dandremid_id) , 
					  CONSTRAINT fk_State_has_Dandremid_State1 
					    FOREIGN KEY (State_id ) 
					    REFERENCES State (id ), 
					  CONSTRAINT fk_State_has_Dandremid_Dandremid1 
					    FOREIGN KEY (Dandremid_id ) 
					    REFERENCES Dandremid (id ));
