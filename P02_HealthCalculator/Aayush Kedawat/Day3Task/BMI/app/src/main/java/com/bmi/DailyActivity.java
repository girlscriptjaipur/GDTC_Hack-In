package com.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DailyActivity extends AppCompatActivity {
    DatabaseReference ref;

    Spinner spTime;
    EditText edtDetails;
    String timeOfFood="",foodDetails="";
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        getSupportActionBar().setTitle("Enter Diet information");

        spTime = findViewById(R.id.spFood);
        edtDetails = findViewById(R.id.edtInfo);
        submit = findViewById(R.id.btnSubmit);
        ref= FirebaseDatabase.getInstance().getReference();

        spTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    timeOfFood = "Breakfast";
                }
                else if(i==1)
                    timeOfFood="Lunch";
                else if(i==2)
                    timeOfFood="Snacks";
                else if(i==3)
                    timeOfFood="Dinner";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtDetails.getText().toString().equals("")){
                    Toast.makeText(DailyActivity.this,"Please enter description",Toast.LENGTH_LONG).show();
                }
                else{
                    foodDetails = edtDetails.getText().toString();
                    FoodModel model = new FoodModel(timeOfFood,foodDetails);
                    String curTime = String.valueOf(System.currentTimeMillis());

                    ref.child(curTime).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(DailyActivity.this, "Entry updated successfully", Toast.LENGTH_SHORT).show();
                                edtDetails.setText("");
                            }
                            else
                                Toast.makeText(DailyActivity.this, "There was an error, kindly try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



    }
}
