package com.example.tiketbioskop;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TiketSaya extends AppCompatActivity {

    private TextView filmNameText, locationText, seatTypeText, dateText, timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket_saya);

        // Inisialisasi TextView
        filmNameText = findViewById(R.id.filmNameText);
        locationText = findViewById(R.id.locationText);
        seatTypeText = findViewById(R.id.seatTypeText);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);

        // Terima data dari Intent
        String filmName = getIntent().getStringExtra("FILM_NAME");
        String lokasiBioskop = getIntent().getStringExtra("LOCATION");
        String jenisKursi = getIntent().getStringExtra("SEAT_TYPE");
        String tanggal = getIntent().getStringExtra("DATE");
        String waktu = getIntent().getStringExtra("TIME");

        // Set data ke TextView
        filmNameText.setText(" " + filmName);
        locationText.setText(" " + lokasiBioskop);
        seatTypeText.setText(" " + jenisKursi);
        dateText.setText(" " + tanggal);
        timeText.setText(" " + waktu);
    }
}
