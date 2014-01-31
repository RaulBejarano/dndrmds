package dandremids.src.fragments;

import java.util.List;

import dandremids.src.HomeActivity;
import dandremids.src.R;
import dandremids.src.customclasses.WikimidsListAdapter;
import dandremids.src.model.Dandremid;
import dandremids.src.model.DandremidBase;
import dandremids.src.model.User;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class WikimidsFragment extends Fragment{

	User user;
	
	ListView dandremidsList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		
		user = ((HomeActivity)this.getActivity()).getUser();		
		
		View v = inflater.inflate(dandremids.src.R.layout.fragment_wikimids, container, false);
		
		List<DandremidBase> list = ((HomeActivity) this.getActivity()).getAllDandremidsList();
		
		dandremidsList = (ListView) v.findViewById(R.id.fragment_wikimids_list);
		dandremidsList.setAdapter(new WikimidsListAdapter(this.getActivity(), list));
		
		
		return v;
	}
}
