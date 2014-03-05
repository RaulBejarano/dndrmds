package dandremids.src;

import java.util.ArrayList;

import dandremids.src.customclasses.MyAlarm;
import dandremids.src.model.Attack;
import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Window;
import android.widget.Toast;

public class CombatActivity extends Activity {
	
	int mode;
	boolean end, localTurn;
	
	User local, rival;
	Dandremid dLocal, dRival;
	CombatView combatView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);				
		end = false;
		
		// 1. Get the data from LoadCombatActivity
		mode = this.getIntent().getExtras().getInt("mode");
		local = (User) this.getIntent().getExtras().get("local");
		rival = (User) this.getIntent().getExtras().get("rival");
		
		// This two for downthere are necesary because in the boundle we lost the images (too heavy)
		for (Dandremid d : local.getDandremidList()) {
			d.getDandremidBase().refreshDandremidImage(this);
		}
		
		for (Dandremid d : rival.getDandremidList()) {
			d.getDandremidBase().refreshDandremidImage(this);
		}	
		
		dLocal = local.getSelectedDandremidList().get(0);
		dRival = rival.getSelectedDandremidList().get(0);
		
		if (dLocal.getSpeed() > dRival.getSpeed()){
			localTurn=true;
		} else if (dLocal.getSpeed() < dRival.getSpeed()) {
			localTurn=false;
		}else {
			int i = (int)(Math.random()*2);
			if (i==0){
				localTurn=true;
			}else{
				localTurn=false;
			}
		}
		
		combatView = new CombatView(this, getWindowManager().getDefaultDisplay(), dLocal, dRival);
		setContentView(combatView);
		
	}
	
	
	
	
	public void launchActionSelectorDialog() {
		if(localTurn){
			combatView.dialogLaunched=true;
			Intent intent = new Intent(CombatActivity.this, DialogCombatActivity.class);
			intent.putExtra("mode", DialogCombatActivity.ACTION_SELECTION_MODE);
			CombatActivity.this.startActivityForResult(intent, 0);
		} else {
			Toast.makeText(this, "Not your turn", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void launchEscapeDialog(){
		Intent intent = new Intent(this, DialogCombatActivity.class);
		intent.putExtra("mode", DialogCombatActivity.EXIT_MODE);
		this.startActivityForResult(intent, 0);
	}
	
	
	private void doFinalTurnStep() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
		// Check life
		if (dRival.getLife() == 0){
			if (rival.getAvailableDandremids() > 0){
				// elegir otro
			} else {
				end = true;
			}
		}
		if (dLocal.getLife() == 0){
			if (local.getAvailableDandremids() > 0) {
				Intent intent = new Intent(this, DialogCombatActivity.class);
				intent.putExtra("mode", DialogCombatActivity.CHANGE_DANDREMID_MODE);
				intent.putExtra("cancelable", false);
				intent.putParcelableArrayListExtra("dandremids", (ArrayList<Dandremid>) local.getSelectedDandremidList());
				this.startActivityForResult(intent, 0);
			} else {
				end = true;
			}			
		}
					
		localTurn=!localTurn;
		
		if (end){
			finish();
		} else {
			if (localTurn){
				
			}else{
				combatView.dialogLaunched = false;
				if (mode == MyAlarm.WILD_COMBAT_MODE) {
					this.doWildDandremidAttack();
				} else {
					// doRivalUserTurn();
				}
			}
		}		
	}

	private void doWildDandremidAttack() {
		
		// Select Random Attack
		int size = dRival.getAttackList().size();		
		Attack attack = dRival.getAttackList().get((int)(Math.random() * size));
				
		Toast.makeText(this, "Enemy "+dRival.getName()+" uses "+attack.getName(), Toast.LENGTH_SHORT).show();
		// Make Attack Animation
		
		// Perform Attack Logic
		dRival.makeAttack(attack, dLocal);
		this.doFinalTurnStep();
	}
	
	public void onBackPressed(){
		this.launchEscapeDialog();
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if (end){
			if (local.getAvailableDandremids() > 0) {
				Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show();
			} else if (rival.getAvailableDandremids() >0) {
				Toast.makeText(this, "You lose", Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Draw", Toast.LENGTH_LONG).show();
			}			
		} else {
			Toast.makeText(this, "You runned away like a chicken", Toast.LENGTH_LONG).show();
		}		
		
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
				case DialogCombatActivity.CHANGE_DANDREMID_MODE:
					changeDandremidHandler(data.getIntExtra("result", -1));
					break;
				case DialogCombatActivity.EXIT_MODE:
					exitHandler(data.getIntExtra("result", -1));
					break;
				default:
			}
		}
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
				intent = new Intent(this, DialogCombatActivity.class);
				intent.putExtra("mode", DialogCombatActivity.CHANGE_DANDREMID_MODE);
				intent.putParcelableArrayListExtra("dandremids", (ArrayList<Dandremid>) local.getSelectedDandremidList());
				this.startActivityForResult(intent, 0);
				break;
			case 3:		// Escape
				this.launchEscapeDialog();
				break;
			default: // User closes dialog
				combatView.dialogLaunched = false;
		}		
	}

	private void attackSelectedHandler(int selection) {
		if (selection != -1){
			Attack attack = dLocal.getAttackList().get(selection);
			Toast.makeText(this, "Selected Attack: "+attack.getName(), Toast.LENGTH_SHORT).show();			
			
			// Make attack animation
			
			// Perform attack logic
			dLocal.makeAttack(attack, dRival);	
			
			doFinalTurnStep();
		} else { // If User closes dialog, Launch action selector			
			this.launchActionSelectorDialog();
		}
	}
	
	private void changeDandremidHandler(int selection) {
		if (selection != -1){
			final Dandremid dSelected=local.getSelectedDandremidList().get(selection);
			 if (dSelected.getLife()==0) {
				Toast.makeText(this, dSelected.getName()+" is too tired to fight now.", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(this, DialogCombatActivity.class);
				intent.putExtra("mode", DialogCombatActivity.CHANGE_DANDREMID_MODE);
				intent.putExtra("cancelable", false);
				intent.putParcelableArrayListExtra("dandremids", (ArrayList<Dandremid>) local.getSelectedDandremidList());
				this.startActivityForResult(intent, 0);
			} else if (dSelected.equals(dLocal)){
				Toast.makeText(this, dLocal.getName()+" is already fighting!!", Toast.LENGTH_LONG).show();
				this.launchActionSelectorDialog();
				return;
			} else {
				// Make change animation
				dLocal = dSelected;
				// Update View
				combatView.changeLocalDandremid(dLocal);
				doFinalTurnStep();
			}
			
		} else {
			this.launchActionSelectorDialog();
		}
	}
	
	private void exitHandler(int selection) {
		if (selection != -1){ // Exit
			saveData();
			finish();
		} else {
			combatView.dialogLaunched=false;
		}
	}
	
	private void saveData(){
		
	}
}
