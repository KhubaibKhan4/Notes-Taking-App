package com.example.notestaking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class NotesDetailedActivity extends AppCompatActivity {
    TextView notes_title, notes_desc, notes_date, notes_id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detailed);

        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        String date = getIntent().getStringExtra("date");
        String id = getIntent().getStringExtra("id");


        notes_title = (TextView) findViewById(R.id.title_txt_detailed);
        notes_desc = (TextView) findViewById(R.id.desc_txt_detailed);
        notes_date = (TextView) findViewById(R.id.textView_date_detailed);

        notes_title.setText(title);
        notes_desc.setText(desc);
        notes_date.setText(date);

    }
}