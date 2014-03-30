package dandremids.src.daos;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dandremids.src.R;
import dandremids.src.model.Dandremid;
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
	
	public void insertUser(dandremids.src.model.db.User u){
		
		this.doLogOut();
		
		String sql = "INSERT INTO User (id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel, gold, fighting) VALUES " +
				"("+u.id+", '"+u.playerName+"', '"+u.name+"', '"+u.password+"', '"+u.email+"', '"+u.surname+"', '"+u.birth+"', '"+u.gender+"', "+u.level+", "+u.exp+", "+u.expNextLevel+", "+u.gold+", 'false' ) ";
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
	
	public User getCurrentUser(){ // Just one in the local database, fetch = position
		String sql="SELECT id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel, gold, fighting FROM User";		
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
	
	private Bitmap getUserImage() {
		
		return BitmapFactory.decodeResource(context.getResources(), R.drawable.carnet);
	}

	public boolean doLogOut(){		
		db.execSQL("DELETE FROM Dandremid_Attack");
		db.execSQL("DELETE FROM Dandremid_State");
		db.execSQL("DELETE FROM Dandremid");
		db.execSQL("DELETE FROM User_Object");
		db.execSQL("DELETE FROM User");		
		return true;
	}
		
	public void saveUser(User user) {
		String sql = "UPDATE User SET" +
				" playerName ='"+user.getPlayerName()+"', name='"+user.getName()+"', email = '"+user.getEmail()+"', surname = '"+user.getSurname()+"', birth = '"+user.getBirth()+"', gender = '"+user.getGender()+"', level = "+user.getLevel()+", exp = "+user.getExp()+", expNextLevel = "+user.getExpNextLevel()+", fighting = " +(user.isFighting()? 1:0)+
				" WHERE id = "+user.getId();
		db.execSQL(sql);
		
		DAO_Dandremid dc = new DAO_Dandremid(context, db);		
		for (Dandremid c : user.getDandremidList()){
			if (c.getId()!=0){
				dc.updateDandremid(c);
			} else {
				c.setId(dc.getNextNegativeId());
				dc.insertDandremid(user,c);
			}
		}
		
		DAO_Object daoObject = new DAO_Object(context, db);
		for (Object o : user.getObjectList()){
			daoObject.updateObject(user, o);
		}
		
	}
}
