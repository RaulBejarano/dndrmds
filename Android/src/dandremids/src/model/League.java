package dandremids.src.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class League implements Parcelable{

	private int id;
	private String name;
	private int rounds;
	private String status;
	private String code;
	private ArrayList<User> userList;
	
	public static final Parcelable.Creator<League> CREATOR =
            new Parcelable.Creator<League>()
            {
                @Override
                public League createFromParcel(Parcel parcel)
                {
                    return new League(parcel);
                }
 
                @Override
                public League[] newArray(int size)
                {
                    return new League[size];
                }
        };
	
	public League(int id, String name, int rounds, String status, String code) {
		super();
		this.id = id;
		this.name = name;
		this.rounds = rounds;
		this.status = status;
		this.code = code;
		this.userList=new ArrayList<User>();
	}

	public League(Parcel p) {
		this.id = p.readInt();
		this.name = p.readString();
		this.rounds = p.readInt();
		this.status = p.readString();
		this.code = p.readString();
		this.userList = p.readArrayList(this.getClass().getClassLoader());
	}
	
	
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(id);
		p.writeString(name);
		p.writeInt(rounds);
		p.writeString(status);
		p.writeString(code);
		p.writeList(userList);
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

	public int getRounds() {
		return rounds;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int describeContents() {
		return 0;
	}
	
	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public dandremids.src.model.db.League toDBLeague() {
		dandremids.src.model.db.League l = new dandremids.src.model.db.League ();
		l.id = this.getId();
		l.name = this.getName();
		l.rounds = this.getRounds();
		l.status = this.getStatus();
		l.code = this.getCode();
		
		return l;
	}

	

}
