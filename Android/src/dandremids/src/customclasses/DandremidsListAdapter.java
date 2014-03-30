package dandremids.src.customclasses;

import java.util.List;

import dandremids.src.R;
import dandremids.src.model.Dandremid;
import dandremids.src.model.Element;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DandremidsListAdapter extends BaseAdapter {

	static class ViewHolder
	{
		TextView levelView;
		ImageView type1View;
		ImageView type2View;
		ImageView imageView;
		ProgressBar expBar;		
		TextView nameView;
		ProgressBar foodBar;
		ProgressBar healthBar;
		ProgressBar happinessBar;
		TextView foodText;
		TextView healthText;
		TextView happinessText;		
	}
	
	private List<Dandremid> list;
	private LayoutInflater inflater = null;
	private Context context;
	
	public DandremidsListAdapter(Context c, List<Dandremid> list){
		this.context=c;
		this.inflater = LayoutInflater.from(c);
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
			view = inflater.inflate(R.layout.row_my_dandremid, null); 
			holder = new ViewHolder(); 
			
			holder.levelView = (TextView) view.findViewById(R.id.row_my_dandremid_level);
			holder.imageView = (ImageView) view.findViewById(R.id.row_my_dandremid_image);
			holder.type1View = (ImageView) view.findViewById(R.id.row_my_dandremid_type_1);
			holder.type2View = (ImageView) view.findViewById(R.id.row_my_dandremid_type_2);
			holder.expBar = (ProgressBar) view.findViewById(R.id.row_my_dandremid_exp);
			holder.nameView = (TextView) view.findViewById(R.id.row_my_dandremid_name);
			holder.foodBar = (ProgressBar) view.findViewById(R.id.row_my_dandremid_food);
			holder.healthBar = (ProgressBar) view.findViewById(R.id.row_my_dandremid_health);
			holder.happinessBar = (ProgressBar) view.findViewById(R.id.row_my_dandremid_happiness);
			holder.foodText = (TextView) view.findViewById(R.id.row_my_dandremid_food_text);
			holder.healthText = (TextView) view.findViewById(R.id.row_my_dandremid_health_text);
			holder.happinessText = (TextView) view.findViewById(R.id.row_my_dandremid_happiness_text);
						
			view.setTag(holder);
 
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		Dandremid c=list.get(position);		
		
		
		holder.levelView.setText(""+c.getLevel());
		holder.imageView.setImageBitmap(c.getDandremidBase().getImage());
		holder.type1View.setImageBitmap(Element.getElementImage(this.context, c.getDandremidBase().getElement1()));		
		holder.type2View.setImageBitmap(Element.getElementImage(this.context, c.getDandremidBase().getElement2()));
		holder.expBar.setMax(c.getExpNextLevel());
		holder.expBar.setProgress(c.getExp());
		holder.nameView.setText(c.getName());
		holder.foodBar.setMax(c.getMaxFeed());
		holder.foodBar.setProgress(c.getFeed());
		holder.healthBar.setMax(c.getMaxLife());
		holder.healthBar.setProgress(c.getLife());
		holder.happinessBar.setProgress(c.getHappiness());
		holder.foodText.setText(c.getFeed()+"/"+c.getMaxFeed());
		holder.healthText.setText(c.getLife()+"/"+c.getMaxLife());
		holder.happinessText.setText(c.getHappiness()+"/100");
				
		
		return view;
	}

}
