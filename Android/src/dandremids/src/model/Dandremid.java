package dandremids.src.model;

import java.util.ArrayList;
import java.util.List;

import dandremids.src.daos.DAO_DandremidBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;


public class Dandremid implements Parcelable {
	
	// General Attributes
	private int id;
	private String name;
	private int level;
	private int exp;
	private int expNextLevel;
	private int selected;
	
	private int strength;
	private int defense;
	private int speed;
	
	private int feed;
	private int maxFeed;
	private int happiness;
	
	private int life;
	private int maxLife;
	
	private DandremidBase dandremidBase;
	private List<Attack> attackList;
	private List<State> stateList;
	
	// Just for combat use
	public int tmp_strength;
	public int tmp_defense;
	public int tmp_speed;
	
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
		this.attackList = new ArrayList<Attack>();
		this.stateList = new ArrayList<State>();
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
		attackList = (ArrayList<Attack>) p.readArrayList(getClass().getClassLoader());
		stateList = (ArrayList<State>) p.readArrayList(getClass().getClassLoader());
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
		p.writeList(attackList);
		p.writeList(stateList);
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

	public List<Attack> getAttackList() {
		return attackList;
	}

	public void setAttackList(List<Attack> attackList) {
		this.attackList = attackList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList=stateList;
	}	
	
	public List<State> getStateList() {
		return stateList;
	}

	public void makeAttack(Context context, Attack attack, Dandremid target, List<ElementElement> elementList) {
		// STRIKE
		double modifier=-1;
		for (ElementElement e : elementList){
			if (( attack.getElement().getId() == e.getElement_id_1() ) &&
				( target.getDandremidBase().getElement1().getId() == e.getElement_id_2() || target.getDandremidBase().getElement2().getId() == e.getElement_id_2() )) {
				
				if (e.getPower()>modifier) {
					modifier=e.getPower() ;
				}
			}			
		}
		
		if (modifier == -1) modifier = 1;
		if (modifier > 1 ) Toast.makeText(context, "Super efective", Toast.LENGTH_SHORT).show();
		if (modifier < 1 )Toast.makeText(context, "Not efective", Toast.LENGTH_SHORT).show();;
		
		double total = modifier * attack.getLevel() * attack.getStrike() + this.getStrength() + this.tmp_strength;
		total = total  -  (target.getDefense() + target.tmp_defense);
		total = (int)(total * (Math.random() * 0.3 + 0.8) );
		if (total <= 0) total = 1;
		target.setLife((int)(target.getLife()-total));
		if (target.getLife()<=0) target.setLife(0);
		
		// HEAL
		total = attack.getLevel() * attack.getHeal();
		this.setLife((int)(this.getLife()+total));
		if(this.getLife()<0) this.setLife(0);
		if(this.getLife()>this.getMaxLife()) this.setLife(this.getMaxLife());
		
		// APPLY STATES
		
	}

	public static Dandremid getWildDandremid(Context context, User currentUser, SQLiteDatabase db) {
		
			DAO_DandremidBase dcb = new DAO_DandremidBase(context, db);				
			DandremidBase cb = dcb.getRandomDandremidBase();
				
			// Set Dandremid parameters
			int level = currentUser.getLevel() - 3 + (int) (Math.random()*6);
			if (level < 1) {
				level = 1;
			}
			int exp = (int)Math.pow(level, 4);
			int expNextLevel = (int)Math.pow(level+1, 4);
			int strength = (int) (cb.getBase_strength() + level * 2);
			int defense = (int) (cb.getBase_defense() + level *2 );
			int speed = (int) (cb.getBase_speed() + level * 2 );			
			int maxFeed = (int)(1.5 * cb.getBase_maxFeed() * Math.log(cb.getBase_maxFeed()*level));
			int feed = maxFeed/2;
			int maxLife = (int)(cb.getBase_maxLife()/5 * (level - 1) + cb.getBase_maxLife());
			int life = maxLife;
			
			
			//Dandremid(int id, String name, int level, int exp, int expNextLevel,int selected, int strength, int defense, int speed, int feed, int maxFeed, int happiness, int life, int maxLife)
			Dandremid d = new Dandremid (0, cb.getName(), level, exp, expNextLevel, 1, strength, defense, speed, feed, maxFeed, 0, life, maxLife);
			d.setDandremidBase(cb);
			
			int nAttacks = (int) (Math.random()*2 + 1); // 1 - 3 Attacks
			for (int i=0; i<nAttacks; i++){
				d.getAttackList().add(Attack.getRandomAttackForDandremid(context, db, d));
			}	
			
			for (Attack a : d.getAttackList()) {
				System.out.println(a.getName());
			}
			
			return d;
	}

	public void updateTimeChangingValues() {
		feed=feed-1;
		if (feed<0) feed=0;
				
		double feedRatius = (double)feed/maxFeed;
		
		if (feedRatius<0.75){
			happiness = happiness-1;
			if (happiness<0) happiness=0;
		}
		
		double happinessRatius = (double)happiness/100;
		
		if ( feedRatius > 0.75 && happinessRatius > 0.75 ){
			life = life + 5;
			if (life > maxLife) life = maxLife;
		} else if (feedRatius < 0.25 || happinessRatius<0.25) {
			life = life - 1;
			if (life<0) life = 0;
		}
	}

	public dandremids.src.model.db.Dandremid toDBDandremid(User u) {
		
		dandremids.src.model.db.Dandremid d = new dandremids.src.model.db.Dandremid();
		d.id = this.id;
		d.name = this.name;
		d.level = this.level;
		d.exp = this.exp;
		d.expNextLevel = this.expNextLevel;
		d.selected = this.selected;
		d.strength = this.strength;
		d.defense = this.defense;
		d.speed = this.speed;
		d.feed = this.feed;
		d.maxFeed = this.maxFeed;
		d.life = this.life;
		d.maxLife = this.maxLife;
		d.happiness = this.happiness;
		d.User_id = u.getId();		
		d.Dandremid_Base_id = this.dandremidBase.getId();
		
		List<dandremids.src.model.db.DandremidAttack> attacks = new ArrayList<dandremids.src.model.db.DandremidAttack>();
		for (Attack a : this.getAttackList()){
			attacks.add(a.toDBDandremidAttack(this));
		}		
		d.attacks = attacks;
		
		List<dandremids.src.model.db.DandremidState> states = new ArrayList<dandremids.src.model.db.DandremidState>();
		for (State s : this.stateList) {
			states.add(s.toDBDandremidState(this));
		}
		d.states = states;
		
		return d;
	}
	
}
