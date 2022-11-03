package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import java.util.Calendar;

import retrofit2.Call;

public class UpdateFuelStationDetails extends AppCompatActivity {

    TextInputLayout fuelArrivalTime, fuelArrivalDate, fuelAmount;
    DatePickerDialog.OnDateSetListener setListener;
    TimePickerDialog.OnTimeSetListener setListener2;
    Button updateFuelStationDetailsBtn;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    RadioButton fuelType;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fuel_station_details);

        //get intent values
        String userName = getIntent().getStringExtra("userName");

        //initialize variables
        fuelArrivalTime = findViewById(R.id.add_fuel_station_time_layout);
        fuelArrivalDate = findViewById(R.id.add_fuel_station_date_layout);
        updateFuelStationDetailsBtn = findViewById(R.id.update_fuel_station_details_button);
        radioGroup = findViewById(R.id.radioGroup);
        fuelAmount = findViewById(R.id.update_fuel_amount_layout);


        //set date picker
        fuelArrivalDate.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateFuelStationDetails.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        //set listener for date picker
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                fuelArrivalDate.getEditText().setText(date);
            }
        };

        //set time picker
        fuelArrivalTime.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateFuelStationDetails.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener2, hour, minute, false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });

        //set listener for time picker
        setListener2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                fuelArrivalTime.getEditText().setText(time);
            }
        };

        //set on click listener for update fuel station details button
        updateFuelStationDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get values from text fields
                jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

                //set fuel type
                int selectedId = radioGroup.getCheckedRadioButtonId();
                fuelType = findViewById(selectedId);
                String type = fuelType.getText().toString();

                //check if all fields are filled
                if (fuelArrivalDate.getEditText().getText().toString().isEmpty() || fuelArrivalTime.getEditText().getText().toString().isEmpty() || fuelAmount.getEditText().getText().toString().isEmpty()) {
                    Toast.makeText(UpdateFuelStationDetails.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {

                    //update fuel station details
                    JsonObject object = new JsonObject();
                    if (type.equals("Petrol")) {
                        object.addProperty("petrolArrivalTime", fuelArrivalTime.getEditText().getText().toString());
                        object.addProperty("petrolArrivalDate", fuelArrivalDate.getEditText().getText().toString());
                        object.addProperty("petrolAmount", fuelAmount.getEditText().getText().toString());
                        object.addProperty("petrolDepartureTime", "null");
                        object.addProperty("petrolDepartureDate", "null");
                    } else {
                        object.addProperty("dieselArrivalTime", fuelArrivalTime.getEditText().getText().toString());
                        object.addProperty("dieselArrivalDate", fuelArrivalDate.getEditText().getText().toString());
                        object.addProperty("dieselAmount", fuelAmount.getEditText().getText().toString());
                        object.addProperty("dieselDepartureTime", "null");
                        object.addProperty("dieselDepartureDate", "null");
                    }


                    //call update fuel station details method
                    Call<JsonObject> call = jsonPlaceHolderApi.updateFuelStation(userName, type, object);
                    call.enqueue(new retrofit2.Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(UpdateFuelStationDetails.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            //get response
                            JsonObject object = response.body();

                            //display success message
                            Toast.makeText(UpdateFuelStationDetails.this, "Fuel Station Details Updated Successfully", Toast.LENGTH_SHORT).show();

                            //redirect to fuel station details page
                            Intent intent = new Intent(UpdateFuelStationDetails.this, FuelOwnerProfile.class);
                            intent.putExtra("userName", userName);
                            startActivity(intent);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Toast.makeText(UpdateFuelStationDetails.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }

            }
        });


    }
}