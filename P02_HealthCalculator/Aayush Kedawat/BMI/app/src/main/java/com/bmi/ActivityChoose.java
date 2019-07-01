package com.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ActivityChoose extends AppCompatActivity {

    Button bmr,bfp,bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        getSupportActionBar().hide();
        Toolbar toolbarChoose = findViewById(R.id.toolbarChoose);
        toolbarChoose.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbarChoose.setTitle("Please select an option");
        toolbarChoose.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        bmr = findViewById(R.id.btnBMR);
        bfp = findViewById(R.id.btnBFP);
        bmi =findViewById(R.id.btnBMI);
        bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityChoose.this,MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            }
        });


    }
}
