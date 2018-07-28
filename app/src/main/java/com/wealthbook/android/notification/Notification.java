package com.wealthbook.android.notification;

import com.google.gson.annotations.SerializedName;

public class Notification {
    @SerializedName("badge")
    private Integer badge;

    @SerializedName("alert")
    private Alert alert;

    public Integer getBadge() {
        return badge;
    }

    public Alert getAlert() {
        return alert;
    }
}
