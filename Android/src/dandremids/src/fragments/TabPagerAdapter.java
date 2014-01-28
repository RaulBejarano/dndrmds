package dandremids.src.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment;
		switch(i){
			default:
			case 0:
				fragment = new HomeFragment();
								
				break;
			case 1:
				fragment = new MyDandremidsFragment();
				
				break;
			case 2:
				fragment = new WikimidsFragment();
			
				break;
			
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
