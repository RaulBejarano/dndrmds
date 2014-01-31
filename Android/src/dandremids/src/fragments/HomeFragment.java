package dandremids.src.fragments;


import dandremids.src.HomeActivity;
import dandremids.src.R;
import dandremids.src.UserSettingsActivity;
import dandremids.src.model.User;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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
	PagerAdapter mPagerAdapter;
	
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
		return v;
	}
	
	
	
	@Override
	public void onResume() {
		super.onResume();
		try {
			mPager = (ViewPager) this.getActivity().findViewById(R.id.fragment_home_pager);
			mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager(), user);
			mPager.setAdapter(mPagerAdapter);
		} catch (Exception e) {			
		}
	}



	protected void onClickEditButton() {
		Intent i = new Intent (this.getActivity(), UserSettingsActivity.class);
		this.startActivity(i);		
	}
	
	
	
	

}
