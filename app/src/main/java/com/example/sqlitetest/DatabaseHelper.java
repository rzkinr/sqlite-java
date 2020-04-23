package com.example.sqlitetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "mahasiswa.db";
    public static final String TABLE_NAME = "tabel_nilai";
    public static final String COL_1 = "nim";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "angkatan";
    public static final String COL_4 = "nilai";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (nim TEXT PRIMARY KEY,nama TEXT, angkatan INTEGER, nilai INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nim, String nama, int angkatan, int nilai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nim);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, angkatan);
        contentValues.put(COL_4, nilai);
        long result = db.insert(TABLE_NAME,null , contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String nim, String nama, int angkatan, int nilai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,nim);
        contentValues.put(COL_2,nama);
        contentValues.put(COL_3,angkatan);
        contentValues.put(COL_4,nilai);
        int result = db.update(TABLE_NAME, contentValues, "nim = ?", new String[] { nim });
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteData(String nim) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME,"nim = ?" ,new String[] { nim });
        if(result == 0)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}
