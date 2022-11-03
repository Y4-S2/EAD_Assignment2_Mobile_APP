package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVehicle extends AppCompatActivity {

    //initialize variables
    Button btnAddVehicle;
    TextInputLayout vehicleName, vehicleType, vehicleNumber;
    RadioGroup radioGroup;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    RadioButton fuelType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        //get intent
        String username = getIntent().getStringExtra("userName");

        //declare variables
        btnAddVehicle = findViewById(R.id.add_vehicle_button);
        vehicleName = findViewById(R.id.add_vehicle_number_layout);
        vehicleType = findViewById(R.id.add_vehicle_type_layout);
        vehicleNumber = findViewById(R.id.add_vehicle_number_layout);
        radioGroup = findViewById(R.id.radioGroup);

        //add vehicle
        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //json object
                jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

                //get values from edit text
                String vName = vehicleName.getEditText().getText().toString();
                String vType = vehicleType.getEditText().getText().toString();
                String vNumber = vehicleNumber.getEditText().getText().toString();

                //usertype
                int selectedId = radioGroup.getCheckedRadioButtonId();
                fuelType = findViewById(selectedId);
                String fType = fuelType.getText().toString();

                //JSON object for vehicle
                JsonObject vehicle = new JsonObject();
                vehicle.addProperty("userName", username);
                vehicle.addProperty("vehicleType", vType);
                vehicle.addProperty("vehicleNumber", vNumber);
                vehicle.addProperty("fuelType", fType);

                //check if the fields are empty
                if(vName.equals("")||vType.equals("")||vNumber.equals(""))
                    Toast.makeText(AddVehicle.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    //retrofit api call
                    Call<JsonObject> call = jsonPlaceHolderApi.addVehicle(vehicle);
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(AddVehicle.this, "Code: "+response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(AddVehicle.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddVehicle.this, Login.class);
                            intent.putExtra("userName", username);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(AddVehicle.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });



    }
}