package dandremids.src;

import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.User;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class UserSettingsActivity extends Activity {

	DandremidsSQLiteHelper dsh;
	DAO_User daoUser;
	
	
	User user;
	
	
	EditText playerName, name, surname, email, birthday;
	RadioButton male, female;
	
	Button saveButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_settings);
		
		playerName = (EditText) this.findViewById(R.id.activity_user_settings_playerName);
		name = (EditText) this.findViewById(R.id.activity_user_settings_name);
		surname = (EditText) this.findViewById(R.id.activity_user_settings_surname);
		email= (EditText) this.findViewById(R.id.activity_user_settings_email);
		birthday = (EditText) this.findViewById(R.id.activity_user_settings_birthday);
		birthday.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onBirthClick();
				
			}
			
		});
		male = (RadioButton) this.findViewById(R.id.activity_user_settings_rb_male);
		female = (RadioButton) this.findViewById(R.id.activity_user_settings_rb_female);
		
		saveButton = (Button) this.findViewById(R.id.activity_user_settings_save);
		saveButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onClickSave();
			}
			
		});
		
		
		dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		daoUser = new DAO_User(this, dsh.getWritableDatabase());
		
		user = daoUser.getCurrentUser();
		playerName.setText(user.getPlayerName());
		name.setText(user.getName());
		surname.setText(user.getSurname());
		email.setText(user.getEmail());
		birthday.setText(user.getBirth());
		
		if(user.getGender().toUpperCase().compareTo("MALE")==0){
			male.setChecked(true);
		} else {
			female.setChecked(true);
		}
		
	}

	protected void onBirthClick() {
		// Create a pop up with a time date picker
		
	}

	protected void onClickSave() {
		user.setPlayerName(playerName.getText().toString());
		user.setName(name.getText().toString());
		user.setSurname(surname.getText().toString());
		user.setEmail(email.getText().toString());
		if (male.isChecked()){
			user.setGender("Male");
		}else{ 
			user.setGender("Female");
		}		
		daoUser.saveUser(user);
		Toast.makeText(this, "Saved successfully", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_settings, menu);
		return true;
	}

}
