package com.example.daffolapmac.wealthbook.screen.login.model;

import com.google.gson.annotations.SerializedName;

public class LoginTroubleRes {

    @SerializedName("support_heading")
    private String supportHeading;

    @SerializedName("support_title")
    private String supportTitle;

    @SerializedName("support_description")
    private String supportDescription;

    @SerializedName("support_email")
    private String supportEmail;

    public String getSupportHeading() {
        return supportHeading;
    }

    public String getSupportTitle() {
        return supportTitle;
    }

    public String getSupportDescription() {
        return supportDescription;
    }

    public String getSupportEmail() {
        return supportEmail;
    }
}
