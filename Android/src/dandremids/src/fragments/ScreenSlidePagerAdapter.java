package dandremids.src.fragments;

import dandremids.src.model.User;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
	
	User user;
	
    public ScreenSlidePagerAdapter(FragmentManager fragmentManager, User user) {
        super(fragmentManager);
        this.user = user;
    }

    @Override
    public SelectedDandremidFragment getItem(int position) {
    	return new SelectedDandremidFragment(user, user.getSelectedDandremidList().get(position));
    }

    @Override
    public int getCount() {
        return user.getSelectedDandremidList().size();
    }
}
