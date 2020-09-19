package com.example.myposyandu.helper;


public class UtilsApi {
<<<<<<< HEAD
//    public static final String BASE_URL_API = "http://192.168.1.19/Api_Android/";
//    public static final String BASE_URL_API = "http://192.168.43.216/Api_Android/";
    public static final String BASE_URL_API = "http://192.168.43.36/Api_Android/";
//    public static final String BASE_URL_API = "http://192.168.1.10/Api_Android/";
=======
//    public static final String BASE_URL_API = "http://192.168.43.216/Api_Android/";
//    public static final String BASE_URL_API = "http://192.168.43.36/Api_Android/";
    public static final String BASE_URL_API = "http://myposyandu.mwebs.id/Api_Android/";
>>>>>>> fa7c3d74c7c92c33168482bb03a2c904c9658422

    // Mendeklarasikan Interface BaseApiService
    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL_API).create(ApiService.class);

    }
}
