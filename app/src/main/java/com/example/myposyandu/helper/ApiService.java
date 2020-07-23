package com.example.myposyandu.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @POST("tampilAkun.php")
    Call<ResponseBody> tampilAkunRequest(@Field("id") String id);
}
