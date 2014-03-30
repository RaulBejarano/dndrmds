package dandremids.src.alarms;

import dandremids.src.HomeActivity;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class UpdateDandremidAlarm extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(context ,"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		DAO_User daoUser = new DAO_User(context, db);
		
		User user = daoUser.getCurrentUser();
		if (!user.isFighting()){
			
			for (Dandremid d : user.getSelectedDandremidList()){
				d.updateTimeChangingValues();
			}		
			daoUser.saveUser(user);
			
			if(HomeActivity.instance != null){
				HomeActivity.instance.updateUser();
			}
		}
		
				
		db.close();
		dsh.close();
				
		UpdateDandremidAlarm.setNextAlarm(context, 1);
	}
	
	public static void setNextAlarm(Context context, int minutes) {
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, UpdateDandremidAlarm.class);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);		
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+minutes*60*1000, pIntent);		
	}

}
