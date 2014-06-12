package dandremids.src.alarms;


import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dandremids.src.HomeActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsREST;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_League;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.League;
import dandremids.src.model.User;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

public class UpdateSecondaryDataAlarm extends BroadcastReceiver {
	
	
	@Override
	public void onReceive(final Context context, Intent intent) {
		DoBackgroundTask dbt = new DoBackgroundTask(context);
		dbt.execute();		
		setNextAlarm(context, 1);		
	}

	public static void setNextAlarm(Context context, int minutes) {
		AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, UpdateSecondaryDataAlarm.class);
		PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);		
		am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+minutes*60*1000, pIntent);		
	}
	
	
	public static void launchNotification(Context context){
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
			.setSmallIcon(R.drawable.ic_launcher)
			.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.notification_combat_icon))
			.setContentTitle("You have pending invitations")
			.setContentText("Please, accept or reject them")
			.setAutoCancel(true);

		Intent intent = new Intent(context.getApplicationContext(), HomeActivity.class);	
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		intent.putExtra("id", HomeActivity.LEAGUE_NOTIFICATION);
		
		PendingIntent contIntent = PendingIntent.getActivity(context, HomeActivity.LEAGUE_NOTIFICATION, intent, 0);
		
		mBuilder.setContentIntent(contIntent);		
		
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(HomeActivity.LEAGUE_NOTIFICATION, mBuilder.build());
		
		
	}
	
	
	public class DoBackgroundTask extends AsyncTask<String, Void, String> {
			
		Context context;
		
		public DoBackgroundTask (Context context) {
			super();
			this.context=context;
		}
		
		@Override
		protected void onPreExecute() {
		}

		
		@Override
		protected String doInBackground(String... urls) {
					
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(context ,"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			
			DAO_League daoLeague = new DAO_League (context, db);
			DAO_User daoUser = new DAO_User (context, db);
			
			User user = daoUser.getLocalUser();
			League league = daoLeague.getLeague();
			
			DandremidsREST dr = new DandremidsREST(context, db);
			
			// Actualizar los datos de la Liga actual o consultar si hay invitaciones
			if (league != null){
				dr.getLeague(user.toDBUser(), league.toDBLeague());
			} else {
				String str = dr.searchLeagueInvitations(user.toDBUser());
				ArrayList<dandremids.src.model.db.League> leagues = new Gson().fromJson(str, new TypeToken<ArrayList<dandremids.src.model.db.League>>(){}.getType());
				
				if(leagues!=null && leagues.size()>0){
					launchNotification(context);
				}
			}
			
			db.close();
			dsh.close();
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
				
		}
	}
	
}
