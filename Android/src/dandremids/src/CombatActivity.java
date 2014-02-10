package dandremids.src;

import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.Window;

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
		
		while (!endGame){
			
		    
		    
		}			
		
	}
}
