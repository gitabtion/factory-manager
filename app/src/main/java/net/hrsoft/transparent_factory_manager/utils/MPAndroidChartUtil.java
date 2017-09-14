package net.hrsoft.transparent_factory_manager.utils;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import net.hrsoft.transparent_factory_manager.R;

import java.util.ArrayList;

/**
 * @author abtion.
 * @since 17/9/12 19:25.
 * email caiheng@hrsoft.net
 */

public class MPAndroidChartUtil {
    public static void setPieChart(PieChart pieChart, float data, String title) {
        ArrayList<Integer> colors = new ArrayList<>();
        ArrayList<PieEntry> entries = new ArrayList<>();
        if (data>1){
            data = 1;
            entries.add(new PieEntry(data));
        }else if (data<=0){
            data = 0;
            entries.add(new PieEntry(data));
            entries.add(new PieEntry(1 - data));
        }else {
            entries.add(new PieEntry(data));
            entries.add(new PieEntry(1 - data));
        }
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(Color.WHITE);
        pieChart.getLegend().setEnabled(false);
        colors.add(0xFF1CC9CC);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);
        pieChart.setCenterText(title);
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        colors.add(0xFFDEDEDE);
        PieDataSet pieDataSet = new PieDataSet(entries, "已完成");
        pieDataSet.setColors(colors);
        pieChart.getDescription().setEnabled(false);
        pieDataSet.setDrawIcons(false);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
    }

    public static void setLineChart(LineChart lineChart,ArrayList<Entry> values) {
        LineDataSet dataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
        dataSet.setColor(0xFF1CC9CC);
        dataSet.enableDashedLine(10f, 5f, 0f);
        LineData data = new LineData(dataSet);
        lineChart.setData(data);

        ArrayList<String> xValues = new ArrayList<>();
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                return "";
            }
        });

    }
}
