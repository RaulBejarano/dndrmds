package dandremids.src.customclasses;

import java.util.ArrayList;

import dandremids.src.R;
import dandremids.src.model.Object;
import dandremids.src.model.db.League;
import dandremids.src.HomeActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

public class LeagueListAdapter extends BaseAdapter implements ListAdapter {

	private Context context;
	private LayoutInflater inflater = null;
	private ArrayList<League> list;
	
	static class ViewHolder
	{		
		TextView nameView;
		Button acceptButton;
		Button rejectButton;
	}
	
	
	public LeagueListAdapter (Context context, ArrayList<League> list) {
		this.context=context;
		inflater = LayoutInflater.from(context);
		this.list=list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		
		if (view == null)
		{
			view = inflater.inflate(R.layout.row_league, null); 
			holder = new ViewHolder(); 
			
			holder.nameView = (TextView) view.findViewById(R.id.row_league_name);
			holder.acceptButton = (Button) view.findViewById(R.id.row_league_accept);
			holder.rejectButton = (Button) view.findViewById(R.id.row_league_decline);
						
			view.setTag(holder);
 
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		final League l=list.get(position);		
		
		
		holder.nameView.setText(""+l.name);
		holder.acceptButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onAcceptButton(l);				
			}
			
		});
		
		holder.rejectButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onRejectButton(l);				
			}
			
		});
		
		return view;
	}

	protected void onRejectButton(League l) {
		((HomeActivity)context).tabPagerAdapter.leagueFragment.onRejectLeagueResponse(l);
	}

	protected void onAcceptButton(League l) {
		((HomeActivity)context).tabPagerAdapter.leagueFragment.onAcceptLeagueResponse(l);		
	}


}
