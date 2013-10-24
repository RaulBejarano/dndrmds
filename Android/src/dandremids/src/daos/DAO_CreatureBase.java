package dandremids.src.daos;

import dandremids.src.R;
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


	public CreatureBase getCreatureBaseById(int creatureBaseId) {
		String sql = "SELECT id, name, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element1, " +
				"(SELECT name FROM Element WHERE id = Element1_id) element2, " +
				"strength, " +
				"defense," +
				"speed, " +
				"feed, " +
				"maxFeed, " +
				"starveSpeed, " +
				"maxLife " +
				"FROM CreatureBase WHERE id = " + creatureBaseId;
		Cursor c = db.rawQuery(sql, null);
		
		if (c.moveToFirst()){
			CreatureBase base = new CreatureBase(
						c.getInt(1),								//int id;
						c.getString(2),								//String name;
						Creature.Type.valueOf(c.getString(3)),		//Creature.Type element1;
						Creature.Type.valueOf(c.getString(4)),		//Creature.Type element2;					
						c.getInt(5),								//int base_strength;
						c.getInt(6),								//int base_defense;
						c.getInt(7),								//int base_speed;
						c.getInt(8),								//int base_feed;
						c.getInt(9),								//int base_maxFeed;
						c.getInt(10),								//int base_starveSpeed;
						c.getInt(11),								//int base_maxLife;
						getCreatureBaseImage()						//Bitmap image;
				);
			
			return base;
			
		}		
		
		return null;
	}


	private Bitmap getCreatureBaseImage() {
		
		return BitmapFactory.decodeResource(context.getResources(), R.drawable.monster2);
	}
	
	
	
}
