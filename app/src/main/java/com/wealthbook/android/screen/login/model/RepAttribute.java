package com.wealthbook.android.screen.login.model;

import com.google.gson.annotations.SerializedName;

public class RepAttribute {

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
