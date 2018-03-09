package com.example.daffolapmac.wealthbook.screen.login.model;

import com.google.gson.annotations.SerializedName;

class RepAttribute {

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
