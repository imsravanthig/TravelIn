package com.gpslocation.travelin;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DetailsActivity extends Activity {

    TextView addText;
    Button showButton;

    String getaddressString, addressString, getDetailsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //  getaddressString = getIntent().getStringExtra("selectcategory");
        getDetailsString = getIntent().getStringExtra("placejsonObject");

        try {
            JSONObject detailsJsonObject = new JSONObject(getDetailsString);
            getaddressString = detailsJsonObject.getString("name");
            addressString = detailsJsonObject.getString("vicinity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("" + addressString);

        addText = (TextView) findViewById(R.id.addressText);
        showButton = (Button) findViewById(R.id.showmap);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(DetailsActivity.this, MapsActivity.class);
                in.putExtra("ADDRESS", addressString);
                startActivity(in);
            }
        });

        addText.setText(getaddressString + ",\n" + addressString);
    }
}
