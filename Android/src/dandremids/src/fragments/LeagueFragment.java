package dandremids.src.fragments;

import dandremids.src.HomeActivity;
import dandremids.src.LoginActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter;
import dandremids.src.customclasses.DandremidsREST;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_League;
import dandremids.src.daos.DAO_Object;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Attack;
import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import dandremids.src.model.League;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class LeagueFragment extends Fragment{
	
	User user;
	League league;
	
	TextView nameText;
	Button rightButton;
	Button leftButton;
	Button bottomButton;
	ListView rankingList;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		user = ((HomeActivity)this.getActivity()).getUser();	
		
		View v = inflater.inflate(dandremids.src.R.layout.fragment_league, container, false);		
		
		nameText = (TextView) v.findViewById(R.id.fragment_league_name);
		rightButton = (Button) v.findViewById(R.id.fragment_league_right);
		leftButton = (Button) v.findViewById(R.id.fragment_league_left);
		bottomButton = (Button) v.findViewById(R.id.fragment_league_bottom);
		rankingList = (ListView) v.findViewById(R.id.fragment_league_list);
		
		this.updateLeague();
		
		return v;
	}
	
	
	private void updateLeague() {
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);			
		SQLiteDatabase db = dsh.getWritableDatabase();			
		DAO_League daoL = new DAO_League(this.getActivity(),db);
		league = daoL.getLeague();	
		db.close();
		dsh.close();
		
		if (league!=null) {
			if (league.getStatus().compareTo("WAITING")==0) {
				setWaitingInterface();				
				
			} else if (league.getStatus().compareTo("ACTIVE")==0) {
				setActiveInterface();				
			}
		} else {
			setBaseInterface();
		}
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
				launchEditDialog(DoBackgroundTask.SEARCH_MODE,"League mode:","Search");
			}			
		});
	
		bottomButton.setText("Start League");
		bottomButton.setEnabled(false);
		bottomButton.setAlpha(0.7f);
	
	}

	private void setWaitingInterface() {
		nameText.setText(league.getName());
		
		// falta el ranking
		
		leftButton.setText("Invite");
		leftButton.setBackgroundResource(R.drawable.background_green_small_button);
		leftButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				launchEditDialog(DoBackgroundTask.INVITE_MODE,"Write player name:","Send invitation");
			}			
		});
		// if (not creator) leftButton.setEnabled(false);		
		
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
	
		bottomButton.setText("Start League");
		bottomButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				DoBackgroundTask dbt = new DoBackgroundTask(DoBackgroundTask.START_LEAGUE_MODE);
				dbt.execute();
			}			
		});
				
		boolean creator = true;
		if (creator){
			bottomButton.setEnabled(true);
			bottomButton.setAlpha(1);
		} else {
			bottomButton.setEnabled(false);
			bottomButton.setAlpha(0.7f);
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
		
		public static final int CREATE_MODE=0, SEARCH_MODE=1, INVITE_MODE=2, ABANDONE_MODE=3, START_LEAGUE_MODE=4, NEXT_COMBAT_MODE=5;
		
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
			if (mode == SEARCH_MODE) {
				d.setMessage("Looking for Leagues...");
			} else if (mode == CREATE_MODE) {
				d.setMessage("Creating League...");
			} else if (mode == INVITE_MODE) {
				d.setMessage("Sending invitation...");
			} else if (mode == ABANDONE_MODE) {
				d.setMessage("Sending request...");
			} else if (mode == START_LEAGUE_MODE){
				d.setMessage("Starting league...");
			} else if (mode == NEXT_COMBAT_MODE){
				d.setMessage("Looking for next combat...");
			}
			d.setCancelable(false);
			d.show();
		}

		
		@Override
		protected String doInBackground(String... urls) {
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(LeagueFragment.this.getActivity(),"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
						
			DandremidsREST dr = new DandremidsREST(LeagueFragment.this.getActivity(), db);
			
			if (mode == SEARCH_MODE) {
				response = dr.searchLeague((String)values[0]);
			} else if (mode == CREATE_MODE) {
				dandremids.src.model.db.League league = new dandremids.src.model.db.League();
				league.name = (String)values[0];
				league.rounds = 1;
				league.status = "WAITING";
				league.code = "";
				
				response = dr.createLeague(league);
			}else if (mode == INVITE_MODE) {
				response = dr.invite((String)values[0]);
			} else if (mode == ABANDONE_MODE) {
				response = dr.abandone(user.toDBUser(), league.toDBLeague());
			} else if (mode == START_LEAGUE_MODE){
				response = dr.searchLeague((String)values[0]);
			} else if (mode == NEXT_COMBAT_MODE){
				response = dr.nextCombat(user.toDBUser(), league.toDBLeague());
			}
			
			db.close();
			dsh.close();
			
			return null;						
		}

		@Override
		protected void onPostExecute(String result) {
			d.dismiss();
			
			if (mode == SEARCH_MODE) {
				onSearchModeResponse(response);
			} else if (mode == CREATE_MODE) {
				onCreateModeResponse(response);
			} else if (mode == INVITE_MODE) {
				Toast.makeText(LeagueFragment.this.getActivity(), response, Toast.LENGTH_SHORT).show();
			} else if (mode == ABANDONE_MODE) {
				Toast.makeText(LeagueFragment.this.getActivity(), response, Toast.LENGTH_SHORT).show();
			} else if (mode == START_LEAGUE_MODE){
				Toast.makeText(LeagueFragment.this.getActivity(), response, Toast.LENGTH_SHORT).show();
			} else if (mode == NEXT_COMBAT_MODE){
				Toast.makeText(LeagueFragment.this.getActivity(), response, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	public void onSearchModeResponse(String response) {
		if (response!=null &&  response.compareTo("true")==0) {
			
		} else {
			Toast.makeText(this.getActivity(), "Error", Toast.LENGTH_SHORT).show();
		}
	}

	public void onCreateModeResponse(String response) {
		if (response!=null &&  response.compareTo("true")==0) {
			updateLeague();			
		} else {
			Toast.makeText(this.getActivity(), "Error", Toast.LENGTH_SHORT).show();
		}
		
	}
}
