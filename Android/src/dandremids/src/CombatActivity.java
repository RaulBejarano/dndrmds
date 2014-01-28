package dandremids.src;

import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class CombatActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);		
		
		setContentView(new CombatView(this, getWindowManager().getDefaultDisplay()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.combat, menu);
		return true;
	}
}
