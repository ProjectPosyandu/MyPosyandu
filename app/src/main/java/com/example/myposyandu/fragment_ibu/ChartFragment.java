package com.example.myposyandu.fragment_ibu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.myposyandu.R;
import com.example.myposyandu.SharedPrefManager;
import com.example.myposyandu.helper.ApiService;
import com.example.myposyandu.helper.UtilsApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartFragment extends Fragment {
    Context mContext;
    ApiService mApiService;
    SharedPrefManager sharedPrefManager;

    private static final String TAG = "TAG";
    List<String> usiaList = new ArrayList<>();
    List<Number> kurangList = new ArrayList<>();
    List<Number> idealBawahList = new ArrayList<>();
    List<Number> idealList = new ArrayList<>();
    List<Number> idealAtasList = new ArrayList<>();
    List<Number> lebihList = new ArrayList<>();

    AnyChartView anyChartView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_jadwal, container, false);
        anyChartView = root.findViewById(R.id.chart_bayi);

        getChartData();
        return root;
    }

    private void getChartData(){
        mContext = getActivity();
        mApiService = UtilsApi.getAPIService();
        mApiService.getDataChartBoys().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        JSONArray dataArray = jsonRESULTS.getJSONArray("data");

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            usiaList.add(dataobj.getString("usia"));
                            kurangList.add(dataobj.getDouble("kurang"));
                            idealBawahList.add(dataobj.getDouble("ideal_bawah"));
                            idealList.add(dataobj.getDouble("ideal"));
                            idealAtasList.add(dataobj.getDouble("ideal_atas"));
                            lebihList.add(dataobj.getDouble("lebih"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    entryData();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.toString());
            }
        });
 }

 private void entryData() {
        Cartesian cartesian = AnyChart.line();
        cartesian.animation(true);
        cartesian.padding(10d, 20d, 5d, 20d);
        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
              .yLabel(true)
              // TODO ystroke
              .yStroke((Stroke) null, null, null, (String) null, (String) null);
        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.title("GRAFIK PERTUMBUHAN BAYI");
        cartesian.yAxis(0).title("Berat Badan (Kg)");
        cartesian.yAxis(0).labels().padding(5d, 5d, 5d, 5d);
        cartesian.xAxis(0).title("Usia (bulan)");
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

        List<DataEntry> seriesData = new ArrayList<>();
        int i = 0;
        while (i<usiaList.size()){
            seriesData.add(new ChartFragment.CustomDataEntry(usiaList.get(i),kurangList.get(i),idealBawahList.get(i),
                idealList.get(i),idealAtasList.get(i),lebihList.get(i)));
            i++;
        }
        Set set = Set.instantiate();
        set.data(seriesData);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Mapping = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Mapping = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping series4Mapping = set.mapAs("{ x: 'x', value: 'value4' }");
        Mapping series5Mapping = set.mapAs("{ x: 'x', value: 'value5' }");

      Line series1 = cartesian.line(series1Mapping);
      series1.name("Kurang");
      series1.hovered().markers().enabled(true);
      series1.hovered().markers()
              .type(MarkerType.CIRCLE)
              .size(4d);
      series1.tooltip()
              .position("right")
              .anchor(Anchor.LEFT_CENTER)
              .offsetX(5d)
              .offsetY(5d);

     Line series2 = cartesian.line(series2Mapping);
     series2.name("Ideal Bawah");
     series2.hovered().markers().enabled(true);
     series2.hovered().markers()
             .type(MarkerType.CIRCLE)
             .size(4d);
     series2.tooltip()
             .position("right")
             .anchor(Anchor.LEFT_CENTER)
             .offsetX(5d)
             .offsetY(5d);

     Line series3 = cartesian.line(series3Mapping);
     series3.name("Ideal");
     series3.hovered().markers().enabled(true);
     series3.hovered().markers()
             .type(MarkerType.CIRCLE)
             .size(4d);
     series3.tooltip()
             .position("right")
             .anchor(Anchor.LEFT_CENTER)
             .offsetX(5d)
             .offsetY(5d);

     Line series4 = cartesian.line(series4Mapping);
     series4.name("Ideal Atas");
     series4.hovered().markers().enabled(true);
     series4.hovered().markers()
             .type(MarkerType.CIRCLE)
             .size(4d);
     series4.tooltip()
             .position("right")
             .anchor(Anchor.LEFT_CENTER)
             .offsetX(5d)
             .offsetY(5d);

     Line series5 = cartesian.line(series5Mapping);
     series5.name("Lebih");
     series5.hovered().markers().enabled(true);
     series5.hovered().markers()
             .type(MarkerType.CIRCLE)
             .size(4d);
     series5.tooltip()
             .position("right")
             .anchor(Anchor.LEFT_CENTER)
             .offsetX(5d)
             .offsetY(5d);

      cartesian.legend().enabled(true);
      cartesian.legend().fontSize(13d);
      cartesian.legend().padding(0d, 0d, 10d, 0d);

      anyChartView.setChart(cartesian);
 }

 public class CustomDataEntry extends ValueDataEntry {
      CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4, Number value5) {
       super(x, value);
       setValue("value2", value2);
       setValue("value3", value3);
       setValue("value4", value4);
       setValue("value5", value5);
  }
 }

}
