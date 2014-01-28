package dandremids.src.customclasses;

import java.util.List;

import dandremids.src.R;
import dandremids.src.customclasses.DandremidsListAdapter.ViewHolder;
import dandremids.src.model.Creature;
import dandremids.src.model.CreatureBase;
import dandremids.src.model.Creature.Type;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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

public class WikimidsListAdapter extends BaseAdapter {

	static class ViewHolder
	{
		
		TextView number;
		ImageView element1;
		ImageView element2;
		ImageView image;	
		TextView name;
		TextView description;
		ImageButton findButton;
		
	}
	
	private List<CreatureBase> list;
	private LayoutInflater inflater = null;
	private Context context;
	
	public WikimidsListAdapter(Context c, List<CreatureBase> list) {
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
			view = inflater.inflate(R.layout.row_wikimids, null); 
			holder = new ViewHolder(); 
			
			holder.number = (TextView) view.findViewById(R.id.row_wikimids_number);
			holder.element1 = (ImageView) view.findViewById(R.id.row_wikimids_element1);
			holder.element2 = (ImageView) view.findViewById(R.id.row_wikimids_element2);
			holder.image = (ImageView) view.findViewById(R.id.row_wikimids_image);
			holder.name = (TextView) view.findViewById(R.id.row_wikimids_name);
			holder.description = (TextView) view.findViewById(R.id.row_wikimids_description);
			holder.findButton = (ImageButton) view.findViewById(R.id.row_wikimids_button);
						
			view.setTag(holder);
 
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		final CreatureBase c = list.get(position);
		
		holder.number.setText("nº"+c.getId());
		holder.number.setTypeface(null, Typeface.BOLD);
		holder.element1.setImageBitmap(getTypeImage(c.getElement1()));
		holder.element2.setImageBitmap(getTypeImage(c.getElement2()));
		holder.image.setImageBitmap(c.getImage());
		holder.name.setText(c.getName());
		holder.description.setText("<La descripción aqui> \n Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus semper magna ante, nec pretium enim venenatis vitae. Ut tincidunt erat in tortor semper egestas. Etiam ut ligula augue. Aliquam elit odio, egestas nec orci ut, gravida placerat tellus. Praesent ac lorem luctus, suscipit lacus eu, vehicula mi. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Pellentesque ligula purus, lacinia vel laoreet sed, eleifend at neque. Cras nec suscipit magna, ac accumsan nunc.");
		holder.findButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				onClickFindButton(c);				
			}});
		
		
		return view;
	}

	protected void onClickFindButton(CreatureBase c) {
		Toast.makeText(context, c.getId()+"  - Va al mapa y muestra la localización", Toast.LENGTH_LONG).show();		
	}

	private Bitmap getTypeImage(Type type) {
		if (type.equals(Type.NONE)) return BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_empty);		
		return BitmapFactory.decodeResource(context.getResources() , context.getResources().getIdentifier("type_"+type.name().toLowerCase(), "drawable", context.getPackageName()));
	}
}
