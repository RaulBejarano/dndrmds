package dandremids.src.model;

import dandremids.src.daos.DAO_Attack;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

public class Attack implements Parcelable{
	int id;
	String name;
	Element element;
	int strike;
	int heal;
	int minimumLevel;
	
	int level;
	int uses;
	int nextLevelUses;
	
	
	public static final Parcelable.Creator<Attack> CREATOR =
            new Parcelable.Creator<Attack>()
            {
                @Override
                public Attack createFromParcel(Parcel parcel)
                {
                    return new Attack(parcel);
                }
 
                @Override
                public Attack[] newArray(int size)
                {
                    return new Attack[size];
                }
        };
	
	
	public Attack(int id, String name, Element element, int strike, int heal,	int minimumLevel, int level, int uses, int nextLevelUses) {
		this.id = id;
		this.name = name;
		this.element = element;
		this.strike = strike;
		this.heal = heal;
		this.minimumLevel = minimumLevel;
		this.level = level;
		this.uses = uses;
		this.nextLevelUses = nextLevelUses;
	}
	
	
	public Attack(Parcel p) {
		this.id = p.readInt();
		this.name=p.readString();
		this.element = p.readParcelable(getClass().getClassLoader());
		this.strike = p.readInt();
		this.heal = p.readInt();
		this.minimumLevel = p.readInt();
		this.level = p.readInt();
		this.uses = p.readInt();
		this.nextLevelUses = p.readInt();
	}
	
	@Override
	public void writeToParcel(Parcel p, int flags) {
		p.writeInt(id);
		p.writeString(name);
		p.writeParcelable(element, flags);
		p.writeInt(strike);
		p.writeInt(heal);
		p.writeInt(minimumLevel);
		p.writeInt(level);
		p.writeInt(uses);
		p.writeInt(nextLevelUses);
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
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	public int getStrike() {
		return strike;
	}
	public void setStrike(int strike) {
		this.strike = strike;
	}
	public int getHeal() {
		return heal;
	}
	public void setHeal(int heal) {
		this.heal = heal;
	}
	public int getMinimumLevel() {
		return minimumLevel;
	}
	public void setMinimumLevel(int minimumLevel) {
		this.minimumLevel = minimumLevel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getUses() {
		return uses;
	}
	public void setUses(int uses) {
		this.uses = uses;
	}
	public int getNextLevelUses() {
		return nextLevelUses;
	}
	public void setNextLevelUses(int nextLevelUses) {
		this.nextLevelUses = nextLevelUses;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static Attack getRandomAttackForDandremid(Context context, SQLiteDatabase db, Dandremid d) {
		DAO_Attack daoAttack = new DAO_Attack(context, db);
		Attack attack = daoAttack.getRandomAttackForDandremid(d);
		return attack;
	}


	
		
}
