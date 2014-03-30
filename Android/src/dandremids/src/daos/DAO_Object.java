package dandremids.src.daos;

import java.util.ArrayList;

import dandremids.src.model.Object;
import dandremids.src.model.User;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAO_Object {
	Context context;
	SQLiteDatabase db;
	
	public DAO_Object(Context context, SQLiteDatabase db){
		super();
		this.context=context;
		this.db=db;
	}

	public void deleteAll() {
		String sql = "DELETE FROM Object";
		db.execSQL(sql);
	}

	public void insertObject(dandremids.src.model.db.Object co) {
		String sql = "INSERT INTO Object (id, name, strength, defense, speed, life, feed, happiness, trap, type, price) VALUES ("+co.id+",'"+co.name+"',"+co.strength+","+co.defense+","+co.speed+","+co.life+","+co.feed+","+co.happiness+","+co.trap+",'"+co.type+"', "+co.price+")";
		db.execSQL(sql);
	}

	public ArrayList<Object> getUserObjects(User u, String type) {
		ArrayList<Object> objectList = new ArrayList<Object>();
		
		String sql = "SELECT O.id, O.name, O.strength, O.defense, O.speed, O.life, O.feed, O.happiness, O.trap, O.type, O.price, UO.quantity " +
				" FROM Object AS O, User_Object AS UO " +
				" WHERE O.id = UO.Object_id AND UO.User_id = "+u.getId()+" AND O.type LIKE '"+type+"' " +
				" ORDER BY UO.quantity DESC";
		Cursor c = db.rawQuery(sql, null);
				
		Object object;
		while(c.moveToNext()){			
			object = new Object(
							c.getInt(0),				//	int id;
							c.getString(1),				//	String name;
							c.getInt(2),				//	int strength;
							c.getInt(3),				//	int defense;
							c.getInt(4),				//	int speed;
							c.getInt(5),				//	int life;
							c.getInt(6),				//	int feed
							c.getInt(7),				//	int happiness
							c.getInt(8)>0,				//  boolean trap
							c.getString(9),				//	String type
							c.getInt(10),				// 	int price
							c.getInt(11)				//  int quantity
						);
							
			objectList.add(object);
		}
		
		c.close();
		return objectList;
	}

	public ArrayList<Object> getShopObjectList(User user) {
		ArrayList<Object> list = new ArrayList<Object>();
		String str = "";
		for (Object o : user.getObjectList()){
			list.add(o);
			str = str  + "," + o.getId();
		}
		str = str.substring(1);
		
		String sql = "SELECT id, name, strength, defense, speed, life, feed, happiness, trap, type, price " +
				" FROM Object " +
				" WHERE id NOT IN ("+str+")";
		Cursor c = db.rawQuery(sql, null);
				
		Object object;
		while(c.moveToNext()){			
			object = new Object(
							c.getInt(0),				//	int id;
							c.getString(1),				//	String name;
							c.getInt(2),				//	int strength;
							c.getInt(3),				//	int defense;
							c.getInt(4),				//	int speed;
							c.getInt(5),				//	int life;
							c.getInt(6),				//	int feed
							c.getInt(7),				//	int happiness
							c.getInt(8)>0,				//  boolean trap
							c.getString(9),				//	String type
							c.getInt(10),				//  int price
							0							//  int quantity
						);
							
			list.add(object);
		}
		
		c.close();
		
		return list;
	}

	public void updateObject(User u, Object o) {
		String sql;
		if (o.getQuantity()<=0){
			sql="DELETE FROM User_Object WHERE Object_id = "+o.getId();
			db.execSQL(sql);
			
		} else {
			sql="SELECT * FROM User_Object WHERE Object_id = "+o.getId();
			Cursor c = db.rawQuery(sql, null);
			if (c.moveToFirst()){ // Ya existe en la tabla UserObject
				sql = "UPDATE User_Object SET quantity = "+o.getQuantity()+" WHERE Object_id = "+o.getId();
				db.execSQL(sql);
			} else { // No existe en la tabla UserObject
				sql = "INSERT INTO User_Object (User_id, Object_id, quantity) VALUES ("+u.getId()+","+o.getId()+","+o.getQuantity()+")";
				db.execSQL(sql);
			}
			
		}
		
	}
}
