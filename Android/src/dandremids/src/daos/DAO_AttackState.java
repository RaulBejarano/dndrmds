package dandremids.src.daos;

import dandremids.src.model.db.AttackState;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DAO_AttackState {

	Context context;
	SQLiteDatabase db;
	
	public DAO_AttackState(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}

	public void insertAttack_State(AttackState as){
		String sql = "INSERT INTO Attack_State (State_id, Attack_id)" +
				"VALUES ("+as.State_id+","+as.Attack_id+")";
		db.execSQL(sql);
	}

	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM Attack_State");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de Attack_State");
		}
	}
	
}
