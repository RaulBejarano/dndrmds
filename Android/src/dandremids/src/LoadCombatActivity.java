package dandremids.src;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.customclasses.DandremidsREST;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.customclasses.MyAlarm;
import dandremids.src.daos.DAO_DandremidBase;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Dandremid;
import dandremids.src.model.DandremidBase;
import dandremids.src.model.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.Window;
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
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_load_combat);
				
		
		spinner = (ProgressBar) this.findViewById(R.id.activity_load_combat_spinner);
		spinner.animate();
		message = (TextView) this.findViewById(R.id.activity_load_combat_message);
		action = (TextView) this.findViewById(R.id.activity_load_combat_action);
		topImage = (ImageView) this.findViewById(R.id.activity_load_combat_top_image);
		bottomImage = (ImageView) this.findViewById(R.id.activity_load_combat_bottom_image);
		
		Bundle extra = this.getIntent().getExtras();
		
		if(extra!=null){
			LoadCombatTask task = new LoadCombatTask(extra.getInt("MODE"));
			task.execute();			
		} else { //          <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Borrar este else
			LoadCombatTask task = new LoadCombatTask(MyAlarm.WILD_COMBAT_MODE);
			task.execute();
		}
		
	}

	private void startWildCombatMode () {
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		
		// 1. Get User and selected Dandremids
		DAO_User daoUser = new DAO_User(this, db);
		final User currentUser = daoUser.getCurrentUser();
				
		// 2. Set up Load User Interface	
		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				topImage.setImageBitmap( BitmapFactory.decodeResource(getResources(), R.drawable.notification_wild_dandremid_icon));
				bottomImage.setImageBitmap(currentUser.getImage());
				
			}
			
		});
		
		// 3. Get Dandremid Base
		DAO_DandremidBase dcb = new DAO_DandremidBase(this, db);				
		DandremidBase cb = dcb.getRandomDandremidBase();
			
		// 4. Close database connection
		db.close();
		
		// 5. Set Dandremid parameters
		int level = currentUser.getLevel() - 3 + (int) (Math.random()*6);
		if (level < 1) {
			level = 1;
		}
		int exp = 0;
		int expNextLevel = 100;
		int strength = 0;
		int defense = 0;
		int speed = 0;
		int feed = 0;
		int maxFeed = 0;
		int life = 100;
		int maxLife = 100;
		
		//Dandremid(int id, String name, int level, int exp, int expNextLevel,int selected, int strength, int defense, int speed, int feed, int maxFeed, int happiness, int life, int maxLife)
		Dandremid d = new Dandremid (-1, cb.getName(), level, exp, expNextLevel, 1, strength, defense, speed, feed, maxFeed, 0, life, maxLife);
		d.setDandremidBase(cb);
		//User(int id, Bitmap image, String playerName, String name, String email, String surname, String birth, String gender, int level, int exp, int expNextLevel) {
		User u = new User (-1, null, null, null, null, null, null, null, 0,0,0);
		ArrayList<Dandremid> auxList = new ArrayList<Dandremid>();
		auxList.add(d);
		u.setDandremidList(auxList);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Intent i = new Intent(this, CombatActivity.class);
		i.putExtra("rival", u);
		i.putExtra("local", currentUser);
		this.startActivity(i);
		this.finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.load_combat, menu);
		return true;
	}
	
	public class LoadCombatTask extends AsyncTask<String, Void, String> {
		
		int mode;
		
		public LoadCombatTask(int i){
			super();
			this.mode=i;
		}
		
		@Override
		protected void onPreExecute() {}
		
		@Override
		protected String doInBackground(String... urls) {
			if (mode == MyAlarm.WILD_COMBAT_MODE){
				startWildCombatMode();					
			} else if (mode == MyAlarm.TRAINER_COMBAT_MODE){
				
			} 			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			
		}
	}
}
