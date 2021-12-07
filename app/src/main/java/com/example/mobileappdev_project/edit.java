package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends AppCompatActivity {
    private Long id;
    private String judulnotes;
    private String konten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().hide();
        this.setTitle("Edit Barang");
        final EditText edtNama = (EditText) findViewById(R.id.edtNama);
        final EditText edtKonten = findViewById(R.id.edtKonten);


        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnSimpan = findViewById(R.id.btnSimpan);

        final MyDBHandler dbHandler = new MyDBHandler(this);

        try {
            dbHandler.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bundle = this.getIntent().getExtras();
        id = bundle.getLong("id");
        judulnotes = bundle.getString("judulnotes");
        konten = bundle.getString("konten");


        this.setTitle("Edit Barang ID : ");
        edtNama.setText(judulnotes);
        edtKonten.setText(konten);


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notes barang = new Notes();
                barang.setID(id);
                barang.setJudulNotes(edtNama.getText().toString());
                barang.setKontenNotes(edtKonten.getText().toString());

                dbHandler.updateNotes(barang);
                Toast.makeText(edit.this, "Notes berhasil diedit", Toast.LENGTH_LONG).show();
                Intent i = new Intent(edit.this, BookmarkReview.class);
                startActivity(i);
                edit.this.finish();
                dbHandler.close();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(edit.this, BookmarkReview.class);
                startActivity(i);
                edit.this.finish();
                dbHandler.close();
            }
        });

    }
}