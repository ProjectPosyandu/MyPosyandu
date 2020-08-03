package com.example.myposyandu.fragment_ibu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.myposyandu.R;
import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.ChartResponse;
import com.example.myposyandu.helper.RetrofitClient;
import com.example.myposyandu.helper.Sales;
import com.example.myposyandu.helper.UtilsApi;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ArtikelFragment extends Fragment {

    private static final String TAG = "TAG";
    List<Sales> salesList = new ArrayList<>();
    List<String> products = new ArrayList<>();
    List<String> monthsList = new ArrayList<>();
    LineChart lineChart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_artikel, container, false);

        lineChart = root.findViewById(R.id.any_chart_view3);
        getChartData();

        return root;
    }

    private void getChartData(){
        ApiService apiService = RetrofitClient.getClient(UtilsApi.BASE_URL_API).create(ApiService.class);
        Call<ChartResponse> call = apiService.getResponse();
        call.enqueue(new Callback<ChartResponse>() {
            @Override
            public void onResponse(Call<ChartResponse> call, Response<ChartResponse> response) {

                Gson gson = new GsonBuilder().create();
                TypeToken<List<Sales>> typeTokenSales = new TypeToken<List<Sales>>(){};
                TypeToken<List<String>> typeTokenProductOnMonths = new TypeToken<List<String>>(){};

                salesList.clear();
                salesList.addAll(gson.fromJson(gson.toJson(response.body().getSalesResponse()),typeTokenSales.getType()));
                products = gson.fromJson(gson.toJson(response.body().getProductsResponse()),typeTokenProductOnMonths.getType());
                monthsList = gson.fromJson(gson.toJson(response.body().getMonthsResponse()),typeTokenProductOnMonths.getType());

                Log.d(TAG, "onResponse: "+gson.toJson(monthsList));
                setLineChart(products,monthsList,"product");
            }

            @Override
            public void onFailure(Call<ChartResponse> call, Throwable t) {

            }
        });

    }

    private void setLineChart(List<String> dataSets, List<String> groupList, String type) {
        int[] MyColor = {
                getResources().getColor(R.color.bar1),
                getResources().getColor(R.color.bar2),
                getResources().getColor(R.color.bar3),
                getResources().getColor(R.color.bar4),
                getResources().getColor(R.color.bar5),
                getResources().getColor(R.color.bar6),
                getResources().getColor(R.color.bar7),
                getResources().getColor(R.color.bar8),
                getResources().getColor(R.color.bar9),
                getResources().getColor(R.color.bar10),
                getResources().getColor(R.color.bar11),
                getResources().getColor(R.color.bar12),
        };

        ArrayList<LineDataSet> arrayList = new ArrayList<>();
        for (int i=0; i<dataSets.size();i++){
            LineDataSet barDataSet = new LineDataSet(lineEntries(dataSets.get(i),groupList,type),dataSets.get(i));
            barDataSet.setColor(MyColor[i]);
            barDataSet.setValueTextSize(6f);
            arrayList.add(barDataSet);
        }

        LineData barData = new LineData();
        for (int i=0; i<arrayList.size(); i++){
            barData.addDataSet(arrayList.get(i));
        }

        barData.setValueTextSize(7f);
        lineChart.setData(barData);
        lineChart.setPinchZoom(true);
        lineChart.setDrawGridBackground(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(groupList));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(true);

        Legend legend = lineChart.getLegend();
        legend.setTextSize(10);
        legend.setWordWrapEnabled(true);

        lineChart.setVisibleXRangeMaximum(2);
        lineChart.getXAxis().setAxisMinimum(0);



    }

    private ArrayList<Entry> lineEntries(String groupName, List<String> groupList, String type) {
        ArrayList<Entry> arrayList = new ArrayList<>();
        int i = 0;
        while (i<groupList.size()){
            for (Sales s: salesList){
                if (type.equals("month")){
                    if (s.getMonth().equals(groupName)){
                        BarEntry barEntry = new BarEntry(i, s.getSales());
                        arrayList.add(barEntry);
                        i++;
                    }
                }else if (type.equals("product")){
                    if (s.getProduct().equals(groupName)){
                        BarEntry barEntry = new BarEntry(i, s.getSales());
                        arrayList.add(barEntry);
                        i++;
                    }
                }
            }
        }
        return arrayList;
    }

}
