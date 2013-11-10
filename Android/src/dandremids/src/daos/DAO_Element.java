package dandremids.src.daos;

import dandremids.src.model.db.Element;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO_Element {

	Context context;
	SQLiteDatabase db;
	
	public DAO_Element(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}

	public void insertElement(Element e) {
		String sql = "INSERT INTO Element (id, name) " +
				"VALUES ("+e.id+", '"+e.name+"')";
		
		db.execSQL(sql);
		
	}
	
	public void deleteAll(){
		String sql = "DELETE FROM Element";
		db.execSQL(sql);
	}

}
