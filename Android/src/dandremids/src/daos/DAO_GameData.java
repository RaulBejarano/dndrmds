package dandremids.src.daos;

import dandremids.src.model.db.Attack;
import dandremids.src.model.db.Attack_State;
import dandremids.src.model.db.Creature_Base;
import dandremids.src.model.db.Element;
import dandremids.src.model.db.GameData;
import dandremids.src.model.db.State;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO_GameData {

	Context context;
	SQLiteDatabase db;
	
	public DAO_GameData(Context context, SQLiteDatabase db){
		super();
		this.context=context;
		this.db=db;
	}
	
	
	public boolean updateGameData(GameData gm){
		
		DAO_CreatureBase daoCreatureBase = new DAO_CreatureBase(context, db);
		DAO_Element daoElement = new DAO_Element(context, db);
		DAO_State daoState = new DAO_State(context, db);
		DAO_Attack daoAttack = new DAO_Attack(context, db);
		DAO_Attack_State daoAttackState = new DAO_Attack_State(context, db);
		
		daoCreatureBase.deleteAll();
		for (Creature_Base cb : gm.creatureBases) {
			daoCreatureBase.insertCreatureBase(cb);
		}
		
		daoElement.deleteAll();
		for (Element e : gm.elements) {
			daoElement.insertElement(e);
		}
		
		daoState.deleteAll();
		for (State s : gm.states) {
			daoState.insertState(s);
		}
		
		daoAttack.deleteAll();
		for (Attack a : gm.attacks) {
			daoAttack.insertAttack(a);
		}
		
		daoAttackState.deleteAll();
		for (Attack_State as : gm.attackStates) {
			daoAttackState.insertAttack_State(as);
		}
		
		
		return true;
	}
}
