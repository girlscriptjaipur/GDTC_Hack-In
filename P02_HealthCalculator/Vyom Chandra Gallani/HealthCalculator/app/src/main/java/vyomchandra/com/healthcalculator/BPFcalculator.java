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

public class BPFcalculator extends AppCompatActivity {

    RadioButton male,female;
    Button calculate;
    EditText height,age,weight;
    double res,bmi;
    TextView Bpf,result;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpfcalculator);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        calculate=findViewById(R.id.calculate);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        age=findViewById(R.id.age);
        Bpf=findViewById(R.id.bmr);
        result=findViewById(R.id.result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Health Calculator");

        age.setInputType(InputType.TYPE_CLASS_NUMBER);
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


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check()){

                }else
                {
                    calculateBMI();
                    if(male.isChecked()&&Integer.parseInt(age.getText().toString())>18){
                        res=1.20*bmi+0.23*Integer.parseInt(age.getText().toString())-16.2;
                    }
                    else if(male.isChecked()&&Integer.parseInt(age.getText().toString())<=18){
                        res=1.51*bmi-0.70*Integer.parseInt(age.getText().toString())-2.2;

                    }else if(female.isChecked()&&Integer.parseInt(age.getText().toString())>18){
                        res=1.20*bmi+0.23*Integer.parseInt(age.getText().toString())-5.4;
                    }else if(female.isChecked()&&Integer.parseInt(age.getText().toString())<=18){
                        res=1.51*bmi-0.70*Integer.parseInt(age.getText().toString())+1.4;
                    }
                    if(res<0){
                        res=res*(-1)/100;
                    }
                    if(res>0){
                        res=res/100;
                    }

                    Bpf.setText(" Body Fat %: "+String.format("%.4f",res));
                    age.setText("");
                    height.setText("");
                    weight.setText("");

                    if(res < 0.15){
                        result.setText("Underweight");
                       // Toast.makeText(BPFcalculator.this, "Underweight", Toast.LENGTH_SHORT).show();

                    }
                    else if (res >= 0.15 && res <= 0.20) {
                        result.setText("Top Athlete");
                       // Toast.makeText(BPFcalculator.this, "Top Athlete", Toast.LENGTH_SHORT).show();

                    } else if (res >= 0.21 && res<= 0.24) {
                        result.setText("Fit");
                        //Toast.makeText(BPFcalculator.this, "Fit", Toast.LENGTH_SHORT).show();

                    } else if (res >= 0.25 && res<= 0.32) {
                        result.setText("Healthy");
                       // Toast.makeText(BPFcalculator.this, "Healthy", Toast.LENGTH_SHORT).show();

                    } else {
                        result.setText("Overweight");
                        //Toast.makeText(BPFcalculator.this, "Overweight", Toast.LENGTH_SHORT).show();

                    }


                }


            }
        });


    }
    public void calculateBMI(){
        bmi=Double.parseDouble(weight.getText().toString())/Math.pow(Double.parseDouble(height.getText().toString())/100,2);
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
        }else if(Integer.parseInt(age.getText().toString())<=5||Integer.parseInt(age.getText().toString())>=100){
            age.setError("should be between 5 and 100");
            return true;
        }else if(!(male.isChecked()||female.isChecked())){
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return true;
        }else if(height.getText().toString().length()==1&&height.getText().toString().charAt(0)=='.'){
            //Toast.makeText(this, "Please enter correct number", Toast.LENGTH_SHORT).show();
            height.setError("Enter correct number ");
            return true;
        }else if(weight.getText().toString().length()==1&&weight.getText().toString().charAt(0)=='.'){
            //Toast.makeText(this, "Please enter correct number", Toast.LENGTH_SHORT).show();
            weight.setError("Enter correct number ");
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
        return super.onContextItemSelected(item);
    }
}
