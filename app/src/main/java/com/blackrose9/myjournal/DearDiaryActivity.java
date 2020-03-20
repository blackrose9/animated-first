package com.blackrose9.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DearDiaryActivity extends AppCompatActivity {
    @BindView(R.id.dearDiaryEditText) EditText editText;
    @BindView(R.id.dearDiaryTitle)
    EditText titleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dear_diary);

        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.submit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.submit:
                Toast.makeText(DearDiaryActivity.this, "Submitted", Toast.LENGTH_LONG).show();
                String entry = editText.getText().toString();
                Intent intent = new Intent(DearDiaryActivity.this, EntryListActivity.class);
                intent.putExtra("entries", entry);
                startActivity(intent);
                return true;
            default:
                Toast.makeText(DearDiaryActivity.this, "Boop Bop", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(menuItem);
        }
    }

}
