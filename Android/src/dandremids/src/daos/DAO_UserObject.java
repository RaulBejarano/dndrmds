package dandremids.src.daos;

import dandremids.src.model.db.UserObject;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DAO_UserObject {

	Context context;
	SQLiteDatabase db;
	
	public DAO_UserObject(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}

	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM User_Object");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de User_Object");
		}
	}

	public void insertUserObject(UserObject c) {
		String sql = "INSERT INTO User_Object (Object_id, User_id, quantity) VALUES ("+c.Object_id+","+c.User_id+","+c.quantity+")";
		db.execSQL(sql);
	}

}
