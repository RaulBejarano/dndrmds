package dandremids.src.customclasses;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter.ViewHolder;
import dandremids.src.model.Dandremid;
import dandremids.src.model.Element;
import dandremids.src.model.Object;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ObjectShopListAdapter extends BaseAdapter {

	static class ViewHolder
	{		
		ImageView imageView;	
		TextView nameView;
		TextView quantityView;
		TextView priceView;
	}
	
	private List<Object> list;
	private LayoutInflater inflater = null;
	private Context context;
	
	public ObjectShopListAdapter(Context context, ArrayList<Object> list) {
		this.context=context;
		inflater=LayoutInflater.from(context);
		this.list=list;
		
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		
		if (view == null)
		{
			view = inflater.inflate(R.layout.row_shop_object, null); 
			holder = new ViewHolder(); 
			
			holder.imageView = (ImageView) view.findViewById(R.id.row_shop_object_image);
			holder.nameView = (TextView) view.findViewById(R.id.row_shop_object_name);
			holder.quantityView = (TextView) view.findViewById(R.id.row_shop_object_quantity);
			holder.priceView = (TextView) view.findViewById(R.id.row_shop_object_price);
						
			view.setTag(holder);
 
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		Object o=list.get(position);		
		
		
		//holder.imageView.setImageBitmap();
		holder.nameView.setText(o.getName());
		if (o.getQuantity()>0){
			holder.quantityView.setText("   "+o.getQuantity()+"      ");
			holder.quantityView.setBackgroundResource(R.drawable.background_title_3);
		} else {
			holder.quantityView.setText("");
			holder.quantityView.setBackgroundColor(Color.TRANSPARENT);
		}	
		
		if (o.getPrice()==0){
			holder.priceView.setText("Free");
		} else {
			holder.priceView.setText(""+o.getPrice());
		}
		
		
		
		return view;
	}
	

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public java.lang.Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	
}
