package com.example.netclansample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;


public class pin1 extends AppCompatActivity {

    PinView pinView;
    Button button;
    TextView t;
    String verificationId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        // hookers (binding view)
        pinView=findViewById(R.id.pinview);
        t=findViewById(R.id.shownumber);
        t.setText(String.format(getIntent().getStringExtra("001")));
        verificationId=getIntent().getStringExtra("Verify");
        button=findViewById(R.id.verifyotp);

        // setting onClickListener on Button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=pinView.getText().toString();
            if(pinView.getText().toString().length()<6 ){
                Toast.makeText(getApplicationContext(),"Please fill otp",Toast.LENGTH_LONG).show();
            }
            if(verificationId!=null){
                PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(verificationId,code);
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent i=new Intent(pin1.this,Home.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Please enter correct otp",Toast.LENGTH_LONG).show();

                    }
                    }
                });
            }
            }
        });
    }
}


















//<?xml version="1.0" encoding="utf-8"?>
//<RelativeLayout
//    xmlns:android="http://schemas.android.com/apk/res/android"
//    xmlns:app="http://schemas.android.com/apk/res-auto"
//    xmlns:tools="http://schemas.android.com/tools"
//    android:layout_width="match_parent"
//    android:layout_height="match_parent"
//    tools:context=".pin1"
//    android:layout_marginTop="30dp"
//    android:layout_marginLeft="30dp"
//    android:layout_marginRight="30dp">
//
//    <TextView
//        android:layout_marginTop="50dp"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="Verification Code "
//        android:textSize="30sp"
//        android:id="@+id/tv1"
//        android:textStyle="bold"
//        android:layout_centerHorizontal="true"/>
//    <TextView
//        android:layout_width="wrap_content"
//        android:id="@+id/tv2"
//        android:layout_height="wrap_content"
//        android:text="Please type verification code sent to "
//        android:layout_below="@+id/tv1"
//        android:gravity="center"
//        android:textSize="15sp"
//        android:layout_marginTop="10dp"/>
//    <TextView
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:id="@+id/shownumber"
//        android:layout_toRightOf="@+id/tv2"
//        android:layout_below="@+id/tv1"
//        android:layout_marginTop="12dp"/>
//
//    <com.chaos.view.PinView
//        android:id="@+id/pinview"
//        app:itemCount="6"
//        app:itemWidth="50dp"
//        app:itemHeight="50dp"
//        android:gravity="center"
//        android:layout_marginTop="60dp"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:itemBackground="@color/white"
//        android:layout_gravity="center"
//        android:inputType="number"
//        android:cursorVisible="true"
//        app:hideLineWhenFilled="false"
//        app:itemRadius="10dp"
//        style="@style/PinWidget.PinView"
//        android:layout_below="@+id/tv2"
//        />
//    <TextView
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:id="@+id/resend"
//        android:textSize="16dp"
//        android:text="Did't get code?Resend"
//        android:layout_below="@+id/pinview"
//        android:layout_marginTop="40dp"/>
//
//    <Button
//        android:id="@+id/verifyotp"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:text="Verify"
//        android:layout_toRightOf="@+id/tv2"
//        android:layout_below="@+id/pinview"
//        android:layout_alignParentEnd="true"
//        android:layout_marginTop="450dp"
//        />
//
//</RelativeLayout>
