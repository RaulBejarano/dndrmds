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
import dandremids.src.daos.DAO_LeagueData;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.db.GameData;
import dandremids.src.model.db.League;
import dandremids.src.model.db.LeagueData;
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
	
	
	public String doLogin(String playerName, String password){
		String urlParameters = "key="+key+"&playerName="+playerName+"&password="+password;	
		String result = sendRequest("login", urlParameters);
		User usr = new Gson().fromJson(result, User.class);			
		if (usr==null){
			return null;
		}
		usr.password=password;
		
		DAO_User daoUser = new DAO_User (context, db);	
		daoUser.deleteAll();
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
		daoUser.deleteLocalUser();
		daoUser.insertUser(usr);
		return "true";		
	}
	
	
	public String searchLeagueInvitations (User user) {
		String usr = new Gson().toJson(user);
		String urlParameters = "key="+key+"&user="+usr;
		String result = sendRequest("searchLeagueInvitations",urlParameters);
		
		return result;
	}
	
	
	public String createLeague (User user, League league){
		String usr = new Gson().toJson(user);
		String lg = new Gson().toJson(league);
		String urlParameters = "key="+key+"&league="+lg+"&user="+usr;
		String result = sendRequest("createLeague", urlParameters);
		
		LeagueData leagueData = new Gson().fromJson(result, LeagueData.class);
		if (leagueData==null){
			return null;
		}
		
		DAO_LeagueData daoLD= new DAO_LeagueData(context, db);
		daoLD.insertLeagueData(user, leagueData);
		
		return "true";
	}
	
	
	public String getLeague(User user, League league){
		String lg = new Gson().toJson(league);
		String urlParameters = "key="+key+"&league="+lg;
		String result = sendRequest("getLeague", urlParameters);
		
		LeagueData leagueData = new Gson().fromJson(result, LeagueData.class);
		if (leagueData==null){
			return null;
		}
		
		DAO_LeagueData daoLD= new DAO_LeagueData(context, db);
		daoLD.deleteAll();
		daoLD.insertLeagueData(user, leagueData);
		
		return "true";
	}
	
	
	public String sendLeagueInvitation(String playerName, League league) {
		String l = new Gson().toJson(league);
		String urlParameters = "key="+key+"&playerName="+playerName+"&league="+l;
		sendRequest("inviteUserToLeague", urlParameters);
		
		return "true";
	}
	
	
	public String aceptLeagueInvitation(User user, League league) {
		String usr = new Gson().toJson(user);
		String l = new Gson().toJson(league);
		String urlParameters = "key="+key+"&user="+usr+"&league="+l;
		String result = sendRequest("aceptLeagueInvitation", urlParameters);		
		
		LeagueData leagueData = new Gson().fromJson(result, LeagueData.class);
		if (leagueData==null){
			return null;
		}
		
		DAO_LeagueData daoLD= new DAO_LeagueData(context, db);
		daoLD.insertLeagueData(user, leagueData);
		
		return "true";
	}
	
	
	public String rejectLeagueInvitation(User user, League league) {
		String u = new Gson().toJson(user);
		String l = new Gson().toJson(league);
		String urlParameters = "key="+key+"&user="+u+"&league="+l;
		String result = sendRequest("rejectLeagueInvitation", urlParameters);
		
		return "true";
	}
	
	
	public String abandoneLeague(User u, League l) {
		String user = new Gson().toJson(u);
		String league = new Gson().toJson(l);
		String urlParameters = "key="+key+"&user="+user+"&league="+league;
		String result = sendRequest("abandoneLeague", urlParameters);
		
		System.out.println(result);
		
		if (result!=null){
			DAO_League daoLeague = new DAO_League(context, db);
			daoLeague.deleteAll();
		}
		return "true";
	}
	
	
	public String startLeague(String string) {
		// TODO Auto-generated method stub
		return null;
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
