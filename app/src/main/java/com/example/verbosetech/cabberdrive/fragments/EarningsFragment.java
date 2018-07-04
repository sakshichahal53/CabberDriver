package com.example.verbosetech.cabberdrive.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.verbosetech.cabberdrive.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

public class EarningsFragment extends android.support.v4.app.Fragment{
    public EarningsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_earnings,container,false);
        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GraphView graph = (GraphView) view.findViewById(R.id.graph);

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

