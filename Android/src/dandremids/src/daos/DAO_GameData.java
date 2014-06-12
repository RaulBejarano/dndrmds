package dandremids.src.daos;

import dandremids.src.model.db.Attack;
import dandremids.src.model.db.AttackState;
import dandremids.src.model.db.Object;
import dandremids.src.model.db.DandremidBase;
import dandremids.src.model.db.Element;
import dandremids.src.model.db.ElementElement;
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
		
		DAO_DandremidBase daoDandremidBase = new DAO_DandremidBase(context, db);
		DAO_Element daoElement = new DAO_Element(context, db);
		DAO_State daoState = new DAO_State(context, db);
		DAO_Attack daoAttack = new DAO_Attack(context, db);
		DAO_AttackState daoAttackState = new DAO_AttackState(context, db);
		DAO_ElementElement daoElementElement = new DAO_ElementElement(context,db);
		DAO_Object daoObject = new DAO_Object(context, db);
		
		daoElement.deleteAll();
		for (Element e : gm.elements) {
			daoElement.insertElement(e);
		}
		
		daoElementElement.deleteAll();
		for(ElementElement co : gm.elementElement){
			daoElementElement.insertCombatObject(co);
		}
				
		daoDandremidBase.deleteAll();
		for (DandremidBase cb : gm.dandremidBases) {
			daoDandremidBase.insertDandremidBase(cb);
		}
		
		daoAttack.deleteAll();
		for (Attack a : gm.attacks) {
			daoAttack.insertAttack(a);
		}
		
		daoState.deleteAll();
		for (State s : gm.states) {
			daoState.insertState(s);
		}
			
		daoAttackState.deleteAll();
		for (AttackState as : gm.attackStates) {
			daoAttackState.insertAttack_State(as);
		}
		
		daoObject.deleteAll();
		for(Object co : gm.objects){
			daoObject.insertObject(co);
		}
		
		
		return true;
	}
}
