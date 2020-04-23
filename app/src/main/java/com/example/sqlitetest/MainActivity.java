package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText inpNim,inpNama, inpAngkatan ,inpNilai;
    Button btnAdd;
    Button btnEdit;
    Button btnDelete;
    Button btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        inpNim = (EditText)findViewById(R.id.inpNim);
        inpNama = (EditText)findViewById(R.id.inpNama);
        inpAngkatan = (EditText)findViewById(R.id.inpAngkatan);
        inpNilai = (EditText)findViewById(R.id.inpNilai);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnDelete= (Button)findViewById(R.id.btnDelete);
        btnView= (Button)findViewById(R.id.btnView);

        addAction();
        editAction();
        deleteAction();
        showAllAction();


    }

    public void addAction() {
        btnAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                inpNim.getText().toString(),
                                inpNama.getText().toString(),
                                Integer.parseInt(inpAngkatan.getText().toString()),
                                Integer.parseInt(inpNilai.getText().toString()) );
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,
                                    "Data Berhasil Ditambahkan",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,
                                    "Data Gagal Ditambahkan",Toast.LENGTH_LONG).show();
                        inpNim.setText("");
                        inpNama.setText("");
                        inpAngkatan.setText("");
                        inpNilai.setText("");
                    }
                }
        );
    }
    public void editAction() {
        btnEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDb.updateData(inpNim.getText().toString(),
                                inpNama.getText().toString(),
                                Integer.parseInt(inpAngkatan.getText().toString()),
                                Integer.parseInt(inpNilai.getText().toString()) );
                        if(isUpdated == true) {
                            Toast.makeText(MainActivity.this,
                                    "Data Berhasil Diedit", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,
                                    "Data Gagal Diedit", Toast.LENGTH_LONG).show();
                        }
                        inpNim.setText("");
                        inpNama.setText("");
                        inpAngkatan.setText("");
                        inpNilai.setText("");
                    }
                }
        );

    }
    public void deleteAction() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nim = inpNim.getText().toString();
                        boolean deletedRows = myDb.deleteData(nim);
                        if(deletedRows) {
                            Toast.makeText(MainActivity.this,
                                    "Data Berhasil Dihapus",
                                    Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,
                                    "NIM tidak terdaftar",
                                    Toast.LENGTH_LONG).show();
                        }
                        inpNim.setText("");
                        inpNama.setText("");
                        inpAngkatan.setText("");
                        inpNilai.setText("");
                    }
                }
        );

    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    public void showAllAction() {
        btnView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            // show message
                            showMessage("Error", "Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("NIM :" + res.getString(0) + "\n");
                            buffer.append("NAMA :" + res.getString(1) + "\n");
                            buffer.append("Angkatan :" + res.getInt(2) + "\n");
                            buffer.append("Nilai :" + res.getInt(3) + "\n\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

    }
