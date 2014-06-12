package dandremids.src;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dandremids.src.customclasses.DandremidsREST;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	TextView user;
	TextView password;
	Button login;
	
	DandremidsSQLiteHelper dsh;
	SQLiteDatabase db;
	DAO_User daoUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		db = dsh.getWritableDatabase();
		daoUser = new DAO_User(this, db);
		
		if(daoUser.getLocalUser()!=null){
			goToHome();			
			return;
		} 
		
		user = (TextView) this.findViewById(R.id.main_user_text);
		password = (TextView) this.findViewById(R.id.main_password_text);
		
		login = (Button) this.findViewById(R.id.main_login_button);
		login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				DoBackgroundTask task = new DoBackgroundTask(DoBackgroundTask.LOGIN);
				task.execute();
			}			
		});
				
		DoBackgroundTask task = new DoBackgroundTask(DoBackgroundTask.UPDATE_GAME_DATA);
		task.execute();				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
		
	public void showToast(String text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}

	public void goToHome() {		
		db.close();	
		dsh.close();
		Intent i=new Intent(this, HomeActivity.class);
		this.startActivity(i);
		this.finish();		
	}
	
	private String encriptPassword (String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");		
			md.update(password.getBytes());			
			byte [] mdBytes = md.digest();			
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<mdBytes.length; i++){
				sb.append(Integer.toHexString(0XFF & mdBytes[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public class DoBackgroundTask extends AsyncTask<String, Void, String> {
		
		public static final int UPDATE_GAME_DATA = 0, LOGIN = 1;
		
		private ProgressDialog d;
		private int mode;
		
		public DoBackgroundTask(int mode){
			super();
			this.mode=mode;
		}
		
		@Override
		protected void onPreExecute() {
			d = new ProgressDialog(LoginActivity.this);
			if (mode==LOGIN){
				d.setMessage("Logging in...");
				
			} else if (mode==UPDATE_GAME_DATA){
				d.setMessage("Please, wait while application makes first configuration");				
			}		
			d.setCancelable(false);
			d.show();
		}

		
		@Override
		protected String doInBackground(String... urls) {
			DandremidsREST dr = new DandremidsREST(LoginActivity.this, LoginActivity.this.db);
			
			if (mode==LOGIN) {
				return dr.doLogin(user.getText().toString(),encriptPassword(password.getText().toString()));
			} else if (mode == UPDATE_GAME_DATA) {
				return dr.updateGameData();
			}
			return null;						
		}

		@Override
		protected void onPostExecute(String result) {
			d.dismiss();			
			if (mode==LOGIN && result!=null) {							
				goToHome();
			} else if (mode==UPDATE_GAME_DATA && result==null) {
				showToast("Error while making first configuration. \nPlease, check your internet connection");
				finish();
			}
		}
	}
}
