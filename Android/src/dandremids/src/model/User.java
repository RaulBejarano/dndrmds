package dandremids.src.model;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class User {
	
	private int id;
	private String playerName;
	private String name;
	private String email;
	private String surname;
	private String birth;
	private String gender;
	private int level;
	private int exp, expNextLevel;
	private List<Dandremid> dandremidList;
	


	private Bitmap image;
	
	
	public User(int id, Bitmap image, String playerName, String name, String email, String surname, String birth, String gender, int level, int exp, int expNextLevel) {
		super();
		this.id=id;
		this.image = image;
		this.playerName = playerName;
		this.name = name;
		this.email = email;
		this.surname = surname;
		this.birth = birth;
		this.gender = gender;
		this.level = level;
		this.exp = exp;
		this.expNextLevel = expNextLevel;
		this.dandremidList = new ArrayList<Dandremid>();
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


	public List<Dandremid> getDandremidList() {
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


	public List<Dandremid> getSelectedDandremidList() {
		
		List <Dandremid> list = new ArrayList<Dandremid>();
		for (Dandremid c : dandremidList) {
			if (c.getSelected()!=-1) {
				list.add(c);
			}
		}
		
		return list;
	}
	
	public List<Dandremid> getUnselectedDandremidList() {
		
		List <Dandremid> list = new ArrayList<Dandremid>();
		for (Dandremid c : dandremidList) {
			if (c.getSelected()==-1) {
				list.add(c);
			}
		}
		
		return list;
	}
	
	
	
}
