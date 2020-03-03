package com.blackrose9.myjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String [] category = new String[] {
            "General", "Private"};
    @BindView(R.id.fabToAll) FloatingActionButton mFabBtn;
    @BindView(R.id.baseGridView) GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gridView.setAdapter(new CategoryAdapter(this, category));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
//                String category = ((TextView)view).getText().toString(); //what does this line even do?
                Toast.makeText(MainActivity.this, "Toasty", Toast.LENGTH_LONG).show();
            }
        });
        mFabBtn.bringToFront();
        mFabBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v==mFabBtn){
            Intent intent = new Intent(MainActivity.this, EntryListActivity.class);
            startActivity(intent);
        }
    }
}
