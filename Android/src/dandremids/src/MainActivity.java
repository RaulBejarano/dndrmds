package dandremids.src;

import dandremids.scr.daos.DAO_User;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView user;
	TextView password;
	Button login;
	
	
	DAO_User daoUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		daoUser = new DAO_User(this, dsh.getWritableDatabase());
		
		if(daoUser.isCurrentUser()){
			Intent i=new Intent(this, HomeActivity.class);
			this.startActivity(i);
			this.finish();
		} else {
			Toast.makeText(this, "no existe", Toast.LENGTH_LONG).show();
		}
				
		user = (TextView) this.findViewById(R.id.main_user_text);
		password = (TextView) this.findViewById(R.id.main_password_text);
		
		login = (Button) this.findViewById(R.id.main_login_button);
		login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				doLogin();				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void doLogin(){
		if (daoUser.doLogIn(user.getText().toString(),password.getText().toString())){
			Intent i = new Intent(this, HomeActivity.class);
			this.startActivity(i);
			this.finish();
		} else {
			Toast.makeText(this, "[ERROR] Wrong user or password", Toast.LENGTH_LONG).show();
		}
		
		
	}

}
