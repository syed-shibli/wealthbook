package com.wealthbook.android.screen.pendingalert.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PendingAlertItem {
    @SerializedName("pending_alert_id")
    private Integer pendingAlertId;

    @SerializedName("attribute")
    private List<PendingAlertAttribute> attribute = null;

    public Integer getPendingAlertId() {
        return pendingAlertId;
    }

    public List<PendingAlertAttribute> getAttribute() {
        return attribute;
    }
}
