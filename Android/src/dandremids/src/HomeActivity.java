package dandremids.src;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.alarms.UpdateDandremidAlarm;
import dandremids.src.alarms.WildDandremidAlarm;
import dandremids.src.customclasses.DandremidsREST;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_DandremidBase;
import dandremids.src.daos.DAO_League;
import dandremids.src.daos.DAO_Object;
import dandremids.src.daos.DAO_User;
import dandremids.src.fragments.ScreenSlidePagerAdapter;
import dandremids.src.fragments.TabPagerAdapter;
import dandremids.src.model.DandremidBase;
import dandremids.src.model.User;
import dandremids.src.model.Object;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;


public class HomeActivity extends FragmentActivity {

	public static final int COMBAT = 0, QR_READER = 1;
	public static HomeActivity instance = null;
	
	User user;	
	ArrayList <DandremidBase> dandremidsBaseList;
	ArrayList <Object> shopObjectList;
	
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
		actionBar.addTab(createTab(actionBar, R.drawable.icon_swords));
		actionBar.addTab(createTab(actionBar, R.drawable.icon_bag));
		actionBar.addTab(createTab(actionBar, R.drawable.icon_wikimids));
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		SQLiteDatabase db = dsh.getWritableDatabase();
		DAO_User daoUser = new DAO_User(this, db);
		user = daoUser.getCurrentUser();
		
		DAO_DandremidBase daoDB = new DAO_DandremidBase(this, db);
		dandremidsBaseList = daoDB.getAllDandremidBase();
		
		DAO_Object daoObject = new DAO_Object(this,db);
		shopObjectList = daoObject.getShopObjectList(user);
		
		db.close();
		dsh.close();
		
		// Alarm Notification System		
		WildDandremidAlarm.setNextAlarm(this, 0, 1);
		UpdateDandremidAlarm.setNextAlarm(this, 1);
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    instance = this;
	}
	@Override
	protected void onPause() {
	    super.onPause();
	    instance = null;
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
		super.onSaveInstanceState(outState);
        outState.putInt("tab",  getActionBar().getSelectedTab().getPosition());
        outState.putParcelable("user", user);
    }

	public User getUser() {
		return user;
	}

	public List<DandremidBase> getAllDandremidsList() {
		return dandremidsBaseList;
	}
	
	public ArrayList<Object> getShopObjectList() {
		return this.shopObjectList;
	}
		
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		updateUser();		
	}

	public void setSelectedCircle (int i) {
		ImageView c1 = (ImageView) this.findViewById(R.id.fragment_home_circle1);
		ImageView c2 = (ImageView) this.findViewById(R.id.fragment_home_circle2);
		ImageView c3 = (ImageView) this.findViewById(R.id.fragment_home_circle3);
		
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
	
	public void updateUser() {
		try {
			DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);			
			SQLiteDatabase db = dsh.getWritableDatabase();			
			DAO_User daoU = new DAO_User(this,db);
			user = daoU.getCurrentUser();	
			DAO_Object daoObject = new DAO_Object(this,db);
			shopObjectList = daoObject.getShopObjectList(user);
			db.close();
			dsh.close();
			
			tabPagerAdapter.updateUser(user);
			
			ViewPager pager = (ViewPager) this.findViewById(R.id.fragment_home_pager);
			int i = pager.getCurrentItem();
			this.setSelectedCircle(i);
			pager.setAdapter(new ScreenSlidePagerAdapter(this.getSupportFragmentManager(), user));
			pager.setCurrentItem(i);
		} catch (Exception e) {
			
		}
	}
	
}
