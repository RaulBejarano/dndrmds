package dandremids.src.customclasses;

import java.util.List;

import dandremids.src.R;
import dandremids.src.model.Creature;
import dandremids.src.model.Creature.Type;





import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
	
	private List<Creature> list;
	private LayoutInflater inflater = null;
	private Context context;
	
	public DandremidsListAdapter(Context c, List<Creature> list){
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
			view = inflater.inflate(R.layout.row_my_creature, null); 
			holder = new ViewHolder(); 
			
			holder.levelView = (TextView) view.findViewById(R.id.row_my_creature_level);
			holder.imageView = (ImageView) view.findViewById(R.id.row_my_creature_image);
			holder.type1View = (ImageView) view.findViewById(R.id.row_my_creature_type_1);
			holder.type2View = (ImageView) view.findViewById(R.id.row_my_creature_type_2);
			holder.expBar = (ProgressBar) view.findViewById(R.id.row_my_creature_exp);
			holder.nameView = (TextView) view.findViewById(R.id.row_my_creature_name);
			holder.foodBar = (ProgressBar) view.findViewById(R.id.row_my_creature_food);
			holder.healthBar = (ProgressBar) view.findViewById(R.id.row_my_creature_health);
			holder.happinessBar = (ProgressBar) view.findViewById(R.id.row_my_creature_happiness);
			holder.foodText = (TextView) view.findViewById(R.id.row_my_creature_food_text);
			holder.healthText = (TextView) view.findViewById(R.id.row_my_creature_health_text);
			holder.happinessText = (TextView) view.findViewById(R.id.row_my_creature_happiness_text);
						
			view.setTag(holder);
 
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		Creature c=list.get(position);		
		
		
		holder.levelView.setText(""+c.getLevel());
		holder.imageView.setImageBitmap(c.getCreatureBase().getImage());
		holder.type1View.setImageBitmap(this.getTypeImage(c.getCreatureBase().getElement1()));		
		holder.type2View.setImageBitmap(this.getTypeImage(c.getCreatureBase().getElement2()));
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


	private Bitmap getTypeImage(Type type) {
		if (type.equals(Type.NONE)) return BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_empty);		
		return BitmapFactory.decodeResource(context.getResources() , context.getResources().getIdentifier("type_"+type.name().toLowerCase(), "drawable", context.getPackageName()));
	}
	
}
