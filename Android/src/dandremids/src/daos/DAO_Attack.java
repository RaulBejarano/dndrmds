package dandremids.src.daos;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.model.Attack;
import dandremids.src.model.Dandremid;
import dandremids.src.model.Element;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
		String sql = "DELETE FROM Attack";
		db.execSQL(sql);
	}

	public List<Attack> getDandremidAttackList(Dandremid dandremid) {
		String sql = "SELECT A.id, A.name, (SELECT name FROM Element WHERE id = A.Element_id) element, A.strike, A.heal, A.minimumLevel, DA.level, DA.uses, DA.nextLevelUses " +
				"FROM Attack AS A, Dandremid_Attack AS DA " +
				"WHERE DA.Attack_id = A.id AND DA.Dandremid_id = "+dandremid.getId();
		Cursor c = db.rawQuery(sql, null);
		
		List<Attack> list = new ArrayList<Attack>();
		Attack attack;
		while(c.moveToNext()){	
			attack = new Attack(
					c.getInt(0),						// id
					c.getString(1),						// name
					Element.valueOf(c.getString(2)),	// element
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

	
}
