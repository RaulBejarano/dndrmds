package dandremids.src.fragments;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
	
	User user;
	List<SelectedDandremidFragment> list;
	
    public ScreenSlidePagerAdapter(FragmentManager fragmentManager, User user) {
        super(fragmentManager);
        updateUser(user);
    }

    @Override
    public SelectedDandremidFragment getItem(int position) {
    	return list.get(position);
    }

    @Override
    public int getCount() {
        return user.getSelectedDandremidList().size();
    }

	public void updateUser(User user) {
		this.user=user;
    	list = new ArrayList<SelectedDandremidFragment> ();
    	for(Dandremid d : user.getSelectedDandremidList()){
    		list.add(new SelectedDandremidFragment(user, d));
    	}
	}
    
   
}
