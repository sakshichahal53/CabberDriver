package com.example.verbosetech.cabberdrive.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.verbosetech.cabberdrive.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

public class EarningsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnings);


        Toolbar toolbar=findViewById(R.id.toolbar_earnings);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 120),
                new DataPoint(2, 140),
                new DataPoint(3, 80),
                new DataPoint(4, 170),
                new DataPoint(5, 220),
                new DataPoint(6,40),
                new DataPoint(7,140)
        });


        double xInterval=1.0;
        graph.getViewport().setXAxisBoundsManual(true);
        if (series instanceof BarGraphSeries ) {
            // Shunt the viewport, per v3.1.3 to show the full width of the first and last bars.
            graph.getViewport().setMinX(series.getLowestValueX() - (xInterval/2.0));
            graph.getViewport().setMaxX(series.getHighestValueX() + (xInterval/2.0));
        } else {
            graph.getViewport().setMinX(series.getLowestValueX() );
            graph.getViewport().setMaxX(series.getHighestValueX());        //frst and last bar same width
        }

        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
        //  graph.getGridLabelRenderer().setVerticalLabelsColor(R.color.white);
       // graph.getGridLabelRenderer().setHorizontalLabelsColor(R.color.white);
        gridLabelRenderer.setGridColor(R.color.graph_uncliked);
        gridLabelRenderer.setGridStyle( GridLabelRenderer.GridStyle.HORIZONTAL);
        gridLabelRenderer.reloadStyles();

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"M", "TU", "W","TH","F","SA","SU"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        graph.getGridLabelRenderer().setNumHorizontalLabels(7);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return getResources().getColor(R.color.graph_uncliked);
            }
        });

        series.setSpacing(10);
        graph.addSeries(series);


        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                //
            }
        });
    }


}
