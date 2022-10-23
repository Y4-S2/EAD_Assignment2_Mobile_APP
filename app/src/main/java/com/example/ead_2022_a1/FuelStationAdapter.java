package com.example.ead_2022_a1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class FuelStationAdapter extends ArrayAdapter<FuelStation> {
    private  Context context;
    private int resource;

    public FuelStationAdapter(@NonNull Context context, int resource, @NonNull List<FuelStation> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null);

        TextView fsName = view.findViewById(R.id.fsName);
        FuelStation fuelStation = getItem(position);
        fsName.setText(fuelStation.getFsName());

        return view;
    }
}
