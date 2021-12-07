package com.example.mobileappdev_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DeleteTiket extends AppCompatActivity {
    private static final String TAG_DATA="data";
    private static final String TAG_ORDER_ID = "OrderID";
    private static final String TAG_JENISTIKET="JenisTiket";
    private static final String TAG_JUMLAHTIKET="JumlahTiket";
    private static final String TAG_TOTAL_HARGA = "TotalHarga";
    String orderID;

    Button btnHapus, btnBatal;

    String url_hapus="http://192.168.1.5/DeleteTiket.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_tiket);
        getSupportActionBar().hide();

        btnBatal = (Button)findViewById(R.id.btnBatal);

        btnHapus = (Button)findViewById(R.id.btnHapus);
        RequestQueue queue = Volley.newRequestQueue(DeleteTiket.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_hapus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray member = jObj.getJSONArray(TAG_DATA);
                    JSONObject a = member.getJSONObject(0);

                }
                catch (Exception ex) {
                    Log.e("Error", ex.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                String errorMsg = "";
                if(response != null && response.data != null){
                    String errorString = new String(response.data);
                    Log.i("log error", errorString);
                }
                Toast.makeText(DeleteTiket.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                finish();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("OrderID", orderID);
                return params;
            }
        };
        queue.add(stringRequest);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteTiket.this);

                builder.setTitle("Konfirmasi Hapus");
                builder.setMessage("Apakah Anda yakin ingin menghapus data ini ?");

                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestQueue queue = Volley.newRequestQueue(DeleteTiket.this);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_hapus, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jObj = new JSONObject(response);
                                    int sukses = jObj.getInt("success");
                                    if (sukses == 1) {
                                        Toast.makeText(DeleteTiket.this, "Data tiket berhasil dihapus", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(DeleteTiket.this, "Data tiket gagal dihapus", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception ex) {
                                    Log.e("Error", ex.toString());
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Error", error.getMessage());
                                Toast.makeText(DeleteTiket.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put("OrderID", orderID);
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
                        Intent i = new Intent(getApplicationContext(), HistoryMenu.class);
                        startActivity(i);
                    }
                });

                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}