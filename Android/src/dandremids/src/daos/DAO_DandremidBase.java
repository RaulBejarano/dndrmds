package dandremids.src.daos;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.model.Dandremid;
import dandremids.src.model.DandremidBase;
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

	public void insertDandremidBase(dandremids.src.model.db.Dandremid_Base cb){
				
		String sql = "INSERT INTO Dandremid_Base (id, name, Element1_id, Element2_id, strength, defense, speed, maxFeed, maxLife, description)" +
				" VALUES ("+cb.id+", '"+cb.name+"', "+cb.Element1_id+", "+cb.Element2_id+", "+cb.stregth+", "+cb.defense+", "+cb.speed+", "+cb.maxFeed+", "+cb.maxLife+", \""+cb.description+"\")";
		db.execSQL(sql);
		
	}
	
	public void deleteAll(){
		String sql = "DELETE FROM Dandremid_Base";
		db.execSQL(sql);
	}

	public DandremidBase getRandomDandremidBase(){
		
				
		String sql = "SELECT id, name, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element1, " +
				"(SELECT name FROM Element WHERE id = Element2_id) element2, " +
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
					
			DandremidBase base = new DandremidBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						DandremidBase.Element.valueOf(c.getString(2)),		//Dandremid.Type element1;
						DandremidBase.Element.valueOf(c.getString(3)),		//Dandremid.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_maxFeed;
						c.getInt(8),								//int base_maxLife;
						c.getString(9),								//String description
						getDandremidBaseImage(c.getString(1))		//Bitmap image;
				);
			
			c.close();
			return base;			
		}		
		
		return null;
	}
	
	public DandremidBase getDandremidBaseById(int dandremidBaseId) {
		String sql = "SELECT id, name, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element1, " +
				"(SELECT name FROM Element WHERE id = Element2_id) element2, " +
				"strength, " +
				"defense," +
				"speed, " +
				"maxFeed, " +
				"maxLife, " +
				"description " +
				"FROM Dandremid_Base WHERE id = " + dandremidBaseId;
		Cursor c = db.rawQuery(sql, null);
		
		if (c.moveToFirst()){
					
			DandremidBase base = new DandremidBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						DandremidBase.Element.valueOf(c.getString(2)),		//Dandremid.Type element1;
						DandremidBase.Element.valueOf(c.getString(3)),		//Dandremid.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_maxFeed;
						c.getInt(8),								//int base_maxLife;
						c.getString(9),								//String description
						getDandremidBaseImage(c.getString(1))		//Bitmap image;
				);
			
			c.close();
			return base;			
		}		
		
		return null;
	}


	private Bitmap getDandremidBaseImage(String name) {		
		return BitmapFactory.decodeResource(context.getResources() , context.getResources().getIdentifier("dndrmd_"+name.toLowerCase(), "drawable", context.getPackageName()));
	}

	public List<DandremidBase> getAllDandremidBase() {
		List<DandremidBase> list = new ArrayList<DandremidBase>();
		
		String sql = "SELECT id, name, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element1, " +
				"(SELECT name FROM Element WHERE id = Element2_id) element2, " +
				"strength, " +
				"defense," +
				"speed, " +
				"maxFeed, " +
				"maxLife, " +
				"description " +
				"FROM Dandremid_Base ORDER BY id ";
		Cursor c = db.rawQuery(sql, null);
		
		while (c.moveToNext()){
					
			DandremidBase base = new DandremidBase(
						c.getInt(0),								//int id;
						c.getString(1),								//String name;
						DandremidBase.Element.valueOf(c.getString(2)),		//Dandremid.Type element1;
						DandremidBase.Element.valueOf(c.getString(3)),		//Dandremid.Type element2;					
						c.getInt(4),								//int base_strength;
						c.getInt(5),								//int base_defense;
						c.getInt(6),								//int base_speed;
						c.getInt(7),								//int base_maxFeed;
						c.getInt(8),								//int base_maxLife;
						c.getString(9),								//description
						getDandremidBaseImage(c.getString(1))		//Bitmap image;
				);
			
			list.add(base);	
		}		
		
		return list;
		
	}
	
	
	
}
