package dandremids.src.daos;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.model.Creature;
import dandremids.src.model.CreatureBase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DAO_CreatureBase {
	private Context context;
	private SQLiteDatabase db;
	
	public DAO_CreatureBase(Context context, SQLiteDatabase db) {
		super();
		this.context = context;
		this.db = db;
	}

	public void insertCreatureBase(dandremids.src.model.db.Creature_Base cb){
				
		String sql = "INSERT INTO Creature_Base (id, name, Element1_id, Element2_id, strength, defense, speed, feed, maxFeed, starveSpeed, maxLife)" +
				" VALUES ("+cb.id+", '"+cb.name+"', "+cb.Element1_id+", "+cb.Element2_id+", "+cb.stregth+", "+cb.defense+", "+cb.speed+", "+cb.feed+", "+cb.maxFeed+", "+cb.starveSpeed+", "+cb.maxLife+")";
		db.execSQL(sql);
		
	}
	
	public void deleteAll(){
		String sql = "DELETE FROM Creature_Base";
		db.execSQL(sql);
	}

	public CreatureBase getRandomCreatureBase(){
		
				
		String sql = "SELECT id, name, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element1, " +
				"(SELECT name FROM Element WHERE id = Element2_id) element2, " +
				"strength, " +
				"defense," +
				"speed, " +
				"feed, " +
				"maxFeed, " +
				"starveSpeed, " +
				"maxLife " +
				"FROM Creature_Base";
		Cursor c = db.rawQuery(sql, null);
		
		int row = (int)(Math.random()*c.getCount());
		
		if (c.moveToPosition(row)){
					
			CreatureBase base = new CreatureBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						Creature.Type.valueOf(c.getString(2)),		//Creature.Type element1;
						Creature.Type.valueOf(c.getString(3)),		//Creature.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_feed;
						c.getInt(8),								//int base_maxFeed;
						c.getInt(9),								//int base_starveSpeed;
						c.getInt(10),								//int base_maxLife;
						getCreatureBaseImage(c.getString(1))						//Bitmap image;
				);
			
			c.close();
			return base;			
		}		
		
		return null;
	}
	
	public CreatureBase getCreatureBaseById(int creatureBaseId) {
		String sql = "SELECT id, name, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element1, " +
				"(SELECT name FROM Element WHERE id = Element2_id) element2, " +
				"strength, " +
				"defense," +
				"speed, " +
				"feed, " +
				"maxFeed, " +
				"starveSpeed, " +
				"maxLife " +
				"FROM Creature_Base WHERE id = " + creatureBaseId;
		Cursor c = db.rawQuery(sql, null);
		
		if (c.moveToFirst()){
					
			CreatureBase base = new CreatureBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						Creature.Type.valueOf(c.getString(2)),		//Creature.Type element1;
						Creature.Type.valueOf(c.getString(3)),		//Creature.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_feed;
						c.getInt(8),								//int base_maxFeed;
						c.getInt(9),								//int base_starveSpeed;
						c.getInt(10),								//int base_maxLife;
						getCreatureBaseImage(c.getString(1))						//Bitmap image;
				);
			
			c.close();
			return base;			
		}		
		
		return null;
	}


	private Bitmap getCreatureBaseImage(String name) {		
		return BitmapFactory.decodeResource(context.getResources() , context.getResources().getIdentifier("dndrmd_"+name.toLowerCase(), "drawable", context.getPackageName()));
	}

	public List<CreatureBase> getAllCreatureBase() {
		List<CreatureBase> list = new ArrayList<CreatureBase>();
		
		String sql = "SELECT id, name, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element1, " +
				"(SELECT name FROM Element WHERE id = Element2_id) element2, " +
				"strength, " +
				"defense," +
				"speed, " +
				"feed, " +
				"maxFeed, " +
				"starveSpeed, " +
				"maxLife " +
				"FROM Creature_Base ORDER BY id ";
		Cursor c = db.rawQuery(sql, null);
		
		while (c.moveToNext()){
					
			CreatureBase base = new CreatureBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						Creature.Type.valueOf(c.getString(2)),		//Creature.Type element1;
						Creature.Type.valueOf(c.getString(3)),		//Creature.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_feed;
						c.getInt(8),								//int base_maxFeed;
						c.getInt(9),								//int base_starveSpeed;
						c.getInt(10),								//int base_maxLife;
						getCreatureBaseImage(c.getString(1))						//Bitmap image;
				);
			
			list.add(base);	
		}		
		
		return list;
		
	}
	
	
	
}
