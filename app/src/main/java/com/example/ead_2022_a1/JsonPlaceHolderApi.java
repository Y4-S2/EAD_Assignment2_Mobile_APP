package com.example.ead_2022_a1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    //get fuel station list
    @GET("fuelStations")
    Call<FuelStationJSONResponse> getFuelStations();
}
