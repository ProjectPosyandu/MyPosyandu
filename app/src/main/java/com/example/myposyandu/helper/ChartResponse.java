package com.example.myposyandu.helper;

import com.google.gson.annotations.SerializedName;

public class ChartResponse {
    @SerializedName("sales")
    private Object salesResponse = null;

    @SerializedName("products")
    private Object productsResponse = null;

    @SerializedName("months")
    private Object monthsResponse = null;

    public Object getSalesResponse() {
        return salesResponse;
    }

    public Object getProductsResponse() {
        return productsResponse;
    }

    public Object getMonthsResponse() {
        return monthsResponse;
    }
}
