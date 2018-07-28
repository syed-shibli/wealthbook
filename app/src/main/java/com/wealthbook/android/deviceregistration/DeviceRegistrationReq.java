package com.wealthbook.android.deviceregistration;

import com.google.gson.annotations.SerializedName;

public class DeviceRegistrationReq {

    @SerializedName("token")
    private String token;

    @SerializedName("fcm_token")
    private String fcm_token;

    @SerializedName("device_type")
    private String device_type;

    public DeviceRegistrationReq(String token, String fcm_token, String device_type) {
        this.token = token;
        this.fcm_token = fcm_token;
        this.device_type = device_type;
    }
}
