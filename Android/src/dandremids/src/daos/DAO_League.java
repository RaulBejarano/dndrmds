package dandremids.src.daos;

import dandremids.src.model.League;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
	
	public void deleteAll(){
		String sql = "DELETE FROM League ";
		db.execSQL(sql);	
	}
	
	public League getLeague () {
		League league=null;
		String sql = "SELECT id, name, rounds, status, code FROM League";
		Cursor c = db.rawQuery(sql, null);
		
		if(c.moveToFirst()){
			league = new League (c.getInt(0), c.getString(1), c.getInt(2), c.getString(3), c.getString(4));
		}
		
		c.close();
		
		return league;
	}
}
