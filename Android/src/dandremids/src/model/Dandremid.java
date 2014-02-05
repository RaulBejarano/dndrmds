package dandremids.src.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Dandremid implements Parcelable {
	
	// General Attributes
	int id;
	String name;
	int level;
	int exp;
	int expNextLevel;
	int selected;
	
	int strength;
	int defense;
	int speed;
	
	int feed;
	int maxFeed;
	int happiness;
	
	int life;
	int maxLife;
	
	DandremidBase dandremidBase;
	
	
	public static final Parcelable.Creator<Dandremid> CREATOR =
            new Parcelable.Creator<Dandremid>()
            {
                @Override
                public Dandremid createFromParcel(Parcel parcel)
                {
                    return new Dandremid(parcel);
                }
 
                @Override
                public Dandremid[] newArray(int size)
                {
                    return new Dandremid[size];
                }
        };
	
	

	public Dandremid(int id, String name, int level, int exp, int expNextLevel,
			int selected, int strength, int defense, int speed, int feed,
			int maxFeed, int happiness, int life, int maxLife) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.exp = exp;
		this.expNextLevel = expNextLevel;
		this.selected = selected;
		this.strength = strength;
		this.defense = defense;
		this.speed = speed;
		this.feed = feed;
		this.maxFeed = maxFeed;
		this.happiness = happiness;
		this.life = life;
		this.maxLife = maxLife;
		this.dandremidBase = null;
	}
	
	public Dandremid (Parcel p) {
		id = p.readInt();
		name = p.readString();
		level = p.readInt();
		exp = p.readInt();
		expNextLevel = p.readInt();
		selected = p.readInt();
		strength = p.readInt();
		defense = p.readInt();
		speed = p.readInt();
		feed = p.readInt();
		maxFeed = p.readInt();
		happiness = p.readInt();
		life = p.readInt();
		maxLife = p.readInt();
		dandremidBase = (DandremidBase) p.readParcelable(getClass().getClassLoader());
	}
	
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(id);
		p.writeString(name);
		p.writeInt(level);
		p.writeInt(exp);
		p.writeInt(expNextLevel);
		p.writeInt(selected);
		p.writeInt(strength);
		p.writeInt(defense);
		p.writeInt(speed);
		p.writeInt(feed);
		p.writeInt(maxFeed);
		p.writeInt(happiness);
		p.writeInt(life);
		p.writeInt(maxLife);
		p.writeParcelable(dandremidBase, flags);		
	}
	
	@Override
	public int describeContents() {
		return 0;
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

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFeed() {
		return feed;
	}

	public void setFeed(int feed) {
		this.feed = feed;
	}

	public int getMaxFeed() {
		return maxFeed;
	}

	public void setMaxFeed(int maxFeed) {
		this.maxFeed = maxFeed;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	public DandremidBase getDandremidBase() {
		return dandremidBase;
	}

	public void setDandremidBase(DandremidBase dandremidBase) {
		this.dandremidBase = dandremidBase;
	}
	
}
