package com.example.myposyandu.helper;


public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    // bisa juga di masukan dengan IP address kalian
//    public static final String BASE_URL_API = "http://192.168.1.10/Api_Android/";
    public static final String BASE_URL_API = "http://192.168.43.216/Api_Android/";

    // Mendeklarasikan Interface BaseApiService
    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiService.class);

    }
}
