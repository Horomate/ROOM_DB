package com.example.room_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    private RecyclerView mRecycleView;
    private WordAdapter mWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton = findViewById(R.id.button_add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AddNewWordActivity.class);
                startActivityForResult(i,1);
            }
        });

        mRecycleView = findViewById(R.id.wordrecycleview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setHasFixedSize(true);

        mWordAdapter = new WordAdapter();
        mRecycleView.setAdapter(mWordAdapter);

        mWordViewModel = ViewModelProvider.of(this).get(WordViewModel.class);
        mWordViewModel.deleteAllword().observe(this, new Observer<List<Word>>(){
            @Override
            public void onChanged(List<Word> word){
                mWordAdapter.setWord(word);
            }
        });
        mWordAdapter.onItemClickListener(new WordAdapter.onItemeClickListener(){
            @Override
            public void onItemClick(Word word){
                Intent i = new Intent(MainActivity.this, AddNewWordActivity.class);
                i.putExtra(AddNewWordActivity.Extra_ID,  word.getId());
                i.putExtra(AddNewWordActivity.Extra_word,  word.getWordName());
                i.putExtra(AddNewWordActivity.Extra_meaning,  word.getWordMeaning());
                i.putExtra(AddNewWordActivity.Extra_type,  word.getWordType());
                startActivities(i);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                mWordViewModel.delete(mWordAdapter.getWordAt(position));
            }
        }).attachToRecyclerView(mRecycleView);
    }
}