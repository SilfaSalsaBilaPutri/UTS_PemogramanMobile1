package com.example.tiketbioskop;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Durasi splash screen (3000 ms = 3 detik)
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handler untuk menunda eksekusi MainActivity selama 3 detik
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // Beralih ke MainActivity setelah splash screen selesai
                Intent intent = new Intent(MainActivity.this, LoginSignUp.class);
                startActivity(intent);
                finish(); // Menutup SplashActivity agar tidak bisa kembali ke sini
            }
        }, SPLASH_TIME_OUT);
    }
}