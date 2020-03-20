package com.blackrose9.myjournal.connection;

import com.blackrose9.myjournal.model.Entry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/16kloc")
    Call<List<Entry>> getEntries();
}
