package dandremids.src.fragments;

import dandremids.src.CombatActivity;
import dandremids.src.HomeActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Creature;
import dandremids.src.model.User;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MyDandremidsFragment extends Fragment {

	User user;
	
	ListView creatureList;
	Button scanButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		user = ((HomeActivity)this.getActivity()).getUser();		
		
		View v = inflater.inflate(dandremids.src.R.layout.fragment_my_dandremids, container, false);
		
		creatureList = (ListView) v.findViewById(R.id.fragment_my_dandremids_list);
		scanButton = (Button) v.findViewById(R.id.fragment_my_dandremids_scan_button);
				
		creatureList.setAdapter(new DandremidsListAdapter(this.getActivity(), user.getUnselectedCreatureList()));		
		
		creatureList.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				onCreatureListItemLongClick(pos);
				return false;
			}
		});
		
		scanButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onClickScanButton();				
			}
		});
		
		return v;
	}
		
		
	protected void onCreatureListItemLongClick(final int pos) {
		final String [] items= new String[]{"Select"};			
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
	    builder.setItems(items, new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int i) {
	    		if (i==0) {		// Select	    			
	    			onSelectCreature(user.getUnselectedCreatureList().get(pos));		    			
	    		}
	    	}
	    });
	    
	    AlertDialog ad = builder.create();
	    ad.show();
	}

		
	
	protected void onSelectCreature(Creature creature) {
		
		if (user.getSelectedCreatureList().size()<3){
			creature.setSelected(true);
						
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			DAO_User daoUser = new DAO_User(this.getActivity(), db);
			daoUser.updateUser(user);	
			db.close();
			dsh.close();
			
			ViewPager pager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			pager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), user));
			
			ListView list = (ListView) this.getActivity().findViewById(R.id.fragment_my_dandremids_list);
			list.setAdapter(new DandremidsListAdapter(this.getActivity(), user.getUnselectedCreatureList()));
		} else {
			Toast.makeText(this.getActivity(), "Not allowed", Toast.LENGTH_LONG).show();
			
		}
		
	}



	protected void onClickScanButton() {
		
		Intent intent = new Intent(this.getActivity(), CombatActivity.class);
		startActivityForResult(intent, 0);
		
		
		//Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		//intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		//startActivityForResult(intent, 0);
	}
}
