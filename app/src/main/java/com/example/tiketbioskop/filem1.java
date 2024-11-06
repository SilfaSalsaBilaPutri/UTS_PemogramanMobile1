package com.example.tiketbioskop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class filem1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filem1);

        // Inisialisasi ImageButton dengan ID buytiket
        ImageButton buyTiketButton = findViewById(R.id.buytiket);

        // Set OnClickListener pada ImageButton
        buyTiketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent untuk berpindah ke PemesananActivity
                Intent intent = new Intent(filem1.this, Pemesanan.class);
                intent.putExtra("FILM_NAME", "BOLEHKAH SEKALI SAJA KUMENANGIS"); // Ganti dengan nama film yang sesuai
                startActivity(intent);
            }
        });
    }
}
