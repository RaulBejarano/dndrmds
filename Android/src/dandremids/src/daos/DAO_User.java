package dandremids.src.daos;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import dandremids.src.R;
import dandremids.src.model.Creature;
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
		
		sql = "INSERT INTO User (id, playerName, name, password, email, surname, birth, gender, level, exp, expNextLevel)" +
				"VALUES ("+u.id+", '"+u.playerName+"', '"+u.name+"', '', '"+u.email+"', '"+u.surname+"', '"+u.birth+"', '"+u.gender+"', "+u.level+", "+u.exp+", "+u.expNextLevel+") ";
		db.execSQL(sql);		
		
		sql = "DELETE FROM Creature";
		db.execSQL(sql);
		DAO_Creature daoCreature = new DAO_Creature(context, db);		
		for (dandremids.src.model.db.Creature c : u.creatures){
			daoCreature.insertCreature(c);
		}
	}
	
	public User getCurrentUser(){ // Just one in the database, fetch = position
		String sql="SELECT id, playerName, name, email, surname, birth, gender, level, exp, expNextLevel FROM User";
		
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
					c.getInt(9)			//expNextLevel 
				);
			
			DAO_Creature daoCreature = new DAO_Creature(context, db);
			user.setCreatureList(daoCreature.getUserCreatures(user));	
				
			c.close();
			return user;
		}		
		
		c.close();
		return null;
	}
	
	private Bitmap getUserImage() {
		
		return BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_text);
	}

	public boolean doLogOut(){		
		db.execSQL("REMOVE FROM Creature_Atack");
		db.execSQL("REMOVE FROM Creature_State");
		db.execSQL("REMOVE FROM Creature");
		db.execSQL("REMOVE FROM User");		
		return true;
	}
	
	public boolean isCurrentUser() {
		String sql="SELECT * FROM User";		
		Cursor c = db.rawQuery(sql, null);		
		if(c.moveToFirst()){
			c.close();
			return true;
		}
		c.close();
		return false;
	}

	
	public void updateUser(User user) {
		String sql = "UPDATE User SET " +
				"playerName ='"+user.getPlayerName()+"', name='"+user.getName()+"', email = '"+user.getEmail()+"', surname = '"+user.getSurname()+"', birth = '"+user.getBirth()+"', gender = '"+user.getGender()+"', level = "+user.getLevel()+", exp = "+user.getExp()+", expNextLevel = "+user.getExp()+" " +
				"WHERE id = "+user.getId();
		db.execSQL(sql);
		
		DAO_Creature dc = new DAO_Creature(context, db);		
		for (Creature c : user.getCreatureList()){
			dc.updateCreature(c);
		}
		
	}
	
	
	
}
