package com.example.daffolapmac.wealthbook.screen.pendingalert.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingAlertRes {
    @SerializedName("pending_alerts")
    private List<PendingAlertItem> pendingAlertList;

    public List<PendingAlertItem> getPendingAlertList() {
        return pendingAlertList;
    }
}
