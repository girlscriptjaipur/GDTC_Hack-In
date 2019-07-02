package com.example.bmrcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChoosingActivity extends AppCompatActivity {

    ImageButton bmi,bmr;


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(ChoosingActivity.this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing);

        bmi= findViewById(R.id.imbtn_bmi);
        bmr = findViewById(R.id.imbtn_bmr);

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChoosingActivity.this,BMIActivity.class);
                startActivity(i);
            }
        });
        bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChoosingActivity.this,MainActivity.class);
                startActivity(i);
            }
        });



    }
}
