package com.example.room_db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddNewWordViewModel extends AndroidViewModel {
    private WordRepo mRepo;


    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepo = new WordRepo(application);
    }
    public void insert(Word word){
        mRepo.insert(word);
    }

    public void update(Word word){
        mRepo.update(word);
    }

}
