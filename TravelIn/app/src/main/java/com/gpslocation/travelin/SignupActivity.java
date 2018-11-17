package com.gpslocation.travelin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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


public class SignupActivity extends Activity {

    EditText nameEdit, mobileEdit, emailEdit, userEdit, passEdit;
    Button submit;
    JSONParser parser = new JSONParser();
    JSONArray response;

    String nameString, mobileString, mailString, usernameString, passwordString, successMessage;
    int successCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nameEdit = (EditText) findViewById(R.id.name);
        mobileEdit = (EditText) findViewById(R.id.mobile);
        emailEdit = (EditText) findViewById(R.id.mail);
        userEdit = (EditText) findViewById(R.id.susername);
        passEdit = (EditText) findViewById(R.id.spassword);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameString = nameEdit.getText().toString();
                mobileString = mobileEdit.getText().toString();
                mailString = emailEdit.getText().toString();
                usernameString = userEdit.getText().toString();
                passwordString = passEdit.getText().toString();

                if (nameString.length() > 0 && mobileString.length() > 0 && mailString.length() > 0 && usernameString.length() > 0 && passwordString.length() > 0) {
                    if (CheckInternetConnection.isInternetOn(SignupActivity.this)) {
                        new Register().execute();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public class Register extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ShowLoader.Loader(SignupActivity.this);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                List<NameValuePair> args = new ArrayList<>();
                args.add(new BasicNameValuePair("name", nameString));
                args.add(new BasicNameValuePair("mobile", mobileString));
                args.add(new BasicNameValuePair("email", mailString));
                args.add(new BasicNameValuePair("username", usernameString));
                args.add(new BasicNameValuePair("password", passwordString));

                JSONObject object = parser.makeHttpRequest(Constants.REGISTER_URL, "POST", args);
                System.out.println("" + object);
                successCode = object.getInt("success");
                successMessage = object.getString("message");
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ShowLoader.Dismiss();
            if (successCode == 1) {
                Intent in = new Intent(SignupActivity.this, LogInActivity.class);
                startActivity(in);
            }
            Toast.makeText(SignupActivity.this, successMessage, Toast.LENGTH_LONG).show();
        }
    }
}
