package com.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ActivityBMICalc extends AppCompatActivity {

    EditText mWeight, mHeight;
    Button calc;
    double height, weight;
    TextView bmr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalc);
        Objects.requireNonNull(getSupportActionBar()).setTitle("BMI Calculator");

        mHeight = findViewById(R.id.edtHeight);
        mWeight = findViewById(R.id.edtWeight);
        bmr = findViewById(R.id.txtBMI);
        calc = findViewById(R.id.btnBMRCalc);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strHeight = mHeight.getText().toString();
                String strWeight = mWeight.getText().toString();
                if(strHeight.equals("")){
                    Toast.makeText(ActivityBMICalc.this, "Please enter height", Toast.LENGTH_SHORT).show();
                }
                else if(strWeight.equals("")){
                    Toast.makeText(ActivityBMICalc.this, "Please enter weight", Toast.LENGTH_SHORT).show();
                }
                else{
                    weight = Double.parseDouble(strWeight);
                    height = Double.parseDouble(strHeight);
                    height = height/100;
                    double calc = weight/(height*height);
                    bmr.setText(String.format("Your BMI is %.2f", calc));
                    //String bmi = String.valueOf(calc);
                    bmr.setVisibility(View.VISIBLE);
                    //bmr.setText(bmi);
                }
            }
        });
    }
}
