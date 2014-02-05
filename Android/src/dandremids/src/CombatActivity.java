package dandremids.src;

import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.Window;

public class CombatActivity extends Activity {
	
	User local, rival;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);				
		setContentView(new CombatView(this, getWindowManager().getDefaultDisplay()));
		
		
		local = (User) this.getIntent().getExtras().get("local");
		rival = (User) this.getIntent().getExtras().get("rival");
		
		for (Dandremid d : local.getDandremidList()) {
			d.getDandremidBase().setImage(getDandremidImage(d.getDandremidBase().getName()));
		}
		
		for (Dandremid d : rival.getDandremidList()) {
			d.getDandremidBase().setImage(getDandremidImage(d.getDandremidBase().getName()));
		}
		
	}
	
	private Bitmap getDandremidImage (String name) {
		return BitmapFactory.decodeResource(getResources(), getResources().getIdentifier("dndrmd_"+name.toLowerCase(), "drawable", getPackageName()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.combat, menu);
		return true;
	}
}
