package com.example.netclansample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class change extends AppCompatActivity {
    String[] country1 = {"+1,USA", "+93,AFGHANISTAN", "+1907,ALASKA", "+355,ALBANIA", "+213,ALGERIA", "+1684,AMERICAN SAMOA", "+376,ANDORRA", "+244,ANGOLLA", "+1264,ANGUILLA", "+54,ARGENTINA", "+91,INDIA", "+92,PAKISTAN", "+61,AUSTRALIA", "+43.AUSTRIA", "+1242,BAHAMAS", "+880,BANGALADESH", "+32,BELGIUM", "+975,BHUTAN", "+55,BRAZIL", "+257,BURUNDI", "+855,CAMBODIA", "+237,CAMEROON", "+1,CANADA", "+56,CHILE", "+53,CUBA", "+45,DENMARK", "20,EGYPT", "+251,ETHIOPIA", "+679,FIJI", "+358,FINLAND", "+33,FRANCE", "+49,GERMANY", "+233,GHANA", "+30,GREECE", "+299,GREENLAND", "+509,HAITI", "+1808,HAWAII", "+852,HONG KONG", "+36,HUNGARY", "+354,ICELAND", "+62,INDONESIA", "+98,IRAN", "+964,IRAQ", "+353,IRELAND", "+972,ISRAEL", "+39,ITALY", "+1876,JAMAICA", "+81,JAPAN", "+962,JORDAN", "+7,KAZAKHSTAN", "+254,KENYA", "+82,KOREA", "+218,LIBYA", "+60,MALAYSIA", "+960,MALDIVES", "+233,MALI", "+356,MALI", "+977,NEPAL", "+31,NETHERLANDS", "+47,NORWAY", "+968,OMAN", "+680,PALU", "+51,PERU", "+48,POLAND", "+974,QATAR", "+40,ROMANIA", "+7,RUSSIA", "+221,SENEGAL", "+65,SINGAPORE", "+252,SOMALIA", "+27,SOUTH AFRICA", "+886,TAIWAN", "+66,THAILAND", "+90,TURKEY", "+39,VATICAN CITY", "+967,YEMEN", "+260,ZAMBIA", "+263,ZIMBABWE", "+84,VIETNAM"};
    AutoCompleteTextView auto;
    EditText phone;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        b1 = findViewById(R.id.continue1);
        phone=findViewById(R.id.phone);
        auto = findViewById(R.id.cc);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, country1);
        auto.setAdapter(adapter);
        auto.setThreshold(1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (phone.getText().toString().length() <= 9)
                    phone.setError("phone no not valid!");
                if (auto.getText().toString().length() <= 3)
                    auto.setError("country  is required!");
                if (phone.getText().toString().length() > 9 && auto.getText().toString().length() > 2) {
                    findViewById(R.id.p).setVisibility(View.VISIBLE);
                    checkPhone();
                }
            }
        });

    }

    private void checkPhone() {
        if (user != null) {

            String phoneNumber = user.getPhoneNumber();
            Toast.makeText(change.this,phoneNumber+" Is Already Registered",Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        } else {
            Toast.makeText(change.this,"Not Registered!",Toast.LENGTH_LONG).show();
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }
    }
}