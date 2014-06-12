package dandremids.src.daos;

import dandremids.src.model.db.User;
import dandremids.src.model.db.UserLeague;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO_LeagueData {
	Context context;
	SQLiteDatabase db;
	
	public DAO_LeagueData (Context context, SQLiteDatabase db) {
		super();
		this.context = context;
		this.db = db;
	}
	
	public void insertLeagueData (dandremids.src.model.db.User user, dandremids.src.model.db.LeagueData ld) {
		DAO_League daoLeague = new DAO_League(context, db);
		daoLeague.deleteAll();
		daoLeague.insertLeague(ld.league);
		
		DAO_User daoUser = new DAO_User(context, db);
		daoUser.deleteAllExternalUsers();
		for (User u : ld.users){
			if (user.id != u.id) daoUser.insertExternalUser(u);
		}
		
		DAO_UserLeague daoUserLeague = new DAO_UserLeague(context, db);
		daoUserLeague.deleteAll();
		for (UserLeague ul : ld.userLeague) {
			daoUserLeague.insertUserLeague(ul);
		}
				
	}

	public void deleteAll() {
		DAO_League daoL = new DAO_League(context, db);
		daoL.deleteAll();
	}
}
