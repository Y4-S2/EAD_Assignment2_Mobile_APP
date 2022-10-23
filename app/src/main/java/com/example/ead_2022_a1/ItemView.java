package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ItemView extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        listView = findViewById(R.id.listView);

        //array list
        ArrayList<FuelStation> fuelStation = new ArrayList<>();

        //add items to array list
        fuelStation.add(new FuelStation("Shell"));
        fuelStation.add(new FuelStation("Caltex"));
        fuelStation.add(new FuelStation("Puma"));
        fuelStation.add(new FuelStation("BP"));

        //create adapter
        FuelStationAdapter adapter = new FuelStationAdapter(this, R.layout.list_item, fuelStation);
        listView.setAdapter(adapter);
    }
}