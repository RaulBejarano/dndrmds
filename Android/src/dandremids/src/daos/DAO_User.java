package dandremids.src.daos;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dandremids.src.R;
import dandremids.src.model.Dandremid;
import dandremids.src.model.User;

public class DAO_User {

	private SQLiteDatabase db;
	private Context context;

	public DAO_User(Context context, SQLiteDatabase db) {
		super();
		this.context=context;
		this.db = db;
	}
	
	public void insertUser(dandremids.src.model.db.User u){
		
		String sql = "DELETE FROM User";
		db.execSQL(sql);
		
		sql = "INSERT INTO User (id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel, fighting)" +
				"VALUES ("+u.id+", '"+u.playerName+"', '"+u.name+"', '', '"+u.email+"', '"+u.surname+"', '"+u.birth+"', '"+u.gender+"', "+u.level+", "+u.exp+", "+u.expNextLevel+", 'false' ) ";
		db.execSQL(sql);		
		
		DAO_Dandremid daoDandremid = new DAO_Dandremid(context, db);
		daoDandremid.deleteAll();
		for (dandremids.src.model.db.Dandremid c : u.dandremids){
			daoDandremid.insertDandremid(c);
		}
	}
	
	public User getCurrentUser(){ // Just one in the local database, fetch = position
		String sql="SELECT id, playerName, name, email, surname, birth, gender, level, exp, expNextLevel, fighting FROM User";		
		Cursor c = db.rawQuery(sql, null);
		
		if(c.moveToFirst()){
			User user = new User(
					c.getInt(0), 		//id
					getUserImage(), 	//image
					c.getString(1), 	//playerName
					c.getString(2), 	//name			
					c.getString(3),		//email
					c.getString(4), 	//surname
					c.getString(5), 	//birth
					c.getString(6), 	//gender
					c.getInt(7), 		//level
					c.getInt(8), 		//exp
					c.getInt(9),		//expNextLevel 
					c.getInt(10)>0		//fighting
				);
			
			DAO_Dandremid daoDandremid = new DAO_Dandremid(context, db);
			user.setDandremidList(daoDandremid.getUserDandremids(user));	
			
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
		db.execSQL("REMOVE FROM Dandremid_Atack");
		db.execSQL("REMOVE FROM Dandremid_State");
		db.execSQL("REMOVE FROM Dandremid");
		db.execSQL("REMOVE FROM User");		
		return true;
	}
		
	public void updateUser(User user) {
		String sql = "UPDATE User SET" +
				" playerName ='"+user.getPlayerName()+"', name='"+user.getName()+"', email = '"+user.getEmail()+"', surname = '"+user.getSurname()+"', birth = '"+user.getBirth()+"', gender = '"+user.getGender()+"', level = "+user.getLevel()+", exp = "+user.getExp()+", expNextLevel = "+user.getExpNextLevel()+", fighting = " +(user.isFighting()? 1:0)+
				" WHERE id = "+user.getId();
		db.execSQL(sql);
		
		DAO_Dandremid dc = new DAO_Dandremid(context, db);		
		for (Dandremid c : user.getDandremidList()){
			dc.updateDandremid(c);
		}
		
	}
}
