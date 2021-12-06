package com.example.mobileappdev_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail;
    EditText edtPassword;
    String email,password;
    String url_kirim_data = "http://lockyourticket.000webhostapp.com/Login.php";


    void kirim_data(){
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_kirim_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    int sukses = jObj.getInt("success");
                    if (sukses == 1) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, "Data pengguna berhasil login", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Data pengguna gagal disimpan", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                        Toast.makeText(LoginActivity.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        ImageButton btnLogin =  findViewById(R.id.nexthome_btn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kirim_data();
            }
        });

    }
}

/*public class LoginActivity extends AppCompatActivity {

}

    private EditText edtEmail, edtPassword;
    private Button nexthome;
    final String URL_Login = "http://lockyourticket.000webhostapp.com/Login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        ImageButton btn = (ImageButton) findViewById(R.id.nexthome_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_email = edtEmail.getText().toString();
                String u_pass = edtPassword.getText().toString();

                if (!u_email.isEmpty() || !u_pass.isEmpty()) {
                    SignIn(u_email, u_pass);
                } else {
                    edtEmail.setError("Please insert your Email correctly!");
                    edtPassword.setError("Please Insert your Password correctly!");
                }
            }
        });
    }

    private void SignIn(String email, String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("SignIn");

                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    } else if (success.equals("0")) {
                        Toast.makeText(LoginActivity.this, "Wrong Email/Password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "Error "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.getCache().clear();
        requestQueue.add(stringRequest);

    }

}*/