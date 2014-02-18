package dandremids.src;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;

public class DialogCombatActivity extends Activity {

	private int mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_combat);
		
		final String [] items;
		this.mode=this.getIntent().getExtras().getInt("mode");
		
		switch (mode){
			case 0:
				items = null;
				break;
			case 1:		// Action Selection Mode
				items = new String [] {"Attack", "Use Object", "Change Dandremid","Escape"};
				break;
			case 2:		// Attack List Mode
				items = this.getIntent().getExtras().getStringArray("attacks");
				break;
			case 3:		// Object List Mode
				items = this.getIntent().getExtras().getStringArray("objects");
				break;
			case 4:
			case 5:
			case 6:
			case 7:
				
			default :
				items = null;
		}
		
		if (items == null) finish();
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		/*
		builder.setAdapter(new AttackListAdapter(this, ), new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int i) {
	    		returnSelectionIntent(i);
	    	}
	    });*/
		
		
	    builder.setItems(items, new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int i) {
	    		returnSelectionIntent(i);
	    	}
	    });	 
	    AlertDialog ad = builder.create();
	    ad.show();	
		
		
	}

	protected void returnSelectionIntent (int i) {
		Intent out = new Intent();
		out.putExtra("mode", mode);
		out.putExtra("result", i);		
		setResult(Activity.RESULT_OK, out);
		this.finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dialog_combat, menu);
		return true;
	}

}
