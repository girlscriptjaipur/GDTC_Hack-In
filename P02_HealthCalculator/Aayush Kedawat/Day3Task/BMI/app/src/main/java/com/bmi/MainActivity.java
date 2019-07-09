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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spAge;
    double bmrConst, bmrAdd;
    RadioButton male,female;
    Button btnCalculate;
    EditText edtWeight;
    boolean isMale=true, isFemale=false;
    TextView txtBMR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalculate = findViewById(R.id.btnCalculate);
        edtWeight= findViewById(R.id.edtWeight);
        male= findViewById(R.id.radioMale);
        spAge= findViewById(R.id.spAge);
        txtBMR = findViewById(R.id.txtBMR);
        female= findViewById(R.id.radioFemale);
        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    isMale = true;
                    isFemale = false;
                    if(spAge.getSelectedItemId()==0)
                    {
                        bmrConst =17.7;
                        bmrAdd=657;
                        //calculateBMR();
                    }
                    else if(spAge.getSelectedItemId()==1){
                        bmrConst =15.1;
                        bmrAdd=692;
                        //calculateBMR();
                    }
                    else if(spAge.getSelectedItemId()==2){
                        bmrConst =11.5;
                        bmrAdd=873;
                        //calculateBMR();
                    }
                }
            }
        });
        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    isFemale = true;
                    isMale = false;
                    if(spAge.getSelectedItemId()==0)
                    {
                        bmrConst = 13.4;
                        bmrAdd=692;
                       //calculateBMR();
                    }
                    else if(spAge.getSelectedItemId()==1){
                        bmrConst = 14.8;
                        bmrAdd=487;
                        //calculateBMR();
                    }
                    else if(spAge.getSelectedItemId()==2){
                        bmrConst = 8.3;
                        bmrAdd=846;
                        //calculateBMR();
                    }
                }
            }
        });


        spAge.setPrompt(getResources().getString(R.string.age_prompt));
        spAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0&&isMale){
                    bmrConst =17.7;
                    bmrAdd=657;
                    //calculateBMR();
                }
                else if(i==0&&isFemale){
                    bmrConst = 13.4;
                    bmrAdd=692;
                    //calculateBMR();
                } else if(i==1&&isMale){
                    bmrConst =15.1;
                    bmrAdd=692;
                    //calculateBMR();
                }
                else if(i==1&&isFemale){
                    bmrConst = 14.8;
                    bmrAdd=487;
                    //calculateBMR();
                }
                else if(i==2&&isMale){
                    bmrConst =11.5;
                    bmrAdd=873;
                    //calculateBMR();
                }
                else if(i==2&&isFemale){
                    bmrConst = 8.3;
                    bmrAdd=846;
                    //calculateBMR();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMR();
            }
        });
    }

    private void calculateBMR(){
        double weight;
        if (edtWeight.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Please enter your weight", Toast.LENGTH_SHORT).show();
            edtWeight.requestFocus();
        } else {
            weight = Double.parseDouble(edtWeight.getText().toString());
            double bmr = bmrConst * weight + bmrAdd;
            String strBMR = String.valueOf(bmr);
            txtBMR.setVisibility(View.VISIBLE);
            String txtDisplay = "Your BMR is "+strBMR;
            txtBMR.setText(txtDisplay);
        }
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final LayoutInflater inflater = getLayoutInflater();
            View order_alert = inflater.inflate(R.layout.info_layout, null);
            builder.setIcon(R.drawable.ic_info_black_24dp);
            builder.setView(order_alert);
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
