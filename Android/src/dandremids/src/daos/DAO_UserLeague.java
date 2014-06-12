package dandremids.src.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import dandremids.src.model.db.UserLeague;

public class DAO_UserLeague {
	Context context;
	SQLiteDatabase db;
	
	public DAO_UserLeague(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}

	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM User_League");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de User_League");
		}
	}

	public void insertUserLeague(UserLeague l) {
		String sql = "INSERT INTO User_League (User_id, League_id, points, status) VALUES ("+l.User_id+","+l.League_id+","+l.points+",'"+l.status+"')";
		db.execSQL(sql);
	}

	
}
