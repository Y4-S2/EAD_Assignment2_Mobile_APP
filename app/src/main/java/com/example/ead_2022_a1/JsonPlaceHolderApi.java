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
    Call<JsonObject> addFuelStation(@Body JsonObject fuelStation);

    //get fuel station by username
    @GET("fuelStations/getFuelStationByUserName/{userName}")
    Call<JsonObject> getFuelStationByUsername(@Path("userName") String userName);

    //update fuel station
    @PUT("fuelStations/updateFuelStation/{userName}/{fuelType}")
    Call<FuelStation> updateFuelStation(@Path("userName") String userName, @Path("fuelType") String type, @Body FuelStation fuelStation);

    //add vehicle
    @POST("vehicles")
    Call<JsonObject> addVehicle(@Body JsonObject vehicle);

    //join queue
    @PUT("fuelStations/addVehicleToQueue/{ownerName}")
    Call<JsonObject> joinQueue(@Path("ownerName") String ownerName, @Body JsonObject vehicle);

    //leave before pump
    @PUT("fuelStations/removeVehicleWithoutFuel/{ownerName}")
    Call<JsonObject> leaveBeforePump(@Path("ownerName") String ownerName, @Body JsonObject vehicle);

    //leave after pump
    @PUT("fuelStations/removeVehicleWithFuel/{ownerName}")
    Call<JsonObject> leaveAfterPump(@Path("ownerName") String ownerName, @Body JsonObject vehicle);

    //get vehicle by username
    @GET("vehicles/getVehicleByUserName/{userName}")
    Call<JsonObject> getVehicleByUsername(@Path("userName") String userName);

}
