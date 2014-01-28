package dandremids.src.customclasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;

import dandremids.src.daos.DAO_GameData;
import dandremids.src.daos.DAO_User;
import dandremids.src.model.db.GameData;
import dandremids.src.model.db.User;

public class DandremidsREST {
	HttpsURLConnection urlConnection = null;
	String key = "7dc6c14f1930142f39a3d2c36546cb345039825b";
	
	private Context context;
	private SQLiteDatabase db;
	
	public DandremidsREST (Context context, SQLiteDatabase db) {	
		super();
		this.context=context;
		this.db=db;
	}
	
	
	public String updateGameData(){
		try {
			URL url = new URL("https://dndrmds.eu1.frbit.net/REST/?op=updateGameData");
			urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			String urlParameters = "key="+key;	
			String result = sendRequest(urlParameters);
			
			GameData gameData = new Gson().fromJson(result, GameData.class);
			
			if (gameData==null) {
				return null;
			}
			
			DAO_GameData daoGM = new DAO_GameData(context, db);
			daoGM.updateGameData(gameData);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			urlConnection.disconnect();			
		}
		
		return "true";
	}
	
		
	
	public String doLogin(String user, String password){
		try {
			URL url = new URL("https://dndrmds.eu1.frbit.net/REST/?op=login");
			urlConnection = (HttpsURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput(true);
			String urlParameters = "key="+key+"&user="+user+"&password="+password;	
			String result = sendRequest(urlParameters);

			User usr = new Gson().fromJson(result, User.class);
			if (usr==null){
				return null;
			}
			
			DAO_User daoUser = new DAO_User (context, db);			
			daoUser.insertUser(usr);		
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			urlConnection.disconnect();			
		}
		
		return "true";
	}	
	
	
	private String sendRequest(String urlParameters){
		String res = "";
		
		try {
			DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
		
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
	
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
	
			while ((inputLine = br.readLine()) != null) {
				res += inputLine;
			}
	
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
}
