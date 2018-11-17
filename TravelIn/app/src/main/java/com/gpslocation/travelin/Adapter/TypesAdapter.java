package com.gpslocation.travelin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gpslocation.travelin.R;

import java.util.ArrayList;

public class TypesAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> namesList;

    public TypesAdapter(Context context, ArrayList<String> namesList) {
        this.context = context;
        this.namesList = namesList;
    }

    @Override
    public int getCount() {
        return namesList.size();
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
        view = layoutInflater.inflate(R.layout.textview, null);
        TextView placeType = (TextView) view.findViewById(R.id.typeText);
        placeType.setText(namesList.get(i).toString());
        return view;
    }
}
