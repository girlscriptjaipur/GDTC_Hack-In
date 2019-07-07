package vyomchandra.com.healthcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMIcalculator extends AppCompatActivity {

    EditText height,weight;
    Button calculate;
    TextView bmi,result;
    double res=0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        calculate=findViewById(R.id.calculate);
        bmi=findViewById(R.id.bmi);
        result=findViewById(R.id.result);



//        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Health Calculator");

        height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()>0){
                    if(height.getText().length()==1&&height.getText().toString().charAt(0)=='.'){
                        height.setError("Enter Correct number ");
                    }
                }
            }
        });
        weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.length()>0){
                    if(weight.getText().length()==1&&weight.getText().toString().charAt(0)=='.'){
                        weight.setError("Enter Correct number ");
                    }
                }
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Double.parseDouble(height.getText().toString())>272)||(Double.parseDouble(height.getText().toString())<53)){
                    height.setError("should be between 53 and 272");
                }
                else if(TextUtils.isEmpty(weight.getText().toString())||Integer.parseInt(weight.getText().toString())<20){
                    weight.setError("should be above 20");
                }

                else{
                    Double pow=(Double.parseDouble(height.getText().toString())*Double.parseDouble(height.getText().toString()))/10000;
                    res=Double.parseDouble(weight.getText().toString())/pow;
                    bmi.setText(" BMI: "+String.format("%.2f",res));
                    height.setText("");
                    weight.setText("");
                    if (res < 18.5) {
                        result.setText("Underweight");
                        //Toast.makeText(BMIcalculator.this, "Underweight", Toast.LENGTH_SHORT).show();
                    } else if (res > 18.5 && res < 24.9) {
                        result.setText("Idel");
                        //Toast.makeText(BMIcalculator.this, "Idel", Toast.LENGTH_SHORT).show();
                    } else if (res > 25 && res < 29.9) {
                        result.setText("Overweight");
                        //Toast.makeText(BMIcalculator.this, "Overweight", Toast.LENGTH_SHORT).show();

                    } else {
                        result.setText("Obesity");
                        //Toast.makeText(BMIcalculator.this, "Obesity", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    }
