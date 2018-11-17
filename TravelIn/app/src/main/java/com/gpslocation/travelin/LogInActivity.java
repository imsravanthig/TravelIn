package com.gpslocation.travelin;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gpslocation.travelin.Helper.CheckInternetConnection;
import com.gpslocation.travelin.Helper.Constants;
import com.gpslocation.travelin.Helper.ShowLoader;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LogInActivity extends Activity {

    private static final int PERMISSION_LOCATION = 2;
    EditText userEdit, passEdit;
    Button loginButton, signupButton;

    JSONParser parser = new JSONParser();
    JSONArray response;
    String userString, passString, resUsername, resPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userEdit = (EditText) findViewById(R.id.lusername);
        passEdit = (EditText) findViewById(R.id.lpassword);
        loginButton = (Button) findViewById(R.id.login);
        signupButton = (Button) findViewById(R.id.signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userString = userEdit.getText().toString();
                passString = passEdit.getText().toString();
                if (userString.length() > 0 && passString.length() > 0) {
                    if (CheckInternetConnection.isInternetOn(LogInActivity.this)) {
                        new Login().execute();
                    }
                } else {
                    Toast.makeText(LogInActivity.this, "Enter all the fields ", Toast.LENGTH_LONG).show();
                }
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LogInActivity.this, SignupActivity.class);
                startActivity(in);
            }
        });

        permissionChecking();
    }

    public void permissionChecking(){
        if (ContextCompat.checkSelfPermission(LogInActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LogInActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
                    }, PERMISSION_LOCATION);
        }
    }

    public class Login extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ShowLoader.Loader(LogInActivity.this);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> args = new ArrayList<>();
                args.add(new BasicNameValuePair("username", userString));

                JSONObject object = parser.makeHttpRequest(Constants.LOGIN_URL, "POST", args);
                response = object.getJSONArray("userlogin");

                for (int i = 0; i < response.length(); i++) {
                    JSONObject c = response.getJSONObject(i);

                    resUsername = c.getString("username");
                    resPassword = c.getString("password");
                }

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (userString.equals(resUsername) && passString.equals(resPassword)) {
                Intent in = new Intent(LogInActivity.this, HomeActivity.class);
                startActivity(in);
                userEdit.setText("");
                passEdit.setText("");
            } else {
                Toast.makeText(LogInActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
            }
            ShowLoader.Dismiss();
        }
    }
}
