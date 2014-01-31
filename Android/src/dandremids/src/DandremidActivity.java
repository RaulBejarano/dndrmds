package dandremids.src;

import dandremids.src.model.Dandremid;
import dandremids.src.views.DandremidView;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.Window;
import android.widget.Toast;

public class DandremidActivity extends Activity {

	
	Dandremid creature;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		int creatureId =  this.getIntent().getExtras().getInt("CREATURE_ID");
		
		Toast.makeText(this, ""+creatureId, Toast.LENGTH_LONG).show();
		
		setContentView(new DandremidView(this, getWindowManager().getDefaultDisplay()));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dandremid, menu);
		return true;
	}

	public Dandremid getCreature() {
		return creature;
	}
	
	

}
