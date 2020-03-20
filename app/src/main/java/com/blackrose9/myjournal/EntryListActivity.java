package com.blackrose9.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blackrose9.myjournal.adapter.RecyclerViewCustomAdapter;
import com.blackrose9.myjournal.connection.GetDataService;
import com.blackrose9.myjournal.connection.RetrofitInstanceClass;
import com.blackrose9.myjournal.model.Entry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryListActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.fabAddEntry) FloatingActionButton mFabAddBtn;

    List<Entry> entries;
    private RecyclerView entryListRecyclerView;
    private GetDataService dataService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        fetchDataFromServer();

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mFabAddBtn.bringToFront();
        mFabAddBtn.setOnClickListener(this);
    }

    private void fetchDataFromServer() {
        dataService = RetrofitInstanceClass.getRetrofit().create(GetDataService.class);
        Call<List<Entry>> call = dataService.getEntries();
        call.enqueue(new Callback<List<Entry>>() {
            @Override
            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
                if (response.code() == 200) {
                    entries = response.body();
                    initializeDisplay();
                }
            }

            @Override
            public void onFailure(Call<List<Entry>> call, Throwable t) {

            }
        });
    }

    private void initializeDisplay() {
        entryListRecyclerView = findViewById(R.id.entryListView);
        RecyclerViewCustomAdapter adapter = new RecyclerViewCustomAdapter(this, entries);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        entryListRecyclerView.setAdapter(adapter);
        entryListRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View v) {
        if(v==mFabAddBtn){
            Intent intent = new Intent(EntryListActivity.this, DearDiaryActivity.class);
            startActivity(intent);
        }
    }

}
