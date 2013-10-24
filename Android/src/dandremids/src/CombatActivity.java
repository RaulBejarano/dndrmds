package dandremids.src;

import dandremids.src.customclasses.MyAlarm;
import dandremids.src.model.User;
import dandremids.src.views.CombatView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class CombatActivity extends Activity {

	private User me, rival;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		Bundle extra = this.getIntent().getExtras();
		
		if(extra!=null){
			if(extra.getInt("MODE")==MyAlarm.WILD_COMBAT_MODE){
				//Obtener dandremid aleatoriamente de la base de datos y configurar combate en local
				//me=;
				//rival=;
				
			} else if (extra.getInt("MODE")==MyAlarm.TRAINER_COMBAT_MODE){
				//me=;
				//rival=;
			}
		}
		
		setContentView(new CombatView(this, getWindowManager().getDefaultDisplay()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.combat, menu);
		return true;
	}

	public User getMe() {
		return me;
	}

	public User getRival() {
		return rival;
	}



}
