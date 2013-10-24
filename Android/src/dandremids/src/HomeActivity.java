package dandremids.src;

import java.util.ArrayList;

import dandremids.scr.daos.DAO_User;
import dandremids.src.customclasses.CreatureListAdapter;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.customclasses.MyAlarm;
import dandremids.src.model.Creature;
import dandremids.src.model.User;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Activity {

	User player;
	
	TextView playerName;
	TextView level;
	ImageView image;
	TextView name;
	TextView surname;
	TextView age;
	TextView gender;
	ImageButton editName;
	ImageButton editSurname;
	ImageButton editAge;
	ImageButton editGender;
	ProgressBar expBar;
	TextView expText;
	ListView creatureList;
	Button scanButton;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		setContentView(R.layout.activity_home);
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		DAO_User daoUser = new DAO_User(this, dsh.getWritableDatabase());
		player = daoUser.getCurrentUser();
		
		
		playerName = (TextView) this.findViewById(R.id.activity_home_player_name);
		level= (TextView) this.findViewById(R.id.activity_home_level);
		image = (ImageView) this.findViewById(R.id.activity_home_image);
		name = (TextView) this.findViewById(R.id.activity_home_name);
		surname = (TextView) this.findViewById(R.id.activity_home_surname);
		age = (TextView) this.findViewById(R.id.activity_home_age);
		gender = (TextView) this.findViewById(R.id.activity_home_gender);
		editName = (ImageButton) this.findViewById(R.id.activity_home_edit_name);
		editSurname = (ImageButton) this.findViewById(R.id.activity_home_edit_surname);
		editAge = (ImageButton) this.findViewById(R.id.activity_home_edit_age);
		editGender = (ImageButton) this.findViewById(R.id.activity_home_edit_gender);
		expBar = (ProgressBar) this.findViewById(R.id.activity_home_exp);
		expText = (TextView) this.findViewById(R.id.activity_home_exp_text);
		creatureList = (ListView) this.findViewById(R.id.activity_home_creature_list);
		scanButton = (Button) this.findViewById(R.id.activity_home_scan_button);
		
		playerName.setText(player.getPlayerName());
		level.setText(player.getLevel()+"");
		image.setImageBitmap(player.getImage());
		name.setText(player.getName());
		surname.setText(player.getSurname());
		age.setText(player.getBirth());
		gender.setText(player.getGender());
		editName.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onEditNameClick(v);
				
			}			
		});
		editSurname.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onEditSurnameClick(v);
				
			}			
		});
		editAge.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onEditAgeClick(v);
				
			}			
		});
		editGender.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onEditGenderClick(v);
				
			}			
		});
		
		expBar.setMax(player.getExpNextLevel());
		expBar.setProgress(player.getExp());
		expText.setText(player.getExp()+"/"+player.getExpNextLevel());
		
		creatureList.setAdapter(new CreatureListAdapter(this, player.getCreatureList()));		
		creatureList.setOnItemClickListener(new OnItemClickListener (){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				onCreatureListItemClick(pos);				
			}			
		});
		creatureList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				onCreatureListItemLongClick(pos);
				return false;
			}
		});
		
		scanButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onClickScanButton();				
			}
		});
		
		// "Wild Creature" Notification System		
		MyAlarm.setNextAlarm(this, 0, 1);		
	}

	

	protected void onCreatureListItemLongClick(int pos) {
		
		ArrayList<Creature> list = player.getCreatureList();
		for (Creature c : list) {
			c.setSelected(false);
		}
		list.get(pos).setSelected(true);
		creatureList.setAdapter(new CreatureListAdapter(this, player.getCreatureList()));		
	}

	protected void onCreatureListItemClick(int pos) {
		Intent i = new Intent(this, CreatureActivity.class);
		i.putExtra("CREATURE_ID", this.player.getCreatureList().get(pos).getId());
		this.startActivity(i);
	}

	
	// Edition Methods	
	protected void onEditGenderClick(View v) {
		// TODO Auto-generated method stub
		
	}

	protected void onEditAgeClick(View v) {
		// TODO Auto-generated method stub
		
	}

	protected void onEditSurnameClick(View v) {
		// TODO Auto-generated method stub
		
	}

	protected void onEditNameClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	protected void onClickScanButton() {
		
		Intent intent = new Intent(this, CombatActivity.class);
		startActivityForResult(intent, 0);
		
		/*
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);*/
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String code = intent.getStringExtra("SCAN_RESULT");
				Toast.makeText(this, code, Toast.LENGTH_LONG).show();
				Intent i = new Intent (this, CombatActivity.class);
				i.putExtra("MODE", MyAlarm.TRAINER_COMBAT_MODE);
				this.startActivity(i);
				
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
