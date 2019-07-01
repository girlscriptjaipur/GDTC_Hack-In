package vyomchandra.com.bmrcalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioButton male,female;
    Button calculate;
    EditText height,age,weight;
    double res;
    TextView Bmr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        calculate=findViewById(R.id.calculate);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        age=findViewById(R.id.age);
        Bmr=findViewById(R.id.bmr);

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
                    }
                    if(female.isChecked()) {
                        res = 66 + (13.7 * (Double.parseDouble(weight.getText().toString()))) + (5 * Double.parseDouble(height.getText().toString())) - (6.8 * (Integer.parseInt(age.getText().toString())));
                        weight.setText("");
                        height.setText("");
                        age.setText("");

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
        else if(Double.parseDouble(height.getText().toString())<=0){
            height.setError("must be positive");
            return true;
        }else if(Double.parseDouble(weight.getText().toString())<=0){
            weight.setError("must be positive");
            return true;
        }else if(Integer.parseInt(age.getText().toString())<=0){
            age.setError("must be positive");
            return true;
        }



        return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to logout?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mAuth.signOut();
               finish();
            }

        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.setTitle("Exit");
        alert.show();

    }
}
