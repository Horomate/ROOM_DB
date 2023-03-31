package com.example.room_db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.xml.transform.Result;

public class WordRepo {
    private WordsDao mWordDao;
    private LiveData<List<Word>> getAllWord;

    public WordRepo(Application app){
        WordRoomDb db = WordRoomDb.getInstance(app);
        mWordDao = db.wordsDao();
        getAllWord = mWordDao.getAllWord();
    }
    //operation

    //insert
    public void insert(Word word){
        new InsertAsyncTask(mWordDao).execute(word);
    }
    //delete
    public void delete(Word word){
        new DeletAsyncTask(mWordDao).execute(word);
    }
    //update
    public void update(Word word){
        new UpdateAsyncTask(mWordDao).execute(word);
    }
    //getAllword
    public LiveData<List<Word>> getAllword(){
        return getAllword();
    }
    //deleteAllword
    public void deleteAllword(){
        new DeletAllWordAsyncTask(mWordDao).execute();
    }
    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordsDao mWordDao;
        public InsertAsyncTask(WordsDao wordsDao){
            mWordDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.Insert(words[0]);
            return null;
        }
    }
    private static class DeletAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordsDao mWordDao;
        public DeletAsyncTask(WordsDao wordsDao){
            mWordDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.Delete(words[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordsDao mWordDao;
        public UpdateAsyncTask(WordsDao wordsDao){
            mWordDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Word... words) {
            mWordDao.Update(words[0]);
            return null;
        }
    }
    private static class DeletAllWordAsyncTask extends AsyncTask<Void, Void, Void>{
        private WordsDao mWordDao;
        public DeletAllWordAsyncTask(WordsDao wordsDao){
            mWordDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordDao.DeleteAllWord();
            return null;
        }
    }

}
