package com.example.bmrcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText= findViewById(R.id.et_inputnote);
        noteId = getIntent().getIntExtra("noteId",-1);//imp since indexing starts from 0 this checks for something invalid
        if(noteId!=-1){
            editText.setText(NoteStartActivity.notes.get(noteId));
        }else{
            NoteStartActivity.notes.add("");
            noteId= NoteStartActivity.notes.size()-1;
            NoteStartActivity.arrayAdapter.notifyDataSetChanged();

        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               NoteStartActivity.notes.set(noteId,String.valueOf(charSequence));
                NoteStartActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("com.example.bmrcalculator", Context.MODE_PRIVATE);
                HashSet<String> set= new HashSet<>(NoteStartActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
