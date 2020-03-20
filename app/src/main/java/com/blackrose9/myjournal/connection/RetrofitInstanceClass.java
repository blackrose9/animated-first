package com.blackrose9.myjournal.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceClass {
    private static final String BASE_URL = "https://api.myjson.com/bins/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        //builder pattern
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
