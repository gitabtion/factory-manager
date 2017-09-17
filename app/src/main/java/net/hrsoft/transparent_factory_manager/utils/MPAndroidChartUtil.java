package net.hrsoft.transparent_factory_manager.utils;

import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.order.models.GetProcedureDataResponse;
import net.hrsoft.transparent_factory_manager.order.models.ProcedureDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
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
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
//        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
    }


    /**
     * 初始化图表
     */
    public static void setLineChart(GetProcedureDataResponse response, LineChart lineChart) {
        if (!response.getLogs().isEmpty()) {
            Map<Long, Integer> chartModelMap = formatValue(response.getLogs()); //获取chart的model
            int xSumCount = 0;//x轴的计数分布
            int maxValue = 0;//y轴最大值(在数据项为1时使用)
            final List<String> xValue = new ArrayList<>();//x轴值
            List<Entry> lineEntries = new ArrayList<>();
            XAxis xAxis = lineChart.getXAxis();
            YAxis yAxis = lineChart.getAxisLeft();
            for (Object o : chartModelMap.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                Long key = (Long) entry.getKey();
                Integer value = (Integer) entry.getValue();
                lineEntries.add(new Entry(xSumCount++, value));
                xValue.add(TimeUtil.setStampToString(key, TimeUtil.TIME_DEFAULT_MONTH));
                if (value > maxValue) {
                    maxValue = value;
                    if (chartModelMap.size() == 1)
                        yAxis.setAxisMaximum(maxValue * 2);
                }
            }
            LineDataSet dataSet = new LineDataSet(lineEntries, "产能进度");
            dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            dataSet.setColors(Color.parseColor("#1CC9CC"));
            lineChart.getLegend().setEnabled(false);
            LineData lineData = new LineData(dataSet);
            lineData.setValueTextSize(12);

            yAxis.setAxisMinimum(0f);
            xAxis.setAxisMinimum(-1f);
            xAxis.setAxisMaximum(chartModelMap.size() + 1);
//            xAxis.setLabelCount(chartModelMap.size() + 1);
            xAxis.setDrawGridLines(false);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的位置
            IAxisValueFormatter formatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    for (int i = 0; i < xValue.size(); i++) {
                        if (value == i) {
                            return xValue.get(i);
                        }
                    }
                    return "";
                }
            };
            xAxis.setValueFormatter(formatter);
            xAxis.setEnabled(true);
            lineChart.setData(lineData);//装载数据
            lineChart.setScaleXEnabled(true);
            lineChart.getAxisRight().setEnabled(false);
            lineChart.setDescription(null);
            lineChart.animateX(1000);
            lineChart.animateY(1000);
            lineChart.invalidate();//刷新
        }
    }

    /**
     * 格式化得到的数据源
     */
    private static Map<Long, Integer> formatValue(ArrayList<ProcedureDataModel> numModels) {
        //使用treeMap，使得Key值升序
        Map<Long, Integer> modelMap = new TreeMap<>();
        for (ProcedureDataModel model : numModels) {
            long timesTamp = TimeUtil.setStringToStamp(model.getUpdatedAt(), TimeUtil.TIME_DEFAULT_MONTH);
            if (modelMap.get(timesTamp) != null) {
                modelMap.put(timesTamp, model.getSuccessCount() + modelMap.get(timesTamp));
            } else {
                modelMap.put(timesTamp, model.getSuccessCount());
            }
        }
        return modelMap;
    }
}
