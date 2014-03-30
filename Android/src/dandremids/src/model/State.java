package dandremids.src.model;

import dandremids.src.model.db.DandremidState;
import android.os.Parcel;
import android.os.Parcelable;

public class State implements Parcelable {

	int id;
	String name;
	String abreviation;
	
	public static final Parcelable.Creator<State> CREATOR =
            new Parcelable.Creator<State>()
            {
                @Override
                public State createFromParcel(Parcel parcel)
                {
                    return new State(parcel);
                }
 
                @Override
                public State[] newArray(int size)
                {
                    return new State[size];
                }
        };
	
	public State (int id, String name, String abreviation){
		this.id=id;
		this.name=name;
		this.abreviation=abreviation;
	}

	public State (Parcel p){
		id = p.readInt();
		name = p.readString();
		abreviation = p.readString();
	}
	
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(id);
		p.writeString(name);
		p.writeString(abreviation);
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

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public DandremidState toDBDandremidState (Dandremid d) {
		DandremidState ds = new DandremidState();
		ds.Dandremid_id = d.getId();
		ds.State_id = this.id;
		
		return ds;
	}
	
}
