package dandremids.src.fragments;

import dandremids.src.DandremidActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Dandremid;
import dandremids.src.model.DandremidBase;
import dandremids.src.model.User;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SelectedDandremidFragment extends Fragment {

	TextView level;
	TextView name;
	ImageView element1;
	ImageView element2;
	ProgressBar exp;
	TextView str;
	TextView defense;
	TextView speed;
	ImageView image;
	ProgressBar health;
	ProgressBar food;
	ProgressBar happiness;	
	
	User user;
	Dandremid dandremid;
	
	public SelectedDandremidFragment(User user, Dandremid dandremid) {
		this.user = user;
		this.dandremid=dandremid;
	}
	 
	 public SelectedDandremidFragment() {
		 super();
	 }

	 
	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_selected_dandremid, container, false);

	        level = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_level);
	        name = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_name);
	        element1 = (ImageView) rootView.findViewById(R.id.fragment_selected_dandremid_element1);
	        element2 = (ImageView) rootView.findViewById(R.id.fragment_selected_dandremid_element2);	        
	        exp = (ProgressBar) rootView.findViewById(R.id.fragment_selected_dandremid_exp);
	        str = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_strength);
	        defense = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_defense);
	        speed = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_speed);
	        image = (ImageView) rootView.findViewById(R.id.fragment_selected_dandremid_img);
	        health = (ProgressBar) rootView.findViewById(R.id.fragment_selected_dandremid_health);
	        food = (ProgressBar) rootView.findViewById(R.id.fragment_selected_dandremid_food);
	        happiness = (ProgressBar) rootView.findViewById(R.id.fragment_selected_dandremid_happiness);
	        
	        
	        level.setText(dandremid.getLevel()+"");
	        name.setText(dandremid.getName());
	        element1.setImageBitmap(getTypeImage(dandremid.getDandremidBase().getElement1()));
	        exp.setMax(dandremid.getExpNextLevel());
	        exp.setProgress(dandremid.getExp());
	        str.setText(dandremid.getStrength()+"");
	        defense.setText(dandremid.getDefense()+"");
	        speed.setText(dandremid.getSpeed()+"");
	        image.setImageBitmap(dandremid.getDandremidBase().getImage());
	        health.setMax(dandremid.getMaxLife());
	        health.setProgress(dandremid.getLife());
	        food.setMax(dandremid.getMaxFeed());
	        food.setProgress(dandremid.getFeed());
	        happiness.setProgress(dandremid.getHappiness());
	        
	        image.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View v) {
					onDandremidLongClick();
					return false;
				}	        	
	        });
	        
	        image.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					onDandremidClick();
					
				}
	        	
	        });        
	        return rootView;
	    }
	
	
		protected void onDandremidClick() {
			Intent i = new Intent(this.getActivity(), DandremidActivity.class);
			i.putExtra("CREATURE_ID", dandremid.getId());
			this.startActivity(i);		
		}

		private void onDandremidLongClick() {
			
			final String [] items= new String[]{"Unselect"};			
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			
		    builder.setItems(items, new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int i) {
		    		if (i==0) {		// Unselect
		    			onUnselectDandremid();		    			
		    		}
		    	}
		    });
		    
		    AlertDialog ad = builder.create();
		    ad.show();			
		}

		private void onUnselectDandremid() {
			dandremid.setSelected(-1);
			
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			DAO_User daoUser = new DAO_User(this.getActivity(), db);
			daoUser.updateUser(user);	
			db.close();
			dsh.close();
			
			ListView list = (ListView) this.getActivity().findViewById(R.id.fragment_my_dandremids_list);
			list.setAdapter(new DandremidsListAdapter(this.getActivity(), user.getUnselectedDandremidList()));
			
			ViewPager pager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			pager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), user));
			
		}
		
		
		private Bitmap getTypeImage(DandremidBase.Element type) {
			if (type.equals(DandremidBase.Element.NONE)) return BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.icon_empty);		
			return BitmapFactory.decodeResource(this.getActivity().getResources() , this.getActivity().getResources().getIdentifier("type_"+type.name().toLowerCase(), "drawable", this.getActivity().getPackageName()));
		}
}
