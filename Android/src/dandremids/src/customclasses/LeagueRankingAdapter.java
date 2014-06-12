package dandremids.src.customclasses;

import java.util.ArrayList;

import dandremids.src.R;
import dandremids.src.customclasses.LeagueListAdapter.ViewHolder;
import dandremids.src.model.User;
import dandremids.src.model.db.League;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

public class LeagueRankingAdapter extends BaseAdapter implements ListAdapter {

	private Context context;
	private LayoutInflater inflater = null;
	private ArrayList<User> list;
	
	static class ViewHolder {		
		TextView posView;
		ImageView imageView;
		TextView levelView;
		TextView nameView;
		TextView pointsView;
	}
	
	public LeagueRankingAdapter(Context context, ArrayList<User> list){
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
		return list.get(position);
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
			view = inflater.inflate(R.layout.row_ranking, null); 
			holder = new ViewHolder(); 
			
			holder.posView = (TextView) view.findViewById(R.id.row_ranking_position);
			holder.imageView = (ImageView) view.findViewById(R.id.row_ranking_image);
			holder.levelView = (TextView) view.findViewById(R.id.row_ranking_level);
			holder.nameView = (TextView) view.findViewById(R.id.row_ranking_user);
			holder.pointsView = (TextView) view.findViewById(R.id.row_ranking_points);
						
			view.setTag(holder);

		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		User u=list.get(position);		
		
		holder.posView.setText("# "+(position+1));
		holder.imageView.setImageBitmap(u.getImage());
		holder.levelView.setText(""+u.getLevel());
		holder.nameView.setText(""+u.getPlayerName());
		holder.pointsView.setText(""+u.getLeaguePoints());
		
		
		return view;
	}

}
