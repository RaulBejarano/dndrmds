package dandremids.src.fragments;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import dandremids.src.HomeActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsREST;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.customclasses.LeagueListAdapter;
import dandremids.src.customclasses.LeagueRankingAdapter;
import dandremids.src.daos.DAO_League;
import dandremids.src.model.User;
import dandremids.src.model.League;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LeagueFragment extends Fragment{
	
	User user;
	League league;
	
	TextView nameText;
	Button rightButton;
	Button leftButton;
	Button bottomButton;
	ListView rankingList;
	
	Dialog dialog;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		user = ((HomeActivity)this.getActivity()).getUser();	
		
		View v = inflater.inflate(dandremids.src.R.layout.fragment_league, container, false);		
		Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(), "HomemadeApple.ttf");
		nameText = (TextView) v.findViewById(R.id.fragment_league_name);
		nameText.setTypeface(tf);
		rightButton = (Button) v.findViewById(R.id.fragment_league_right);
		leftButton = (Button) v.findViewById(R.id.fragment_league_left);
		bottomButton = (Button) v.findViewById(R.id.fragment_league_bottom);
		rankingList = (ListView) v.findViewById(R.id.fragment_league_list);
		
		this.updateLeague();
		
		return v;
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		Bundle extras = this.getActivity().getIntent().getExtras();
		if (extras!=null && extras.getInt("id", -1) == HomeActivity.LEAGUE_NOTIFICATION){
			this.onSearchClick();
		}
	}
	
	
	public void updateLeague() {
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);			
		SQLiteDatabase db = dsh.getWritableDatabase();			
		DAO_League daoL = new DAO_League(this.getActivity(),db);
		league = daoL.getLeague();	
				
		if (league!=null) {
			if (league.getStatus().compareTo("WAITING")==0) {
				setWaitingInterface();				
				
			} else if (league.getStatus().compareTo("ACTIVE")==0) {
				setActiveInterface();				
			}
		} else {
			setBaseInterface();
		}
		
		db.close();
		dsh.close();
	}
	
	
	private void setBaseInterface() {
		nameText.setText("No League participation");
		
		leftButton.setText("Create");
		leftButton.setBackgroundResource(R.drawable.background_green_small_button);
		leftButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				launchEditDialog(DoBackgroundTask.CREATE_MODE,"New league name:","Create");
			}			
		});
		
		rightButton.setText("Search");
		rightButton.setBackgroundResource(R.drawable.background_green_small_button);
		rightButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				onSearchClick();
			}			
		});
		
		rankingList.setAdapter(new LeagueRankingAdapter(this.getActivity(), new ArrayList<User>()));
			
		bottomButton.setText("Start League");
		bottomButton.setEnabled(false);
		bottomButton.setAlpha(0.7f);
	
	}

	private void setWaitingInterface() {
		nameText.setText(league.getName());
		
		leftButton.setText("Invite");
		leftButton.setBackgroundResource(R.drawable.background_green_small_button);
		leftButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				launchEditDialog(DoBackgroundTask.INVITE_MODE,"Write player name:","Send invitation");
			}			
		});
				
		rightButton.setText("Abandone");
		rightButton.setBackgroundResource(R.drawable.background_red_small_button);
		rightButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(LeagueFragment.this.getActivity());
				builder.setMessage("Do you really want to abandone?");
				builder.setNegativeButton("Cancel", null);
				builder.setPositiveButton("Abandone", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.ABANDONE_MODE);
						dbt.execute();
					}					
				});
				builder.create().show();
			}			
		});
	
		rankingList.setAdapter(new LeagueRankingAdapter(this.getActivity(), league.getUserList()));
				
		bottomButton.setText("Start League");
		bottomButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.START_LEAGUE_MODE);
				dbt.execute();
			}			
		});
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);			
		SQLiteDatabase db = dsh.getWritableDatabase();			
		DAO_League daoL = new DAO_League(this.getActivity(),db);
		int id = daoL.getCreatorId();		
		db.close();
		dsh.close();
		
		if (id == user.getId()){
			bottomButton.setEnabled(true);
			bottomButton.setAlpha(1);
			leftButton.setEnabled(true);
			leftButton.setAlpha(1);
		} else {
			bottomButton.setEnabled(false);
			bottomButton.setAlpha(0.7f);
			leftButton.setEnabled(false);
			leftButton.setAlpha(0.7f);
		}
		
	}
	
	private void setActiveInterface() {
		nameText.setText(league.getName());
		
		// falta el ranking
		
		leftButton.setText("Invite");
		leftButton.setBackgroundResource(R.drawable.background_green_small_button);
		leftButton.setEnabled(false);
		leftButton.setAlpha(0.7f);
		
		rightButton.setText("Abandone");
		rightButton.setBackgroundResource(R.drawable.background_red_small_button);
		rightButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.ABANDONE_MODE);
				dbt.execute();
			}			
		});
	
		bottomButton.setText("Next Combat");
		bottomButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.NEXT_COMBAT_MODE);
				dbt.execute();
			}
		});
		
		bottomButton.setEnabled(true);
		bottomButton.setAlpha(1);
		
	}
	
	public void onSearchClick(){
		//launchEditDialog(DoBackgroundTask.SEARCH_MODE,"League mode:","Search");
		DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.SEARCH_MODE);
		dbt.execute();
	}
	
	private void launchEditDialog (final int mode, String txt, String btn){
		LayoutInflater li = LayoutInflater.from(LeagueFragment.this.getActivity());
		View view = li.inflate(R.layout.dialog_edit_text, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(LeagueFragment.this.getActivity());
		builder.setView(view);
		
		final TextView text = (TextView) view.findViewById(R.id.dialog_edit_text_text);
		final EditText edit = (EditText) view.findViewById(R.id.dialog_edit_text_edit);
		
		text.setText(txt);
		builder.setNegativeButton("Cancel", null);
		builder.setPositiveButton(btn, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (mode == DoBackgroundTask.SEARCH_MODE) {
					DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.SEARCH_MODE, edit.getText().toString());
					dbt.execute();
				} else if (mode == DoBackgroundTask.CREATE_MODE) {
					DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.CREATE_MODE, edit.getText().toString());
					dbt.execute();	
				} else if (mode == DoBackgroundTask.INVITE_MODE) {
					DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.INVITE_MODE, edit.getText().toString());
					dbt.execute();
				} 
				
			}	
		});
		builder.create().show();
	}
	
	
	// -------------------------------------------------------------
	
	
	public class DoBackgroundTask extends AsyncTask<String, Void, String> {
		
		public static final int CREATE_MODE=0, SEARCH_MODE=1, INVITE_MODE=2, ABANDONE_MODE=3, START_LEAGUE_MODE=4, NEXT_COMBAT_MODE=5, REJECT_LEAGUE_MODE=6, JOIN_LEAGUE_MODE=7;
		
		private ProgressDialog d;
		private int mode;
		private Object [] values;
		private String response;
		
		
		public DoBackgroundTask (int mode, Object ...values ) {
			this.mode=mode;
			this.values = values;
		}
		
		
		@Override
		protected void onPreExecute() {
			d = new ProgressDialog(LeagueFragment.this.getActivity());	
			
			switch (mode) {
				case SEARCH_MODE:
					d.setMessage("Looking for Leagues...");
					break;
				case CREATE_MODE:
					d.setMessage("Creating League...");
					break;
				case INVITE_MODE:
					d.setMessage("Sending invitation...");
					break;
				case ABANDONE_MODE:
					d.setMessage("Sending request...");
					break;
				case START_LEAGUE_MODE:
					d.setMessage("Starting league...");
					break;
				case NEXT_COMBAT_MODE:
					d.setMessage("Looking for next combat...");
					break;
				case REJECT_LEAGUE_MODE:
					d.setMessage("Sending request...");
					break;
				case JOIN_LEAGUE_MODE:
					d.setMessage("Sending request...");
					break;
			}
			
			
			d.setCancelable(false);
			d.show();
		}

		
		@Override
		protected String doInBackground(String... urls) {
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(LeagueFragment.this.getActivity(),"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
						
			DandremidsREST dr = new DandremidsREST(LeagueFragment.this.getActivity(), db);
			
			switch (mode) {
				case SEARCH_MODE:
					response = dr.searchLeagueInvitations(user.toDBUser());
					break;
					
				case CREATE_MODE:
					dandremids.src.model.db.League l = new dandremids.src.model.db.League();
					l.name = (String)values[0];
					l.rounds = 1;
					l.status = "WAITING";
					l.code = "";
					
					response = dr.createLeague(user.toDBUser(),l);
					break;
					
				case INVITE_MODE:
					response = dr.sendLeagueInvitation((String)values[0], league.toDBLeague());
					break;
					
				case ABANDONE_MODE:
					response = dr.abandoneLeague(user.toDBUser(), league.toDBLeague());
					break;
					
				case START_LEAGUE_MODE:
					response = dr.startLeague((String)values[0]);
					break;
					
				case NEXT_COMBAT_MODE:
					response = dr.nextCombat(user.toDBUser(), league.toDBLeague());
					break;
					
				case REJECT_LEAGUE_MODE:
					response = dr.rejectLeagueInvitation(user.toDBUser(), ((dandremids.src.model.db.League)values[0]));
					break;
					
				case JOIN_LEAGUE_MODE:
					response = dr.aceptLeagueInvitation(user.toDBUser(), ((dandremids.src.model.db.League)values[0]));
					break;
			}
			
			db.close();
			dsh.close();
			
			return null;						
		}

		@Override
		protected void onPostExecute(String result) {
			d.dismiss();
			
			switch (mode) {
				case SEARCH_MODE:
					if (response != null){
						ArrayList<dandremids.src.model.db.League> leagues = new Gson().fromJson(response, new TypeToken<ArrayList<dandremids.src.model.db.League>>(){}.getType());
						if (leagues.size()>0){
							launchLeagueListDialog(leagues);
						} else {
							Toast.makeText(getActivity(), "No league invitations", Toast.LENGTH_SHORT).show();
						}						
					} else {
						Toast.makeText(getActivity(), "Request Error", Toast.LENGTH_SHORT).show();
					}
					break;
				case CREATE_MODE:
					updateLeague();							
					break;
				case INVITE_MODE:
					Toast.makeText(LeagueFragment.this.getActivity(), response, Toast.LENGTH_SHORT).show();
					break;
				case ABANDONE_MODE:
					updateLeague();
					break;
				case START_LEAGUE_MODE:
					Toast.makeText(LeagueFragment.this.getActivity(), response, Toast.LENGTH_SHORT).show();
					break;
				case NEXT_COMBAT_MODE:
					Toast.makeText(LeagueFragment.this.getActivity(), response, Toast.LENGTH_SHORT).show();
					break;
				case REJECT_LEAGUE_MODE:
					if (response==null){
						Toast.makeText(getActivity(), "Request Error", Toast.LENGTH_SHORT).show();
					} 
					break;
				case JOIN_LEAGUE_MODE:
					updateLeague();
					break;
			}			
		}
	}
	
	public void launchLeagueListDialog(ArrayList<dandremids.src.model.db.League> leagues) {
		dialog = new Dialog(this.getActivity());
		dialog.setContentView(R.layout.dialog_list);
		
		ListView list = (ListView) dialog.findViewById(R.id.dialog_list_list);
		list.setAdapter(new LeagueListAdapter(this.getActivity(), leagues));
		
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog.show();
	}
	
	public void onRejectLeagueResponse(dandremids.src.model.db.League l) {
		DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.REJECT_LEAGUE_MODE, l);
		dbt.execute();		
		if (dialog!=null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	public void onAcceptLeagueResponse(dandremids.src.model.db.League l) {
		DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.JOIN_LEAGUE_MODE, l);
		dbt.execute();
		if (dialog!=null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
