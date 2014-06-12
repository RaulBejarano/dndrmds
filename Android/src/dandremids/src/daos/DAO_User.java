package dandremids.src.daos;


import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import dandremids.src.R;
import dandremids.src.model.Dandremid;
import dandremids.src.model.League;
import dandremids.src.model.User;
import dandremids.src.model.Object;

public class DAO_User {

	private SQLiteDatabase db;
	private Context context;

	public DAO_User(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db = db;
	}
		
	public void insertUser (dandremids.src.model.db.User u){		
		
		String sql = "INSERT INTO User (id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel, gold, fighting, local) " +
				" VALUES ("+u.id+", '"+u.playerName+"', '"+u.name+"', '"+u.password+"', '"+u.email+"', '"+u.surname+"', '"+u.birth+"', '"+u.gender+"', "+u.level+", "+u.exp+", "+u.expNextLevel+", "+u.gold+", 'false', 'true' ) ";
		db.execSQL(sql);		
		
		DAO_Dandremid daoDandremid = new DAO_Dandremid(context, db);
		for (dandremids.src.model.db.Dandremid d : u.dandremids) {
			daoDandremid.insertDandremid(d);
		}
		
		DAO_UserObject daoUserObject = new DAO_UserObject (context, db);
		for (dandremids.src.model.db.UserObject co : u.userObjects){
			daoUserObject.insertUserObject(co);
		}		
	}
	
	public void insertExternalUser(dandremids.src.model.db.User u){
		String sql = "INSERT INTO User (id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel, gold, fighting, local) VALUES " +
				"("+u.id+", '"+u.playerName+"', '"+u.name+"', '"+u.password+"', '"+u.email+"', '"+u.surname+"', '"+u.birth+"', '"+u.gender+"', "+u.level+", "+u.exp+", "+u.expNextLevel+", "+u.gold+", 'false', 'false' ) ";
		db.execSQL(sql);
	}
	
	public ArrayList<User> getExternalUsers(){
		ArrayList<User> list = new ArrayList<User>();
		String sql="SELECT id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel, gold, fighting FROM User WHERE local = 'false'";		
		Cursor c = db.rawQuery(sql, null);
		
		while(c.moveToNext()){
			User user = new User(
					c.getInt(0), 		//id
					getUserImage(), 	//image
					c.getString(1), 	//playerName
					c.getString(2), 	//name	
					c.getString(3),		//password
					c.getString(4),		//email
					c.getString(5), 	//surname
					c.getString(6), 	//birth
					c.getString(7), 	//gender
					c.getInt(8), 		//level
					c.getInt(9), 		//exp
					c.getInt(10),		//expNextLevel 
					c.getInt(11),		//gold
					c.getInt(12)>0		//fighting
				);
			
			list.add(user);
		}		
		c.close();
		return list;
	}
		
	
	public User getLocalUser(){ // Just one in the local database, fetch = position
		String sql="SELECT id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel, gold, fighting FROM User WHERE local = 'true'";		
		Cursor c = db.rawQuery(sql, null);
		
		if(c.moveToFirst()){
			User user = new User(
					c.getInt(0), 		//id
					getUserImage(), 	//image
					c.getString(1), 	//playerName
					c.getString(2), 	//name	
					c.getString(3),		//password
					c.getString(4),		//email
					c.getString(5), 	//surname
					c.getString(6), 	//birth
					c.getString(7), 	//gender
					c.getInt(8), 		//level
					c.getInt(9), 		//exp
					c.getInt(10),		//expNextLevel 
					c.getInt(11),		//gold
					c.getInt(12)>0		//fighting
				);
			
			DAO_Dandremid daoDandremid = new DAO_Dandremid(context, db);
			user.setDandremidList(daoDandremid.getUserDandremids(user));	
			
			DAO_Object daoObject = new DAO_Object(context, db);
			user.setObjectList(daoObject.getUserObjects(user,"%"));
						
			c.close();
			return user;
		}		
		
		c.close();
		return null;
	}
	
	
	public void updateUser(User user) {
		String sql = "UPDATE User SET" +
				" playerName ='"+user.getPlayerName()+"', name='"+user.getName()+"', email = '"+user.getEmail()+"', surname = '"+user.getSurname()+"', birth = '"+user.getBirth()+"', gender = '"+user.getGender()+"', level = "+user.getLevel()+", exp = "+user.getExp()+", expNextLevel = "+user.getExpNextLevel()+", fighting = " +(user.isFighting()? 1:0)+
				" WHERE id = "+user.getId();
		db.execSQL(sql);
		
		DAO_Dandremid dc = new DAO_Dandremid(context, db);		
		for (Dandremid c : user.getDandremidList()){
			if (c.getId()!=0){
				System.out.println("Actualizo Dandremid "+c.getName()+", "+c.getId());
				dc.updateDandremid(c);
			} else {
				System.out.println("Inserto Dadremid "+c.getName()+", "+c.getId());
				c.setId(dc.getNextNegativeId());
				dc.insertDandremid(user,c);
			}
		}
		
		DAO_Object daoObject = new DAO_Object(context, db);
		for (Object o : user.getObjectList()){
			daoObject.updateObject(user, o);
		}
		
	}
	
	
	public ArrayList<User> getUsersInLeague(League league) {
		ArrayList<User> list = new ArrayList<User>();
		
		String sql="SELECT U.id, U.playerName, U.name, U.password, U.email, U.surname, U.birth, U.gender, U.level, U.exp, U.expNextLevel, U.gold, U.fighting, UL.points " +
				" FROM User AS U, User_League AS UL" +
				" WHERE UL.User_id = U.id AND UL.League_id = "+league.getId();		
		Cursor c = db.rawQuery(sql, null);
		
		while(c.moveToNext()){
			User user = new User(
					c.getInt(0), 		//id
					getUserImage(), 	//image
					c.getString(1), 	//playerName
					c.getString(2), 	//name	
					c.getString(3),		//password
					c.getString(4),		//email
					c.getString(5), 	//surname
					c.getString(6), 	//birth
					c.getString(7), 	//gender
					c.getInt(8), 		//level
					c.getInt(9), 		//exp
					c.getInt(10),		//expNextLevel 
					c.getInt(11),		//gold
					c.getInt(12)>0		//fighting
				);
			
			user.setLeaguePoints(c.getInt(13));
			list.add(user);
		}		
		c.close();		
		return list;		
	}
		
	public void deleteAll() {
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM User");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de User");
		}
	}
	
	public void deleteLocalUser() {
		
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM User WHERE local='true'");
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de User");
		}
	}
	
	public void deleteAllExternalUsers() {
		
		try {
			db.execSQL("PRAGMA foreign_keys = ON");
			db.execSQL("DELETE FROM User WHERE local='false'");	
			db.execSQL("PRAGMA foreign_keys = OFF");
		} catch (SQLiteException e){
			Log.i("DELETE EXCEPTION", "Error en el borrado total de User");
		}
	}
	
	private Bitmap getUserImage() {		
		return BitmapFactory.decodeResource(context.getResources(), R.drawable.carnet);
	}

}
