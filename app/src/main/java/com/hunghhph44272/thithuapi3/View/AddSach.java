package com.hunghhph44272.thithuapi3.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hunghhph44272.thithuapi3.Model.Response;
import com.hunghhph44272.thithuapi3.Model.Sach;
import com.hunghhph44272.thithuapi3.R;
import com.hunghhph44272.thithuapi3.Service.HttpRequest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class AddSach extends AppCompatActivity {

    private File file;
    EditText edtMasach, edtTieuDe, edtTacGia, edtNamxb, edtSoTrang, edtTheLoai;
    String id, ma, tieude, tacgia, namxb, theloai, sotrang;
    HttpRequest httpRequest = new HttpRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach);
        edtMasach = findViewById(R.id.edt_masach);
        edtTieuDe = findViewById(R.id.edt_tieude);
        edtTacGia = findViewById(R.id.edt_tac_gia);
        edtNamxb = findViewById(R.id.edt_namxb);
        edtSoTrang = findViewById(R.id.edt_so_trang);
        edtTheLoai = findViewById(R.id.edt_the_loai);

        getIntentMain();

        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, RequestBody> mapRequestBody = new HashMap<>();
                String _ma = edtMasach.getText().toString().trim();
                String _tieude = edtTieuDe.getText().toString().trim();
                String _tacgia = edtTacGia.getText().toString().trim();
                String _namxb = edtNamxb.getText().toString().trim();
                String _sotrang = edtSoTrang.getText().toString().trim();
                String _theloai = edtTheLoai.getText().toString().trim();
                if (_tieude.isEmpty() || _tacgia.isEmpty() || _namxb.isEmpty() || _sotrang.isEmpty() || _theloai.isEmpty()) {
                    Toast.makeText(AddSach.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

//                mapRequestBody.put("ma_sinh_vien", getRequestBody(_ma));
//                mapRequestBody.put("ten", getRequestBody(_ten));
//                mapRequestBody.put("gioi_tinh", getRequestBody(_gioitinh));
//                mapRequestBody.put("ngay_sinh", getRequestBody(_ngaysinh));
//                mapRequestBody.put("khoa_hoc", getRequestBody(_khoahoc));
//                mapRequestBody.put("lop", getRequestBody(_lop));
                Sach sach = new Sach(_ma, _tieude, _tacgia, _namxb, _sotrang, _theloai);
                if (getIntent().getStringExtra("type").equals("update")) {
                    Toast.makeText(AddSach.this, "cập nhật", Toast.LENGTH_SHORT).show();
                    httpRequest.callAPI().UpdateSach(getIntent().getStringExtra("id"), sach).enqueue(new Callback<Response<Sach>>() {
                        @Override
                        public void onResponse(Call<Response<Sach>> call, retrofit2.Response<Response<Sach>> response) {
                            Toast.makeText(AddSach.this, "oke", Toast.LENGTH_SHORT).show();
                            if (response.isSuccessful()) {
                                Log.d("123123", "onResponse: " + response.body().getStatus());
                                if (response.body().getStatus() == 200) {
                                    Toast.makeText(AddSach.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<Sach>> call, Throwable t) {

                        }
                    });
                } else {
                    httpRequest.callAPI().addSach(sach).enqueue(new Callback<Response<Sach>>() {
                        @Override
                        public void onResponse(Call<Response<Sach>> call, retrofit2.Response<Response<Sach>> response) {
                            if (response.isSuccessful()) {
                                Log.d("123123", "onResponse: " + response.body().getStatus());
                                if (response.body().getStatus() == 200) {
                                    Toast.makeText(AddSach.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<Sach>> call, Throwable t) {
                            // Handle failure
                        }
                    });
                }


            }
        });

    }

    private RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }

    private void getIntentMain() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getInt("type") == 0) {
            id = bundle.getString("id");
            ma = bundle.getString("masach");
            tieude = bundle.getString("tieude");
            tacgia = bundle.getString("tacgia");
            theloai = bundle.getString("theloai");
            namxb = bundle.getString("namxuatban");
            sotrang = bundle.getString("sotrang");
//
//            if (gioitinh.equals("Nam")) {
//                spnGioiTinh.setSelection(0);
//            } else if (gioitinh.equals("Nữ")) {
//                spnGioiTinh.setSelection(1);
//            } else {
//                spnGioiTinh.setSelection(2);
//            }
            edtMasach.setText(ma);
            edtTieuDe.setText(tieude);
            edtTacGia.setText(tacgia);
            edtNamxb.setText(namxb);
            edtSoTrang.setText(sotrang);
            edtTheLoai.setText(theloai);

        }
    }

}