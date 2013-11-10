package dandremids.src.daos;

import dandremids.src.model.db.Attack_State;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO_Attack_State {

	Context context;
	SQLiteDatabase db;
	
	public DAO_Attack_State(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}

	public void insertAttack_State(Attack_State as){
		String sql = "INSERT INTO Attack_State (State_id, Attack_id)" +
				"VALUES ("+as.State_id+","+as.Attack_id+")";
		db.execSQL(sql);
	}

	public void deleteAll() {
		String sql = "DELETE FROM Attack_State";
		db.execSQL(sql);
	}
	
}
