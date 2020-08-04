package com.example.myposyandu.helper;

import com.example.myposyandu.model.ResponseModel;
import com.example.myposyandu.model.ResponseModelArtikel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    // Fungsi ini untuk memanggil API http://192.168.88.20/latihan1/login.php
    // penamaan link sesuai dengan localhost masing-masing
    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://192.168.88.20/latihan1/register.php

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("InputBayi.php")
    Call<ResponseBody> inputBayiRequest(@Field("nama_bayi") String nama_bayi,
                                        @Field("tgl_lahir") String tgl_lahir,
                                        @Field("jenis_kelamin") String jenis_kelamin,
                                        @Field("foto_bayi") String foto_bayi,
                                        @Field("id") String id,
                                        @Field("berat_bayi") String berat_bayi,
                                        @Field("tinggi_bayi") String tinggi_bayi);

    @FormUrlEncoded
    Call<ResponseBody> tampilAkunRequest(@Field("id") String id);

    @GET("getChartBoys.php")
    Call<ResponseBody> getDataChartBoys();

    @GET("getChartGirls.php")
    Call<ResponseBody> getDataChartGirls();

    @FormUrlEncoded
    @POST("tampilBayi.php")
    Call<ResponseModel> getDataBayi(@Field("id") String id);

    @FormUrlEncoded

    @POST("InputDetailBayi.php")
    Call<ResponseBody> inputDetailBayi( @Field("id_bayi") String id_bayi,
                                        @Field("usia_bayi") String usia_bayi,
                                        @Field("berat_bayi") String berat_bayi,
                                        @Field("tinggi_bayi") String tinggi_bayi);

    @GET("tampilArtikel.php")
    Call<ResponseModelArtikel> getDataArtikel();

    @POST("tampilPerBayi.php")
    Call<ResponseBody> getDataPerBayi(@Field("id") int id);
    


}
