package com.example.room_db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    private static List<Word> mwordList = new ArrayList<>();
    private static View.OnClickListener mListener;
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itimeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item,parent,false);
        return new WordViewHolder(itimeView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word currentWord = mwordList.get(position);
        holder.textViewWord.setText(currentWord.getWordName());
        holder.textViewType.setText(currentWord.getWordType());
        holder.textViewMeaning.setText(currentWord.getWordType());
    }
    public void setWord(List<Word> words){
        mwordList = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mwordList.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewWord;
        public TextView textViewMeaning;
        public TextView textViewType;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewWord = itemView.findViewById(R.id.wordtextview);
            textViewMeaning = itemView.findViewById(R.id.meaningtextview);
            textViewType = itemView.findViewById(R.id.typetextview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int index = getAdapterPosition();
                    if (mListener !=null && index !=RecyclerView.NO_POSITION){
                        mListener.onItemeClick(mwordList.get(index));
                    }
                }
            });
        }
    }
    public interface onItemeClickListener{
         void onItemClick(Word word);
    }
    public void onItemClickListener(AdapterView.OnItemClickListener listener){
        View.OnClickListener mlistener = (View.OnClickListener) listener;
    }
    public Word getWordAt(int pos){
        return mwordList.get(pos);
    }

}
