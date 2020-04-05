package com.blackrose9.myjournal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blackrose9.myjournal.R;
import com.blackrose9.myjournal.model.Entry;
import com.blackrose9.myjournal.util.FirebaseEntryViewHolder;
import com.blackrose9.myjournal.util.ItemTouchHelperAdapter;
import com.blackrose9.myjournal.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseEntryAdapter extends FirebaseRecyclerAdapter<Entry, FirebaseEntryViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference getReference;
    private ChildEventListener mChildEventListener;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    private ArrayList<Entry> mEntries = new ArrayList<>();

    public FirebaseEntryAdapter(FirebaseRecyclerOptions<Entry> options,
                                Query reference,
                                OnStartDragListener onStartDragListener,
                                Context context) {
        super(options);
        getReference = reference.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = getReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                mEntries.add(dataSnapshot.getValue(Entry.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onItemDismiss(int position) {
        mEntries.remove(position);
        getRef(position).removeValue();
    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mEntries, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        setIndexInFirebase();
        return false;
    }

    @NonNull
    @Override
    public FirebaseEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_list_item, parent, false);
        return new FirebaseEntryViewHolder(view);
    }
    @Override
    protected void onBindViewHolder(@NonNull final FirebaseEntryViewHolder firebaseEntryViewHolder, int position, @NonNull Entry entry) {
        firebaseEntryViewHolder.bindEntries(entry);
        firebaseEntryViewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(firebaseEntryViewHolder);
                }
                return false;
            }
        });
    }

    public void setIndexInFirebase() {
        for (Entry entry : mEntries) {
            int index = mEntries.indexOf(entry);
            DatabaseReference reference = getRef(index);
            entry.setIndex(Integer.toString(index));
            reference.setValue(entry);
        }
    }

    @Override
    public void stopListening() {
        super.stopListening();
        getReference.removeEventListener(mChildEventListener);
    }
}
