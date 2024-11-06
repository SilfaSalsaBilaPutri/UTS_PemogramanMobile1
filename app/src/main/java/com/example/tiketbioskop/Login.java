package com.example.tiketbioskop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private ImageButton loginButton;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi EditText dan tombol
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

        // Inisialisasi database helper
        db = new DatabaseHelper(this);

        // Set listener untuk tombol login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();

                // Validasi input
                if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)) {
                    Toast.makeText(Login.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    // Cek apakah email dan password valid
                    boolean checkUser = db.checkUser(userEmail, userPassword);
                    if (checkUser) {
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        // Berpindah ke aktivitas Menu setelah login sukses
                        Intent intent = new Intent(Login.this, Menu.class);
                        startActivity(intent);
                        finish(); // Mengakhiri Login activity agar pengguna tidak kembali ke login dengan tombol kembali
                    } else {
                        Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}