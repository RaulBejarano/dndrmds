package dandremids.src.customclasses;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter.ViewHolder;
import dandremids.src.model.Object;
import dandremids.src.model.Dandremid;
import dandremids.src.model.Element;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ObjectListAdapter extends BaseAdapter {

	static class ViewHolder
	{		
		TextView nameText;
		TextView quantityText;
	}
	
	private List<Object> list;
	private LayoutInflater inflater = null;
	private Context context;
	
	public ObjectListAdapter(Context context,	ArrayList<Object> list) {
		this.context=context;
		this.inflater=LayoutInflater.from(context);
		this.list=list;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		
		if (view == null)
		{
			view = inflater.inflate(R.layout.row_combat_object, null); 
			holder = new ViewHolder(); 
			
			holder.nameText = (TextView) view.findViewById(R.id.row_combat_object_name);
			holder.quantityText = (TextView) view.findViewById(R.id.row_combat_object_quantity);
						
			view.setTag(holder);
 
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		Object co=list.get(position);		
		
		
		holder.nameText.setText(""+co.getName());
		holder.quantityText.setText("x "+co.getQuantity());
				
		
		return view;
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



}
