package com.ritik.whatsappfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText edtPhoneNumber;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Login Using Phone");

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
            finish();
        }

        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        findViewById(R.id.btnSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "+91" + edtPhoneNumber.getText().toString().trim();

                if(phoneNumber.length() >= 10) {
                    Intent intent = new Intent(MainActivity.this, Verification.class);
                    intent.putExtra("phoneNumber", phoneNumber);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Wrong Phone Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
