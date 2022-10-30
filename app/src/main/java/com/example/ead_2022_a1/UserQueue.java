package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserQueue extends AppCompatActivity {

    //initialize variables
    CardView leave_before_pump_card, leave_after_pump_card;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_queue);

        //get intent
        String userName = getIntent().getStringExtra("userName");
        String ownerName = getIntent().getStringExtra("ownerName");
        String vehicleId = getIntent().getStringExtra("vehicleId");

        //declare variables
        leave_before_pump_card = findViewById(R.id.leave_before_pump_card);
        leave_after_pump_card = findViewById(R.id.leave_after_pump_card);

        //set on click listener for leave before pump card
        leave_before_pump_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //json object
                jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

                //json object
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("vehicleId", vehicleId);

                //call api
                Call<JsonObject> call = jsonPlaceHolderApi.leaveBeforePump(ownerName, jsonObject);

                //enqueue call
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UserQueue.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //get response
                        JsonObject jsonObject = response.body();

                        //check if response is null
                        if(jsonObject == null){
                            Toast.makeText(UserQueue.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //check if response is successful
                        if(jsonObject.get("success").getAsBoolean()){
                            Toast.makeText(UserQueue.this, "You have left the queue before pumping", Toast.LENGTH_SHORT).show();

                            //go to user home page
                            Intent intent = new Intent(UserQueue.this, ItemView.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("ownerName", ownerName);
                            startActivity(intent);
                        }else{
                            Toast.makeText(UserQueue.this, "Error: " + jsonObject.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(UserQueue.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //set on click listener for leave after pump card
        leave_after_pump_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //json object
                jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

                //json object
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("userName", userName);

                //call api
                Call<JsonObject> call = jsonPlaceHolderApi.leaveAfterPump(ownerName, jsonObject);

                //enqueue call
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(UserQueue.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //get response
                        JsonObject jsonObject = response.body();

                        //check if response is null
                        if(jsonObject == null){
                            Toast.makeText(UserQueue.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        //check if response is successful
                        if(jsonObject.get("success").getAsBoolean()){
                            Toast.makeText(UserQueue.this, "You have left the queue after pumping", Toast.LENGTH_SHORT).show();

                            //go to user home page
                            Intent intent = new Intent(UserQueue.this, ItemView.class);
                            intent.putExtra("userName", userName);
                            intent.putExtra("ownerName", ownerName);
                            startActivity(intent);
                        }else{
                            Toast.makeText(UserQueue.this, "Error: " + jsonObject.get("message").getAsString(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(UserQueue.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}