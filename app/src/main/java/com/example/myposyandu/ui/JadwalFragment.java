package com.example.myposyandu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.example.myposyandu.MainActivity;
import com.example.myposyandu.R;

import java.util.ArrayList;
import java.util.List;

public class JadwalFragment extends Fragment {

     public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_jadwal, container, false);
         AnyChartView anyChartView = root.findViewById(R.id.any_chart_view2);

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
         cartesian.xAxis(0).title("Usia (bulan)");
         cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);

         List<DataEntry> seriesData = new ArrayList<>();
         seriesData.add(new JadwalFragment.CustomDataEntry("1", 3.1));
         seriesData.add(new JadwalFragment.CustomDataEntry("2", 3.2));
         seriesData.add(new JadwalFragment.CustomDataEntry("3", 3.5));
         seriesData.add(new JadwalFragment.CustomDataEntry("4", 3.9));
         seriesData.add(new JadwalFragment.CustomDataEntry("5", 4));
         seriesData.add(new JadwalFragment.CustomDataEntry("6", 4.1));
         seriesData.add(new JadwalFragment.CustomDataEntry("7", 4.2));
         seriesData.add(new JadwalFragment.CustomDataEntry("8", 4.3));
         seriesData.add(new JadwalFragment.CustomDataEntry("9", 4.4));
         seriesData.add(new JadwalFragment.CustomDataEntry("10", 4.5));
         seriesData.add(new JadwalFragment.CustomDataEntry("11", 4.6));
         seriesData.add(new JadwalFragment.CustomDataEntry("12", 4.7));
         seriesData.add(new JadwalFragment.CustomDataEntry("13", 4.8));
         seriesData.add(new JadwalFragment.CustomDataEntry("14", 4.9));
         seriesData.add(new JadwalFragment.CustomDataEntry("15", 5));
         seriesData.add(new JadwalFragment.CustomDataEntry("16", 5.1));

         Set set = Set.instantiate();
         set.data(seriesData);
         Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");

         Line series1 = cartesian.line(series1Mapping);
         series1.hovered().markers().enabled(true);
         series1.hovered().markers()
                 .type(MarkerType.CIRCLE)
                 .size(4d);
         series1.tooltip()
                 .position("right")
                 .anchor(Anchor.LEFT_CENTER)
                 .offsetX(5d)
                 .offsetY(5d);

         cartesian.legend().enabled(false);
         cartesian.legend().fontSize(13d);
         cartesian.legend().padding(0d, 0d, 10d, 0d);

         anyChartView.setChart(cartesian);
        return root;
    }

    public class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value) {
         super(x, value);
        }
    }
}
