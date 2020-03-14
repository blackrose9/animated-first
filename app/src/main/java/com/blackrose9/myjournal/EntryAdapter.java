package com.blackrose9.myjournal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EntryAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mTitle;
    private String[] mEntries;

    public EntryAdapter(Context mContext, int resource, String[] mTitle, String[] mEntries) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mTitle = mTitle;
        this.mEntries = mEntries;
    }

    @Override
    public Object getItem (int position) {
        String title = mTitle[position];
        String entry = mEntries[position];
        return new String[]{title, entry};
    }

    @Override
    public int getCount(){
        return mEntries.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listView;
        String title = mTitle[position];
        String content = mEntries[position];

        if (convertView == null) {
            listView = inflater.inflate(R.layout.entry_list_item, null);
            TextView titleView = listView.findViewById(R.id.entry_title);
            titleView.setText(title);
            TextView contentView = listView.findViewById(R.id.entry_content);
            contentView.setText(content);
        } else {
            listView = convertView;
        }
        return listView;
    }
}
