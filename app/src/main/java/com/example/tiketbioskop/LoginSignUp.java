package com.example.tiketbioskop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class LoginSignUp extends AppCompatActivity {

    private ImageButton loginButton;
    private ImageButton signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);

        // Hubungkan tombol dengan ID di XML
        loginButton = findViewById(R.id.button1);
        signUpButton = findViewById(R.id.button2);


        // Listener untuk tombol LOGIN
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignUp.this, Login.class); // Pastikan Login.class ada
                startActivity(intent);
            }
        });

        // Listener untuk tombol SIGN UP
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignUp.this, SignUp.class); // Pastikan SignUp.class ada
                startActivity(intent);
            }
        });
    }
}