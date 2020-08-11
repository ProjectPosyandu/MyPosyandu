package com.example.myposyandu.helper;


public class UtilsApi {
//    public static final String BASE_URL_API = "http://192.168.1.10/Api_Android/";
    public static final String BASE_URL_API = "http://192.168.43.216/Api_Android/";
//    public static final String BASE_URL_API = "http://192.168.1.16/Api_Android/";
//    public static final String BASE_URL_API = "http://192.168.1.4/Api_Android/";

    // Mendeklarasikan Interface BaseApiService
    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiService.class);

    }
}
