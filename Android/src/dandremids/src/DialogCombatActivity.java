package dandremids.src;


import java.util.List;

import dandremids.src.customclasses.AttackListAdapter;
import dandremids.src.customclasses.DandremidsListAdapter;
import dandremids.src.model.Dandremid;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class DialogCombatActivity extends Activity {

	public static final int ACTION_SELECTION_MODE = 1, ATTACK_LIST_MODE = 2, OBJECT_LIST_MODE = 3, CHANGE_DANDREMID_MODE = 4, EXIT_MODE = 5;
	
	private int mode;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_combat);
				
		this.mode=this.getIntent().getExtras().getInt("mode");		
		switch (mode){
			case ACTION_SELECTION_MODE:		// Action Selection Mode
				this.launchActionSelectorDialog();
				break;
			case ATTACK_LIST_MODE:		// Attack List Mode
				Dandremid dandremid = this.getIntent().getExtras().getParcelable("dandremid");
				this.launchListDialog(new AttackListAdapter(this, dandremid.getAttackList()));
				break;
			case OBJECT_LIST_MODE:		// Object List Mode
				
				break;
			case CHANGE_DANDREMID_MODE:
				List<Dandremid> aux = this.getIntent().getExtras().getParcelableArrayList("dandremids");
				for (Dandremid d:aux){
					d.getDandremidBase().refreshDandremidImage(this);
				}				
				this.launchListDialog(new DandremidsListAdapter(this,aux));
				break;
			case EXIT_MODE:
				launchExitDialog();				
				break;
			default :				
		}
	}
	
	private void launchActionSelectorDialog() {
		dialog = new Dialog (this);
		dialog.setContentView(R.layout.dialog_action_selector);
		Button bAttack = (Button) dialog.findViewById(R.id.dialog_action_selector_attack);
		bAttack.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				returnSelectionIntent(0);
			}			
		});
		Button bObject = (Button) dialog.findViewById(R.id.dialog_action_selector_object);
		bObject.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				returnSelectionIntent(1);
			}			
		});
		Button bChange = (Button) dialog.findViewById(R.id.dialog_action_selector_change);
		bChange.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				returnSelectionIntent(2);
			}			
		});
		Button bEscape = (Button) dialog.findViewById(R.id.dialog_action_selector_escape);
		bEscape.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				returnSelectionIntent(3);
			}			
		});
		
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.show();
	}

	private void launchExitDialog () {
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_exit);
		dialog.setCancelable(false);
		RelativeLayout esc = (RelativeLayout) dialog.findViewById(R.id.dialog_exit_escape_layout);
		esc.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				returnSelectionIntent(0);
			}
		});
		RelativeLayout close = (RelativeLayout) dialog.findViewById(R.id.dialog_exit_close_layout);
		close.setOnClickListener(new OnClickListener () {
			@Override
			public void onClick(View v) {
				returnSelectionIntent(-1);
			}
		});
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.show();
	}
	
	private void launchListDialog (Adapter adapter) {
		dialog = new Dialog (this);
		dialog.setCancelable(this.getIntent().getBooleanExtra("cancelable", true));
		
		dialog.setContentView(R.layout.dialog_list);
		dialog.setOnCancelListener(new OnCancelListener(){
			
			@Override
			public void onCancel(DialogInterface dialog) {
				onBackPressed();
			}
			
		});
		
		ListView list = (ListView) dialog.findViewById(R.id.dialog_list_list);
		list.setAdapter((BaseAdapter)adapter);
		list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,	long arg3) {
				returnSelectionIntent(pos);	
			}			
		});
		 
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.show();
	}

	protected void returnSelectionIntent (int i) {
		Intent out = new Intent();
		out.putExtra("mode", mode);
		out.putExtra("result", i);		
		setResult(Activity.RESULT_OK, out);
		dialog.dismiss();
		this.finish();
	}
	
	@Override
	public void onBackPressed() {
		returnSelectionIntent(-1);
	}

	

}
