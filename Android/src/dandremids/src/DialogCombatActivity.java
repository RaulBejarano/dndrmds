package dandremids.src;

import dandremids.src.customclasses.AttackListAdapter;
import dandremids.src.model.Dandremid;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DialogCombatActivity extends Activity {

	public static final int ACTION_SELECTION_MODE = 1, ATTACK_LIST_MODE = 2, OBJECT_LIST_MODE = 3, CHANGE_DANDREMID_MODE = 4;
	
	private int mode;
	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_combat);
		
				
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_list);
		dialog.setOnCancelListener(new OnCancelListener(){

			@Override
			public void onCancel(DialogInterface dialog) {
				onBackPressed();
			}
			
		});
		
		ListView list = (ListView) dialog.findViewById(R.id.dialog_list_list);
		
		this.mode=this.getIntent().getExtras().getInt("mode");		
		switch (mode){
			case ACTION_SELECTION_MODE:		// Action Selection Mode
				String [] items = new String [] {"Attack", "Use Object", "Change Dandremid","Escape"};
				list.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items));
				break;
			case ATTACK_LIST_MODE:		// Attack List Mode
				System.out.println("Attack list mode");
				Dandremid dandremid = this.getIntent().getExtras().getParcelable("dandremid");
				list.setAdapter(new AttackListAdapter(this, dandremid.getAttackList()));
				break;
			case OBJECT_LIST_MODE:		// Object List Mode
				items = this.getIntent().getExtras().getStringArray("objects");
				break;
			case CHANGE_DANDREMID_MODE:
				
							
			default :
				
		}
		
		list.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,	long arg3) {
				returnSelectionIntent(pos);	
			}			
		});
		 
		
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
