package ma.emsi.gestmachine;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MachineYearFragment extends Fragment {
    View machineYearView;
    ArrayList arrayList = new ArrayList();
    private static final String TAG = "MachineYearFragment";
    private static final String getUrl = "http://10.0.2.2:8090/machines/byYear";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        machineYearView = inflater.inflate(R.layout.machine_year_fragment, container, false);

        BarChart barChart = machineYearView.findViewById(R.id.barChart);
        loadChart(barChart);

        return machineYearView;
    }

    private void loadChart(BarChart barChart) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray stats = new JSONArray(response);
                            ArrayList labels = new ArrayList();
                            int c = 0;
                            for (int i = 0; i < stats.length(); i++) {
                                c++;
                                JSONArray yearStat = stats.getJSONArray(i);
                                arrayList.add(new BarEntry(c, Float.parseFloat(String.valueOf(yearStat.getInt(1))),
                                        String.valueOf(yearStat.getInt(0))));
                                labels.add(String.valueOf(yearStat.getInt(0)));
                            }
                            BarDataSet barDataSet = new BarDataSet(arrayList, "Les machines par annee");
                            BarData barData = new BarData(barDataSet);
                            barChart.setData(barData);
                            barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
                            XAxis bottomAxis = barChart.getXAxis();
                            bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            bottomAxis.setCenterAxisLabels(true);
                            bottomAxis.setLabelCount(labels.size());
                            barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                            barDataSet.setValueTextColor(Color.BLACK);
                            barDataSet.setValueTextSize(16f);
                            barChart.getDescription().setEnabled(false);
                            barChart.animateY(1000);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MainActivity", error.toString());
            }
        });
        queue.add(stringRequest);
    }
}
