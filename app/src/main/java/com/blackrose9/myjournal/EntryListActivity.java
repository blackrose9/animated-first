package com.blackrose9.myjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryListActivity extends AppCompatActivity {
    @BindView(R.id.entryListView) ListView mEntryListView;
    @BindView(R.id.fabAddEntry) FloatingActionButton mFabAddBtn;

    private String[] entries = new String[] { "Day 1: So this thing happened, I was so thrilled", "Day 2: I cannot believe this other thing happened, I am so angry!", "Personal Entry: I had a dream that this amazing thing happened and then I woke up... Bummer..." };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        ButterKnife.bind(this);

        EntryAdapter adapter = new EntryAdapter(this, android.R.layout.simple_list_item_1, entries);
        mEntryListView.setAdapter(adapter);
    }
}
