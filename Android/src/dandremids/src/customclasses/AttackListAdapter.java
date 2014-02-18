package dandremids.src.customclasses;

import java.util.List;

import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter.ViewHolder;
import dandremids.src.model.Attack;
import dandremids.src.model.Element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AttackListAdapter extends BaseAdapter{

	static class ViewHolder {
		ImageView image;
		TextView text;
	}
		
	Context context;
	LayoutInflater inflater;
	List<Attack> list;
	
	public AttackListAdapter(Context context, List<Attack> list){
		this.context=context;
		this.inflater = LayoutInflater.from(context);
		this.list=list;
	}
	
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		
		if (view == null) {
			view = inflater.inflate(R.layout.row_attack, null); 
			holder = new ViewHolder(); 
			
			holder.image = (ImageView) view.findViewById(R.id.row_attack_image);
			holder.text = (TextView) view.findViewById(R.id.row_attack_text);
			
			view.setTag(holder);
			
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		Attack attack = list.get(position);
		
		holder.image.setImageBitmap(getTypeImage(attack.getElement()));
		holder.text.setText(attack.getName());
		
		return view;
	}
	
	private Bitmap getTypeImage(Element type) {
		if (type.equals(Element.NONE)) return BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_empty);		
		return BitmapFactory.decodeResource(context.getResources() , context.getResources().getIdentifier("type_"+type.name().toLowerCase(), "drawable", context.getPackageName()));
	}

}
