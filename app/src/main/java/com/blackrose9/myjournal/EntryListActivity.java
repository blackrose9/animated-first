package com.blackrose9.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blackrose9.myjournal.adapter.FirebaseEntryAdapter;
import com.blackrose9.myjournal.model.Entry;
import com.blackrose9.myjournal.util.FirebaseEntryViewHolder;
import com.blackrose9.myjournal.util.ItemTouchHelperCallback;
import com.blackrose9.myjournal.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryListActivity extends AppCompatActivity implements View.OnClickListener, OnStartDragListener {
    private static final String TAG = "boo";
    @BindView(R.id.fabAddEntry) FloatingActionButton mFabAddBtn;
    @BindView(R.id.entryListView)
    RecyclerView mRecyclerView;


    private FirebaseRecyclerAdapter<Entry, FirebaseEntryViewHolder> mFirebaseRecyclerAdapter;
    private DatabaseReference mEntryListReference;
    private ValueEventListener mEntryListReferenceListener;

    private FirebaseEntryAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        ButterKnife.bind(this);


        mEntryListReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Entries");

        setUpFirebaseAdapter();

/*
        mEntryListReferenceListener = mEntryListReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot entriesSnapshot : dataSnapshot.getChildren()) {
                    String entries = entriesSnapshot.getValue().toString();
//                    Toast.makeText(EntryListActivity.this, entries, Toast.LENGTH_LONG).show();
                    Log.d("Entries", entries);
                    mFirebaseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "loading entries is cancelled", databaseError.toException());
            }
        });
*/

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

        mFirebaseAdapter = new FirebaseEntryAdapter(options, mEntryListReference, this, this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
        mRecyclerView.setHasFixedSize(true);
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mFirebaseAdapter.notifyDataSetChanged();
    }

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

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mEntryListReference.removeEventListener(mEntryListReferenceListener);
//    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
