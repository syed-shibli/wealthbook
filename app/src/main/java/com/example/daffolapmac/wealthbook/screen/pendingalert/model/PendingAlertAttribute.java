package com.example.daffolapmac.wealthbook.screen.pendingalert.model;

import com.google.gson.annotations.SerializedName;

public class PendingAlertAttribute {
    @SerializedName("label")
    private String label;

    @SerializedName("value")
    private String value;

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
