package dandremids.src.fragments;

import dandremids.src.model.User;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

	HomeFragment homeFragment;
	MyDandremidsFragment myDandremidsFragment;
	WikimidsFragment wikimidsFragment;
	
	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		homeFragment = new HomeFragment();
		myDandremidsFragment = new MyDandremidsFragment();
		wikimidsFragment = new WikimidsFragment();
	}

	@Override
	public Fragment getItem(int i) {
		
		switch(i){
			default:
			case 0:
				return this.homeFragment;
				
			case 1:
				return this.myDandremidsFragment;
				
			case 2:
				return this.wikimidsFragment;		
		}		
	}

	@Override
	public int getCount() {
		return 3;
	}
	
	public void updateUser(User user){
		homeFragment.updateUser(user);
		myDandremidsFragment.updateUser(user);
	}
}
