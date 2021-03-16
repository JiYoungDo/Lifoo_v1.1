package com.example.lifoo_v11.src.AlertFragment.models;

import android.graphics.drawable.Drawable;

public class AlertItem {

    String alert_imoji;
    String alert_string;
    String alert_hour;
    Drawable alert_img;

    public AlertItem(String alert_imoji, String alert_string, String alert_hour, Drawable alert_img) {
        this.alert_imoji = alert_imoji;
        this.alert_string = alert_string;
        this.alert_hour = alert_hour;
        this.alert_img = alert_img;
    }


    public String getAlert_imoji() {
        return alert_imoji;
    }

    public void setAlert_imoji(String alert_imoji) {
        this.alert_imoji = alert_imoji;
    }

    public String getAlert_string() {
        return alert_string;
    }

    public void setAlert_string(String alert_string) {
        this.alert_string = alert_string;
    }

    public String getAlert_hour() {
        return alert_hour;
    }

    public void setAlert_hour(String alert_hour) {
        this.alert_hour = alert_hour;
    }

    public Drawable getAlert_img() {
        return alert_img;
    }

    public void setAlert_img(Drawable alert_img) {
        this.alert_img = alert_img;
    }
}
