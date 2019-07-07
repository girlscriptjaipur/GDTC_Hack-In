package vyomchandra.com.healthcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class homeactivity extends AppCompatActivity {


    Toolbar toolbar;
    CardView bmi,bmr,bpf,health,notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

//        toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setTitle("Health Calculator");

        bmi=findViewById(R.id.bmi);
        bmr=findViewById(R.id.bmr);
        bpf=findViewById(R.id.bpf);
        health=findViewById(R.id.health);
        notes=findViewById(R.id.notes);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeactivity.this,BMIcalculator.class));

            }
        });
        bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeactivity.this,BMRCalculator.class));

            }
        });
        bpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeactivity.this,BPFcalculator.class));
            }
        });

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeactivity.this,healthActivity.class));
            }
        });
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homeactivity.this,notes.class));
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharemenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.share){

            Intent share=new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            String shareBody="https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName();

            String shareSub="four subjects here";
            share.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            share.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(share,"Share via"));


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
