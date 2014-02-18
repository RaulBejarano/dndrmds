package dandremids.src;

import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;

public class CombatActivity extends Activity {
	
	User local, rival;
	
	boolean endGame;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);				
		
		// 1. Get the data from LoadCombatActivity
		local = (User) this.getIntent().getExtras().get("local");
		rival = (User) this.getIntent().getExtras().get("rival");
		
		// This two for downthere are necesary because in the boundle we lost the images (too heavy)
		for (Dandremid d : local.getDandremidList()) {
			d.getDandremidBase().setImage(getDandremidImage(d.getDandremidBase().getName()));
		}
		
		for (Dandremid d : rival.getDandremidList()) {
			d.getDandremidBase().setImage(getDandremidImage(d.getDandremidBase().getName()));
		}	
		setContentView(new CombatView(this, getWindowManager().getDefaultDisplay(), local, rival));
	
		this.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				doGameLoopRoutine();			
			}			
		});
		
	}
	
	private Bitmap getDandremidImage (String name) {
		return BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("dndrmd_"+name.toLowerCase(), "drawable", getPackageName()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.combat, menu);
		return true;
	}
	
	public void doGameLoopRoutine() {
		endGame = false;
		
		Intent intent = new Intent(this, DialogCombatActivity.class);
		intent.putExtra("mode", 1);
		this.startActivityForResult(intent, 0);			
		
	}
	
	private void actionSelectedHandler(int selection){
		Intent intent;
		
		switch (selection){
			case 0:		// Attack				
				intent = new Intent(this, DialogCombatActivity.class);
				intent.putExtra("mode", 2);		// Attack List Mode
				intent.putExtra("attacks", new String [] {"attack 1","attack 2","attack 3"});
				this.startActivityForResult(intent, 0);	
				
				break;
			case 1:		// Use Object
				intent = new Intent(this, DialogCombatActivity.class);
				intent.putExtra("mode", 3);		// Object List Mode
				intent.putExtra("objects", new String [] {"object 1","object 2","object 3","object 4","object 5","object 6","object 7","object 8","object 9","object 10","object 11","object 12"});
				this.startActivityForResult(intent, 0);	
				
				break;
			case 2:		// Change Dandremid
				break;
			case 3:		// Escape
				break;
			default:
		}		
	}

	private void attackSelectedHandler(int selection) {
		Toast.makeText(this, ""+selection, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
			int mode = data.getIntExtra("mode", -1);
			switch (mode){
			case 0:
				break;
			case 1:
				actionSelectedHandler(data.getIntExtra("result", -1));
				break;	
			case 2:
				attackSelectedHandler(data.getIntExtra("result", -1));
				break;
			}
			
		}
	}

		
}
