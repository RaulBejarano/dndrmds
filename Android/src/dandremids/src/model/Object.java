package dandremids.src.model;

import dandremids.src.model.db.UserObject;
import android.os.Parcel;
import android.os.Parcelable;

public class Object implements Parcelable{
	int id;
	String name;
	int strength;
	int defense;
	int speed;
	int life;
	int feed;
	int happiness;
	boolean trap;
	String type;
	int price;
	int quantity;
	
	public static final Parcelable.Creator<Object> CREATOR =
            new Parcelable.Creator<Object>()
            {
                @Override
                public Object createFromParcel(Parcel parcel)
                {
                    return new Object(parcel);
                }
 
                @Override
                public Object[] newArray(int size)
                {
                    return new Object[size];
                }
        };
	
	public Object(int id, String name, int strength, int defense, int speed, int life, int feed, int happiness, boolean trap, String type, int price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.strength = strength;
		this.defense = defense;
		this.speed = speed;
		this.life = life;
		this.feed = feed;
		this.happiness = happiness;
		this.trap = trap;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
	}

	public Object (Parcel p){
		id = p.readInt();
		name = p.readString();
		strength = p.readInt();
		defense = p.readInt();
		speed = p.readInt();
		life = p.readInt();
		feed = p.readInt();
		happiness = p.readInt();
		trap = p.readInt()>0;
		type = p.readString();
		price = p.readInt();
		quantity = p.readInt();
	}
	
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(id);
		p.writeString(name);
		p.writeInt(strength);
		p.writeInt(defense);
		p.writeInt(speed);
		p.writeInt(life);
		p.writeInt(feed);
		p.writeInt(happiness);
		p.writeInt(trap?1:0);
		p.writeString(type);
		p.writeInt(price);
		p.writeInt(quantity);
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getLife() {
		return life;
	}


	public void setLife(int life) {
		this.life = life;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isTrap() {
		return trap;
	}


	public void setTrap(boolean trap) {
		this.trap = trap;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public int getFeed() {
		return feed;
	}

	public void setFeed(int feed) {
		this.feed = feed;
	}

	public int getHappiness() {
		return happiness;
	}

	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserObject toDBUserObject(User u) {
		UserObject uo = new UserObject();
		uo.User_id = u.getId();
		uo.Object_id = this.id;
		uo.quantity = this.quantity;
		
		return uo;
	}
	
}
