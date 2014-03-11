package dandremids.src.fragments;

import dandremids.src.HomeActivity;
import dandremids.src.R;
import dandremids.src.UserSettingsActivity;
import dandremids.src.model.User;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	TextView playerName;
	TextView level;
	ImageView image;
	TextView name;
	TextView surname;
	TextView age;
	TextView gender;
	ImageButton editButton;
	ProgressBar expBar;
	TextView expText;
	ViewPager mPager;
	ScreenSlidePagerAdapter mPagerAdapter;	
	ImageView c1, c2, c3;
	
	User user;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		user = ((HomeActivity)this.getActivity()).getUser();
				
		View v = inflater.inflate(dandremids.src.R.layout.fragment_home, container, false);
		playerName = (TextView) v.findViewById(R.id.fragment_home_player_name);
		level= (TextView) v.findViewById(R.id.fragment_home_level);
		image = (ImageView) v.findViewById(R.id.fragment_home_image);
		name = (TextView) v.findViewById(R.id.fragment_home_name);
		surname = (TextView) v.findViewById(R.id.fragment_home_surname);
		age = (TextView) v.findViewById(R.id.fragment_home_age);
		gender = (TextView) v.findViewById(R.id.fragment_home_gender);
		editButton = (ImageButton) v.findViewById(R.id.fragment_home_edit_button);
		expBar = (ProgressBar) v.findViewById(R.id.fragment_home_exp);
		expText = (TextView) v.findViewById(R.id.fragment_home_exp_text);
		mPager = (ViewPager) v.findViewById(R.id.fragment_home_pager);
		mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager(), user);
		c1 = (ImageView) v.findViewById(R.id.fragment_home_circle1);
		c2 = (ImageView) v.findViewById(R.id.fragment_home_circle2);
		c3 = (ImageView) v.findViewById(R.id.fragment_home_circle3);
		
		playerName.setText(user.getPlayerName());
		level.setText(user.getLevel()+"");
		image.setImageBitmap(user.getImage());
		name.setText(user.getName());
		surname.setText(user.getSurname());
		age.setText(user.getBirth());
		gender.setText(user.getGender());
				
		expBar.setMax(user.getExpNextLevel());
		expBar.setProgress(user.getExp());
		expText.setText(user.getExp()+"/"+user.getExpNextLevel());
		
		editButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				onClickEditButton();				
			}			
		});
		
		mPager.setAdapter(mPagerAdapter);
		mPager.setPageTransformer(true, new ZoomOutPageTransformer());
		mPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int i) {
				setSelectedCircle(i);
			}			
		});
		
		this.setSelectedCircle(mPager.getCurrentItem());
		return v;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		try {
			mPager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager(), user);
			mPager.setAdapter(mPagerAdapter);
			this.setSelectedCircle(mPager.getCurrentItem());
			
		} catch (Exception e) {			
		}
	}

	protected void onClickEditButton() {
		Intent i = new Intent (this.getActivity(), UserSettingsActivity.class);
		this.startActivity(i);		
	}
	
	public void setSelectedCircle (int i) {
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

	public void updateUser(User user){
		this.user=user;
		mPagerAdapter.updateUser(user);
	}
	
	

}
