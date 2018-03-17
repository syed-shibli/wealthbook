package com.example.daffolapmac.wealthbook.screen.portfolio.model;

import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("current_price")
    private Integer currentPrice;

    @SerializedName("formated_current_price")
    private String formatedCurrentPrice;

    @SerializedName("gain_loss_percent")
    private Double gainLossPercent;

    @SerializedName("formated_gain_loss_percent")
    private String formatedGainLossPercent;

    @SerializedName("gain_loss")
    private Integer gainLoss;

    @SerializedName("lifetime_return")
    private String lifetimeReturn;

    @SerializedName("formatted_lifetime_return")
    private String formattedLifetimeReturn;

    @SerializedName("net_teturn")
    private String netTeturn;

    @SerializedName("formatted_net_return")
    private String formattedNetReturn;

    @SerializedName("inception_date")
    private String inceptionDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentPrice() {
        return currentPrice;
    }

    public String getFormatedCurrentPrice() {
        return formatedCurrentPrice;
    }

    public Double getGainLossPercent() {
        return gainLossPercent;
    }

    public String getFormatedGainLossPercent() {
        return formatedGainLossPercent;
    }

    public Integer getGainLoss() {
        return gainLoss;
    }

    public String getLifetimeReturn() {
        return lifetimeReturn;
    }

    public String getFormattedLifetimeReturn() {
        return formattedLifetimeReturn;
    }

    public String getNetTeturn() {
        return netTeturn;
    }

    public String getFormattedNetReturn() {
        return formattedNetReturn;
    }

    public String getInceptionDate() {
        return inceptionDate;
    }
}
