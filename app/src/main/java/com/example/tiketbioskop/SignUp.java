package com.example.tiketbioskop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    private EditText fullName, email, password, confirmPassword;
    private ImageButton signUpButton;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Inisialisasi EditText dan tombol
        fullName = findViewById(R.id.editext1);
        email = findViewById(R.id.edittext2);
        password = findViewById(R.id.edittext3);
        confirmPassword = findViewById(R.id.edittext4);
        signUpButton = findViewById(R.id.gambarb1);

        // Inisialisasi database helper
        db = new DatabaseHelper(this);

        // Set listener untuk tombol signup
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullName.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                String userConfirmPassword = confirmPassword.getText().toString();

                // Validasi input
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword) || TextUtils.isEmpty(userConfirmPassword)) {
                    Toast.makeText(SignUp.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (!userPassword.equals(userConfirmPassword)) {
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Cek apakah email sudah digunakan
                    boolean checkEmail = db.checkEmail(userEmail);
                    if (!checkEmail) {
                        // Insert data ke database
                        boolean insert = db.insertData(name, userEmail, userPassword);
                        if (insert) {
                            Toast.makeText(SignUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            // Pindahkan ke halaman login
                            Intent intent = new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignUp.this, "Account creation failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Email already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    }
