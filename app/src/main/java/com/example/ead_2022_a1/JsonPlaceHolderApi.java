package com.example.ead_2022_a1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    //get fuel station list
    @GET("fuelStations")
    Call<FuelStationJSONResponse> getFuelStations();

    //create fuel station
    @POST("fuelStations")
    Call<FuelStation> addFuelStation(@Body FuelStation fuelStation);

    //get fuel station by username
    @GET("fuelStations/getFuelStationByUserName/{userName}")
    Call<JsonObject> getFuelStationByUsername(@Path("userName") String userName);

    @PUT("fuelStations/{userName}/{fuelType}")
    Call<FuelStation> updateFuelStation(@Path("userName") String userName, @Path("fuelType") String type, @Body FuelStation fuelStation);

}
