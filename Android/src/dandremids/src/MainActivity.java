package dandremids.src;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import dandremids.src.customclasses.DandremidsSQLiteHelper;
import dandremids.src.daos.DAO_User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView user;
	TextView password;
	Button login;
	
	
	DAO_User daoUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		DandremidsSQLiteHelper dsh = new DandremidsSQLiteHelper(this,"DandremidsDB",null,1);
		daoUser = new DAO_User(this, dsh.getWritableDatabase());
		
		/*
		if(daoUser.isCurrentUser()){
			Intent i=new Intent(this, HomeActivity.class);
			this.startActivity(i);
			this.finish();
		} 
		*/
				
		user = (TextView) this.findViewById(R.id.main_user_text);
		password = (TextView) this.findViewById(R.id.main_password_text);
		
		login = (Button) this.findViewById(R.id.main_login_button);
		login.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				doLogin();				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	protected void doLogin(){
		DoLoginTask task = new DoLoginTask();
		task.execute();
		
/*
		if (daoUser.doLogIn(user.getText().toString(),password.getText().toString())){
			Intent i = new Intent(this, HomeActivity.class);
			this.startActivity(i);
			this.finish();
		} else {
			Toast.makeText(this, "[ERROR] Wrong user or password", Toast.LENGTH_LONG).show();
		}
*/		
		
	}
	
	
	
private class DoLoginTask extends AsyncTask<String, Void, String> {
        
        private ProgressDialog d;
        
        @Override
        protected void onPreExecute() {
              d= new ProgressDialog(MainActivity.this);
              d.setMessage("Logging in...");
              d.show();
        }

        @Override
        protected String doInBackground(String... urls) {
        	String res ="";
        	HttpsURLConnection urlConnection = null;
        	
        	try { 
        		URL url = new URL("https://dndrmds.eu1.frbit.net/REST/");
        		urlConnection = (HttpsURLConnection) url.openConnection();
        		
        		urlConnection.setRequestMethod("POST");
        		urlConnection.setDoOutput(true);
        		String urlParameters = "key=7dc6c14f1930142f39a3d2c36546cb345039825b";
        		 
        		// Send post request
        		urlConnection.setDoOutput(true);
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
        	} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        finally {
	        	urlConnection.disconnect();
	        }
        	
        	
        	return res;

        }

        @Override
        protected void onPostExecute(String result) {
        	 showToast(result);
             d.dismiss();
        }
  }


	public void showToast(String text){
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
	}
}
