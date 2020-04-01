package com.blackrose9.myjournal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blackrose9.myjournal.R;

public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mCategory;
    public CategoryAdapter (Context context, String[] category) {
        this.mContext = context;
        this.mCategory = category;
    }

    public CategoryAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return mCategory.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        String category = mCategory[position];

        if (convertView == null){
            gridView = inflater.inflate(R.layout.category_grid_item, null);
            TextView categoryView = gridView
                    .findViewById(R.id.grid_item_name);
            categoryView.setText(category);
        } else {
            gridView = convertView;
        }
        return gridView;
    }
}
