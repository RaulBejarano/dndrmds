package dandremids.scr.daos;


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
	
	public void insertUser(User u){
		db.execSQL("INSERT INTO User (playerName, password, name, email, surname, birth, gender, level, exp, expNextlevel) " +
				"VALUES ('"+u.getPlayerName()+"', '', '"+u.getName()+"', '"+u.getEmail()+"', '"+u.getSurname()+"','"+u.getBirth()+"', '"+u.getGender()+"', "+u.getLevel()+" , "+u.getExp()+", "+u.getExpNextLevel()+")");
		
	}
	
	public void deleteAllUsers(){
		db.execSQL("DELETE FROM User");		
	}
	
	public void editUser(User u){
		String sql = "UPDATE User SET " +
				"playername='"+u.getPlayerName()+"'," +
				"name='"+u.getName()+"'," +
				"email='"+u.getEmail()+"'," +
				"surname='"+u.getSurname()+"'," +
				"birth='"+u.getBirth()+"'," +
				"gender='"+u.getGender()+"'," +
				" WHERE id = "+u.getId();
		
		db.execSQL(sql);
		
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


	public boolean doLogIn(String user, String password){		

		/////// ------------------------------------------------------------ ///////
		/////// ------------------ Hacer login en la nube ------------------ ///////
		/////// ------------------------------------------------------------ ///////
		
	
		User u = new User(
				0, 						//id
				getUserImage(), 		//image
				"Mi Player Name 123", 	//playerName
				"Hola", 				//name			
				"pepinillos",			//email
				"que ase?", 			//surname
				"18/8/2008", 			//birth
				"Male", 				//gender
				125, 					//level
				121212, 				//exp
				231232					//expNextLevel 
			);
		
		
		this.deleteAllUsers();
		this.insertUser(u);
		
		
		return true;
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
			return true;
		}
		c.close();
		return false;
	}
	
	
	
}
