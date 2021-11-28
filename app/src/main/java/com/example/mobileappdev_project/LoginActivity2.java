package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class LoginActivity2 extends AppCompatActivity {

    EditText email, password;
    Button btn_login;
    TextView yuk;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        btn_login = (Button) findViewById(R.id.yukdaftar);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("")||password.getText().toString().equals("")){
                    builder = new AlertDialog.Builder(LoginActivity2.this);
                    builder.setTitle("Warning..!");
                    builder.setMessage("Your fill not input..!!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    proses_login backgroundTask = new proses_login(LoginActivity2.this);
                    backgroundTask.execute("Login", email.getText().toString(),password.getText().toString());
                }
            }
        });

    }
}