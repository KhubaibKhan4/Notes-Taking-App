package com.example.notestaking.Common;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notestaking.Models.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("select * from Notes")
    List<Notes> getAllData();

    @Insert
    void addNotes(Notes notes);

    @Update
    void updateNotes(Notes notes);

    @Delete
    void deleteNotes(Notes notes);

}
