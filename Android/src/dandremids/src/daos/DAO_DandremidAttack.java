package dandremids.src.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO_DandremidAttack {

	private SQLiteDatabase db;
	private Context context;
	
	
	public DAO_DandremidAttack(Context context, SQLiteDatabase db) {
		this.context=context;
		this.db=db;
	}
	
	public void insertDandremidAttack(dandremids.src.model.db.DandremidAttack da){
		String sql ="INSERT INTO Dandremid_Attack (Attack_id, Dandremid_id, level, uses, nextLevelUses)" +
				"VALUES ("+da.Attack_id+","+da.Dandremid_id+","+da.level+","+da.uses+","+da.nextLevelUses+")";
		db.execSQL(sql);
	}
	
	public void deleteAll(){
		String sql = "DELETE FROM DandremidAttack";
		db.execSQL(sql);
	}
}
