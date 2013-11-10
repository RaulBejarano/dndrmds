package dandremids.src.daos;

import dandremids.src.model.db.State;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO_State {

	Context context;
	SQLiteDatabase db;
	
	public DAO_State(Context context, SQLiteDatabase db) {
		super();
		this.context = context;
		this.db = db;
	}

	public void insertState(State s) {
		String sql = "INSERT INTO State (id, name, abreviation) " +
				"VALUES("+s.id+", '"+s.name+"', '"+s.abreviation+"')";
		
		db.execSQL(sql);
	}

	public void deleteAll() {
		String sql = "DELETE FROM State";
		db.execSQL(sql);		
	}

}
