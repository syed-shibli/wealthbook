package com.example.daffolapmac.wealthbook.screen.portfolio.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllPortfolioRes {
    @SerializedName("data")
    private List<Datum> data = null;

    @SerializedName("eod")
    private Integer eod;

    @SerializedName("total_price")
    private Integer totalPrice;

    @SerializedName("formated_total_price")
    private String formatedTotalPrice;

    @SerializedName("gain_loss")
    private Integer gainLoss;

    @SerializedName("formated_gain_loss")
    private String formatedGainLoss;

    @SerializedName("gain_loss_percent")
    private Double gainLossPercent;

    @SerializedName("formated_gain_loss_percent")
    private String formatedGainLossPercent;

    public List<Datum> getData() {
        return data;
    }

    public Integer getEod() {
        return eod;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getFormatedTotalPrice() {
        return formatedTotalPrice;
    }

    public Integer getGainLoss() {
        return gainLoss;
    }

    public String getFormatedGainLoss() {
        return formatedGainLoss;
    }

    public Double getGainLossPercent() {
        return gainLossPercent;
    }

    public String getFormatedGainLossPercent() {
        return formatedGainLossPercent;
    }
}
