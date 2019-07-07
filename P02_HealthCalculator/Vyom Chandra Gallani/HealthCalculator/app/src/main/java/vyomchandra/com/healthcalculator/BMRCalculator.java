package vyomchandra.com.healthcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class BMRCalculator extends AppCompatActivity {

    RadioButton male,female;
    Button calculate;
    EditText height,age,weight;
    double res;
    TextView Bmr,result;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmrcalculator);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        calculate=findViewById(R.id.calculate);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        age=findViewById(R.id.age);
        Bmr=findViewById(R.id.bmr);
        result=findViewById(R.id.result);


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

//        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Health Calculator");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        age.setInputType(InputType.TYPE_CLASS_NUMBER);


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){}
                else {
                    if (male.isChecked()) {
                        res = 655 + (9.5 * (Double.parseDouble(weight.getText().toString()))) + (1.8 * Double.parseDouble(height.getText().toString())) - (4.7 * (Integer.parseInt(age.getText().toString())));

                        weight.setText("");
                        height.setText("");
                        age.setText("");
                        if (res < 1493) {
                            result.setText("Underweight");
                            //Toast.makeText(BMRCalculator.this, "Underweight", Toast.LENGTH_SHORT).show();
                        } else if(res  == 1493) {
                            result.setText("Fit");
                           // Toast.makeText(BMRCalculator.this, "Fit", Toast.LENGTH_SHORT).show();

                        } else {
                            result.setText("Overweight");
                            // Toast.makeText(BMRCalculator.this, "Overweight" +
                            //         "", Toast.LENGTH_SHORT).show();                    }
                        }
                    }
                    if(female.isChecked()) {
                        res = 66 + (13.7 * (Double.parseDouble(weight.getText().toString()))) + (5 * Double.parseDouble(height.getText().toString())) - (6.8 * (Integer.parseInt(age.getText().toString())));
                        weight.setText("");
                        height.setText("");
                        age.setText("");
                        if (res < 1493) {
                            //Toast.makeText(BMRCalculator.this, "Underweight", Toast.LENGTH_SHORT).show();
                            result.setText("Underweight");
                        } else if(res  == 1493) {
                           // Toast.makeText(BMRCalculator.this, "Fit", Toast.LENGTH_SHORT).show();
                            result.setText("Fit");
                        } else {
                            result.setText("Overweight");
                        }


                    }
                    Bmr.setText(" BMR : "+String.format("%.2f",res));


                }

            }
        });
    }
    public boolean check(){
        if(TextUtils.isEmpty(height.getText().toString())){
            height.setError("required");
            return true;
        }else if(TextUtils.isEmpty(weight.getText().toString())){
            weight.setError("required");
            return true;
        }else if(TextUtils.isEmpty(age.getText().toString())){
            age.setError("required");
            return true;
        }
        else if(Double.parseDouble(height.getText().toString())>=272||Double.parseDouble(height.getText().toString())<=53){
            height.setError("should be between 53 and 272");
            return true;
        }else if(Double.parseDouble(weight.getText().toString())<=20){
            weight.setError("should be above 20");
            return true;
        }else if(Integer.parseInt(age.getText().toString())>=100||Integer.parseInt(age.getText().toString())<=5){
            age.setError("should be between 5 to 100");
            return true;
        }else if(!(male.isChecked()||female.isChecked())){
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return true;
        }



        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
