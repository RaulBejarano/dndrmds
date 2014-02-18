package dandremids.src.model;

public class Attack {
	int id;
	String name;
	Element element;
	int strike;
	int heal;
	int minimumLevel;
	
	int level;
	int uses;
	int nextLevelUses;
	
	
	public Attack(int id, String name, Element element, int strike, int heal,
			int minimumLevel, int level, int uses, int nextLevelUses) {
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
		
}
