package com.example.room_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWordActivity extends AppCompatActivity {
    private EditText wordedittext;
    private EditText meaningedittext;
    private EditText typeeditetext;

    private boolean editemode;
    private int mid;

    private AddNewWordViewModel mViewModel;
    public static final  String Extra_ID = "com.exemple.room_db";

    public static final  String Extra_word = "com.exemple.room_db.word";
    public static final  String Extra_meaning = "com.exemple.room_db.meaning";
    public static final  String Extra_type = "com.exemple.room_db.type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_new_word);
        wordedittext = findViewById(R.id.edittextWord);
        meaningedittext = findViewById(R.id.edittextmaening);
        typeeditetext = findViewById(R.id.edittexttype);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.exit);

        Intent i = getIntent();
        if(i.hasExtra(Extra_ID)){
            setTitle("Edit word");
            editemode=true;
            mid= i.getIntExtra(Extra_ID, -1);
            wordedittext.setText(i.getStringExtra(Extra_word));
            meaningedittext.setText(i.getStringExtra(Extra_meaning));
            typeeditetext.setText(i.getStringExtra(Extra_type));

        }else {
            setTitle("Add new Word");
            editemode=false;
        }

        mViewModel = ViewModelProviders.of(this).get(AddNewWordViewModel.class);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu, menu);
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.saveword:
                saveWord();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }

    }
    public void saveWord(){
        String word = wordedittext.getText().toString().trim();
        String meaning = meaningedittext.getText().toString().trim();
        String type = typeeditetext.getText().toString().trim();

        Word wordObject = new Word(word, meaning, type);

        if(word.isEmpty() || type.isEmpty() || meaning.isEmpty()){
            Toast.makeText(AddNewWordActivity.this, "Please fill all fiedl", Toast.LENGTH_LONG).show();
            return;
        }
        if (editemode){
            mViewModel.update(wordObject);
        }else {
            mViewModel.insert(wordObject);
        }
        finish();
    }
}