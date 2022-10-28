package com.example.ead_2022_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemView extends AppCompatActivity {


    List <FuelStation> fuelStationList;
    private RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        recyclerView = findViewById(R.id.item_recycle);

        setFuelStationInfo();
    }

    //set adapter
    private void setAdapter(List<FuelStation> fuelStationList) {

        ItemRecyclerAdapter itemAdapter = new ItemRecyclerAdapter(this, fuelStationList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemAdapter);
    }

    //set fuel station info
    private void setFuelStationInfo() {

        jsonPlaceHolderApi = RetrofitBuilder.getInstance().configure();

        //retrofit call
        Call<FuelStationJSONResponse> call = jsonPlaceHolderApi.getFuelStations();


        //retrofit enqueue
        call.enqueue(new Callback<FuelStationJSONResponse>() {
            @Override
            public void onResponse(Call<FuelStationJSONResponse> call, Response<FuelStationJSONResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ItemView.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                FuelStationJSONResponse fuelStationJSONResponse = response.body();

                fuelStationList = new ArrayList<>(Arrays.asList(fuelStationJSONResponse.getFuelStations()));

                setAdapter(fuelStationList);

            }

            @Override
            public void onFailure(Call<FuelStationJSONResponse> call, Throwable t) {
                Toast.makeText(ItemView.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
