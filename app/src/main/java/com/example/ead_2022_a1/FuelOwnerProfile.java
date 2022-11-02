package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelOwnerProfile extends AppCompatActivity {

    // Declaring variables
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView patrolAmountText, dieselAmountText, ownerNameText, fuelStationNameText, petrolArriavalTimeText, dieselArriavalTimeText, petrolArriavalDateText, dieselArriavalDateText ,petrolFinishTimeText, dieselFinishTimeText, petrolFinishDateText, dieselFinishDateText;
    Button updateBtn , updateFinishTimeBtn;
    String petrolArrivalDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_owner_profile);

        // Initializing variables
        patrolAmountText = findViewById(R.id.textViewPetrolAmount);
        dieselAmountText = findViewById(R.id.textViewDieselAmount);
        ownerNameText = findViewById(R.id.textViewFuelOwnerName);
        fuelStationNameText = findViewById(R.id.textViewFuelStationName);
        updateBtn = findViewById(R.id.buttonUpdateFuelArrivalTime);
        petrolArriavalTimeText = findViewById(R.id.textViewPetrolArrivalTimeValue);
        dieselArriavalTimeText = findViewById(R.id.textViewDieselArrivalTimeValue);
        petrolArriavalDateText = findViewById(R.id.textViewPetrolArrivalDateValue);
        dieselArriavalDateText = findViewById(R.id.textViewDieselArrivalDateValue);
        updateFinishTimeBtn = findViewById(R.id.buttonUpdateFuelFinishTime);
        petrolFinishTimeText = findViewById(R.id.textViewPetrolFinishTimeValue);
        dieselFinishTimeText = findViewById(R.id.textViewDieselFinishTimeValue);
        petrolFinishDateText = findViewById(R.id.textViewPetrolFinishDateValue);
        dieselFinishDateText = findViewById(R.id.textViewDieselFinishDateValue);

        // Getting the data from the previous activity
        String userName = getIntent().getStringExtra("userName");

        // jsonPlaceHolderApi is an object of the JsonPlaceHolderApi class
        jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

        //retrofit call
        Call<JsonObject> call = jsonPlaceHolderApi.getFuelStationByUsername(userName);

        //retrofit enqueue
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(FuelOwnerProfile.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                // json object
                JsonObject jsonObject = response.body();
                JsonObject fuelStation = jsonObject.getAsJsonObject("fuelStation");
                String userName = fuelStation.get("userName").getAsString();
                String name = fuelStation.get("name").getAsString();
                String location = fuelStation.get("location").getAsString();
                String petrolAmount = fuelStation.get("petrolAmount").getAsString();
                String dieselAmount = fuelStation.get("dieselAmount").getAsString();
                String petrolArrivalTime = fuelStation.get("petrolArrivalTime").getAsString();
                String dieselArrivalTime = fuelStation.get("dieselArrivalTime").getAsString();
                petrolArrivalDate = fuelStation.get("petrolArrivalDate").getAsString();
                String dieselArrivalDate = fuelStation.get("dieselArrivalDate").getAsString();
                String petrolFinishTime = fuelStation.get("petrolDepartureTime").getAsString();
                String dieselFinishTime = fuelStation.get("dieselDepartureTime").getAsString();
                String petrolFinishDate = fuelStation.get("petrolDepartureDate").getAsString();
                String dieselFinishDate = fuelStation.get("dieselDepartureDate").getAsString();


                // Setting the values
                patrolAmountText.setText(petrolAmount + " L");
                dieselAmountText.setText(dieselAmount + " L");
                ownerNameText.setText(userName);
                fuelStationNameText.setText(name);
                petrolArriavalTimeText.setText(petrolArrivalTime);
                dieselArriavalTimeText.setText(dieselArrivalTime);
                petrolArriavalDateText.setText(petrolArrivalDate);
                dieselArriavalDateText.setText(dieselArrivalDate);
                petrolFinishTimeText.setText(petrolFinishTime);
                dieselFinishTimeText.setText(dieselFinishTime);
                petrolFinishDateText.setText(petrolFinishDate);
                dieselFinishDateText.setText(dieselFinishDate);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // Log error
                Toast.makeText(FuelOwnerProfile.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //update btn onclick listener
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //onclick navigate to update fuel arrival time
                    Intent intent = new Intent(FuelOwnerProfile.this, UpdateFuelStationDetails.class);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
            }
        });

        //update finish time btn onclick listener
        updateFinishTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onclick navigate to update fuel finish time
                Intent intent = new Intent(FuelOwnerProfile.this, UpdateFuelFinishTime.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });



    }
}