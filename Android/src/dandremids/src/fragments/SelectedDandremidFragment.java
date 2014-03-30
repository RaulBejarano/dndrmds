package dandremids.src.fragments;

import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Dandremid;
import dandremids.src.model.Element;
import dandremids.src.model.User;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
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

	ViewGroup rootView;
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
	TextView healthText;
	TextView foodText;
	TextView happinessText;
	
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
	        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_selected_dandremid, container, false);

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
	        healthText = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_health_text);
	        foodText = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_food_text);
	        happinessText = (TextView) rootView.findViewById(R.id.fragment_selected_dandremid_happiness_text);
	        
	        
	        level.setText(dandremid.getLevel()+"");
	        name.setText(dandremid.getName());
	        element1.setImageBitmap(Element.getElementImage(this.getActivity(), dandremid.getDandremidBase().getElement1()));
	        element2.setImageBitmap(Element.getElementImage(this.getActivity(), dandremid.getDandremidBase().getElement2()));
	        exp.setMax(dandremid.getExpNextLevel());
	        exp.setProgress(dandremid.getExp());
	        str.setText(dandremid.getStrength()+"");
	        defense.setText(dandremid.getDefense()+"");
	        speed.setText(dandremid.getSpeed()+"");
	        image.setImageBitmap(dandremid.getDandremidBase().getImage());
	        health.setMax(dandremid.getMaxLife());
	        health.setProgress(dandremid.getLife());
	        if (dandremid.getLife()<dandremid.getMaxFeed()/10) health.setProgressDrawable(getResources().getDrawable(R.drawable.style_red_progress_bar));
	        else if (dandremid.getLife()<dandremid.getMaxLife()/2) health.setProgressDrawable(getResources().getDrawable(R.drawable.style_yellow_progress_bar));
	        	        
	        food.setMax(dandremid.getMaxFeed());
	        food.setProgress(dandremid.getFeed());
	        if (dandremid.getFeed()<dandremid.getMaxFeed()/10) food.setProgressDrawable(getResources().getDrawable(R.drawable.style_red_progress_bar));
	        else if (dandremid.getFeed()<dandremid.getMaxFeed()/2) food.setProgressDrawable(getResources().getDrawable(R.drawable.style_yellow_progress_bar));
	        
	        happiness.setProgress(dandremid.getHappiness());
	        healthText.setText(dandremid.getLife()+"/"+dandremid.getMaxLife());
	        foodText.setText(dandremid.getFeed()+"/"+dandremid.getMaxFeed());
	        happinessText.setText(dandremid.getHappiness()+"%");
	        if (dandremid.getHappiness()<10) happiness.setProgressDrawable(getResources().getDrawable(R.drawable.style_red_progress_bar));
	        else if (dandremid.getHappiness()<50) happiness.setProgressDrawable(getResources().getDrawable(R.drawable.style_yellow_progress_bar));
	        	        
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
			
			dandremid.setLife(dandremid.getLife()+10);
			if (dandremid.getLife()>dandremid.getMaxLife()) dandremid.setLife(dandremid.getMaxLife());
			dandremid.setFeed(dandremid.getFeed() + 10);
			if (dandremid.getFeed()>dandremid.getMaxFeed()) dandremid.setFeed(dandremid.getMaxFeed());
			dandremid.setHappiness(dandremid.getHappiness()+2);
			if (dandremid.getHappiness()>100) dandremid.setHappiness(100);
			this.saveDandremidsChanges();
			
			
			/*
			Intent i = new Intent(this.getActivity(), DandremidActivity.class);
			i.putExtra("CREATURE_ID", dandremid.getId());
			this.startActivity(i);	*/
		}

		private void onDandremidLongClick() {
			String [] items = null;
			final int size = user.getSelectedDandremidList().size();
			
			switch (dandremid.getSelected()){
				case 1:
					if (size > 1)	items = new String[]{"Unselect", "Move rigth"};
					break;
				case 2:
					if (size < 3)	items = new String[]{"Unselect", "Move left"};
					else 			items = new String[]{"Unselect", "Move rigth", "Move left"};
					break;
				case 3:					
					items = new String[]{"Unselect", "Move left"};
					break;
				default:
					return;
			}
				
			if (items==null) return;
			
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());			
		    builder.setItems(items, new DialogInterface.OnClickListener() {
		    	public void onClick(DialogInterface dialog, int i) {
		    		switch (dandremid.getSelected()){
						case 1:
							if (size > 1) {
								if (i == 0) onUnselectDandremid();
								else onMoveRightDandremid();
							}
							break;
						case 2:	
							if (size < 3) {
								if (i == 0) onUnselectDandremid();
								else onMoveLeftDandremid();
							} else {
								if (i == 0) onUnselectDandremid();
								else if (i == 1) onMoveRightDandremid();
								else onMoveLeftDandremid();
							}																
							break;
						case 3:
							if (i==0) onUnselectDandremid();
							else onMoveLeftDandremid();							
							break;
						default:
							return;
					}
		    	}
		    });		    
		    AlertDialog ad = builder.create();
		    ad.show();			
		}

		private void onUnselectDandremid() {
			user.setUnselectedDandremid(dandremid);
						
			saveDandremidsChanges();
			
			ListView list = (ListView) this.getActivity().findViewById(R.id.fragment_my_dandremids_list);
			list.setAdapter(new DandremidsListAdapter(this.getActivity(), user.getUnselectedDandremidList()));
			
			ViewPager pager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			int i = 0;
			this.setSelectedCircle(i);
			
			pager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), user));
			pager.setCurrentItem(i);
		}
		
		private void onMoveLeftDandremid(){
			// 1. Get the Dandremid on the left and change order attribute
			Dandremid d = user.getSelectedDandremidList().get((dandremid.getSelected()-1)-1);
			d.setSelected(d.getSelected()+1);
			
			// 2. Add one at selected attribute in the clicked Dandremid
			dandremid.setSelected(dandremid.getSelected()-1);			
			
			saveDandremidsChanges();
			
			ViewPager pager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			int i = pager.getCurrentItem()-1;
			this.setSelectedCircle(i);
			
			pager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), user));
			pager.setCurrentItem(i);
		}
		
		private void onMoveRightDandremid() {
			// 1. Get the Dandremid on the right and change order attribute
			Dandremid d = user.getSelectedDandremidList().get((dandremid.getSelected()-1)+1);
			d.setSelected(d.getSelected()-1);
			
			// 2. Substract one at selected attribute in the clicked Dandremid
			dandremid.setSelected(dandremid.getSelected()+1);			
			
			saveDandremidsChanges();
						
			ViewPager pager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			int i = pager.getCurrentItem()+1;
			this.setSelectedCircle(pager.getCurrentItem()+1);
			
			pager.setAdapter(new ScreenSlidePagerAdapter(getFragmentManager(), user));
			pager.setCurrentItem(i);
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

		private void saveDandremidsChanges(){
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);
			SQLiteDatabase db = dsh.getWritableDatabase();
			DAO_User daoUser = new DAO_User(this.getActivity(), db);
			daoUser.saveUser(user);	
			db.close();
			dsh.close();
		}
				
}
