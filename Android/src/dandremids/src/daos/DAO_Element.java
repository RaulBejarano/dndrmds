package dandremids.src.daos;


import java.util.ArrayList;

import dandremids.src.model.Element;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DAO_Element {

	Context context;
	SQLiteDatabase db;
	
	public DAO_Element(Context context, SQLiteDatabase db) {
		this.context=context;
		this.db=db;
	}

	public void insertElement(dandremids.src.model.db.Element e) {
		String sql = "INSERT INTO Element (id, name) " +
				"VALUES ("+e.id+", '"+e.name+"')";
		
		db.execSQL(sql);
		
	}
	
	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM Element");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de Element");
		}
	}

	public Element getElementById(int id) {
		String sql = "SELECT id, name FROM Element WHERE id = "+id;
		Cursor c = db.rawQuery(sql, null);
		if (c.moveToFirst()){
			Element element = new Element(c.getInt(0), c.getString(1));
			c.close();
			return element;
		}
		c.close();
		return null;
	}

	public ArrayList<Element> getAllElements() {
		ArrayList<Element> list = new ArrayList<Element>();
		String sql = "SELECT id, name FROM Element";
		Cursor c = db.rawQuery(sql, null);
		Element aux;
		while(c.moveToNext()){
			aux = new Element(c.getInt(0),c.getString(1));
			list.add(aux);
		}
		
		return list;
	}

	

}
