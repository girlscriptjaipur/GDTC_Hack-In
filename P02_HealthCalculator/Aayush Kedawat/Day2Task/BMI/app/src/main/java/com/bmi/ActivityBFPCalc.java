package com.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ActivityBFPCalc extends AppCompatActivity {
    EditText mWeight, mHeight, mAge;
    Button calc;
    double height, weight;
    int age;
    boolean isMale = true, isFemale= false;
    TextView bfp;
    RadioButton radioMale, radioFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bfpcalc);
        Objects.requireNonNull(getSupportActionBar()).setTitle("BFP Calculator");
        mWeight= findViewById(R.id.edtWeight);
        mHeight = findViewById(R.id.edtHeight);
        mAge = findViewById(R.id.edtAge);
        calc = findViewById(R.id.btnBFPCalc);
        bfp = findViewById(R.id.txtBFP);
        radioFemale = findViewById(R.id.radioFemale);
        radioMale = findViewById(R.id.radioMale);
        radioMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isMale= true;
                    isFemale = false;
                }
            }
        });
        radioFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    isFemale= true;
                    isMale= false;
                }
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strWeight = mWeight.getText().toString();
                String strHeight = mHeight.getText().toString();
                String strAge = mAge.getText().toString();
                if(strWeight.equals("")){
                    Toast.makeText(ActivityBFPCalc.this, "Please enter Weight", Toast.LENGTH_SHORT).show();
                    mWeight.requestFocus();
                }
                else if(strHeight.equals("")){
                    Toast.makeText(ActivityBFPCalc.this, "Please enter Height", Toast.LENGTH_SHORT).show();
                    mHeight.requestFocus();
                }
                else if(strAge.equals("")){
                    Toast.makeText(ActivityBFPCalc.this, "Please enter Age", Toast.LENGTH_SHORT).show();
                    mAge.requestFocus();
                }
                else{
                    height = Double.parseDouble(strHeight);
                    weight = Double.parseDouble(strWeight);
                    age= Integer.parseInt(strAge);
                    height = height/100;
                    double bmi = weight/(height*height);
                    if(isMale){
                        double bfpCalc = (1.20*bmi)+(0.23*age)-16.2;
                        bfp.setText(String.format("Your Body Fat Percentage is %.2f", bfpCalc));
                        bfp.setVisibility(View.VISIBLE);
                    }
                    else if (isFemale)
                    {
                        double bfpCalc = (1.20*bmi)+(0.23*age)-5.4;
                        bfp.setText(String.format("Your Body Fat Percentage is %.2f", bfpCalc));
                        bfp.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_actionbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.menu_info)
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ActivityBFPCalc.this);
            final LayoutInflater inflater = getLayoutInflater();
            View order_alert = inflater.inflate(R.layout.info_layout_bfp, null);
            builder.setIcon(R.drawable.ic_info_black_24dp);
            builder.setView(order_alert);
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
