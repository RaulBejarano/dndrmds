package dandremids.src.customclasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import dandremids.src.daos.DAO_GameData;
import dandremids.src.daos.DAO_League;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.db.GameData;
import dandremids.src.model.db.League;
import dandremids.src.model.db.User;

public class DandremidsREST {
	String key = "7dc6c14f1930142f39a3d2c36546cb345039825b";
	
	private Context context;
	private SQLiteDatabase db;
	
	public DandremidsREST (Context context, SQLiteDatabase db) {	
		super();
		this.context=context;
		this.db=db;
	}
	
		
	public String updateGameData(){
		String urlParameters = "key="+key;	
		String result = sendRequest("updateGameData",urlParameters);		
		GameData gameData = new Gson().fromJson(result, GameData.class);		
		if (gameData==null) {
			return null;
		}
		
		DAO_GameData daoGM = new DAO_GameData(context, db);
		daoGM.updateGameData(gameData);
						
		return "true";
	}
	
	
	public String doLogin(String user, String password){
		String urlParameters = "key="+key+"&user="+user+"&password="+password;	
		String result = sendRequest("login", urlParameters);
		User usr = new Gson().fromJson(result, User.class);			
		if (usr==null){
			return null;
		}
		usr.password=password;
		
		DAO_User daoUser = new DAO_User (context, db);			
		daoUser.insertUser(usr);		
		return "true";
	}
	
	public String saveUser (User user) {
		String data = new Gson().toJson(user);
		String urlParameters = "key="+key+"&data="+data;
		String result = sendRequest("saveUser", urlParameters);
		User usr = new Gson().fromJson(result, User.class);			
		if (usr==null){
			return null;
		}
		usr.password=user.password;
		
		DAO_User daoUser = new DAO_User (context, db);			
		daoUser.insertUser(usr);		
		return "true";		
	}
	
	public String searchLeague (String code) {
		String urlParameters = "key="+key+"&code="+code;
		String result = sendRequest("searchLeague",urlParameters);
		System.out.println(result);
		
		return "true";
	}

	public String createLeague (League league){
		String data = new Gson().toJson(league);
		String urlParameters = "key="+key+"&league="+data;
		String result = sendRequest("createLeague", urlParameters);
		
		System.out.println(result);
		League l = new Gson().fromJson(result, League.class);
		if (l==null){
			return null;
		}
		
		DAO_League dl = new DAO_League(context, db);
		dl.deleteAll();
		dl.insertLeague(l);
		
		return "true";
	}
	
	public String invite(String playerName) {
		String urlParameters = "key="+key+"&playerName="+playerName;
		String result = sendRequest("invite", urlParameters);
		System.out.println(result);
		
		
		return "true";
	}

	public String abandone(User u, League l) {
		String user = new Gson().toJson(u);
		String league = new Gson().toJson(l);
		String urlParameters = "key="+key+"&user="+user+"&league="+league;
		String result = sendRequest("abandone", urlParameters);
		System.out.println(result);
		
		return "true";
	}

	public String nextCombat(User u, League l) {
		String user = new Gson().toJson(u);
		String league = new Gson().toJson(l);
		String urlParameters = "key="+key+"&user="+user+"&league="+league;
		String result = sendRequest("nextCombat", urlParameters);
		System.out.println(result);
		
		return "true";
	}
	
	
	
	// -------------- Send Request ----------------
	
	private String sendRequest(String op, String urlParameters){
		HttpsURLConnection urlConnection = null;
		String res = "";		
		try {
			URL url = new URL("https://dndrmds.eu1.frbit.net/REST/?op="+op);
			urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());		
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();	
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;	
			while ((inputLine = br.readLine()) != null) res += inputLine;				
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			urlConnection.disconnect();			
		}	
		return res;
	}

}
