package com.wealthbook.android.notification;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Alert {
    @SerializedName("loc-args")
    private List<String> locArgs = null;
    @SerializedName("notification_type")
    private String notificationType;
    @SerializedName("loc-key")
    private String locKey;

    public List<String> getLocArgs() {
        return locArgs;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public String getLocKey() {
        return locKey;
    }
}
