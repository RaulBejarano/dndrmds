package dandremids.src.model;

import android.graphics.Bitmap;


public class CreatureBase {

	int id;
	String name;
	Creature.Type element1;
	Creature.Type element2;
	
	int base_strength;
	int base_defense;
	int base_speed;

	int base_feed;
	int base_maxFeed;
	int base_starveSpeed;
	
	int base_maxLife;
	
	Bitmap image;

	public CreatureBase(int id, String name, Creature.Type element1,
			Creature.Type element2, int base_strength, int base_defense,
			int base_speed, int base_feed, int base_maxFeed,
			int base_starveSpeed, int base_maxLife, Bitmap image) {
		super();
		this.id = id;
		this.name = name;
		this.element1 = element1;
		this.element2 = element2;
		this.base_strength = base_strength;
		this.base_defense = base_defense;
		this.base_speed = base_speed;
		this.base_feed = base_feed;
		this.base_maxFeed = base_maxFeed;
		this.base_starveSpeed = base_starveSpeed;
		this.base_maxLife = base_maxLife;
		this.image=image;
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

	public Creature.Type getElement1() {
		return element1;
	}

	public void setElement1(Creature.Type element1) {
		this.element1 = element1;
	}

	public Creature.Type getElement2() {
		return element2;
	}

	public void setElement2(Creature.Type element2) {
		this.element2 = element2;
	}

	public int getBase_strength() {
		return base_strength;
	}

	public void setBase_strength(int base_strength) {
		this.base_strength = base_strength;
	}

	public int getBase_defense() {
		return base_defense;
	}

	public void setBase_defense(int base_defense) {
		this.base_defense = base_defense;
	}

	public int getBase_speed() {
		return base_speed;
	}

	public void setBase_speed(int base_speed) {
		this.base_speed = base_speed;
	}

	public int getBase_feed() {
		return base_feed;
	}

	public void setBase_feed(int base_feed) {
		this.base_feed = base_feed;
	}

	public int getBase_maxFeed() {
		return base_maxFeed;
	}

	public void setBase_maxFeed(int base_maxFeed) {
		this.base_maxFeed = base_maxFeed;
	}

	public int getBase_starveSpeed() {
		return base_starveSpeed;
	}

	public void setBase_starveSpeed(int base_starveSpeed) {
		this.base_starveSpeed = base_starveSpeed;
	}

	public int getBase_maxLife() {
		return base_maxLife;
	}

	public void setBase_maxLife(int base_maxLife) {
		this.base_maxLife = base_maxLife;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
		
}
