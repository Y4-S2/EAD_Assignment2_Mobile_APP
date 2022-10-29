package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.security.interfaces.DSAKey;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFuelStation extends AppCompatActivity {

    Button btnAddFuelStation;
    TextInputEditText fuelStationName, fuelStationLocation;
    TextInputLayout fuelStationNameLayout, fuelStationLocationLayout;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fuel_station);

        //initialize variables
        btnAddFuelStation = findViewById(R.id.add_fuel_station_button);
        fuelStationName = findViewById(R.id.fuel_station_name_edit_text);
        fuelStationLocation = findViewById(R.id.fuel_station_location_edit_text);
        fuelStationNameLayout = findViewById(R.id.fuel_station_name_layout);
        fuelStationLocationLayout = findViewById(R.id.fuel_station_location_layout);

        //get intent
        String username = getIntent().getStringExtra("username");

        //add fuel station with retrofit
        btnAddFuelStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get values from edit text
                String fuelStationName = fuelStationNameLayout.getEditText().getText().toString();
                String fuelStationLocation = fuelStationLocationLayout.getEditText().getText().toString();

                //fuel station object
                FuelStation fuelStation = new FuelStation(username, fuelStationName, fuelStationLocation , "0", "0");

                //check if the fields are empty
                if(fuelStationName.equals("")||fuelStationLocation.equals(""))
                    //if empty show error
                    Toast.makeText(AddFuelStation.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{

                    //create fuel station object
                   jsonPlaceHolderApi =  RetrofitBuilder.getInstance().configure();

                   //call the add fuel station method
                    jsonPlaceHolderApi.addFuelStation(fuelStation).enqueue(new Callback<FuelStation>() {
                        @Override
                        public void onResponse(Call<FuelStation> call, Response<FuelStation> response) {
                            if(response.isSuccessful()){
                                //if successful show message
                                Toast.makeText(AddFuelStation.this, "Fuel station added successfully", Toast.LENGTH_SHORT).show();

                                //go back to the main activity
                                Intent intent = new Intent(AddFuelStation.this, FuelOwnerProfile.class);
                            }
                        }

                        @Override
                        public void onFailure(Call<FuelStation> call, Throwable t) {
                            //if failed show error
                            Toast.makeText(AddFuelStation.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });


    }
}