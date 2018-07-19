package com.wealthbook.android.screen.notificationalert.model;

import com.google.gson.annotations.SerializedName;

public class LatestPortfolioReviewRes {
    @SerializedName("data")
    private LatestPortfolioReviewData data;

    @SerializedName("from")
    private String from;

    @SerializedName("to")
    private String to;

    @SerializedName("show_legend")
    private String showLegend;

    public LatestPortfolioReviewData getData() {
        return data;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getShowLegend() {
        return showLegend;
    }
}
