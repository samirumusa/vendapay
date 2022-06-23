package com.example.vendaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    EditText regName, regEmail, regPhone, regPassword;
    Button regButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regName = (EditText) findViewById(R.id.editName);
        regEmail = (EditText) findViewById(R.id.editEmail);
        regPhone = (EditText) findViewById(R.id.editPhone);
        regPassword = (EditText) findViewById(R.id.editPassword);
        regButton = (Button) findViewById(R.id.buttonReg);

        // save data to firebase on Button Click
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Users");

                String name = regName.getText().toString();
                String email = regEmail.getText().toString();
                String phone = regPhone.getText().toString();
                String password = regPassword.getText().toString();
                UserHelperClass helperClass = new UserHelperClass(name, email, phone, password);

                reference.child(phone).setValue(helperClass);

                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });

    }

    public void registerUser(View view){



    }
}