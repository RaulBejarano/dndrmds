package dandremids.src.daos;

import dandremids.src.model.db.Attack;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO_Attack {

	Context context;
	SQLiteDatabase db;
	
	public DAO_Attack(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}

	public void insertAttack(Attack a) {
		String sql = "INSERT INTO Attack (id, name, Element_id, strike, heal, minimumLevel) " +
				"VALUES ("+a.id+", '"+a.name+"', "+a.Element_id+", "+a.strike+", "+a.heal+", "+a.minimumLevel+")";
		db.execSQL(sql);
	}

	public void deleteAll() {
		String sql = "DELETE FROM Attack";
		db.execSQL(sql);
	}

	
}
