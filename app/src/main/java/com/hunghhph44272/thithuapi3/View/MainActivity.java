package com.hunghhph44272.thithuapi3.View;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hunghhph44272.thithuapi3.Adapter.AdapterSach;
import com.hunghhph44272.thithuapi3.Model.Response;
import com.hunghhph44272.thithuapi3.Model.Sach;
import com.hunghhph44272.thithuapi3.R;
import com.hunghhph44272.thithuapi3.Service.HttpRequest;
import com.hunghhph44272.thithuapi3.Service.OnClick;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    ArrayList<Sach> list = new ArrayList<>();
    RecyclerView recyclerView;
    Toolbar toolbar;
    HttpRequest httpRequest = new HttpRequest();
    AdapterSach adapter;
    ArrayList<Sach> listSeacrch = new ArrayList<>();
    SearchView searchView;
    EditText edt_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rcv);
//        searchView = findViewById(R.id.search_view);
        edt_search = findViewById(R.id.edt_search);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HOME");
        getSupportActionBar().setSubtitle("Quản lý Sách");

        handleCallData();

        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ( i == EditorInfo.IME_ACTION_SEARCH) {
                    String key = edt_search.getText().toString().trim();
                    filterList(key);
                    return true;
                }
                return false;
            }
        });
    }
    private void filterList(String text) {
        if (!text.equals("")) {
            listSeacrch.clear();
            httpRequest.callAPI().searchSachName(text).enqueue(new Callback<Response<ArrayList<Sach>>>() {
                @Override
                public void onResponse(Call<Response<ArrayList<Sach>>> call, retrofit2.Response<Response<ArrayList<Sach>>> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {
                            listSeacrch = response.body().getData();
                            getData(listSeacrch);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Response<ArrayList<Sach>>> call, Throwable t) {

                }
            });
        } else {
            handleCallData();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_them) {
            Intent intent = new Intent(MainActivity.this, AddSach.class);
            intent.putExtra("type","");
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void handleCallData() {
        httpRequest.callAPI().getSach().enqueue(new Callback<Response<ArrayList<Sach>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<Sach>>> call, retrofit2.Response<Response<ArrayList<Sach>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        list = response.body().getData();
                        getData(list);
                        Log.d(TAG, "onResponse: " + list);
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<ArrayList<Sach>>> call, Throwable t) {

            }
        });
    }
    private void getData(ArrayList<Sach> listSach) {
        Toast.makeText(this, "oke", Toast.LENGTH_SHORT).show();
        adapter = new AdapterSach(MainActivity.this, listSach);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnClick(new OnClick() {
            @Override
            public void update(Sach sach) {
                Intent intent = new Intent(MainActivity.this, AddSach.class);
                intent.putExtra("type","update");
                intent.putExtra("id",sach.get_id());
                startActivity(intent);
            }

            @Override
            public void delete(Sach sach) {
                deleteSinhVien(sach);
            }
        });
    }

    @Override
    protected void onResume() {
        handleCallData();
        super.onResume();
    }
    private void deleteSinhVien(Sach sach){
        Toast.makeText(this, ""+ sach.get_id(), Toast.LENGTH_SHORT).show();
        httpRequest.callAPI().deleteSach(sach.get_id()).enqueue(new Callback<Response<Void>>() {
            @Override
            public void onResponse(Call<Response<Void>> call, retrofit2.Response<Response<Void>> response) {
                if (response.isSuccessful()) {
                    Log.d("123123", "onResponse: " + response.body().getStatus());
                    if (response.body().getStatus() == 200) {
                        Toast.makeText(MainActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        handleCallData();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<Void>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}