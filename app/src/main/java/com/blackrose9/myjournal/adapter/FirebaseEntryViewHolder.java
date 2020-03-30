package com.blackrose9.myjournal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blackrose9.myjournal.R;
import com.blackrose9.myjournal.model.Entry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseEntryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseEntryViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mView = mView;
        this.mContext = mContext;
        itemView.setOnClickListener(this);
    }

    public void bindEntries(Entry entry) {
        TextView entryTitle = mView.findViewById(R.id.entry_title);
        TextView entryBody = mView.findViewById(R.id.entry_content);

        entryTitle.setText(entry.getEntryTitle());
        entryBody.setText(entry.getEntryBody());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Entry> entries = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase
                .getInstance()
                .getReference("Entries");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    entries.add(snapshot.getValue(Entry.class));
                }
                int itemPosition = getLayoutPosition();
                Log.d("Entries", String.valueOf(entries));
//                Intent intent = new Intent(mContext, EditEntryActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("title",)
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
