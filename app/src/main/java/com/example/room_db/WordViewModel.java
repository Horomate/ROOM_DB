package com.example.room_db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepo mRepo;
    private LiveData<List<Word>> mAllwords;


    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepo = new WordRepo(application);
        mAllwords = mRepo.getAllword();
    }
    public void insert(Word word){
        mRepo.insert(word);
    }
    public void delete(Word word){
        mRepo.delete(word);
    }
    public void update(Word word){
        mRepo.update(word);
    }
    public void deleteAllword(){
        mRepo.deleteAllword();
    }
    public LiveData<List<Word>> getAllword(){
        return mAllwords;
    }

}
