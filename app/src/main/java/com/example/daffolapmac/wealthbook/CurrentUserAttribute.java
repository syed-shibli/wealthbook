package com.example.daffolapmac.wealthbook;

import com.google.gson.annotations.SerializedName;

class CurrentUserAttribute {

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
