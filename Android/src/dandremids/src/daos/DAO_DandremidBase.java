package dandremids.src.daos;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.model.DandremidBase;
import dandremids.src.model.Element;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DAO_DandremidBase {
	private Context context;
	private SQLiteDatabase db;
	
	public DAO_DandremidBase(Context context, SQLiteDatabase db) {
		super();
		this.context = context;
		this.db = db;
	}

	public void insertDandremidBase(dandremids.src.model.db.DandremidBase cb){
				
		String sql = "INSERT INTO Dandremid_Base (id, name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description)" +
				" VALUES ("+cb.id+", '"+cb.name+"', "+cb.Element1_id+", "+cb.Element2_id+", "+cb.strength+", "+cb.defense+", "+cb.speed+", "+cb.maxFeed+", "+cb.maxLife+", \""+cb.description+"\")";
		db.execSQL(sql);
		
	}
	
	public void deleteAll(){
		String sql = "DELETE FROM Dandremid_Base";
		db.execSQL(sql);
	}

	public DandremidBase getRandomDandremidBase(){
			
		String sql = "SELECT id, name, " +
				"Element1_id, " +
				"Element2_id, " +
				"strength, " +
				"defense," +
				"speed, " +
				"maxFeed, " +
				"maxLife, " +
				"description " +
				"FROM Dandremid_Base";
		Cursor c = db.rawQuery(sql, null);
		
		int row = (int)(Math.random()*c.getCount());
		
		if (c.moveToPosition(row)){
			DAO_Element daoElement = new DAO_Element (context, db);
						
			DandremidBase base = new DandremidBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						daoElement.getElementById(c.getInt(2)),		//Dandremid.Type element1;
						daoElement.getElementById(c.getInt(3)),		//Dandremid.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_maxFeed;
						c.getInt(8),								//int base_maxLife;
						c.getString(9), 							//String description
						getDandremidImage(c.getString(1))
				);
			
			c.close();
			return base;			
		}		
		
		return null;
	}
	
	public DandremidBase getDandremidBaseById(int dandremidBaseId) {
		String sql = "SELECT id, name, " +
				"Element1_id, " +
				"Element2_id, " +
				"strength, " +
				"defense," +
				"speed, " +
				"maxFeed, " +
				"maxLife, " +
				"description " +
				"FROM Dandremid_Base WHERE id = " + dandremidBaseId;
		Cursor c = db.rawQuery(sql, null);
		
		if (c.moveToFirst()){
			DAO_Element daoElement = new DAO_Element (context, db);
			
			DandremidBase base = new DandremidBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						daoElement.getElementById(c.getInt(2)),		//Dandremid.Type element1;
						daoElement.getElementById(c.getInt(3)),		//Dandremid.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_maxFeed;
						c.getInt(8),								//int base_maxLife;
						c.getString(9), 							//String description
						getDandremidImage(c.getString(1))
				);
			
			c.close();
			return base;			
		}		
		c.close();
		return null;
	}


	public List<DandremidBase> getAllDandremidBase() {
		List<DandremidBase> list = new ArrayList<DandremidBase>();
		
		String sql = "SELECT id, name, " +
				"Element1_id, " +
				"Element2_id, " +
				"strength, " +
				"defense," +
				"speed, " +
				"maxFeed, " +
				"maxLife, " +
				"description " +
				"FROM Dandremid_Base ORDER BY id ";
		Cursor c = db.rawQuery(sql, null);
		
		DAO_Element daoElement = new DAO_Element (context, db);
		
		while (c.moveToNext()){						
			DandremidBase base = new DandremidBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						daoElement.getElementById(c.getInt(2)),		//Dandremid.Type element1;
						daoElement.getElementById(c.getInt(3)),		//Dandremid.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_maxFeed;
						c.getInt(8),								//int base_maxLife;
						c.getString(9), 							//String description
						getDandremidImage(c.getString(1))
				);
			
			list.add(base);	
		}		
		
		return list;
		
	}
	
	
	private Bitmap getDandremidImage (String name) {
		return BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("dndrmd_"+name.toLowerCase(), "drawable", context.getPackageName()));
	}
	
}
