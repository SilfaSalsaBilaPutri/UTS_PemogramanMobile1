package com.example.tiketbioskop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper { // Memperbaiki extends

    // Nama database dan tabel
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE_NAME = "user_table";

    // Kolom-kolom dalam tabel
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FULLNAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";

    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel user
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FULLNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Menghapus tabel jika sudah ada
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Menambahkan pengguna baru ke dalam database
    public boolean insertData(String fullName, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, fullName);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // Jika insert berhasil, result bukan -1
    }

    // Mengecek apakah email sudah ada di database (untuk mencegah duplikasi)
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0; // Jika ada data yang cocok, return true
        cursor.close(); // Menutup cursor
        return exists;
    }

    // Mengecek email dan password saat login
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = ? AND PASSWORD = ?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close(); // Menutup cursor
        return exists;
    }
}
