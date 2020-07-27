package com.example.myposyandu.helper;


public class UtilsApi {
    // 10.0.2.2 ini adalah localhost.
    // bisa juga di masukan dengan IP address kalian
<<<<<<< HEAD
//    public static final String BASE_URL_API = "http://192.168.1.10/Api_Android/";
//    public static final String BASE_URL_API = "http://192.168.43.216/Api_Android/";
    public static final String BASE_URL_API = "http://192.168.43.36/Api_Android/";
=======
//    public static final String BASE_URL_API = "http://192.168.1.4/Api_Android/";
    public static final String BASE_URL_API = "http://192.168.43.216/Api_Android/";
>>>>>>> 04e737f2d59688f63e7b846d358f45dba5b88994

    // Mendeklarasikan Interface BaseApiService
    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiService.class);

    }
}
