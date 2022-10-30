package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserJoinQueue extends AppCompatActivity {
    //initialize variables
    TextInputLayout fuelAmount;
    Button joinQueueButton;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_join_queue);

        //get intent
        String ownerName = getIntent().getStringExtra("ownerName");
        String userName = getIntent().getStringExtra("userName");
        String vehicleId = getIntent().getStringExtra("vehicleId");

        //declare variables
        fuelAmount = findViewById(R.id.join_queue_fuel_amount_layout);
        joinQueueButton = findViewById(R.id.join_queue_button);

        //set on click listener for join queue button
        joinQueueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //json object
                jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

                //get fuel amount
                String fuelAmountString = fuelAmount.getEditText().getText().toString();

                //check if fuel amount is empty
                if(fuelAmountString.isEmpty()){
                    fuelAmount.setError("Fuel amount is required");
                    return;
                }

                //check if fuel amount is valid
                if(!fuelAmountString.matches("^[0-9]*$")){
                    fuelAmount.setError("Fuel amount is invalid");
                    return;
                }

                //check if fuel amount is less than 10
                if(Integer.parseInt(fuelAmountString) < 1){
                    fuelAmount.setError("Fuel amount must be greater than 10");
                    return;
                }

                //check if fuel amount is greater than 100
                if(Integer.parseInt(fuelAmountString) > 20){
                    fuelAmount.setError("Fuel amount must be less than 100");
                    return;
                }

                //create json object
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("fuelAmount", fuelAmountString);
                jsonObject.addProperty("vehicleId", userName);

                //call api
                Call<JsonObject> call = jsonPlaceHolderApi.joinQueue(ownerName, jsonObject);

                //enqueue
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UserJoinQueue.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //get response
                        JsonObject jsonObject = response.body();

                        //check if response is null
                        if(jsonObject == null){
                            return;
                        }

                        //check if response is success
                        if(jsonObject.get("success").getAsBoolean()){
                            //success
                            Toast.makeText(UserJoinQueue.this, "You have successfully joined the queue", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserJoinQueue.this, UserQueue.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("ownerName", ownerName);
                            intent.putExtra("vehicleId", vehicleId);
                            startActivity(intent);
                        }else{
                            //error
                            Toast.makeText(UserJoinQueue.this, "Error: " + jsonObject.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(UserJoinQueue.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}