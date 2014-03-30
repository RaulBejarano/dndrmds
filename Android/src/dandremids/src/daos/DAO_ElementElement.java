package dandremids.src.daos;

import java.util.ArrayList;

import dandremids.src.model.ElementElement;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO_ElementElement {
	Context context;
	SQLiteDatabase db;
	
	public DAO_ElementElement(Context context, SQLiteDatabase db){
		super();
		this.context=context;
		this.db=db;
	}

	public void deleteAll() {
		String sql = "DELETE FROM Element_Element";
		db.execSQL(sql);		
	}

	public void insertCombatObject(dandremids.src.model.db.ElementElement e) {
		String sql = "INSERT INTO Element_Element (Element_id_1, Element_id_2, power) VALUES ("+e.Element_id_1+","+e.Element_id_2+","+e.power+") ";
		db.execSQL(sql);
		
	}

	public ArrayList<ElementElement> getAllElementsRelations() {
		ArrayList<ElementElement> list = new ArrayList<ElementElement>();
		String sql = "SELECT Element_id_1, Element_id_2, power FROM Element_Element";
		Cursor c = db.rawQuery(sql, null);
		ElementElement e;
		while(c.moveToNext()){
			e = new ElementElement(c.getInt(0),c.getInt(1),c.getDouble(2));
			list.add(e);
		}
		return list;
	}
}
