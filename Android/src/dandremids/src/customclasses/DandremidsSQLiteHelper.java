package dandremids.src.customclasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DandremidsSQLiteHelper extends SQLiteOpenHelper {

	Context context;
	
	public DandremidsSQLiteHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
				
		db.execSQL("CREATE TABLE User( "+
					  " id INTEGER PRIMARY KEY  NOT NULL, "+
					  " playerName VARCHAR(60) NOT NULL, "+
					  " name VARCHAR(60) NOT NULL, "+
					  " password VARCHAR(256) NOT NULL, "+
					  " email VARCHAR(250) NOT NULL, "+
					  " surname VARCHAR(45) NOT NULL, "+
					  " birth VARCHAR(45) NOT NULL, "+
					  " gender VARCHAR(45) NOT NULL, "+
					  " level INTEGER NOT NULL, "+
					  " exp INTEGER NOT NULL, "+
					  " expNextLevel INTEGER NOT NULL, "+
					  " gold INTEGER NOT NULL, "+
					  " fighting BOOLEAN NOT NULL, "+
					  " CONSTRAINT email_UNIQUE "+
					  "    UNIQUE(email), "+
					  "  CONSTRAINT playerName_UNIQUE "+
					  "    UNIQUE(playerName) )");		
			 	
		db.execSQL("CREATE  TABLE IF NOT EXISTS Element ( "+
					  " id INTEGER NOT NULL , "+
					  " name VARCHAR(45) NOT NULL," +
					  " CONSTRAINT name_UNIQUE "+
					  " UNIQUE(name)) ");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid_Base ( "+
					  " id INTEGER NOT NULL , "+
					  " name VARCHAR(45) NOT NULL , "+
					  " Element1_id INTEGER NOT NULL , "+
					  " Element2_id INTEGER NOT NULL , "+
					  " strength INTEGER NOT NULL , "+
					  " defense INTEGER NOT NULL , "+
					  " speed INTEGER NOT NULL , "+
					  " maxFeed INTEGER NOT NULL , "+
					  " maxLife INTEGER NOT NULL , "+
					  " description VARCHAR(250) NOT NULL, " +
					  " CONSTRAINT fk_Dandremid_Base_Element1 "+
					  "   FOREIGN KEY (Element1_id ) "+
					  "   REFERENCES Element (id ), " +
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE,"+
					  " CONSTRAINT fk_Dandremid_Base_Element2 "+
					  "   FOREIGN KEY (Element2_id ) "+
					  "   REFERENCES Element (id )" +
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid ( "+
					  " id INTEGER NOT NULL , "+
					  " name VARCHAR(45) NOT NULL , "+
					  " level INTEGER NOT NULL , "+
					  " exp INTEGER NOT NULL , "+
					  " expNextLevel INTEGER NOT NULL , "+
					  " selected INTEGER NOT NULL , "+
					  " strength INTEGER NOT NULL , "+
					  " defense INTEGER NOT NULL , "+
					  " speed INTEGER NOT NULL , "+
					  " feed INTEGER NOT NULL , "+
					  " maxFeed INTEGER NOT NULL , "+
					  " life INTEGER NOT NULL , "+
					  " maxLife INTEGER NOT NULL , "+
					  " happiness INTEGER NOT NULL , "+					  
					  " Dandremid_Base_id INTEGER NOT NULL , "+
					  " User_id INTEGER NOT NULL , "+
					  " CONSTRAINT fk_Dandremid_User "+
					  "   FOREIGN KEY (User_id ) "+
					  "   REFERENCES User (id )" +
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE, "+
					  " CONSTRAINT fk_Dandremid_Dandremid_Base1 "+
					  "   FOREIGN KEY (Dandremid_Base_id ) "+
					  "   REFERENCES Dandremid_Base (id ) " +
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Attack ( "+
					  " id INTEGER NOT NULL  , "+
					  " name VARCHAR(45) NOT NULL , "+
					  " Element_id INTEGER NOT NULL , "+
					  " strike INTEGER NOT NULL , "+
					  " heal INTEGER NOT NULL , " +
					  " minimumLevel INTEGER NOT NULL, "+
					  " CONSTRAINT fk_Attack_Element1 "+
					  "   FOREIGN KEY (Element_id ) "+
					  "   REFERENCES Element (id ) " +
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid_Attack (  "+
					  " Attack_id INTEGER NOT NULL , "+
					  " Dandremid_id INTEGER NOT NULL , "+
					  " level INTEGER NOT NULL , "+
					  " uses INTEGER NOT NULL , "+
					  " nextLevelUses INTEGER NOT NULL , "+
					  " CONSTRAINT fk_Attack_Dandremid_Attack1 "+
					  "   FOREIGN KEY (Attack_id ) "+
					  "   REFERENCES Attack (id )" +
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE, "+
					  " CONSTRAINT fk_Attack_Dandremid_Dandremid1 "+
					  "   FOREIGN KEY (Dandremid_id ) "+
					  "   REFERENCES Dandremid (id ) " +
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS State ( "+
					  " id INTEGER NOT NULL , "+
					  " name VARCHAR(45) NOT NULL , "+
					  " abreviation VARCHAR(3) NOT NULL )"); 
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Attack_State ( "+
					  	" State_id INTEGER NOT NULL , "+
					  	" Attack_id INTEGER NOT NULL , "+
					  	" PRIMARY KEY (State_id, Attack_id) , "+
					  	" CONSTRAINT fk_State_Attack_State1 "+
					  	"   FOREIGN KEY (State_id ) "+
					  	"   REFERENCES State (id )" +
					  	"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE , "+
					  	" CONSTRAINT fk_State_Attack_Attack1 "+
					  	"   FOREIGN KEY (Attack_id ) "+
					  	"   REFERENCES Attack (id )" +
					  	"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid_State ( "+
					  	" State_id INTEGER NOT NULL , "+
					  	" Dandremid_id INTEGER NOT NULL , "+
					  	" PRIMARY KEY (State_id, Dandremid_id) , "+
					  	" CONSTRAINT fk_State_Dandremid_State1 "+
					  	"   FOREIGN KEY (State_id ) "+
					  	"   REFERENCES State (id ) " +
					  	"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE, "+
					  	" CONSTRAINT fk_State_Dandremid_Dandremid1 "+
					  	"   FOREIGN KEY (Dandremid_id ) "+
					  	"   REFERENCES Dandremid (id ) " +
					  	"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Element_Element ( "+
						" Element_id_1 INTEGER NOT NULL , "+
						" Element_id_2 INTEGER NOT NULL , "+
						" power DOUBLE NOT NULL , "+
						" CONSTRAINT fk_Element_Element_Element1 "+
						"   FOREIGN KEY (Element_id_1 ) "+
						"   REFERENCES Element (id )" +
						"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE , "+
						" CONSTRAINT fk_Element_Element_Element2 "+
						"   FOREIGN KEY (Element_id_2 ) "+
						"   REFERENCES Element (id )" +
						"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE )");
						
		db.execSQL("CREATE  TABLE IF NOT EXISTS Object ( "+
						" id INTEGER NOT NULL , "+
						" name VARCHAR(45) NOT NULL , "+
						" strength INTEGER NOT NULL , "+
						" defense INTEGER NOT NULL , "+
						" speed INTEGER NOT NULL , "+
						" life INTEGER NOT NULL , "+
						" feed INTEGER NOT NULL , "+
						" happiness INTEGER NOT NULL , "+
						" trap TINYINT(1) NOT NULL , " +
						" type VARCHAR(45) NOT NULL," +
						" price INTEGER NOT NULL )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS User_Object ( "+ 
						" Object_id INTEGER NOT NULL , "+
						" User_id INTEGER NOT NULL , "+
						" quantity INTEGER NOT NULL, "+
						" CONSTRAINT fk_CombatObject_User_CombatObject1 "+
						"   FOREIGN KEY (Object_id ) "+
						"   REFERENCES CombatObject (id )" +
						"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE , "+
						" CONSTRAINT fk_CombatObject_User_User1 "+
						"   FOREIGN KEY (User_id ) "+
						"   REFERENCES User (id )" +
						"   ON DELETE CASCADE "+
					  	"   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE TABLE Object_State( "+
					  " Object_id INTEGER NOT NULL, "+
					  " State_id INTEGER NOT NULL, "+
					  " PRIMARY KEY(Object_id,State_id), "+
					  " CONSTRAINT fk_Object_has_State_Object1 "+
					  "   FOREIGN KEY(Object_id) "+
					  "   REFERENCES Object(id) "+
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE, "+
					  " CONSTRAINT fk_Object_has_State_State1 "+
					  "   FOREIGN KEY(State_id) "+
					  "   REFERENCES State(id) "+
					  "   ON DELETE CASCADE "+
					  "   ON UPDATE CASCADE )");
		
		
		db.execSQL("CREATE TABLE IF NOT EXISTS League ( " +
						" id INTEGER NOT NULL, " +
						" name VARCHAR(45) NOT NULL, " +
						" rounds INTEGER NOT NULL," +
						" status VARCHAR(45) NOT NULL," +
						" code VARCHAR(256) NOT NULL )");
		
		db.execSQL("CREATE TABLE IF NOT EXISTS User_League ( " +
						" User_id INTEGER NOT NULL, " +
						" League_id INTEGER NOT NULL, " +
						" points INTEGER NOT NULL, " +
						" CONSTRAINT fk_User_League_User1 " +
						"   FOREIGN KEY (User_id ) " +
						"   REFERENCES User (id )" +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE, " +
						" CONSTRAINT fk_User_League_League1 " +
						"   FOREIGN KEY (League_id ) " +
						"   REFERENCES League (id ) " +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Combat ( " +
						" League_id INTEGER NOT NULL, " +
						" id INTEGER NOT NULL, " +
						" User_1_id INTEGER NOT NULL, " +
						" User_2_id INTEGER NOT NULL, " +
						" CONSTRAINT fk_Combat_User_League1 " +
						"   FOREIGN KEY (League_id ) " +
						"   REFERENCES User_League (League_id )" +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE, " +
						" CONSTRAINT fk_Combat_User_League2 " +
						"   FOREIGN KEY (User_1_id ) " +
						"   REFERENCES User_League (User_id )" +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE, " +
						" CONSTRAINT fk_Combat_User_League3 " +
						"   FOREIGN KEY (User_2_id ) " +
						"   REFERENCES User_League (User_id )" +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE )");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Turn ( " +
						" League_id INTEGER NOT NULL, " +
						" Combat_id INTEGER NOT NULL, " +
						" id INTEGER NOT NULL, " +
						" User_id INTEGER NOT NULL, " +
						" action VARCHAR(45) NOT NULL, " +
						" value VARCHAR(45) NOT NULL, " +
						" date DATE NOT NULL," +
						" CONSTRAINT fk_Turn_Combat1 " +
						"   FOREIGN KEY (League_id , Combat_id ) " +
						"   REFERENCES Combat (League_id , id )" +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE, " +
						" CONSTRAINT fk_Turn_Combat2 " +
						"   FOREIGN KEY (User_id ) " +
						"   REFERENCES Combat (User_1_id )" +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE, " +
						" CONSTRAINT fk_Turn_Combat3 " +
						"   FOREIGN KEY (User_id ) " +
						"   REFERENCES Combat (User_2_id )" +
						"   ON DELETE CASCADE "+
						"   ON UPDATE CASCADE	 )");
		
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS User");
		db.execSQL("DROP TABLE IF EXISTS Element");
		db.execSQL("DROP TABLE IF EXISTS Dandremid_Base");
		db.execSQL("DROP TABLE IF EXISTS Dandremid");
		db.execSQL("DROP TABLE IF EXISTS Attack");
		db.execSQL("DROP TABLE IF EXISTS Dandremid_Attack");
		db.execSQL("DROP TABLE IF EXISTS State");
		db.execSQL("DROP TABLE IF EXISTS Attack_State");
		db.execSQL("DROP TABLE IF EXISTS Dandremid_State");
		db.execSQL("DROP TABLE IF EXISTS Element_Element");
		db.execSQL("DROP TABLE IF EXISTS Object_User");
		db.execSQL("DROP TABLE IF EXISTS Object");
		db.execSQL("DROP TABLE IF EXISTS League");
		db.execSQL("DROP TABLE IF EXISTS League");
		db.execSQL("DROP TABLE IF EXISTS User_League");
		db.execSQL("DROP TABLE IF EXISTS Combat");
		db.execSQL("DROP TABLE IF EXISTS Turn");		
		
		this.onCreate(db);
	}

}
