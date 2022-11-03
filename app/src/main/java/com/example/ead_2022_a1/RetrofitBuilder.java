package com.example.ead_2022_a1;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static RetrofitBuilder instance = new RetrofitBuilder();
    private RetrofitBuilder(){}

    public static RetrofitBuilder getInstance(){
        return instance;
    }

    public JsonPlaceHolderApi configure (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit.create(JsonPlaceHolderApi.class);
    }



}