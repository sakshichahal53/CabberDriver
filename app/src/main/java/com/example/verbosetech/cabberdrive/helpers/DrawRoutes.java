package com.example.verbosetech.cabberdrive.helpers;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.example.verbosetech.cabberdrive.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrawRoutes {

    private static final String TAG = "SET_MARKERS";
    private Context context;
    private Marker s_marker, d_marker;
    private GoogleMap google_map_this;
    private ArrayList markerpoints = new ArrayList<>();
    private MapDirectionsHelper directions_helper;

    private PolylineOptions lineOptions = null;
    public Polyline poly_line_var;

    public DrawRoutes(Context context) {
        this.context = context;
        directions_helper = new MapDirectionsHelper(context);
        markerpoints = new ArrayList();

    }

    public void set_markers(Marker source_marker, Marker dest_marker, GoogleMap googleMap)
    {
        s_marker=source_marker;
        d_marker=dest_marker;
        google_map_this=googleMap;

        if(s_marker!=null && d_marker!=null)
            draw_route();
        else Log.e("DrawRoutes is null","null");

    }





    private void draw_route() {

        if (poly_line_var != null)
            poly_line_var.remove();

        LatLng origin = new LatLng(s_marker.getPosition().latitude, s_marker.getPosition().longitude);
        LatLng dest = new LatLng(d_marker.getPosition().latitude, d_marker.getPosition().longitude);


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(origin);
        builder.include(dest);
        LatLngBounds bounds = builder.build();

        int padding = 2; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        google_map_this.animateCamera(cu);

        Log.e(TAG, "source:" + origin + " destination: " + dest);
        String url = directions_helper.getDirectionsUrl(origin, dest);
        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        Log.e(TAG, "Download Start");
        downloadTask.execute(url);

    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = directions_helper.downloadUrl(url[0]);
            } catch (Exception e) {
                Log.e("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            Log.e(TAG, "PArsser Executing the PArse Task");
            parserTask.execute(result);

        }
    }


    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = null;
            Log.e(TAG, "inside Post Execute of the PArser private class");
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(15);
                lineOptions.color(R.color.colorAccent);
                lineOptions.geodesic(true);

            }

            poly_line_var = google_map_this.addPolyline(lineOptions);


        }
    }

}
