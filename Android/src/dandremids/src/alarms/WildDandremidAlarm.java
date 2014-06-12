package dandremids.src.alarms;


import dandremids.src.HomeActivity;
import dandremids.src.LoadCombatActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.User;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class WildDandremidAlarm extends BroadcastReceiver {

	public final static int WILD_COMBAT_MODE = 0 , TRAINER_COMBAT_MODE = 1;	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(context ,"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		DAO_User daoUser = new DAO_User(context, db);
		
		User user = daoUser.getLocalUser();
		 db.close();
		 dsh.close();
		
		
		if (!user.isFighting()){			
		
			WildDandremidAlarm.launchNotification(context, WildDandremidAlarm.WILD_COMBAT_MODE);			
			WildDandremidAlarm.setNextAlarm(context, 1, 60);
		
		}
				
	}
	
	
	public static void launchNotification(Context context, int mode){
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.notification_wild_dandremid_icon))
			.setContentTitle("Aaah!! What happens!?")
			.setContentText("Wild Dandremid appeared").setAutoCancel(true);

		Intent notIntent = new Intent(context.getApplicationContext(), LoadCombatActivity.class);
		notIntent.putExtra("MODE", WildDandremidAlarm.WILD_COMBAT_MODE);
		
		PendingIntent contIntent = PendingIntent.getActivity(context, HomeActivity.COMBAT, notIntent, 0);
		
		mBuilder.setContentIntent(contIntent);
		
		
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(1, mBuilder.build());
		
		
	}
	
	public static void setNextAlarm(Context context, int minMinutes, int maxMinutes) {
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, WildDandremidAlarm.class);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		int millis = (int)(Math.random()*((maxMinutes-minMinutes)*60*1000))+(minMinutes*60*1000);
		
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+millis, pIntent);
		
	}
	
	

}
