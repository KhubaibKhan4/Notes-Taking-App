package com.example.notestaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.notestaking.Adapters.NotesAdapter;
import com.example.notestaking.Common.DatabaseHelper;
import com.example.notestaking.Models.Notes;

import java.util.List;

public class NotesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NotesAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        List<Notes> notesList = databaseHelper.notesDao().getAllData();
        adapter = new NotesAdapter(this, notesList);
        recyclerView.setAdapter(adapter);
    }
}