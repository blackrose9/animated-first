package com.blackrose9.myjournal;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class EntryAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mEntries;


    public EntryAdapter(Context mContext, int resource, String[] mEntries) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mEntries = mEntries;
    }

    @Override
    public Object getItem (int position) {
        String entry = mEntries[position];
        return (entry);
    }

    @Override
    public int getCount(){
        return mEntries.length;
    }
}
