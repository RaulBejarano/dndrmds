package dandremids.src;

import java.util.ArrayList;


import dandremids.src.alarms.WildDandremidAlarm;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_Dandremid;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Attack;
import dandremids.src.model.Object;
import dandremids.src.model.Dandremid;
import dandremids.src.model.ElementElement;
import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

public class CombatActivity extends Activity {
	
	int mode;
	boolean end, localTurn;
	
	User local, rival;
	Dandremid dLocal, dRival;
	CombatView combatView;
	
	ArrayList<ElementElement> elementElementList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);				
		end = false;
		
		// 1. Get the data from LoadCombatActivity
		mode = this.getIntent().getExtras().getInt("mode");
		local = (User) this.getIntent().getExtras().get("local");
		rival = (User) this.getIntent().getExtras().get("rival");
		elementElementList= this.getIntent().getExtras().getParcelableArrayList("elementRelations");
		
				
		// This two for downthere are necesary because in the boundle we lost the images (too heavy)
		for (Dandremid d : local.getDandremidList()) {
			d.getDandremidBase().refreshDandremidImage(this);
			
		}
		
		for (Dandremid d : rival.getSelectedDandremidList()) {
			d.getDandremidBase().refreshDandremidImage(this);
		}	
		
		// Dandremid Selection
		for (Dandremid d : local.getSelectedDandremidList()) {
			if (d.getLife()>0){
				dLocal = d;
				break;
			}
			
		}
		
		for (Dandremid d : rival.getDandremidList()) {
			if (d.getLife()>0){
				dRival = d;
				break;
			}
		}	
	
	
		if (dLocal.getSpeed() > dRival.getSpeed()){
			localTurn=true;
		} else if (dLocal.getSpeed() < dRival.getSpeed()) {
			localTurn=false;
			if (mode == WildDandremidAlarm.WILD_COMBAT_MODE){
				this.doWildDandremidAttack();
			} else {
				// doRivalTurn
			}
		}else {
			int i = (int)(Math.random()*2);
			if (i==0){
				localTurn=true;
			}else{
				localTurn=false;
			}
		}
		
			
		combatView = new CombatView(this, getWindowManager().getDefaultDisplay(), dLocal, dRival);
		combatView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if (!combatView.dialogLaunched){
					launchActionSelectorDialog();
				}				
			}
			
		});
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
			this.makeExit();
		} else {
			if (localTurn){
				
			}else{
				combatView.dialogLaunched = false;
				if (mode == WildDandremidAlarm.WILD_COMBAT_MODE) {
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
		dRival.makeAttack(this,attack, dLocal, elementElementList);
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
				case DialogCombatActivity.OBJECT_LIST_MODE:
					objectSelectedHandler(data.getIntExtra("result", -1));
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
				intent.putParcelableArrayListExtra("objects", local.getObjectList());
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
			dLocal.makeAttack(this, attack, dRival, elementElementList);	
			
			doFinalTurnStep();
		} else { // If User closes dialog, Launch action selector			
			this.launchActionSelectorDialog();
		}
	}
	
	private void objectSelectedHandler(int selection) {
		if (selection != -1){
			Object co = local.getObjectList().get(selection);
			
			if(co.isTrap()){
				// Do trap logic
				boolean trapped = (dRival.getLife() / dRival.getMaxLife()) < ( 0.1 * co.getStrength() * (Math.random() * 0.7 + 0.7)) ;
				trapped=true;
				if (trapped) {
					// Animation
					
					dRival.setSelected(-1);
					local.getDandremidList().add(dRival);
					Toast.makeText(this, "Trapped", Toast.LENGTH_LONG).show();
					end=true;
				} else {
					// Animation
					Toast.makeText(this, "NOT Trapped", Toast.LENGTH_LONG).show();
				}
			} else {
				// Animation
				
				dLocal.tmp_strength=dLocal.tmp_strength+co.getStrength();
				dLocal.tmp_defense=dLocal.tmp_defense+co.getDefense();
				dLocal.tmp_speed=dLocal.tmp_speed+co.getSpeed();
				
				dLocal.setLife(dLocal.getLife()+co.getLife());
				if (dLocal.getLife()>dLocal.getMaxLife()) dLocal.setLife(dLocal.getMaxLife());
			}
			
			co.setQuantity(co.getQuantity()-1);
			
			doFinalTurnStep();
		} else {
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
			makeExit();
		} else {
			combatView.dialogLaunched=false;
		}
	}
	
	private void makeExit() {
		local.setFighting(false);
		saveLocalData();
		finish();
	}

	private void saveLocalData(){
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		
		DAO_User daoU = new DAO_User(this,db);
		daoU.updateUser(local);
		
		db.close();
		dsh.close();
	}
}
