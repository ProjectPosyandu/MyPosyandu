package com.example.myposyandu;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.ChartResponse;
import com.example.myposyandu.helper.RetrofitClient;
import com.example.myposyandu.helper.Sales;
import com.example.myposyandu.helper.UtilsApi;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
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


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    float barSpace = 0.02f;
    float groupSpace = 0.3f;
    List<Sales> salesList = new ArrayList<>();
    List<String> products = new ArrayList<>();
    List<String> monthsList = new ArrayList<>();
    BarChart barChart;
    LineChart lineChart;
//    Button switchChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        barChart = findViewById(R.id.any_chart_view);
        lineChart = findViewById(R.id.any_chart_view);
        getChartData();
//        switchChart = findViewById(R.id.tampilChart);
//        switchChart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getChartData(switchChart.getText().toString());
//            }
//        });



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
//                if (type.equals("Show Sales/Product")){
//                    setBarChart(products,monthsList,"product");
//                    switchChart.setText("Show Sales/Month");
                    setLineChart(products,monthsList,"product");
//                    switchChart.setText("Show Sales/Month");
//                }else if (type.equals("Show Sales/Month")){
//                    setBarChart(monthsList,products,"month");
//                    switchChart.setText("Show Sales/Product");
//                    setLineChart(monthsList,products,"month");
//                    switchChart.setText("Show Sales/Product");
//                }
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
//        for (int i=0; i<dataSets.size();i++){
//            LineDataSet barDataSet = new LineDataSet(lineEntries(dataSets.get(i),groupList,type),dataSets.get(i));
//            barDataSet.setColor(MyColor[i]);
//            barDataSet.setValueTextSize(6f);
//            arrayList.add(barDataSet);
//        }

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

//        float defaultBarWidth;
//        int groupCount = groupList.size();
//
//        defaultBarWidth = (1 - groupSpace)/arrayList.size() - barSpace;
//        if (defaultBarWidth >= 0){
//            barData.setBarWidth(defaultBarWidth);
//        }
//
//        if (groupCount != -1){
//            barChart.getXAxis().setAxisMinimum(0);
//            barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupList.size());
//            barChart.getXAxis().setCenterAxisLabels(true);
//        }
//
//        if (barData.getDataSetCount() > 1){
//            barChart.groupBars(0, groupSpace, barSpace);
//        }
//
//        barChart.invalidate();


    }
//
//    private ArrayList<Entry> lineEntries(String groupName, List<String> groupList, String type) {
//        ArrayList<Entry> arrayList = new ArrayList<>();
//        int i = 0;
//        while (i<groupList.size()){
//            for (Sales s: salesList){
//                if (type.equals("month")){
//                    if (s.getMonth().equals(groupName)){
//                        BarEntry barEntry = new BarEntry(i, s.getSales());
//                        arrayList.add(barEntry);
//                        i++;
//                    }
//                }else if (type.equals("product")){
//                    if (s.getProduct().equals(groupName)){
//                        BarEntry barEntry = new BarEntry(i, s.getSales());
//                        arrayList.add(barEntry);
//                        i++;
//                    }
//                }
//            }
//        }
//        return arrayList;
//    }
//
//
//
//    private void setBarChart(List<String> dataSets, List<String> groupList, String type) {
//        int[] MyColor = {
//                getResources().getColor(R.color.bar1),
//                getResources().getColor(R.color.bar2),
//                getResources().getColor(R.color.bar3),
//                getResources().getColor(R.color.bar4),
//                getResources().getColor(R.color.bar5),
//                getResources().getColor(R.color.bar6),
//                getResources().getColor(R.color.bar7),
//                getResources().getColor(R.color.bar8),
//                getResources().getColor(R.color.bar9),
//                getResources().getColor(R.color.bar10),
//                getResources().getColor(R.color.bar11),
//                getResources().getColor(R.color.bar12),
//        };
//
//        ArrayList<BarDataSet> arrayList = new ArrayList<>();
//        for (int i=0; i<dataSets.size();i++){
//            BarDataSet barDataSet = new BarDataSet(barEntries(dataSets.get(i),groupList,type),dataSets.get(i));
//            barDataSet.setColor(MyColor[i]);
//            barDataSet.setValueTextSize(6f);
//            arrayList.add(barDataSet);
//        }
//
//        BarData barData = new BarData();
//        for (int i=0; i<arrayList.size(); i++){
//            barData.addDataSet(arrayList.get(i));
//        }
//
//        barData.setValueTextSize(7f);
//            barChart.setData(barData);
//            barChart.setPinchZoom(true);
//            barChart.setDrawGridBackground(false);
//            XAxis xAxis = barChart.getXAxis();
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(groupList));
//            xAxis.setPosition(XAxis.XAxisPosition.TOP);
//            xAxis.setGranularity(1);
//            xAxis.setGranularityEnabled(true);
//            xAxis.setDrawGridLines(true);
//
//            barData.setBarWidth(.02f);
//
//        Legend legend = barChart.getLegend();
//        legend.setTextSize(10);
//        legend.setWordWrapEnabled(true);
//
//        barChart.setVisibleXRangeMaximum(2);
//        barChart.getXAxis().setAxisMinimum(0);
//
//        float defaultBarWidth;
//        int groupCount = groupList.size();
//
//        defaultBarWidth = (1 - groupSpace)/arrayList.size() - barSpace;
//        if (defaultBarWidth >= 0){
//            barData.setBarWidth(defaultBarWidth);
//        }
//
//        if (groupCount != -1){
//            barChart.getXAxis().setAxisMinimum(0);
//            barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupList.size());
//            barChart.getXAxis().setCenterAxisLabels(true);
//        }
//
//        if (barData.getDataSetCount() > 1){
//            barChart.groupBars(0, groupSpace, barSpace);
//        }
//
//        barChart.invalidate();
//
//
//    }
//
//    private List<BarEntry> barEntries(String groupName, List<String> groupList, String type) {
//        ArrayList<BarEntry> arrayList = new ArrayList<>();
//        int i = 0;
//        while (i<groupList.size()){
//            for (Sales s: salesList){
//                if (type.equals("month")){
//                    if (s.getMonth().equals(groupName)){
//                        BarEntry barEntry = new BarEntry(i, s.getSales());
//                        arrayList.add(barEntry);
//                        i++;
//                    }
//                }else if (type.equals("product")){
//                    if (s.getProduct().equals(groupName)){
//                        BarEntry barEntry = new BarEntry(i, s.getSales());
//                        arrayList.add(barEntry);
//                        i++;
//                    }
//                }
//            }
//        }
//        return arrayList;
//    }

}
