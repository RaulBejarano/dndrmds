package dandremids.src.model;

import android.graphics.Bitmap;


public class DandremidBase {
	public enum Element {NONE, RARE, NORMAL, FLAME, DROP, THUNDER, LEAF, COLD, FIST, VENOM, WING, MENTAL, INSECT, STONE, IRON};
	
	
	int id;
	String name;
	Element element1;
	Element element2;
	
	int base_strength;
	int base_defense;
	int base_speed;

	int base_maxFeed;
	
	int base_maxLife;
	String description;
	
	Bitmap image;

	public DandremidBase(int id, String name, Element element1,
			Element element2, int base_strength, int base_defense,
			int base_speed, int base_maxFeed,
			int base_maxLife, String description, Bitmap image) {
		super();
		this.id = id;
		this.name = name;
		this.element1 = element1;
		this.element2 = element2;
		this.base_strength = base_strength;
		this.base_defense = base_defense;
		this.base_speed = base_speed;
		this.base_maxFeed = base_maxFeed;
		this.base_maxLife = base_maxLife;
		this.description = description;
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

	public Element getElement1() {
		return element1;
	}

	public void setElement1(Element element1) {
		this.element1 = element1;
	}

	public Element getElement2() {
		return element2;
	}

	public void setElement2(Element element2) {
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

	public int getBase_maxFeed() {
		return base_maxFeed;
	}

	public void setBase_maxFeed(int base_maxFeed) {
		this.base_maxFeed = base_maxFeed;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
		
}
