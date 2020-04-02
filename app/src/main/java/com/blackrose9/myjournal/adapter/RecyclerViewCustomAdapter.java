package com.blackrose9.myjournal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blackrose9.myjournal.R;
import com.blackrose9.myjournal.model.Entry;

import java.util.List;

public class RecyclerViewCustomAdapter extends RecyclerView.Adapter<RecyclerViewCustomAdapter.CustomViewHolder> {
    private Context mContext;
    private List<Entry> mEntries;

    public RecyclerViewCustomAdapter(Context context, List<Entry> entries) {
        this.mContext = context;
        this.mEntries = entries;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.entry_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.entry_title.setText(mEntries.get(position).entryTitle);
        holder.entry_content.setText(mEntries.get(position).entryBody);
    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView entry_title, entry_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            entry_title = itemView.findViewById(R.id.entry_title);
            entry_content = itemView.findViewById(R.id.entry_content);
        }
    }
}
