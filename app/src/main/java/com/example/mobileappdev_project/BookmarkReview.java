package com.example.mobileappdev_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookmarkReview extends ListActivity implements AdapterView.OnItemLongClickListener {

    private MyDBHandler dbHandler;
    private ArrayList<Notes> values;
    private Button btnEdit, btnHapus;
    private ListView list;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_review);

        dbHandler = new MyDBHandler(this);

        try {
            dbHandler.open();
        } catch (SQLException e){
            e.printStackTrace();
        }

        values = dbHandler.getAllNotes();

        ArrayAdapter<Notes> adapter = new ArrayAdapter <Notes> (this, android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        list = (ListView) findViewById(android.R.id.list);
        list.setOnItemLongClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),tambah.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Pilih Aksi");
        dialog.show();

        final Notes notes = (Notes) getListAdapter().getItem(i);
        final Long id = notes.getID();
        btnEdit = dialog.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notes notes = dbHandler.getNotes(id);
                Intent i = new Intent(getApplicationContext(),edit.class);
                Bundle bundle = new Bundle();
                bundle.putLong("id",notes.getID());
                bundle.putString("judulnotes",notes.getJudulNotes());
                bundle.putString("konten",notes.getKontenNotes());

                i.putExtras(bundle);
                startActivity(i);
                dialog.dismiss();

            }
        });
        btnHapus = dialog.findViewById(R.id.btnHapus);
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder konfirm = new AlertDialog.Builder(context);
                konfirm.setTitle("Hapus Notes");
                konfirm.setMessage("Anda yakin akan menghapus notes ini?");
                konfirm.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dbHandler.deleteNotes(id);

                        finish();
                        startActivity(getIntent());

                        Toast.makeText(BookmarkReview.this, "Notes berhasil dihapus",Toast.LENGTH_LONG).show();
                    }
                });
                konfirm.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                konfirm.show();
                dialog.dismiss();
            }
        });

        return true;
    }
}