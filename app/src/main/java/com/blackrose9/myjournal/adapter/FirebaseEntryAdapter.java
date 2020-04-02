package com.blackrose9.myjournal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.blackrose9.myjournal.R;
import com.blackrose9.myjournal.model.Entry;
import com.blackrose9.myjournal.util.FirebaseEntryViewHolder;
import com.blackrose9.myjournal.util.ItemTouchHelperAdapter;
import com.blackrose9.myjournal.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class FirebaseEntryAdapter extends FirebaseRecyclerAdapter<Entry, FirebaseEntryViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference getReference;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseEntryAdapter(FirebaseRecyclerOptions<Entry> options,
                                DatabaseReference reference,
                                OnStartDragListener onStartDragListener,
                                Context context) {
        super(options);
        getReference = reference.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
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
}
