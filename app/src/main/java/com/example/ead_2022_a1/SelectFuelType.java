package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectFuelType extends AppCompatActivity {

    CardView petrolCard, dieselCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_fuel_type);

        //initialize variables
        petrolCard = findViewById(R.id.fuel_type_card_view_petrol);
        dieselCard = findViewById(R.id.fuel_type_card_view_diesel);

        //petrol card
        petrolCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectFuelType.this, ItemView.class);
                startActivity(intent);
            }
        });

        //diesel card
        dieselCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectFuelType.this, ItemView.class);
                startActivity(intent);
            }
        });


    }
}