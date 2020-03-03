package com.blackrose9.myjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    GridView gridView;
    String [] category = new String[] {
            "General", "Private"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.baseGridView);
        gridView.setAdapter(new CategoryAdapter(this, category));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
//                String category = ((TextView)view).getText().toString();
                Toast.makeText(MainActivity.this, "Toasty", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onClick(View v){

    }
}
