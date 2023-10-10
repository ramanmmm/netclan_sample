package com.example.netclansample;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView details1,details2,details3 ;
    Button b1,b2,b3;
    RelativeLayout layout1,layout2,layout3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        details1 = findViewById(R.id.detail1);
        layout1=findViewById(R.id.layout1);
        details2 = findViewById(R.id.detail2);
        layout2=findViewById(R.id.layout2);
        details3 = findViewById(R.id.detail3);
        layout3=findViewById(R.id.layout3);
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(MainActivity.this,Login.class);
            startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Login.class);
                startActivity(i);
            }
        });
    }


    public void expend1(View view) {
        b2.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);
        int v1=(details1.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        int a=(b1.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        findViewById(R.id.detail2).setVisibility(View.GONE);
        findViewById(R.id.detail3).setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(layout1,new AutoTransition());
        details1.setVisibility(v1);
        b1.setVisibility(a);


    }
    public void expend2(View view) {
        b1.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);
        int v2=(details2.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        int b=(b2.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        findViewById(R.id.detail1).setVisibility(View.GONE);
        findViewById(R.id.detail3).setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(layout2,new AutoTransition());
        details2.setVisibility(v2);
        b2.setVisibility(b);

    }
    public void expend3(View view) {
        b1.setVisibility(View.GONE);
        b2.setVisibility(View.GONE);
        int v3=(details3.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        int c=(b3.getVisibility()==View.GONE)?View.VISIBLE:View.GONE;
        findViewById(R.id.detail1).setVisibility(View.GONE);
        findViewById(R.id.detail2).setVisibility(View.GONE);
        TransitionManager.beginDelayedTransition(layout3,new AutoTransition());
        details3.setVisibility(v3);
        b3.setVisibility(c);

    }
}