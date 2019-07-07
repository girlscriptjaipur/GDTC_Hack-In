package com.example.bmrcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class NoteStartActivity extends AppCompatActivity {
    ListView listView;

    static ArrayAdapter<String> arrayAdapter;
    static ArrayList<String > notes = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_new_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.add_note){
            Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= findViewById(R.id.lv_listview);//use ..toi save space we don't want duplicates //new concept learn
        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("com.example.bmrcalculator", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if(set==null)
            notes.add("Example note");
        else
            notes = new ArrayList<>(set);


        arrayAdapter= new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                notes);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(),NoteEditorActivity.class);
                intent.putExtra("noteId",i);
                startActivity(intent);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int item_to_delete = i;
                new AlertDialog.Builder( NoteStartActivity.this)
                        .setCancelable(false)
                        .setTitle("Are yiou sure?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                notes.remove(item_to_delete);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("com.example.bmrcalculator", Context.MODE_PRIVATE);
                                HashSet<String> set= new HashSet<>(NoteStartActivity.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();


                return true;
            }
        });

    }
}
