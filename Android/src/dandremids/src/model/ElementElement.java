package dandremids.src.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ElementElement implements Parcelable{
	
	int element_id_1;
	int element_id_2;
	double power;
	
	public static final Parcelable.Creator<ElementElement> CREATOR =
            new Parcelable.Creator<ElementElement>()
            {
                @Override
                public ElementElement createFromParcel(Parcel parcel)
                {
                    return new ElementElement(parcel);
                }
 
                @Override
                public ElementElement[] newArray(int size)
                {
                    return new ElementElement[size];
                }
        };
        
	
	public ElementElement(int element_id_1, int element_id_2, double power) {
		this.element_id_1 = element_id_1;
		this.element_id_2 = element_id_2;
		this.power = power;
	}
	
	public ElementElement(Parcel p){
		this.element_id_1 = p.readInt();
		this.element_id_2 = p.readInt();
		this.power = p.readDouble();
	}
	
	
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(this.element_id_1);
		p.writeInt(this.element_id_2);
		p.writeDouble(this.power);
	}


	public int getElement_id_1() {
		return element_id_1;
	}


	public void setElement_id_1(int element_id_1) {
		this.element_id_1 = element_id_1;
	}


	public int getElement_id_2() {
		return element_id_2;
	}


	public void setElement_id_2(int element_id_2) {
		this.element_id_2 = element_id_2;
	}


	public double getPower() {
		return power;
	}


	public void setPower(double power) {
		this.power = power;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	
	
	
}
