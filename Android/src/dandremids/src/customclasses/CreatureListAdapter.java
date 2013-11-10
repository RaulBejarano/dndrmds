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

public class CreatureListAdapter extends BaseAdapter {

	static class ViewHolder
	{
		RelativeLayout layout;
		TextView levelView;
		ImageView type1View;
		ImageView type2View;
		ImageView imageView;
		ImageView selectedView;
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
	
	public CreatureListAdapter(Context c, List<Creature> list){
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
			view = inflater.inflate(R.layout.row_creature, null); 
			holder = new ViewHolder(); 
			
			holder.layout = (RelativeLayout) view.findViewById(R.id.row_creature_layout);	
			holder.levelView = (TextView) view.findViewById(R.id.row_creature_level);
			holder.imageView = (ImageView) view.findViewById(R.id.row_creature_image);
			holder.type1View = (ImageView) view.findViewById(R.id.row_creature_type_1);
			holder.type2View = (ImageView) view.findViewById(R.id.row_creature_type_2);
			holder.selectedView = (ImageView) view.findViewById(R.id.row_creature_selected);
			holder.expBar = (ProgressBar) view.findViewById(R.id.row_creature_exp);
			holder.nameView = (TextView) view.findViewById(R.id.row_creature_name);
			holder.foodBar = (ProgressBar) view.findViewById(R.id.row_creature_food);
			holder.healthBar = (ProgressBar) view.findViewById(R.id.row_creature_health);
			holder.happinessBar = (ProgressBar) view.findViewById(R.id.row_creature_happiness);
			holder.foodText = (TextView) view.findViewById(R.id.row_creature_food_text);
			holder.healthText = (TextView) view.findViewById(R.id.row_creature_health_text);
			holder.happinessText = (TextView) view.findViewById(R.id.row_creature_happiness_text);
			

		
			
			view.setTag(holder);
 
		} else {
			holder = (ViewHolder) view.getTag();
		}

		Creature c=list.get(position);
		
		holder.levelView.setText(c.getLevel()+"");
		
		System.out.println("creature base: "+c.getCreatureBase());
		System.out.println("creature base image: "+c.getCreatureBase().getImage());
		
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
		
		if(c.isSelected()){
			holder.selectedView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_tick));
		} else {
			holder.selectedView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_empty));
		}
		
		return view;
	}


	private Bitmap getTypeImage(Type type) {
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_empty);;
		
		switch (type) {
		
			case DROP:
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_water_icon);
				break;
			case FLAME:
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_fire_icon);
				break;
			case THUNDER:
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_electric_icon);
				break;
			case LEAF:
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_leaf_icon);
				break;
			case NORMAL:
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_normal_icon);
				break;
			case RARE:
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_rare_icon);
				break;
			default:
				bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_empty);	
		}
		
		
		return bmp;
	}

}
