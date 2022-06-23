package com.example.vendaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView name, email, phone, password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        phone = (TextView) findViewById(R.id.phone);
        password = (TextView) findViewById(R.id.password);
        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String Fullname = intent.getStringExtra("name");
        String emailadd = intent.getStringExtra("email");
        String passkey = intent.getStringExtra("passwords");
        String mobilephone = intent.getStringExtra("phone");

        name.setText(Fullname);
        email.setText(emailadd);
        phone.setText(mobilephone);
        password.setText(passkey);

    }
}