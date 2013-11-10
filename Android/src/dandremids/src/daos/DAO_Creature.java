package dandremids.src.daos;


import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dandremids.src.model.Creature;
import dandremids.src.model.User;


public class DAO_Creature {

	private SQLiteDatabase db;
	private Context context;
	
	
	public DAO_Creature(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db=db;
	}
	
	public ArrayList<Creature> getUserCreatures (User u){
		ArrayList<Creature> creatureList = new ArrayList<Creature>();
		
		String sql = "SELECT id, name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, happiness, life, maxLife, Creature_Base_id FROM Creature WHERE User_id = " + u.getId();
		Cursor c = db.rawQuery(sql, null);
				
		Creature creature;
		while(c.moveToNext()){			
				creature = new Creature(
							c.getInt(0),				//	int id;
							c.getString(1),				//	String name;
							c.getInt(2),				//	int level;
							c.getInt(3),				//	int exp;
							c.getInt(4),				//	int expNextLevel;
							intToBoolean(c.getInt(5)),	//	boolean selected;
							c.getInt(6),				//	int strength;
							c.getInt(7),				//	int defense;
							c.getInt(8),				//	int speed;
							c.getInt(9),				//	int feed;
							c.getInt(10),				//	int maxFeed;
							c.getInt(11),				//	int starveSpeed;
							c.getInt(12),				//	int happiness;
							c.getInt(13),				//	int life;
							c.getInt(14)				//	int maxLife;								
						);
				
				DAO_CreatureBase daoCreatureBase = new DAO_CreatureBase(context, db);
				
				creature.setCreatureBase(daoCreatureBase.getCreatureBaseById(c.getInt(15)));
				
				creatureList.add(creature);
		}
		
		c.close();
		return creatureList;
	}
	
	public void insertCreature (dandremids.src.model.db.Creature c){
		String sql = "INSERT INTO Creature (id, name, level, exp, expNextLevel, selected, strength, defense, speed, feed, maxFeed, starveSpeed, happiness, life, maxLife, Creature_Base_id, User_id) " +
				"VALUES ("+c.id+", '"+c.name+"', "+c.level+", "+c.exp+", "+c.expNextLevel+", "+c.selected+", "+c.strength+", "+c.defense+", "+c.speed+", "+c.feed+", "+c.maxFeed+", "+c.starveSpeed+", "+c.happiness+", "+c.life+", "+c.maxLife+", "+c.Creature_Base_id+", "+c.User_id+")";
		
		db.execSQL(sql);
	}
	
	private boolean intToBoolean (int i){
		if(i==0){
			return false;
		}else{
			return true;
		}
	}
}
