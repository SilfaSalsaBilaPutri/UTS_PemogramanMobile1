package com.example.tiketbioskop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class Pemesanan extends AppCompatActivity {

    private Spinner spinnerLocation, seatTypeSpinner, bankSpinner;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private TextView selectedJenis, harga, metodePembayaran, rekening;
    private EditText norek;
    private ImageButton btnPay;

    // Tentukan harga untuk setiap jenis kursi
    private final int[] seatPrices = {30000, 40000, 60000}; // Contoh harga untuk berbagai jenis kursi
    private String[] seatTypes; // Untuk menyimpan label jenis kursi
    private String filmName; // Variable untuk menyimpan nama film

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        // Inisialisasi View
        initializeViews();

        // Terima nama film dari intent
        filmName = getIntent().getStringExtra("FILM_NAME");

        // Siapkan data kursi
        seatTypes = new String[]{"Reguler", "VIP", "VVIP"};

        // Siapkan Spinner untuk lokasi
        setupLocationSpinner();

        // Siapkan Spinner untuk jenis kursi
        setupSeatTypeSpinner();

        // Siapkan Spinner untuk bank
        setupBankSpinner();

        // Konfigurasi TimePicker
        timePicker.setIs24HourView(true);

        // Set listener untuk pemilihan jenis kursi
        setupSeatTypeListener();

        // Event Click Button
        setupPayButtonListener();
    }

    private void initializeViews() {
        spinnerLocation = findViewById(R.id.spinnerLocation);
        seatTypeSpinner = findViewById(R.id.seatTypeSpinner);
        bankSpinner = findViewById(R.id.bankSpinner);
        datePicker = findViewById(R.id.data_pick);
        timePicker = findViewById(R.id.timepick);
        selectedJenis = findViewById(R.id.jenisdipilih);
        harga = findViewById(R.id.harga);
        metodePembayaran = findViewById(R.id.metodePembayaran);
        rekening = findViewById(R.id.rekening);
        norek = findViewById(R.id.norek);
        btnPay = findViewById(R.id.btnPay);
    }

    private void setupLocationSpinner() {
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(locationAdapter);
    }

    private void setupSeatTypeSpinner() {
        ArrayAdapter<String> seatTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, seatTypes);
        seatTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatTypeSpinner.setAdapter(seatTypeAdapter);
    }

    private void setupBankSpinner() {
        ArrayAdapter<CharSequence> bankAdapter = ArrayAdapter.createFromResource(this,
                R.array.bank_array, android.R.layout.simple_spinner_item);
        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankSpinner.setAdapter(bankAdapter);
    }

    private void setupSeatTypeListener() {
        seatTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Perbarui TextView harga berdasarkan jenis kursi yang dipilih
                int selectedPrice = seatPrices[position];
                harga.setText("Harga: Rp " + selectedPrice);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Tidak melakukan apa-apa
            }
        });
    }

    private void setupPayButtonListener() {
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan tanggal yang dipilih
                String selectedDate = getSelectedDate();

                // Dapatkan waktu yang dipilih
                String selectedTime = getSelectedTime();

                // Dapatkan nilai spinner yang dipilih
                String location = spinnerLocation.getSelectedItem().toString();
                String seatType = seatTypeSpinner.getSelectedItem().toString();
                String bank = bankSpinner.getSelectedItem().toString();
                String accountNumber = norek.getText().toString();

                // Validasi
                if (accountNumber.isEmpty()) {
                    Toast.makeText(Pemesanan.this, "Silakan masukkan nomor rekening Anda.", Toast.LENGTH_SHORT).show();
                } else {
                    // Tampilkan detail yang dipilih
                    displaySelectedDetails(location, seatType, selectedDate, selectedTime, bank, accountNumber);

                    // Mulai aktivitas TiketSaya
                    startTiketSayaActivity(location, seatType, selectedDate, selectedTime, bank, accountNumber);
                }
            }
        });
    }

    private String getSelectedDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1; // Tambahkan 1 karena bulan mulai dari 0
        int year = datePicker.getYear();
        return day + "/" + month + "/" + year;
    }

    private String getSelectedTime() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        return hour + ":" + (minute < 10 ? "0" + minute : minute);
    }

    private void displaySelectedDetails(String location, String seatType, String selectedDate, String selectedTime, String bank, String accountNumber) {
        Toast.makeText(Pemesanan.this,
                "Lokasi: " + location + "\n" +
                        "Jenis Kursi: " + seatType + "\n" +
                        "Tanggal: " + selectedDate + "\n" +
                        "Waktu: " + selectedTime + "\n" +
                        "Bank: " + bank + "\n" +
                        "Nomor Rekening: " + accountNumber,
                Toast.LENGTH_LONG).show();
    }

    private void startTiketSayaActivity(String location, String seatType, String selectedDate, String selectedTime, String bank, String accountNumber) {
        Intent intent = new Intent(Pemesanan.this, TiketSaya.class);
        // Kirim data yang dipilih ke TiketSaya
        intent.putExtra("LOCATION", location);
        intent.putExtra("SEAT_TYPE", seatType);
        intent.putExtra("DATE", selectedDate);
        intent.putExtra("TIME", selectedTime);
        intent.putExtra("BANK", bank);
        intent.putExtra("ACCOUNT_NUMBER", accountNumber);
        intent.putExtra("FILM_NAME", filmName); // Kirim nama film
        startActivity(intent);
    }
}
