package dandremids.src.fragments;


import com.google.gson.Gson;

import dandremids.src.HomeActivity;
import dandremids.src.LoadCombatActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_Dandremid;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MyDandremidsFragment extends Fragment {

	User user;
	
	ListView dandremidList;
	Button scanButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		user = ((HomeActivity)this.getActivity()).getUser();				
		View v = inflater.inflate(dandremids.src.R.layout.fragment_my_dandremids, container, false);		
		dandremidList = (ListView) v.findViewById(R.id.fragment_my_dandremids_list);
		scanButton = (Button) v.findViewById(R.id.fragment_my_dandremids_scan_button);				
		dandremidList.setAdapter(new DandremidsListAdapter(this.getActivity(), user.getUnselectedDandremidList()));		
		dandremidList.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				onDandremidListItemLongClick(pos);
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
		
		
	protected void onDandremidListItemLongClick(final int pos) {
		final String [] items= new String[]{"Select", "Liberate"};
		final FragmentActivity activity = this.getActivity();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());		
	    builder.setItems(items, new DialogInterface.OnClickListener() {
	    	public void onClick(DialogInterface dialog, int i) {
	    		switch (i) {
		    		case 0:
		    			onSelectDandremid(user.getUnselectedDandremidList().get(pos));
		    			break;
		    		case 1:
		    			final Dandremid c = user.getUnselectedDandremidList().get(pos);
		    			
		    			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		    	    	builder
		    	    	.setMessage("Are you sure to release "+c.getName()+"?")
		    	    	.setIcon(android.R.drawable.ic_dialog_alert)
		    	    	.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		    	    	    public void onClick(DialogInterface dialog, int which) {			      	
		    	    	    	onLiberateDandremid(c);
		    	    	    }
		    	    	})
		    	    	.setNegativeButton("No", null)
		    	    	.show();		    			
		    			break;
	    		}	    			    		
	    	}
	    });
	    
	    AlertDialog ad = builder.create();
	    ad.show();
	}

		
	
	protected void onLiberateDandremid(final Dandremid c) {		    	
    	user.getDandremidList().remove(c);    	
    	
    	DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		DAO_Dandremid dc = new DAO_Dandremid(this.getActivity(), db);
		dc.deleteDandremid(c);
		db.close();
		dsh.close();
    	
    	updateDataList();
	}

	protected void onSelectDandremid(Dandremid dandremid) {
		if (user.getSelectedDandremidList().size()<3){
			dandremid.setSelected(user.getSelectedDandremidList().size()+1);
			
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			DAO_User daoUser = new DAO_User(this.getActivity(), db);
			daoUser.updateUser(user);	
			db.close();
			dsh.close();
			
			updateDataList();
			
		} else {
			Toast.makeText(this.getActivity(), "Not allowed", Toast.LENGTH_LONG).show();			
		}		
	}
	
	protected void updateDataList() {
		ViewPager pager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
		pager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), user));
		this.setSelectedCircle(pager.getCurrentItem());
		
		ListView list = (ListView) this.getActivity().findViewById(R.id.fragment_my_dandremids_list);
		list.setAdapter(new DandremidsListAdapter(this.getActivity(), user.getUnselectedDandremidList()));
		
		
	}
	
	protected void onClickScanButton() {
		
		Intent intent = new Intent(this.getActivity(), LoadCombatActivity.class);
		this.startActivity(intent);
		
		//Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		//intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		//startActivityForResult(intent, 0);
	}
	
	public void setSelectedCircle (int i) {
		ImageView c1 = (ImageView) this.getActivity().findViewById(R.id.fragment_home_circle1);
		ImageView c2 = (ImageView) this.getActivity().findViewById(R.id.fragment_home_circle2);
		ImageView c3 = (ImageView) this.getActivity().findViewById(R.id.fragment_home_circle3);
		
		c1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_circle_unselected));
		c2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_circle_unselected));
		c3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_circle_unselected));
				
		if (i==0) c1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_circle_selected));
		if (i==1) c2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_circle_selected));
		if (i==2) c3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_circle_selected));
			
		switch(user.getSelectedDandremidList().size()){
			case 0: c1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_empty));
			case 1: c2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_empty));
			case 2: c3.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_empty));
			default:
		}
	}
}
