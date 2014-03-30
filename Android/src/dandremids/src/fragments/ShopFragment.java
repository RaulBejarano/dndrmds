package dandremids.src.fragments;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import dandremids.src.HomeActivity;
import dandremids.src.R;
import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.customclasses.ObjectShopListAdapter;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.Attack;
import dandremids.src.model.Dandremid;
import dandremids.src.model.User;
import dandremids.src.model.Object;

public class ShopFragment extends Fragment {

	User user;
	ArrayList<Object> list;
	ListView objectList;
	ImageButton scanButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		user = ((HomeActivity)this.getActivity()).getUser();	
		list = ((HomeActivity)this.getActivity()).getShopObjectList();
		
		View v = inflater.inflate(dandremids.src.R.layout.fragment_shop, container, false);		
		objectList = (ListView) v.findViewById(R.id.fragment_shop_list);
		scanButton = (ImageButton) v.findViewById(R.id.fragment_shop_button);				
		objectList.setAdapter(new ObjectShopListAdapter(this.getActivity(), list));		
		objectList.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				onObjectLongClick(pos);
				return false;
			}
			
		});
		
		scanButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onClickScanButton();				
			}
		});
		
		return v;
	}

	protected void onObjectLongClick(int pos) {
		final Object o = list.get(pos);	
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());	
		
		builder.setMessage("Do you really want to buy "+o.getName()+"?");
		builder.setNegativeButton("Buy", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				buy(o, 1);
				
			}
			
		});
		builder.setNeutralButton("Buy 5", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				buy(o, 5);
				
			}
			
		});
		builder.setPositiveButton("Buy 10", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				buy(o, 10);
				
			}
			
		});
		
		builder.create().show();
		
	}

	protected void buy(Object o, int quantity) {
		boolean added = false;
		for (Object object : user.getObjectList()){
			if (o.getId() == object.getId()){
				object.setQuantity(object.getQuantity()+quantity);
				added=true;
			}
		}
		if (!added){
			o.setQuantity(quantity);
			user.getObjectList().add(o);
		}
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this.getActivity(),"DandremidsDB",null,1);			
		SQLiteDatabase db = dsh.getWritableDatabase();			
		DAO_User daoU = new DAO_User(this.getActivity(),db);
		daoU.saveUser(user);	
		db.close();
		dsh.close();
		
		((HomeActivity)this.getActivity()).updateUser();
	}

	protected void onClickScanButton() {
		// TODO Auto-generated method stub
		
	}

	public void updateUser(User user) {
		this.user=user;
		list = ((HomeActivity)this.getActivity()).getShopObjectList();
		objectList.setAdapter(new ObjectShopListAdapter(this.getActivity(), list));			
	}
}
