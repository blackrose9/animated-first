package com.blackrose9.myjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DearDiaryActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.dearDiaryEditText) EditText editText;
    @BindView(R.id.saveEntry) Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dear_diary);

        ButterKnife.bind(this);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String entry = editText.getText().toString();
        Intent intent = new Intent(DearDiaryActivity.this, EntryListActivity.class);
        intent.putExtra("entries", entry);
        startActivity(intent);
    }
}
