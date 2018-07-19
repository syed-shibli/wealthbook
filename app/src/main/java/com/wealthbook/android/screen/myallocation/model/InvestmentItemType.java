package com.wealthbook.android.screen.myallocation.model;

import com.google.gson.annotations.SerializedName;

public class InvestmentItemType {
    @SerializedName("y")
    private Double y;

    @SerializedName("color")
    private String color;

    @SerializedName("name")
    private String name;

    public Double getY() {
        return y;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
