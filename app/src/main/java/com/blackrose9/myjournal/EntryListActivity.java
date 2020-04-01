package com.blackrose9.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blackrose9.myjournal.connection.GetDataService;
import com.blackrose9.myjournal.model.Entry;
import com.blackrose9.myjournal.util.FirebaseEntryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "boo";
    @BindView(R.id.fabAddEntry) FloatingActionButton mFabAddBtn;
    @BindView(R.id.entryListView)
    RecyclerView mRecyclerView;

    private RecyclerView entryListRecyclerView;
    private GetDataService dataService;

    private FirebaseRecyclerAdapter<Entry, FirebaseEntryViewHolder> mFirebaseAdapter;
    private DatabaseReference mEntryListReference;
    private ValueEventListener mEntryListReferenceListener;
//    private List<Entry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        ButterKnife.bind(this);
//        fetchDataFromServer();
//        initializeDisplay();

        mEntryListReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Entries");

        setUpFirebaseAdapter();

//        mEntryListReferenceListener = mEntryListReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot entriesSnapshot : dataSnapshot.getChildren()) {
//                    String entries = entriesSnapshot.getValue().toString();
////                    Toast.makeText(EntryListActivity.this, entries, Toast.LENGTH_LONG).show();
//                    Log.d("Entries", entries);
//                    mFirebaseAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.w(TAG, "loading entries is cancelled", databaseError.toException());
//            }
//        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mFabAddBtn.bringToFront();
        mFabAddBtn.setOnClickListener(this);
    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Entry> options = new FirebaseRecyclerOptions.Builder<Entry>()
                .setQuery(mEntryListReference, Entry.class)
                .build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Entry, FirebaseEntryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseEntryViewHolder firebaseEntryViewHolder, int position, @NonNull Entry entry) {
                firebaseEntryViewHolder.bindEntries(entry);
            }

            @NonNull
            @Override
            public FirebaseEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_list_item, parent, false);
                return new FirebaseEntryViewHolder(view);
            }
        };
//        mFirebaseAdapter.startListening();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mFirebaseAdapter.notifyDataSetChanged();
    }

//    private void fetchDataFromServer() {
//        dataService = RetrofitInstanceClass.getRetrofit().create(GetDataService.class);
//        Call<List<Entry>> call = dataService.getEntries();
//        call.enqueue(new Callback<List<Entry>>() {
//            @Override
//            public void onResponse(Call<List<Entry>> call, Response<List<Entry>> response) {
//                if (response.code() == 200) {
//                    entries = response.body();
//                    initializeDisplay();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Entry>> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void initializeDisplay() {
//        entryListRecyclerView = findViewById(R.id.entryListView);
//        RecyclerViewCustomAdapter adapter = new RecyclerViewCustomAdapter(this, entries);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        entryListRecyclerView.setAdapter(adapter);
//        entryListRecyclerView.setLayoutManager(layoutManager);
//    }

    @Override
    public void onClick(View v) {
        if(v==mFabAddBtn){
            Intent intent = new Intent(EntryListActivity.this, DearDiaryActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mFirebaseAdapter != null) {
            mFirebaseAdapter.stopListening();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEntryListReference.removeEventListener(mEntryListReferenceListener);
    }
}
