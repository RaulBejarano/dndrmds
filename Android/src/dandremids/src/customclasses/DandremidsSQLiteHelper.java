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
		db.execSQL("CREATE  TABLE IF NOT EXISTS User ( "+
					  "id INTEGER NOT NULL , "+
					  "playerName VARCHAR(60) NOT NULL , "+
					  "name VARCHAR(60) NOT NULL , "+
					  "password VARCHAR(256) NOT NULL , "+
					  "email VARCHAR(250) NOT NULL UNIQUE , "+
					  "surname VARCHAR(45) NOT NULL UNIQUE, "+
					  "birth VARCHAR(45) NOT NULL , "+
					  "gender VARCHAR(45) NOT NULL, "+
					  "level INTEGER NOT NULL , "+
					  "exp INTEGER NOT NULL , "+
					  "expNextLevel INTEGER NOT NULL, " +
					  "fighting BOOLEAN NOT NULL )");		
		
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Element ( "+
					 "id INTEGER NOT NULL , "+
					 "name VARCHAR(45) NOT NULL UNIQUE ) ");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid_Base ( "+
					  "id INTEGER NOT NULL , "+
					  "name VARCHAR(45) NOT NULL , "+
					  "Element1_id INTEGER NOT NULL , "+
					  "Element2_id INTEGER NOT NULL , "+
					  "strength INTEGER NOT NULL , "+
					  "defense INTEGER NOT NULL , "+
					  "speed INTEGER NOT NULL , "+
					  "maxFeed INTEGER NOT NULL , "+
					  "maxLife INTEGER NOT NULL , "+
					  "description VARCHAR(250) NOT NULL, " +
					  "CONSTRAINT fk_Dandremid_Base_Element1 "+
					    "FOREIGN KEY (Element1_id ) "+
					    "REFERENCES Element (id ), "+
					  "CONSTRAINT fk_Dandremid_Base_Element2 "+
					    "FOREIGN KEY (Element2_id ) "+
					    "REFERENCES Element (id ))");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid ( "+
					  "id INTEGER NOT NULL , "+
					  "name VARCHAR(45) NOT NULL , "+
					  "level INTEGER NOT NULL , "+
					  "exp INTEGER NOT NULL , "+
					  "expNextLevel INTEGER NOT NULL , "+
					  "selected INTEGER NOT NULL , "+
					  "strength INTEGER NOT NULL , "+
					  "defense INTEGER NOT NULL , "+
					  "speed INTEGER NOT NULL , "+
					  "feed INTEGER NOT NULL , "+
					  "maxFeed INTEGER NOT NULL , "+
					  "life INTEGER NOT NULL , "+
					  "maxLife INTEGER NOT NULL , "+
					  "happiness INTEGER NOT NULL , "+
					  "User_id INTEGER NOT NULL , "+
					  "Dandremid_Base_id INTEGER NOT NULL , "+
					  "CONSTRAINT fk_Dandremid_User "+
					    "FOREIGN KEY (User_id ) "+
					    "REFERENCES User (id ), "+
					  "CONSTRAINT fk_Dandremid_Dandremid_Base1 "+
					    "FOREIGN KEY (Dandremid_Base_id ) "+
					    "REFERENCES Dandremid_Base (id ))");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Attack ( "+
					  "id INTEGER NOT NULL  , "+
					  "name VARCHAR(45) NOT NULL UNIQUE , "+
					  "Element_id INTEGER NOT NULL , "+
					  "strike INTEGER NOT NULL , "+
					  "heal INTEGER NOT NULL , " +
					  "minimumLevel INTEGER NOT NULL, "+
					  "CONSTRAINT fk_Attack_Element1 "+
					    "FOREIGN KEY (Element_id ) "+
					    "REFERENCES Element (id ))");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid_Attack (  "+
					  "Attack_id INTEGER NOT NULL , "+
					  "Dandremid_id INTEGER NOT NULL , "+
					  "level INTEGER NOT NULL , "+
					  "uses INTEGER NOT NULL , "+
					  "nextLevelUses INTEGER NOT NULL , "+
					  "CONSTRAINT fk_Attack_has_Dandremid_Attack1 "+
					    "FOREIGN KEY (Attack_id ) "+
					    "REFERENCES Attack (id ), "+
					  "CONSTRAINT fk_Attack_has_Dandremid_Dandremid1 "+
					    "FOREIGN KEY (Dandremid_id ) "+
					    "REFERENCES Dandremid (id ))");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS State ( "+
					  "id INTEGER NOT NULL , "+
					  "name VARCHAR(45) NOT NULL , "+
					  "abreviation VARCHAR(3) NOT NULL )"); 
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Attack_State ( "+
					  "State_id INTEGER NOT NULL , "+
					  "Attack_id INTEGER NOT NULL , "+
					  "PRIMARY KEY (State_id, Attack_id) , "+
					 "CONSTRAINT fk_State_has_Attack_State1 "+
					    "FOREIGN KEY (State_id ) "+
					    "REFERENCES State (id ), "+
					  "CONSTRAINT fk_State_has_Attack_Attack1 "+
					    "FOREIGN KEY (Attack_id ) "+
					    "REFERENCES Attack (id ))");
		
		db.execSQL("CREATE  TABLE IF NOT EXISTS Dandremid_State ( "+
					  "State_id INTEGER NOT NULL , "+
					  "Dandremid_id INTEGER NOT NULL , "+
					  "PRIMARY KEY (State_id, Dandremid_id) , "+
					  "CONSTRAINT fk_State_has_Dandremid_State1 "+
					    "FOREIGN KEY (State_id ) "+
					    "REFERENCES State (id ), "+
					  "CONSTRAINT fk_State_has_Dandremid_Dandremid1 "+
					    "FOREIGN KEY (Dandremid_id ) "+
					    "REFERENCES Dandremid (id ))");
		
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
		
		this.onCreate(db);
	}

}
