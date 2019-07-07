package com.example.bmrcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {
    EditText bmi_weight,bmi_height,bmi_age;
    TextView ans,bfp,bmi_gender;
    Button bmi_cal;
    int no = 0;
    double find_ans=0.0;
    double find_weight,find_height,find_bfp,find_age;
    RadioGroup bmi_radiogroup;
    RadioButton male,female;

    @Override
    public void onBackPressed()
    {
        finish();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        bmi_weight= findViewById(R.id.et_bmi_weight);
        bmi_height= findViewById(R.id.et_bmi_height);
        bmi_cal= findViewById(R.id.btn_bmi_cal);
        ans= findViewById(R.id.tv_bmi_ans);
        bfp = findViewById(R.id.tv_bmi_bodyfat);
        bmi_radiogroup= findViewById(R.id.rgroup_bmi_radiogroup);
        bmi_gender= findViewById(R.id.tv_bmi_gender);
        bmi_age= findViewById(R.id.et_bmi_age);
        male= findViewById(R.id.rbtn_bmi_male);
        female= findViewById(R.id.rbtn_bmi_female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no = 2;
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                no = 1;
            }
        });





        bmi_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if (TextUtils.isEmpty(bmi_height.getText().toString())) {
                    bmi_height.setError("Enter height");
                    return;
                }else if (TextUtils.isEmpty(bmi_age.getText().toString())) {
                     bmi_age.setError("Enter weight");
                     return;
                 }
                else if (TextUtils.isEmpty(bmi_weight.getText().toString())) {
                    bmi_weight.setError("Enter weight");
                    return;
                }else if (bmi_radiogroup.getCheckedRadioButtonId() == -1)
                 {
                     bmi_gender.setError("Choose gender");
                     return ;
                 }
                else{

                    find_weight= Double.parseDouble(bmi_weight.getText().toString());
                     find_age= Double.parseDouble(bmi_age.getText().toString());
                    find_height= Double.parseDouble(bmi_height.getText().toString());
                    find_ans= find_weight/(find_height*find_height);

                    //logic for bmi
                    ans.setText(Double.toString(find_ans) + "kg/m*m");

                    if(find_ans<16)
                        ((TextView)(findViewById(R.id.tv_print_stuff))).setText(R.string.severe_underweighgt);
                     else if(find_ans>=16 && find_ans<16.9)
                         ((TextView)(findViewById(R.id.tv_print_stuff))).setText(R.string.moderate_weight);
                    else if(find_ans>=17 && find_ans<18.4)
                        ((TextView)(findViewById(R.id.tv_print_stuff))).setText("Underweight");
                    else if(find_ans>=18.5 && find_ans<24.9)
                        ((TextView)(findViewById(R.id.tv_print_stuff))).setText("Normal");
                    else if(find_ans>=25 && find_ans<29.9)
                        ((TextView)(findViewById(R.id.tv_print_stuff))).setText("Overweight");
                    else if(find_ans>=30 && find_ans<34.9)
                        ((TextView)(findViewById(R.id.tv_print_stuff))).setText("Obese Class 1");
                    else if(find_ans>=35 && find_ans<39.9)
                        ((TextView)(findViewById(R.id.tv_print_stuff))).setText("Obese Class 2");
                    else if(find_ans>=40)
                        ((TextView)(findViewById(R.id.tv_print_stuff))).setText("Obese Class 3");

                    //logic for bmi over


                     //logic for bfp

                     if(no==1){

                         if(find_age>= 0 && find_age<=17)
                             find_bfp= 1.51*find_ans-0.70*find_age+1.4;
                         else  if(find_age>=18)
                             find_bfp= 1.2*find_ans + 0.23*find_age -5.4;

                     }else if(no==2){

                         if(find_age>= 0 && find_age<=17)
                             find_bfp= 1.51*find_ans-0.70*find_age-2.2;
                         else  if(find_age>=18)
                             find_bfp= 1.2*find_ans + 0.23*find_age -16.2;

                     }

                     bfp.setText(Double.toString(find_bfp)+ " %");

                     //logic for bfp over

                 }
            }
        });



    }

}
