package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemView extends AppCompatActivity {


    private ArrayList<FuelStation> fuelStation;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        fuelStation = new ArrayList<>();
        recyclerView = findViewById(R.id.item_recycle);

        setAdapter();
        setFuelStationInfo();
    }

    //set adapter
    private void setAdapter() {
       recyclerView = findViewById(R.id.item_recycle);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new ItemRecyclerAdapter(fuelStation , this));

    }

    //set fuel station info
    private void setFuelStationInfo() {
        //add items to array list
        fuelStation.add(new FuelStation("Wattala Station"));
        fuelStation.add(new FuelStation("Homagama Station"));
        fuelStation.add(new FuelStation("Kaduwela Station"));
        fuelStation.add(new FuelStation("Kadawatha Station"));
    }


}