package dandremids.src.daos;

import dandremids.src.model.League;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DAO_League {
	Context context;
	SQLiteDatabase db;
	
	public DAO_League (Context context, SQLiteDatabase db) {
		super();
		this.context = context;
		this.db = db;
	}
	
	public void insertLeague (dandremids.src.model.db.League l) {
		String sql = "INSERT INTO League (id, name, rounds, status, code) VALUES ("+l.id+",'"+l.name+"',"+l.rounds+",'"+l.status+"','"+l.code+"')";
		db.execSQL(sql);	
	}
	
	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM League");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de League");
		}
	}
	
	public League getLeague () {
		League league=null;
		String sql = "SELECT id, name, rounds, status, code FROM League";
		Cursor c = db.rawQuery(sql, null);
		
		if(c.moveToFirst()){
			league = new League (c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getString(4));
			
			DAO_User daoUser = new DAO_User(context, db);
			league.setUserList(daoUser.getUsersInLeague(league));
		}
		
		c.close();
		
		return league;
	}

	public int getCreatorId() {
		int id = -1;
		
		String sql = "SELECT User_id FROM User_League WHERE status = 'CREATOR'";
		Cursor c = db.rawQuery(sql, null);
		
		if(c.moveToFirst()){
			id = c.getInt(0);			
		}
		
		c.close();
		
		return id;
	}
	
}
