package dandremids.src;

import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.customclasses.MyAlarm;
import dandremids.src.daos.DAO_CreatureBase;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Creature;
import dandremids.src.model.CreatureBase;
import dandremids.src.model.User;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadCombatActivity extends Activity {

	ProgressBar spinner;
	TextView message, action;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_combat);
		
		spinner = (ProgressBar) this.findViewById(R.id.activity_load_combat_spinner);
		spinner.animate();
		message = (TextView) this.findViewById(R.id.activity_load_combat_message);
		action = (TextView) this.findViewById(R.id.activity_load_combat_action);
		
		Bundle extra = this.getIntent().getExtras();
		
		if(extra!=null){
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			if(extra.getInt("MODE")==MyAlarm.WILD_COMBAT_MODE){
				// 1. Get User and selected Creature
				DAO_User daoUser = new DAO_User(this, db);
				User currentUser = daoUser.getCurrentUser();
				Creature userSelectedCreature = currentUser.getSelectedCreature();
								
				// 1. Get Creature Base
				DAO_CreatureBase dcb = new DAO_CreatureBase(this, db);				
				CreatureBase cb = dcb.getRandomCreatureBase();
				
				int level = currentUser.getLevel() - 3 + (int) (Math.random()*6);
				if (level < 1) {
					level = 1;
				}
				int exp = 0;
				int expNextLevel = 0;
				int strength = 0;
				int defense = 0;
				int speed = 0;
				int feed = 0;
				int maxFeed = 0;
				int starveSpeed = 0;
				int life = 0;
				int maxLife = 0;
				
				//Creature(int id, String name, int level, int exp, int expNextLevel,boolean selected, int strength, int defense, int speed, int feed, int maxFeed, int starveSpeed, int happiness, int life, int maxLife)
				Creature c = new Creature (-1, cb.getName(), level, exp, expNextLevel, false, strength, defense, speed, feed, maxFeed, starveSpeed, 0, life, maxLife);
				
			
			} else if (extra.getInt("MODE")==MyAlarm.TRAINER_COMBAT_MODE){
				
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.load_combat, menu);
		return true;
	}

}
