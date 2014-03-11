package dandremids.src.daos;


import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dandremids.src.model.Dandremid;
import dandremids.src.model.User;


public class DAO_Dandremid {

	private SQLiteDatabase db;
	private Context context;
	
	
	public DAO_Dandremid(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}
	
		
	public ArrayList<Dandremid> getUserDandremids (User u){
		ArrayList<Dandremid> creatureList = new ArrayList<Dandremid>();
		
		String sql = "SELECT id, name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, happiness, life, maxLife, Dandremid_Base_id FROM Dandremid WHERE User_id = " + u.getId();
		Cursor c = db.rawQuery(sql, null);
				
		Dandremid dandremid;
		while(c.moveToNext()){			
				dandremid = new Dandremid(
							c.getInt(0),				//	int id;
							c.getString(1),				//	String name;
							c.getInt(2),				//	int level;
							c.getInt(3),				//	int exp;
							c.getInt(4),				//	int expNextLevel;
							c.getInt(5),				//	int selected;
							c.getInt(6),				//	int strength;
							c.getInt(7),				//	int defense;
							c.getInt(8),				//	int speed;
							c.getInt(9),				//	int feed;
							c.getInt(10),				//	int maxFeed;							
							c.getInt(11),				//	int happiness;
							c.getInt(12),				//	int life;
							c.getInt(13)				//	int maxLife;								
						);
				
				DAO_DandremidBase daoDandremidBase = new DAO_DandremidBase(context, db);
				dandremid.setDandremidBase(daoDandremidBase.getDandremidBaseById(c.getInt(14)));
				
				DAO_Attack daoAttack = new DAO_Attack (context, db);
				dandremid.setAttackList(daoAttack.getDandremidAttackList(dandremid));
				
				creatureList.add(dandremid);
		}
		
		c.close();
		return creatureList;
	}
	
	public void insertDandremid (dandremids.src.model.db.Dandremid c){
		String sql = "INSERT INTO Dandremid (id, name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, happiness, life, maxLife, Dandremid_Base_id, User_id) " +
				"VALUES ("+c.id+", '"+c.name+"', "+c.level+", "+c.exp+", "+c.expNextLevel+", "+c.selected+", "+c.strength+", "+c.defense+", "+c.speed+", "+c.feed+", "+c.maxFeed+", "+c.happiness+", "+c.life+", "+c.maxLife+", "+c.Dandremid_Base_id+", "+c.User_id+")";
		
		db.execSQL(sql);
		
		DAO_DandremidAttack daoDA = new DAO_DandremidAttack(context,db);
		for (dandremids.src.model.db.DandremidAttack da : c.attacks) {
			daoDA.insertDandremidAttack(da);
		}
		
	}
	
	
	public void updateDandremid(Dandremid c) {
		String sql = "UPDATE Dandremid SET " +
				"name = '"+c.getName()+"', level = "+c.getLevel()+", exp = "+c.getExp()+", expNextLevel = "+c.getExpNextLevel()+", selected = "+c.getSelected()+", strength = "+c.getStrength()+", defense = "+c.getDefense()+", speed = "+c.getSpeed()+", feed = "+c.getFeed()+", maxFeed = "+c.getMaxFeed()+", happiness = "+c.getHappiness()+", life = "+c.getLife()+", maxLife = "+c.getMaxLife()+
				" WHERE id = "+c.getId();
		db.execSQL(sql);
		
	}


	public void deleteDandremid(Dandremid c) {
		String sql = "DELETE FROM Dandremid WHERE id = "+c.getId();
		db.execSQL(sql);
		
	}


	public void deleteAll() {
		String sql = "DELETE FROM Dandremid";
		db.execSQL(sql);
	}
}
