package com.example.daffolapmac.wealthbook.screen.home.model;

import com.google.gson.annotations.SerializedName;

public class PendingAlertCountRes {
    @SerializedName("pending_alert_count")
    private int count;

    public int getCount() {
        return count;
    }
}
