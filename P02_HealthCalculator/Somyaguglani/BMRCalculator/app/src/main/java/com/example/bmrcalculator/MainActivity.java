package com.example.bmrcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText age,weight,height;
    int gen = 0 ;
    TextView gender;
    Button calculate;
    RadioGroup radiogroup;
    RadioButton radiobutton;
    int find_age;
    double find_ans=0.0;
    double find_weight,find_height;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this)
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
        setContentView(R.layout.activity_main);


        radiogroup= findViewById(R.id.rgroup_radiogroup);
        age = findViewById(R.id.et_age);
        weight= findViewById(R.id.et_weight);
        height= findViewById(R.id.et_height);
        calculate= findViewById(R.id.btn_calculate);
        gender= findViewById(R.id.tv_gender);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(age.getText().toString())) {
                    age.setError("Enter age");
                    return;
                }
                else if (TextUtils.isEmpty(height.getText().toString())) {
                    height.setError("Enter weight");
                    return;
                }
                else if (TextUtils.isEmpty(weight.getText().toString())) {
                    weight.setError("Enter weight");
                    return;
                }

                else if (radiogroup.getCheckedRadioButtonId() == -1)
                {
                    gender.setError("Choose gender");
                    return ;
                }else{

                    find_age= Integer.parseInt(age.getText().toString());
                    find_weight= Double.parseDouble(weight.getText().toString());
                    find_height= Double.parseDouble(height.getText().toString());


                    if(gen==1){

                        if(find_age>=10 && find_age<=17)
                            find_ans= 13.4*find_weight + 692;
                        else  if(find_age>=18 && find_age<=29)
                            find_ans= 14.8*find_weight + 487;
                        else if(find_age>=30 && find_age<=59)
                            find_ans= 8.3*find_weight + 846;
                        else if (find_age>=60)
                            find_ans = 9.247*find_weight+ 3.098*find_height - 4.330*find_age + 447.593;


                    }else if(gen==2){


                        if(find_age>=10 && find_age<=17)
                            find_ans= 17.7*find_weight + 657;
                        else  if(find_age>=18 && find_age<=29)
                            find_ans= 15.1*find_weight + 692;
                        else if(find_age>=30 && find_age<=59)
                            find_ans= 11.5*find_weight + 873;
                        else if (find_age>=60)
                            find_ans = 13.397*find_weight+  4.799*find_height - 5.677*find_age + 88.362;


                    }

                    Intent intent = new Intent(MainActivity.this,OtherActivity.class);
                    intent.putExtra("ANS",Double.toString(Double.parseDouble(String.format("%.2f", find_ans))));
                    startActivity(intent);




                }
            }
        });




    }

    public void Check_gender(View view) {
        switch(view.getId()){

            case R.id.rbtn_male:
                gen=2;
                break;
            case R.id.rbtn_female:
                gen=1;
                break;

        }
    }
}
