package com.wealthbook.android.notification;

import com.google.gson.annotations.SerializedName;

public class PushDataPayload {
    @SerializedName("notification")
    private Notification notification;

    public Notification getNotification() {
        return notification;
    }
}
