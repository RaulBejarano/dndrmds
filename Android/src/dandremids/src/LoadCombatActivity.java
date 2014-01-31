package dandremids.src;

import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.customclasses.MyAlarm;
import dandremids.src.daos.DAO_DandremidBase;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Dandremid;
import dandremids.src.model.DandremidBase;
import dandremids.src.model.User;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadCombatActivity extends Activity {

	ProgressBar spinner;
	TextView message, action;
	ImageView topImage, bottomImage;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_combat);
		
		spinner = (ProgressBar) this.findViewById(R.id.activity_load_combat_spinner);
		spinner.animate();
		message = (TextView) this.findViewById(R.id.activity_load_combat_message);
		action = (TextView) this.findViewById(R.id.activity_load_combat_action);
		topImage = (ImageView) this.findViewById(R.id.activity_load_combat_top_image);
		bottomImage = (ImageView) this.findViewById(R.id.activity_load_combat_bottom_image);
		
		Bundle extra = this.getIntent().getExtras();
		
		if(extra!=null){
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			if(extra.getInt("MODE")==MyAlarm.WILD_COMBAT_MODE){
								
				// 1. Get User and selected Dandremids
				DAO_User daoUser = new DAO_User(this, db);
				User currentUser = daoUser.getCurrentUser();
				//Dandremid userSelectedDandremid = currentUser.getSelectedDandremid();
						
				// 2. Set up Load User Interface				
				topImage.setImageBitmap( BitmapFactory.decodeResource(this.getResources(), R.drawable.notification_wild_dandremid_icon));
				bottomImage.setImageBitmap(currentUser.getImage());
				
				// 3. Get Dandremid Base
				DAO_DandremidBase dcb = new DAO_DandremidBase(this, db);				
				DandremidBase cb = dcb.getRandomDandremidBase();
								
				// 4. Set Dandremid parameters
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
				int life = 0;
				int maxLife = 0;
				
				//Dandremid(int id, String name, int level, int exp, int expNextLevel,int selected, int strength, int defense, int speed, int feed, int maxFeed, int happiness, int life, int maxLife)
				Dandremid c = new Dandremid (-1, cb.getName(), level, exp, expNextLevel, -1, strength, defense, speed, feed, maxFeed, 0, life, maxLife);
				c.setDandremidBase(cb);
				
								
				
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
