package com.example.notestaking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.notestaking.Adapters.NotesAdapter;
import com.example.notestaking.Common.DatabaseHelper;
import com.example.notestaking.Models.Notes;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NotesAdapter adapter;
    SearchView searchView;
    List<Notes> notesList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);
        searchView = (SearchView) findViewById(R.id.search_bar);
        searchView.clearFocus();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        List<Notes> notesList = databaseHelper.notesDao().getAllData();
        adapter = new NotesAdapter(this, notesList);
        recyclerView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterlist(newText);
                return true;
            }
        });

    }

    private void filterlist(String newText) {
        List<Notes> filteredList = new ArrayList<>();
        for (Notes notes : notesList) {
            if (notes.getTitle().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(notes);
            }
        }
       adapter.setFilteredList(filteredList);
    }
}