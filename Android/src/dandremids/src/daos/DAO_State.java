package dandremids.src.daos;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.model.Dandremid;
import dandremids.src.model.State;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DAO_State {

	Context context;
	SQLiteDatabase db;
	
	public DAO_State(Context context, SQLiteDatabase db) {
		super();
		this.context = context;
		this.db = db;
	}

	public void insertState(dandremids.src.model.db.State s) {
		String sql = "INSERT INTO State (id, name, abreviation) " +
				"VALUES("+s.id+", '"+s.name+"', '"+s.abreviation+"')";
		
		db.execSQL(sql);
	}

	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM State");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de State");
		}
	}

	public List<State> getDandremidStateList(Dandremid dandremid) {
		String sql = "SELECT id, name, abreviation " +
				" FROM State" +
				" WHERE id IN " +
				" (SELECT State_id FROM Dandremid_State WHERE Dandremid_id = "+dandremid.getId()+")";
		Cursor c = db.rawQuery(sql, null);
		
		List<State> list = new ArrayList<State>();
		State state;
		
		while(c.moveToNext()){	
			state = new State(
					c.getInt(0),						// id
					c.getString(1),						// name
					c.getString(2)						// abreviation
				);
			list.add(state);
		}		
		return list;
	}

}
