package com.example.verbosetech.cabberdrive.others;

import android.app.Application;

import com.example.verbosetech.cabberdrive.R;

public class GlobalVariables extends Application {

    private int bottom_nav_bar_layout_id= R.id.ll_home;
    private boolean is_cancelled=false;



    public int getBottom_nav_bar_layout_id() {
        return bottom_nav_bar_layout_id;
    }

    public void setBottom_nav_bar_layout_id(int bottom_nav_bar_layout_id) {
        this.bottom_nav_bar_layout_id = bottom_nav_bar_layout_id;
    }

    public boolean isIs_cancelled() {
        return is_cancelled;
    }

    public void setIs_cancelled(boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }
}
