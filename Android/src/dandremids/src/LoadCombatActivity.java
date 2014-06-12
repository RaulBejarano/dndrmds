package dandremids.src;

import java.util.ArrayList;

import dandremids.src.alarms.WildDandremidAlarm;
import dandremids.src.customclasses.DandremidsREST;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_ElementElement;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Dandremid;
import dandremids.src.model.ElementElement;
import dandremids.src.model.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadCombatActivity extends Activity {

	ProgressBar spinner;
	TextView message, action;
	ImageView rivalImage, localImage;	
	TextView localName, localLevel, rivalName, rivalLevel;
		
	LoadCombatTask task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);	
		setContentView(R.layout.activity_load_combat);
				
		
		spinner = (ProgressBar) this.findViewById(R.id.activity_load_combat_spinner);
		spinner.animate();
		message = (TextView) this.findViewById(R.id.activity_load_combat_message);
		action = (TextView) this.findViewById(R.id.activity_load_combat_action);
		rivalImage = (ImageView) this.findViewById(R.id.activity_load_combat_rival_image);
		localImage = (ImageView) this.findViewById(R.id.activity_load_combat_local_image);
		localName = (TextView) this.findViewById(R.id.activity_load_combat_local_name);
		localLevel = (TextView) this.findViewById(R.id.activity_load_combat_local_level);
		rivalName = (TextView) this.findViewById(R.id.activity_load_combat_rival_name);
		rivalLevel = (TextView) this.findViewById(R.id.activity_load_combat_rival_level);
		
		Bundle extra = this.getIntent().getExtras();
		
		if(extra!=null){
			task = new LoadCombatTask(extra.getInt("MODE"));
			task.execute();			
		} else { //          <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Borrar este else
			task = new LoadCombatTask(WildDandremidAlarm.WILD_COMBAT_MODE);
			task.execute();
		}
		
	}

	private void startWildCombatMode () {
		
		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				action.setText("Getting user's information");
			}
			
		});
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		
		// 0. Get Game Data
		DAO_ElementElement daoElementElement = new DAO_ElementElement(this,db);
		ArrayList<ElementElement> elementRelations = daoElementElement.getAllElementsRelations();
		
		// 1. Get User and selected Dandremids
		DAO_User daoUser = new DAO_User(this, db);
		final User currentUser = daoUser.getLocalUser();
		
		DandremidsREST dr = new DandremidsREST(this, db);
		dr.saveUser(currentUser.toDBUser());
		
		if (currentUser.getAvailableDandremids()==0) this.onBackPressed();
		
		
		currentUser.setFighting(true);
		daoUser.updateUser(currentUser);
		
		// 2. Set up Load User Interface	
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				rivalImage.setImageBitmap( BitmapFactory.decodeResource(getResources(), R.drawable.notification_wild_dandremid_icon));
				rivalName.setText("Wild Dandremid");
				rivalLevel.setText("?");
				
				localImage.setImageBitmap(currentUser.getImage());
				localName.setText(currentUser.getPlayerName());
				localLevel.setText(currentUser.getLevel()+"");
				
				action.setText("Analizing dandremid with Wikimid");
				
			}			
		});
		
		final Dandremid d = Dandremid.getWildDandremid(this, currentUser, db);
		
		runOnUiThread(new Runnable(){

			@Override
			public void run() {
				rivalImage.setImageBitmap(d.getDandremidBase().getImage());
				rivalName.setText(d.getName());
				rivalLevel.setText(d.getLevel()+"");
				action.setText("Starting combat");
			}
			
		});
		
		
		//User(int id, Bitmap image, String playerName, String name, String email, String surname, String birth, String gender, int level, int exp, int expNextLevel) {
		User u = new User (-1, null, null, null, null, null, null, null, null, 0,0,0,0,true);
		ArrayList<Dandremid> auxList = new ArrayList<Dandremid>();
		auxList.add(d);
		u.setDandremidList(auxList);
		
		db.close();
		dsh.close();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
						
		Intent i = new Intent(this, CombatActivity.class);
		i.putExtra("mode", WildDandremidAlarm.WILD_COMBAT_MODE);
		i.putParcelableArrayListExtra("elementRelations", elementRelations);
		i.putExtra("rival", u);
		i.putExtra("local", currentUser);
		
		this.startActivity(i);	
		this.finish();
	}
	
	
	
	@Override
	public void onBackPressed() {
		
		task.cancel(true);
		
		super.onBackPressed();
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
			if (mode == WildDandremidAlarm.WILD_COMBAT_MODE){
				startWildCombatMode();					
			} else if (mode == WildDandremidAlarm.TRAINER_COMBAT_MODE){
				
			} 			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			
		}
	}
}
