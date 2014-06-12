package dandremids.src.daos;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.model.Attack;
import dandremids.src.model.Dandremid;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DAO_Attack {

	Context context;
	SQLiteDatabase db;
	
	public DAO_Attack(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}

	public void insertAttack(dandremids.src.model.db.Attack a) {
		String sql = "INSERT INTO Attack (id, name, Element_id, strike, heal, minimumLevel) " +
				"VALUES ("+a.id+", '"+a.name+"', "+a.Element_id+", "+a.strike+", "+a.heal+", "+a.minimumLevel+")";
		db.execSQL(sql);
	}

	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM Attack");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de attack");
		}
	}

	public List<Attack> getDandremidAttackList(Dandremid dandremid) {
		String sql = "SELECT A.id, A.name, A.Element_id, A.strike, A.heal, A.minimumLevel, DA.level, DA.uses, DA.nextLevelUses " +
				"FROM Attack AS A, Dandremid_Attack AS DA " +
				"WHERE DA.Attack_id = A.id AND DA.Dandremid_id = "+dandremid.getId();
		Cursor c = db.rawQuery(sql, null);
		
		List<Attack> list = new ArrayList<Attack>();
		Attack attack;
		DAO_Element daoElement = new DAO_Element (context, db);
		
		while(c.moveToNext()){	
			attack = new Attack(
					c.getInt(0),						// id
					c.getString(1),						// name
					daoElement.getElementById(c.getInt(2)),	// element
					c.getInt(3),						// strike
					c.getInt(4),						// heal
					c.getInt(5),						// minimumLevel
					c.getInt(6),						// level
					c.getInt(7),						// uses
					c.getInt(8)							// nextLevelUses
				);
			list.add(attack);
		}		
		return list;		
	}

	public Attack getRandomAttackForDandremid(Dandremid d) {
		
		String str = "";
		for (Attack a:d.getAttackList()){
			str+=a.getId()+",";
		}
		if (str.length()>0) str=str.substring(0, str.length()-1);
		
		String sql = "SELECT id, name, Element_id, strike, heal, minimumLevel" +
				" FROM Attack" +
				" WHERE Element_id = "+d.getDandremidBase().getElement1().getId() + " OR Element_id = "+d.getDandremidBase().getElement2().getId() + 
				" AND minimumLevel <= "+d.getLevel() +
				" AND id NOT IN ("+str+")";
		Cursor c = db.rawQuery(sql, null);	
		
		int row = (int)(Math.random() * c.getCount());
	
		if (c.moveToPosition(row)){
			DAO_Element daoElement = new DAO_Element(context, db);
			Attack attack = new Attack(
					c.getInt(0),						// id
					c.getString(1),						// name
					daoElement.getElementById(c.getInt(2)),	// element
					c.getInt(3),						// strike
					c.getInt(4),						// heal
					c.getInt(5),						// minimumLevel
					1,									// level
					0,									// uses
					10									// nextLevelUses
				);
			c.close();
			return attack;
			
		}
		c.close();
		return null;
	}

	
}
