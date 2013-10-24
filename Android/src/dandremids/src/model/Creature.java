package dandremids.src.model;


public class Creature {
	public enum Type {NONE, DROP, FLAME, THUNDER, NORMAL, LEAF, RARE};
	
	// General Attributes
	int id;
	String name;
	int level;
	int exp;
	int expNextLevel;
	boolean selected;
	
	int strength;
	int defense;
	int speed;
	
	int feed;
	int maxFeed;
	int starveSpeed;
	int happiness;
	
	int life;
	int maxLife;
	
	CreatureBase creatureBase;

	public Creature(int id, String name, int level, int exp, int expNextLevel,
			boolean selected, int strength, int defense, int speed, int feed,
			int maxFeed, int starveSpeed, int happiness, int life, int maxLife) {
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
		this.starveSpeed = starveSpeed;
		this.happiness = happiness;
		this.life = life;
		this.maxLife = maxLife;
		this.creatureBase = null;
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
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

	public int getStarveSpeed() {
		return starveSpeed;
	}

	public void setStarveSpeed(int starveSpeed) {
		this.starveSpeed = starveSpeed;
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

	public CreatureBase getCreatureBase() {
		return creatureBase;
	}

	public void setCreatureBase(CreatureBase creatureBase) {
		this.creatureBase = creatureBase;
	}
	
	
	
	
	
}
