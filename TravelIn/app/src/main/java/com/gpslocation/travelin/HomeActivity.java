package com.gpslocation.travelin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gpslocation.travelin.Adapter.TypesAdapter;

import java.util.ArrayList;


public class HomeActivity extends Activity {

    //   String[] catagories = {"Restaurants", "Parking spots", "Car Rentals", "Motels", "Visiting Places", "Best food", "Events"};

    String[] catagoriesType = {"restaurant", "parking", "car_rental", "hotel", "Visiting Places", "Best food", "Events"};

    ArrayList<String> typesArray = new ArrayList<>();

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = (ListView) findViewById(R.id.category);
        typesArray.add("Restaurants");
        typesArray.add("Parking Spots");
        typesArray.add("Car Rentals");
        typesArray.add("Motels");
        typesArray.add("Visiting Places");
        typesArray.add("Best food");
        typesArray.add("Events");

        //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(HomeActivity.this, R.layout.textview, catagories);
        TypesAdapter typesAdapter = new TypesAdapter(HomeActivity.this, typesArray);
        listView.setAdapter(typesAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position < 4) {
                    Intent intent = new Intent(HomeActivity.this, NeartestPlacesActivity.class);
                    intent.putExtra("SearchType", catagoriesType[position].toString());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(HomeActivity.this, WebActivity.class);
                    intent.putExtra("SearchType", catagoriesType[position].toString());
                    startActivity(intent);
                }
            }
        });
    }
}
