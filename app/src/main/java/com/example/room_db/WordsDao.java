package com.example.room_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordsDao {
    @Insert
    void Insert(Word word);

    @Update
    void  Update(Word word);

    @Delete
    void  Delete(Word word);

    @Query("DELETE From WordTable ")
    void DeleteAllWord();

    @Query("SELECT * From wordtable")
    LiveData<List<Word>> getAllWord();
}
