package dandremids.src.model.db;

import java.util.List;

public class Dandremid {
	public int id;
	public String name;
	public int level;
	public int exp;
	public int expNextLevel;
	public int selected;
	public int strength;
	public int defense;
	public int speed;
	public int feed;
	public int maxFeed;
	public int life;
	public int maxLife;
	public int happiness;
	public int User_id;
	public int Dandremid_Base_id;
	public List<DandremidAttack> attacks;
	public List<DandremidState> states;
}
