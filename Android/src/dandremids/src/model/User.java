package dandremids.src.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable{
	
	private int id;
	private String playerName;
	private String name;
	private String password;
	private String email;
	private String surname;
	private String birth;
	private String gender;
	private int level;
	private int exp, expNextLevel;
	private int gold;
	private boolean fighting;
	private ArrayList<Dandremid> dandremidList;	
	private ArrayList<Object> objectList;
	private Bitmap image;
	
	private int leaguePoints;
	
	public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
                @Override
                public User createFromParcel(Parcel parcel)
                {
                    return new User(parcel);
                }
 
                @Override
                public User[] newArray(int size)
                {
                    return new User[size];
                }
        };
	
	
	
	public User(int id, Bitmap image, String playerName, String name, String password, String email, String surname, String birth, String gender, int level, int exp, int expNextLevel, int gold, boolean fighting) {
		this.id=id;
		this.image = image;
		this.playerName = playerName;
		this.name = name;
		this.password = password;
		this.email = email;
		this.surname = surname;
		this.birth = birth;
		this.gender = gender;
		this.level = level;
		this.exp = exp;
		this.expNextLevel = expNextLevel;
		this.gold=gold;
		this.fighting= fighting;
		this.dandremidList = new ArrayList<Dandremid>();
		this.objectList = new ArrayList<Object>();
	}
	
	public User (Parcel p) {
		id = p.readInt();
		image = (Bitmap) p.readParcelable(getClass().getClassLoader());
		playerName = p.readString();
		name = p.readString();
		password = p.readString();
		email = p.readString();
		surname = p.readString();
		birth = p.readString();
		gender = p.readString();
		level = p.readInt();
		exp = p.readInt();
		expNextLevel = p.readInt();	
		gold = p.readInt();
		fighting = p.readInt()>0;
		dandremidList = (ArrayList<Dandremid>) p.readArrayList(getClass().getClassLoader());		
		objectList = (ArrayList<Object>) p.readArrayList(getClass().getClassLoader());
		
	}

	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(id);
		p.writeParcelable(image, flags);
		p.writeString(playerName);
		p.writeString(name);
		p.writeString(password);
		p.writeString(email);
		p.writeString(surname);
		p.writeString(birth);
		p.writeString(gender);
		p.writeInt(level);
		p.writeInt(exp);
		p.writeInt(expNextLevel);
		p.writeInt(gold);
		p.writeInt(fighting?1:0);
		p.writeList(dandremidList);
		p.writeList(objectList);
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpNextLevel() {
		return expNextLevel;
	}

	public void setExpNextLevel(int expNextLevel) {
		this.expNextLevel = expNextLevel;
	}
	
	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public boolean isFighting() {
		return fighting;
	}
	
	public void setFighting(boolean val){
		this.fighting=val;
	}
	
	public ArrayList<Object> getObjectList() {
		return objectList;
	}

	public void setObjectList(ArrayList<Object> combatObjectList) {
		this.objectList = combatObjectList;
	}

	public ArrayList<Dandremid> getDandremidList() {
		return dandremidList;
	}

	public void setDandremidList(ArrayList<Dandremid> dandremidList) {
		this.dandremidList = dandremidList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLeaguePoints() {
		return leaguePoints;
	}

	public void setLeaguePoints(int leaguePoints) {
		this.leaguePoints = leaguePoints;
	}

	
	public ArrayList<Dandremid> getSelectedDandremidList() {
		
		ArrayList <Dandremid> list = new ArrayList<Dandremid>();
		for (int i=0; i<dandremidList.size(); i++){
			Dandremid d = dandremidList.get(i);
			if (d.getSelected()!=-1) {
				if (list.isEmpty()){
					list.add(d);
				} else {
					if (d.getSelected()<list.get(0).getSelected()) {
						list.add(0, d);
					} else if (d.getSelected() > list.get(list.size()-1).getSelected()) {
						list.add(d);
					} else {
						list.add(1, d);
					}
				}	
				
			}
		}
		
		return list;
	}
	
	public ArrayList<Dandremid> getUnselectedDandremidList() {
		
		ArrayList <Dandremid> list = new ArrayList<Dandremid>();
		for (Dandremid c : dandremidList) {
			if (c.getSelected()==-1) {
				list.add(c);
			}
		}
		
		return list;
	}

	public void setUnselectedDandremid(Dandremid dandremid) {
		dandremid.setSelected(-1);
		ArrayList<Dandremid> list = this.getSelectedDandremidList();
		for(int i=0; i<list.size(); i++){
			list.get(i).setSelected(i+1);
		}
		
	}

	public int getAvailableDandremids() {
		int i=0;
		for (Dandremid d : this.getSelectedDandremidList()){
			if (d.getLife()>0){
				i++;
			}
		}
		return i;
	}

	public dandremids.src.model.db.User toDBUser() {
		dandremids.src.model.db.User u = new dandremids.src.model.db.User();
		u.id = this.id;
		u.playerName = this.playerName;
		u.name = this.name;
		u.password = this.password;
		u.email = this.email;
		u.surname = this.surname;
		u.birth = this.birth;
		u.gender = this.gender;
		u.level = this.level;
		u.exp = this.exp;
		u.expNextLevel = this.expNextLevel;
		
		List<dandremids.src.model.db.Dandremid> dandremidsList = new ArrayList<dandremids.src.model.db.Dandremid>();
		for (dandremids.src.model.Dandremid d : this.dandremidList) {
			dandremidsList.add(d.toDBDandremid(this));
		}
		u.dandremids = dandremidsList;
		
		List<dandremids.src.model.db.UserObject> objectList = new ArrayList<dandremids.src.model.db.UserObject>();
		for (dandremids.src.model.Object o : this.objectList) {
			objectList.add(o.toDBUserObject(this));
		}
		u.userObjects = objectList;
		
		return u;
	}

	
}
