package dandremids.src.model;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

//public enum Element {NONE, RARE, NORMAL, FLAME, DROP, THUNDER, LEAF, COLD, FIST, VENOM, WING, MENTAL, INSECT, STONE, IRON};

public class Element implements Parcelable{
	private int id;
	private String name;
	private ArrayList<Element> elementList;
	
	public static final Parcelable.Creator<Element> CREATOR =
            new Parcelable.Creator<Element>()
            {
                @Override
                public Element createFromParcel(Parcel parcel)
                {
                    return new Element(parcel);
                }
 
                @Override
                public Element[] newArray(int size)
                {
                    return new Element[size];
                }
        };
	
	public Element (int id, String name) {
		this.id=id;
		this.name=name;
		this.elementList = null;
	}
	
	public Element (Parcel p){
		id=p.readInt();
		name=p.readString();
		elementList = (ArrayList<Element>) p.readArrayList(getClass().getClassLoader());
	}
	
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(id);
		p.writeString(name);
		p.writeList(elementList);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Element> getElementList() {
		return elementList;
	}

	public void setElementList(ArrayList<Element> elementList) {
		this.elementList = elementList;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static Bitmap getElementImage(Context context, Element element) {
		return BitmapFactory.decodeResource(context.getResources() , context.getResources().getIdentifier("type_"+element.getName().toLowerCase(), "drawable", context.getPackageName()));
	}
	
	
}
