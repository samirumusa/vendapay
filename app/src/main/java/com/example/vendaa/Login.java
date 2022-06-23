package com.example.vendaa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText  regPhone, regPassword;
    Button regButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regPhone = (EditText) findViewById(R.id.editPhone);
        regPassword = (EditText) findViewById(R.id.editPassword);
        regButton = (Button) findViewById(R.id.button3);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String enteredphone = regPhone.getText().toString().trim();
                final String enteredpassword = regPassword.getText().toString().trim();

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

                Query checkUser = reference.orderByChild("phone").equalTo(enteredphone);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()) {

                            regPhone.setError(null);
                            String passwordFromDB = dataSnapshot.child(enteredphone).child("password").getValue(String.class);

                            if(passwordFromDB.equals(enteredpassword))
                            {
                                regPhone.setError(null);

                                String nameFromDB = dataSnapshot.child(enteredphone).child("name").getValue(String.class);
                                String emailFromDB = dataSnapshot.child(enteredphone).child("email").getValue(String.class);
                                String phoneFromDB = dataSnapshot.child(enteredphone).child("phone").getValue(String.class);

                                Intent intent = new Intent(Login.this, TransferActivity.class);

                                intent.putExtra("name",nameFromDB);
                                intent.putExtra("email",emailFromDB);
                                intent.putExtra("passwords",passwordFromDB);
                                intent.putExtra("phone",phoneFromDB);

                                startActivity(intent);
                            }

                              else{
                                  regPassword.setError("wrong passwords");
                                  regPassword.requestFocus();

                            }
                        }

                        else {
                            regPhone.setError("No such phone number in our database");
                            regPhone.requestFocus();
                       }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });


    }
}