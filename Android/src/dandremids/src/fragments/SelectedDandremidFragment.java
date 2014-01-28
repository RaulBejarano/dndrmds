package dandremids.src.fragments;

import dandremids.src.CreatureActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Creature;
import dandremids.src.model.User;
import dandremids.src.model.Creature.Type;
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
	Creature creature;
	
	public SelectedDandremidFragment(User user, Creature creature) {
		this.user = user;
		this.creature=creature;
	}
	 
	 public SelectedDandremidFragment() {
		 super();
	 }

	@Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_selected_creature, container, false);

	        level = (TextView) rootView.findViewById(R.id.fragment_selected_creature_level);
	        name = (TextView) rootView.findViewById(R.id.fragment_selected_creature_name);
	        element1 = (ImageView) rootView.findViewById(R.id.fragment_selected_creature_element1);
	        element2 = (ImageView) rootView.findViewById(R.id.fragment_selected_creature_element2);	        
	        exp = (ProgressBar) rootView.findViewById(R.id.fragment_selected_creature_exp);
	        str = (TextView) rootView.findViewById(R.id.fragment_selected_creature_strength);
	        defense = (TextView) rootView.findViewById(R.id.fragment_selected_creature_defense);
	        speed = (TextView) rootView.findViewById(R.id.fragment_selected_creature_speed);
	        image = (ImageView) rootView.findViewById(R.id.fragment_selected_creature_img);
	        health = (ProgressBar) rootView.findViewById(R.id.fragment_selected_creature_health);
	        food = (ProgressBar) rootView.findViewById(R.id.fragment_selected_creature_food);
	        happiness = (ProgressBar) rootView.findViewById(R.id.fragment_selected_creature_happiness);
	        
	        
	        level.setText(creature.getLevel()+"");
	        name.setText(creature.getName());
	        element1.setImageBitmap(getTypeImage(creature.getCreatureBase().getElement1()));
	        exp.setMax(creature.getExpNextLevel());
	        exp.setProgress(creature.getExp());
	        str.setText(creature.getStrength()+"");
	        defense.setText(creature.getDefense()+"");
	        speed.setText(creature.getSpeed()+"");
	        image.setImageBitmap(creature.getCreatureBase().getImage());
	        health.setMax(creature.getMaxLife());
	        health.setProgress(creature.getLife());
	        food.setMax(creature.getMaxFeed());
	        food.setProgress(creature.getFeed());
	        happiness.setProgress(creature.getHappiness());
	        
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
			Intent i = new Intent(this.getActivity(), CreatureActivity.class);
			i.putExtra("CREATURE_ID", creature.getId());
			this.startActivity(i);		
		}

		private void onDandremidLongClick() {
			
			final String [] items= new String[]{"Unselect"};			
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			
		    builder.setItems(items, new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int i) {
		    		if (i==0) {		// Unselect
		    			onUnselectCreature();		    			
		    		}
		    	}
		    });
		    
		    AlertDialog ad = builder.create();
		    ad.show();			
		}

		private void onUnselectCreature() {
			creature.setSelected(false);
			
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			DAO_User daoUser = new DAO_User(this.getActivity(), db);
			daoUser.updateUser(user);	
			db.close();
			dsh.close();
			
			ListView list = (ListView) this.getActivity().findViewById(R.id.fragment_my_dandremids_list);
			list.setAdapter(new DandremidsListAdapter(this.getActivity(), user.getUnselectedCreatureList()));
			
			ViewPager pager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			pager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), user));
			
		}
		
		
		private Bitmap getTypeImage(Type type) {
			if (type.equals(Type.NONE)) return BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.icon_empty);		
			return BitmapFactory.decodeResource(this.getActivity().getResources() , this.getActivity().getResources().getIdentifier("type_"+type.name().toLowerCase(), "drawable", this.getActivity().getPackageName()));
		}
}
