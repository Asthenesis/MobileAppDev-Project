package com.example.mobileappdev_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Pembayaran extends AppCompatActivity {
    EditText edtOrder;
    double jumlahtiket;
    double hargatiket = 399000;
    String jumlaht;
    String url_add_order = "http://192.168.1.5/OrderTiket.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        getSupportActionBar().hide();

        ImageButton order = findViewById(R.id.OrderID);

        edtOrder = findViewById(R.id.edtJumlah);




    }
        public void Order(View view) {
            jumlaht = edtOrder.getText().toString();
            Intent i = getIntent();
            String jenistiket = i.getStringExtra("jenistiket");
            double jt = Double.parseDouble(jumlaht);
            double totalharga = jt*hargatiket;
            if(jumlaht.equals("")){
                Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
            }
            else if(!jumlaht.equals("") ){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url_add_order, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("success")) {
                            Toast.makeText(Pembayaran.this, "Order berhasil, Silahkan Konfirmasi Pembayaran", Toast.LENGTH_SHORT).show();

                        } else if (response.equals("failure")) {
                            Toast.makeText(Pembayaran.this, "Order gagal, silahkan coba lagi", Toast.LENGTH_SHORT).show();               }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();

                        data.put("JenisTiket",jenistiket);
                        data.put("JumlahTiket", String.valueOf(jt));
                        data.put("TotalHarga",String.valueOf(totalharga));
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        }


      /* order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumlaht = edtOrder.getText().toString();

                double jt = Double.parseDouble(jumlaht);
                double totalharga = jt*hargatiket;

                RequestQueue queue = Volley.newRequestQueue(Pembayaran.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url_add_order, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObj = new JSONObject(response);
                            int sukses = jObj.getInt("success");
                            if (sukses == 1) {
                                Toast.makeText(Pembayaran.this, "User Berhasil Registrasi", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(Pembayaran.this, "Gagal Registrasi, silahkan coba lagi", Toast.LENGTH_SHORT).show();
                            }
                            // progressBar.setVisibility(View.GONE);
                        } catch (Exception ex) {
                            Log.e("Error", ex.toString());
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                        Toast.makeText(Pembayaran.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.GONE);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("JenisTiket",jenistiket);
                        params.put("JumlahTiket", String.valueOf(jt));
                        params.put("TotalHarga",String.valueOf(totalharga));
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
        });


    }*/
    }
