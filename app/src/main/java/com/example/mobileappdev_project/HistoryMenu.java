package com.example.mobileappdev_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryMenu extends AppCompatActivity {
    ListView lv;
    ArrayList<HashMap<String,String>> list_anggota;
    String url_get_mahasiswa="http://192.168.1.5/getTiket.php";

    private static final String TAG_DATA="data";
    private static final String TAG_ORDER_ID = "OrderID";
    private static final String TAG_JENISTIKET="JenisTiket";
    private static final String TAG_JUMLAHTIKET="JumlahTiket";
    private static final String TAG_TOTAL_HARGA = "TotalHarga";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_menu);
        getSupportActionBar().hide();

        list_anggota=new ArrayList<>();
        lv=findViewById(R.id.listView);


        RequestQueue queue = Volley.newRequestQueue(HistoryMenu.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_get_mahasiswa, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray member = jObj.getJSONArray(TAG_DATA);

                    for (int i = 0; i < member.length(); i++) {
                        JSONObject a = member.getJSONObject(i);
                        String orderid = a.getString(TAG_ORDER_ID);
                        String jenistiket = a.getString(TAG_JENISTIKET);
                        String jumlahtiket = a.getString(TAG_JUMLAHTIKET);
                        String totalharga = a.getString(TAG_TOTAL_HARGA);

                        HashMap<String, String> map = new HashMap<>();
                        map.put("OrderID", orderid);
                        map.put("JenisTiket", "Jenis : "+jenistiket);
                        map.put("JumlahTiket", "Jumlah : "+jumlahtiket);
                        map.put("TotalHarga","Rp "+totalharga);

                        list_anggota.add(map);
                        String[] from = {"OrderID","JenisTiket", "JumlahTiket", "TotalHarga"};
                        int[] to = {R.id.edtOrderID,R.id.edtJenis, R.id.edtJumlahTiket, R.id.edtTotalHarga};

                        ListAdapter adapter = new SimpleAdapter(
                                HistoryMenu.this, list_anggota, R.layout.list_tiket,
                                from, to);
                        lv.setAdapter(adapter);
                        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                String nomor = list_anggota.get(position).get(TAG_ORDER_ID);
                                Intent i = new Intent(getApplicationContext(),DeleteTiket.class);
                                i.putExtra("OrderID", nomor);
                                startActivity(i);

                                return true;
                            }
                        });
                    }
                }
                catch (Exception ex) {
                    Log.e("Error", ex.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.getMessage());
                Toast.makeText(HistoryMenu.this, "silahkan cek koneksi internet anda", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        queue.add(stringRequest);

    }
}