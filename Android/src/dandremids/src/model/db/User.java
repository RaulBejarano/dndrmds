package dandremids.src.model.db;

import java.util.List;

public class User {
	public int id;
	public String playerName;
	public String name;
	public String password;
	public String email;
	public String surname;
	public String birth;
	public String gender;
	public int level;
	public int exp;
	public int expNextLevel;
	public List<Creature> creatures;
}
