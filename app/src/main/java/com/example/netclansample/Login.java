package com.example.netclansample;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login extends AppCompatActivity {
    String[] country = {"+1,USA", "+93,AFGHANISTAN", "+1907,ALASKA", "+355,ALBANIA", "+213,ALGERIA", "+1684,AMERICAN SAMOA", "+376,ANDORRA", "+244,ANGOLLA", "+1264,ANGUILLA", "+54,ARGENTINA", "+91,INDIA", "+92,PAKISTAN", "+61,AUSTRALIA", "+43.AUSTRIA", "+1242,BAHAMAS", "+880,BANGALADESH", "+32,BELGIUM", "+975,BHUTAN", "+55,BRAZIL", "+257,BURUNDI", "+855,CAMBODIA", "+237,CAMEROON", "+1,CANADA", "+56,CHILE", "+53,CUBA", "+45,DENMARK", "20,EGYPT", "+251,ETHIOPIA", "+679,FIJI", "+358,FINLAND", "+33,FRANCE", "+49,GERMANY", "+233,GHANA", "+30,GREECE", "+299,GREENLAND", "+509,HAITI", "+1808,HAWAII", "+852,HONG KONG", "+36,HUNGARY", "+354,ICELAND", "+62,INDONESIA", "+98,IRAN", "+964,IRAQ", "+353,IRELAND", "+972,ISRAEL", "+39,ITALY", "+1876,JAMAICA", "+81,JAPAN", "+962,JORDAN", "+7,KAZAKHSTAN", "+254,KENYA", "+82,KOREA", "+218,LIBYA", "+60,MALAYSIA", "+960,MALDIVES", "+233,MALI", "+356,MALI", "+977,NEPAL", "+31,NETHERLANDS", "+47,NORWAY", "+968,OMAN", "+680,PALU", "+51,PERU", "+48,POLAND", "+974,QATAR", "+40,ROMANIA", "+7,RUSSIA", "+221,SENEGAL", "+65,SINGAPORE", "+252,SOMALIA", "+27,SOUTH AFRICA", "+886,TAIWAN", "+66,THAILAND", "+90,TURKEY", "+39,VATICAN CITY", "+967,YEMEN", "+260,ZAMBIA", "+263,ZIMBABWE", "+84,VIETNAM"};
    Button b1;
    TextView tv;
    AutoCompleteTextView auto;
    FirebaseAuth mAuth;
    String temp = "";

    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        b1 = findViewById(R.id.continue1);
        mAuth = FirebaseAuth.getInstance();
        phone = findViewById(R.id.phone);
        auto = findViewById(R.id.cc);
        tv=findViewById(R.id.changephone);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, country);
        auto.setAdapter(adapter);
        auto.setThreshold(1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),change.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (phone.getText().toString().length() <= 9)
                    phone.setError("phone no not valid!");
                if (auto.getText().toString().length() <= 3)
                    auto.setError("country  is required!");
                if (phone.getText().toString().length() > 9 && auto.getText().toString().length() > 2) {
                    String number = phone.getText().toString();
                    findViewById(R.id.p).setVisibility(View.VISIBLE);
                    sendverificationCode(number);
                }
            }
        });
    }

    private void sendverificationCode(String num) {
        findViewById(R.id.show).setVisibility(View.VISIBLE);
        String a = auto.getText().toString();
        temp = "";
        int i = 0;
        if (a.length() > 0) {
            while (a.charAt(i) != ',') {
                temp += a.charAt(i);
                i++;
            }
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                temp + num,
                60,
                TimeUnit.SECONDS,
                Login.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        findViewById(R.id.p).setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        findViewById(R.id.p).setVisibility(View.GONE);
                        Intent intent = new Intent(getApplicationContext(), pin1.class);
                        intent.putExtra("001", temp+num);
                        intent.putExtra("Verify", verificationId);
                        startActivity(intent);

                    }
                });

    }
}
