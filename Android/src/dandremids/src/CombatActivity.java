package dandremids.src;

import dandremids.src.customclasses.MyAlarm;
import dandremids.src.model.Attack;
import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Window;
import android.widget.Toast;

public class CombatActivity extends Activity {
	
	int mode;
	
	User local, rival;
	Dandremid dLocal, dRival;
	CombatView combatView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);				
		
		// 1. Get the data from LoadCombatActivity
		mode = this.getIntent().getExtras().getInt("mode");
		local = (User) this.getIntent().getExtras().get("local");
		rival = (User) this.getIntent().getExtras().get("rival");
		
		// This two for downthere are necesary because in the boundle we lost the images (too heavy)
		for (Dandremid d : local.getDandremidList()) {
			d.getDandremidBase().setImage(getDandremidImage(d.getDandremidBase().getName()));
		}
		
		for (Dandremid d : rival.getDandremidList()) {
			d.getDandremidBase().setImage(getDandremidImage(d.getDandremidBase().getName()));
		}	
		
		dLocal = local.getSelectedDandremidList().get(0);
		dRival = rival.getSelectedDandremidList().get(0);
		
		combatView = new CombatView(this, getWindowManager().getDefaultDisplay(), dLocal, dRival);
		setContentView(combatView);
		
	}
	
	private Bitmap getDandremidImage (String name) {
		return BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("dndrmd_"+name.toLowerCase(), "drawable", getPackageName()));
	}

	
	
	public void launchActionSelectorDialog() {
		combatView.dialogLaunched=true;
		
		this.runOnUiThread(new Runnable(){
			@Override
			public void run() {
				Intent intent = new Intent(CombatActivity.this, DialogCombatActivity.class);
				intent.putExtra("mode", DialogCombatActivity.ACTION_SELECTION_MODE);
				CombatActivity.this.startActivityForResult(intent, 0);					
			}			
		});
	}
	
	private void actionSelectedHandler(int selection){
		Intent intent;
		
		switch (selection){
			case 0:		// Attack	
				intent = new Intent(this, DialogCombatActivity.class);
				intent.putExtra("mode", DialogCombatActivity.ATTACK_LIST_MODE);		// Attack List Mode
				intent.putExtra("dandremid", dLocal);
				this.startActivityForResult(intent, 0);	
				
				break;
			case 1:		// Use Object
				intent = new Intent(this, DialogCombatActivity.class);
				intent.putExtra("mode", DialogCombatActivity.OBJECT_LIST_MODE);		// Object List Mode
				intent.putExtra("objects", new String [] {"object 1","object 2","object 3","object 4","object 5","object 6","object 7","object 8","object 9","object 10","object 11","object 12"});
				this.startActivityForResult(intent, 0);	
				
				break;
			case 2:		// Change Dandremid
				break;
			case 3:		// Escape
				
				/* Hacer cosicas aqui */				
				finish();
				break;
			default: // User closes dialog
				combatView.dialogLaunched = false;
		}		
	}

	private void attackSelectedHandler(int selection) {
		if (selection != -1){
			Attack attack = dLocal.getAttackList().get(selection);
			Toast.makeText(this, "Selected Attack: "+attack.getName(), Toast.LENGTH_LONG).show();
			
			// Make attack animation
			
			// Perform attack logic
			dLocal.makeAttack(attack, dRival);	
			
			doFinalTurnStep();
		} else { // If User closes dialog, Launch action selector			
			this.launchActionSelectorDialog();
		}
	}
	
	private void doFinalTurnStep() {
		if (mode == MyAlarm.WILD_COMBAT_MODE){
			doWildDandremidAttack();
		} else {
			
		}
		
	}

	private void doWildDandremidAttack() {
		/*
		// Select Random Attack
		int size = dRival.getAttackList().size();		
		Attack attack = dRival.getAttackList().get((int)(Math.random() * size));
				
		Toast.makeText(this, "Enemy "+dRival.getName()+" uses "+attack.getName(), Toast.LENGTH_SHORT);
		// Make Attack Animation
		
		// Perform Attack Logic
		dRival.makeAttack(attack, dLocal);
		*/
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
			int mode = data.getIntExtra("mode", -1);
			switch (mode){				
				case DialogCombatActivity.ACTION_SELECTION_MODE:
					actionSelectedHandler(data.getIntExtra("result", -1));
					break;	
				case DialogCombatActivity.ATTACK_LIST_MODE:
					attackSelectedHandler(data.getIntExtra("result", -1));
					break;
			}
		}
	}
}
