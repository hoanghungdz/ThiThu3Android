package com.hunghhph44272.thithuapi3.Service;

import com.hunghhph44272.thithuapi3.Model.Response;
import com.hunghhph44272.thithuapi3.Model.Sach;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    //leTai: 192.168.1.11  vietBach: 192.168.1.23  hoanghung: 172.20.10.2
    String ipv4 = "172.20.10.2";
    String DOMAIN = "http://"+ ipv4 +":3000/api/";

    @GET("get-list-sach")
    Call<Response<ArrayList<Sach>>> getSach();
    @DELETE("delete-sach-by-id/{id}")
    Call<Response<Void>> deleteSach(@Path("id") String id);

    //    @Multipart
//    @POST("add-sinhvien")
//    Call<Response<SinhVien>> addXeMayWithFileImage(@PartMap Map<String, RequestBody> requestBodyMap);
    @POST("add-sach")
    Call<Response<Sach>> addSach(@Body Sach sach);

    @PUT("update-sach-by-id/{id}")
    Call<Response<Sach>> UpdateSach(@Path("id") String id, @Body Sach sach);

    @GET("search-sach-by-name")
    Call<Response<ArrayList<Sach>>> searchSachName(@Query("name") String name);
}
