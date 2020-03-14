package com.blackrose9.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryListActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.entryListView) ListView mEntryListView;
    @BindView(R.id.fabAddEntry) FloatingActionButton mFabAddBtn;

    private String[] entries = new String[] { "Day 1: So this thing happened, I was so thrilled", "Day 2: I cannot believe this other thing happened, I am so angry!", "Personal Entry: I had a dream that this amazing thing happened and then I woke up... Bummer..." };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        EntryAdapter adapter = new EntryAdapter(this, android.R.layout.simple_list_item_1, entries);
        mEntryListView.setAdapter(adapter);

        mEntryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(EntryListActivity.this, "Bread and Toast", Toast.LENGTH_LONG).show();
            }
        });

        mFabAddBtn.bringToFront();
        mFabAddBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==mFabAddBtn){
            Intent intent = new Intent(EntryListActivity.this, DearDiaryActivity.class);
            startActivity(intent);
        }
    }

}
