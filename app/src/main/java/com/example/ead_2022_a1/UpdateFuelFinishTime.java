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

public class UpdateFuelFinishTime extends AppCompatActivity {

    //initialize variables
    TextInputLayout finishTimeLayout ,finishDateLayout;
    DatePickerDialog.OnDateSetListener setListener;
    TimePickerDialog.OnTimeSetListener setListener2;
    Button updateButton;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    RadioButton fuelType;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_fuel_finish_time);

        //get intent
        String userName = getIntent().getStringExtra("userName");

        //initialize variables
        finishTimeLayout = findViewById(R.id.update_fuel_finish_time_layout);
        finishDateLayout = findViewById(R.id.update_fuel_finish_date_layout);
        updateButton = findViewById(R.id.update_fuel_station_finish_button);
        radioGroup = findViewById(R.id.radioGroup);

        //set date picker
        finishDateLayout.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateFuelFinishTime.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener, year, month, day);
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
                finishDateLayout.getEditText().setText(date);
            }
        };

        //set time picker
        finishTimeLayout.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateFuelFinishTime.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setListener2, hour, minute, false);
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
            }
        });

        //set listener for time picker
        setListener2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                finishTimeLayout.getEditText().setText(time);
            }
        };

        //on update button click
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //jsonplaceholderapi
                jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

                //get values from text fields
                String finishTime = finishTimeLayout.getEditText().getText().toString();
                String finishDate = finishDateLayout.getEditText().getText().toString();

                //set fuel type
                int selectedId = radioGroup.getCheckedRadioButtonId();
                fuelType = findViewById(selectedId);
                String type = fuelType.getText().toString();

                //create object
                JsonObject object = new JsonObject();

                if(type.equals("Petrol")){
                    object.addProperty("petrolDepartureTime",finishTime);
                    object.addProperty("petrolDepartureDate",finishDate);
                    object.addProperty("petrolAmount",0);
                }else if(type.equals("Diesel")){
                    object.addProperty("dieselDepartureTime",finishTime);
                    object.addProperty("dieselDepartureDate",finishDate);
                    object.addProperty("dieselAmount",0);
                }

                //call api
                Call<JsonObject> call = jsonPlaceHolderApi.updateFinishFuelStation(userName, type, object);

                //set on success listener
                call.enqueue(new retrofit2.Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                        if(!response.isSuccessful()){
                            //if not successful toast error message
                            Toast.makeText(UpdateFuelFinishTime.this, "Error", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //toast
                        Toast.makeText(UpdateFuelFinishTime.this, "Updated Successfully", Toast.LENGTH_SHORT).show();

                        //intent
                        Intent intent = new Intent(UpdateFuelFinishTime.this, FuelOwnerProfile.class);
                        intent.putExtra("userName",userName);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        //if failed toast error message
                        Toast.makeText(UpdateFuelFinishTime.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });
    }


}