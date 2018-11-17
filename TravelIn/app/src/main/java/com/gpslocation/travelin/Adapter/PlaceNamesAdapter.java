package com.gpslocation.travelin.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gpslocation.travelin.DetailsActivity;
import com.gpslocation.travelin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlaceNamesAdapter extends BaseAdapter {
    Context context;
    ArrayList<JSONObject> jsonObjectArrayList;

    public PlaceNamesAdapter(Context context, ArrayList<JSONObject> jsonObjectArrayList) {
        this.context = context;
        this.jsonObjectArrayList = jsonObjectArrayList;
    }

    @Override
    public int getCount() {
        return jsonObjectArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.placename, null);
        TextView placeName = (TextView) view.findViewById(R.id.placeNameText);
        try {
            placeName.setText(jsonObjectArrayList.get(i).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        view.setTag(jsonObjectArrayList.get(i));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsActivity.class);
                intent.putExtra("placejsonObject",""+view.getTag());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
