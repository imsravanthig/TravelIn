package com.gpslocation.travelin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Splashscreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Thread background = new Thread() {

            public void run()
            {
                try
                {
                    sleep(5*1000);

                    Intent in=new Intent(Splashscreen.this,LogInActivity.class);
                    startActivity(in);

                    finish();
                }catch (Exception e)
                {

                }
            }

        };
        background.start();
    }
}
