package com.blackrose9.myjournal.connection;

import com.blackrose9.myjournal.model.Entry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/bins/dztes")
    Call<List<Entry>> getEntries();
}

//https://api.myjson.com/bins/dztes