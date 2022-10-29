package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FuelOwnerProfile extends AppCompatActivity {
    // Declaring variables
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView patrolAmountText, dieselAmountText , ownerNameText , fuelStationNameText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_owner_profile);

        // Initializing variables
        patrolAmountText = findViewById(R.id.textViewPetrolAmount);
        dieselAmountText = findViewById(R.id.textViewDieselAmount);
        ownerNameText = findViewById(R.id.textViewFuelOwnerName);
        fuelStationNameText = findViewById(R.id.textViewFuelStationName);

        String userName = getIntent().getStringExtra("userName");

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
                System.out.println(response.body().getAsJsonObject("fuelStation"));
                JsonObject jsonObject = response.body();
                JsonObject fuelStation = jsonObject.getAsJsonObject("fuelStation");
                String userName = fuelStation.get("userName").getAsString();
                String name = fuelStation.get("name").getAsString();
                String location = fuelStation.get("location").getAsString();
                String petrolAmount = fuelStation.get("petrolAmount").getAsString();
                String dieselAmount = fuelStation.get("dieselAmount").getAsString();

                Toast.makeText(FuelOwnerProfile.this, dieselAmount, Toast.LENGTH_SHORT).show();

                // Setting the values
                patrolAmountText.setText(petrolAmount+" L");
                dieselAmountText.setText(dieselAmount+" L");
                ownerNameText.setText(userName);
                fuelStationNameText.setText(name);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(FuelOwnerProfile.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}