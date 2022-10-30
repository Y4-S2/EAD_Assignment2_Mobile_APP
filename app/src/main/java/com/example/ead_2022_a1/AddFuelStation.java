package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

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
        String username = getIntent().getStringExtra("userName");

        //add fuel station with retrofit
        btnAddFuelStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

                //get values from edit text
                String fuelStationName = fuelStationNameLayout.getEditText().getText().toString();
                String fuelStationLocation = fuelStationLocationLayout.getEditText().getText().toString();

                JsonObject fuelStation = new JsonObject();
                fuelStation.addProperty("userName", username);
                fuelStation.addProperty("name", fuelStationName);
                fuelStation.addProperty("location", fuelStationLocation);

                System.out.println(fuelStation);

                //check if the fields are empty
                if(fuelStationName.equals("")||fuelStationLocation.equals(""))
                    //if empty show error
                    Toast.makeText(AddFuelStation.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{

                   //call the add fuel station method
                    jsonPlaceHolderApi.addFuelStation(fuelStation).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.isSuccessful()){
                                //if successful show message
                                Toast.makeText(AddFuelStation.this, "Fuel station added successfully", Toast.LENGTH_SHORT).show();
                            }
                            Intent intent = new Intent(AddFuelStation.this, FuelOwnerProfile.class);
                            intent.putExtra("userName", username);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            //if failed show error
                            Toast.makeText(AddFuelStation.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });


    }
}