package dandremids.src;

import java.util.List;

import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.customclasses.MyAlarm;
import dandremids.src.daos.DAO_DandremidBase;
import dandremids.src.daos.DAO_User;
import dandremids.src.fragments.TabPagerAdapter;
import dandremids.src.model.DandremidBase;
import dandremids.src.model.User;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity {

	User user;	
	List <DandremidBase> dandremidsBaseList;
	
	public TabPagerAdapter tabPagerAdapter;
	public ViewPager mViewPager;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.fragment_holder);
		
		tabPagerAdapter = new TabPagerAdapter(this.getSupportFragmentManager());
		mViewPager = (ViewPager) this.findViewById(R.id.fragment_holder_pager);
		mViewPager.setAdapter(tabPagerAdapter);		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int arg0) {}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}

			@Override
			public void onPageSelected(int i) {
				getActionBar().setSelectedNavigationItem(i);
			}
			
		});
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		
		actionBar.addTab(createTab(actionBar, R.drawable.icon_home));
		actionBar.addTab(createTab(actionBar, R.drawable.icon_my_dandremids));
		actionBar.addTab(createTab(actionBar, R.drawable.icon_wikimids));
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		DAO_User daoUser = new DAO_User(this, db);
		user = daoUser.getCurrentUser();
		
		DAO_DandremidBase daoDB = new DAO_DandremidBase(this, db);
		dandremidsBaseList = daoDB.getAllDandremidBase();
		db.close();
		dsh.close();
		// "Wild Dandremid" Notification System		
		MyAlarm.setNextAlarm(this, 0, 1);
		
	}

	private Tab createTab(ActionBar actionBar, int drawable){
		View tabView = getLayoutInflater().inflate(R.layout.tab_actionbar, null);
		
		ImageView tabImage = (ImageView) tabView.findViewById(R.id.tab_actionbar_image);
		tabImage.setImageDrawable(getResources().getDrawable(drawable));
		Tab tab = actionBar.newTab().setCustomView(tabView);
		
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
			
			@Override
			public void onTabSelected(Tab tab, FragmentTransaction ft) {
				if(mViewPager.getCurrentItem()!=tab.getPosition()){
					mViewPager.setCurrentItem(tab.getPosition());
				}				
			}
			
			@Override
			public void onTabReselected(Tab tab, FragmentTransaction ft) {
			
			}
		};
		
		tab.setTabListener(tabListener);
		
		return tab;
	}
	
	protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("tab",  getActionBar().getSelectedTab().getPosition());    
    }

	public User getUser() {
		return user;
	}

		
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String code = intent.getStringExtra("SCAN_RESULT");
				Toast.makeText(this, code, Toast.LENGTH_LONG).show();
				Intent i = new Intent (this, LoadCombatActivity.class);
				i.putExtra("MODE", MyAlarm.TRAINER_COMBAT_MODE);
				this.startActivity(i);
				
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

	public List<DandremidBase> getAllDandremidsList() {
		return dandremidsBaseList;
	}


}
