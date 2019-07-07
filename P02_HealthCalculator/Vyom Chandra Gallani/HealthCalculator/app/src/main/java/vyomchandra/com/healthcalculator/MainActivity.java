package vyomchandra.com.healthcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   TextView textView;
   ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=findViewById(R.id.text);
        imageView=findViewById(R.id.logo);

        imageView.setTranslationY(2000);
        textView.setTranslationX(-2000);
        imageView.animate().translationY(30).setDuration(500);
        textView.animate().translationX(0).setDuration(1000);


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(MainActivity.this,homeactivity.class));

            }
        },3000);
    }
}
