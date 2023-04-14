package com.example.notestaking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notestaking.Common.DatabaseHelper;
import com.example.notestaking.Models.Notes;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText edtTitle, edtDescription;
    Button uploadBtn, viewNotes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtDescription = (EditText) findViewById(R.id.edtDesc);
        uploadBtn = (Button) findViewById(R.id.uploadBtn);
        viewNotes = (Button) findViewById(R.id.viewBtn);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = edtTitle.getText().toString();
                String desc = edtDescription.getText().toString();

                Date date = new Date();
                int day = date.getDay();
                int month = date.getMonth();
                int year = date.getYear();
                String Date = day + "-" + month + "-" + year;

                databaseHelper.notesDao().addNotes(new Notes(title, desc, Date));
                edtTitle.setText("");
                edtDescription.setText("");

            }
        });

        viewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotesActivity.class));
            }
        });
    }
}