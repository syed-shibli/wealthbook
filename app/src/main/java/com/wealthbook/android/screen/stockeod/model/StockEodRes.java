package com.wealthbook.android.screen.stockeod.model;

import com.google.gson.annotations.SerializedName;

public class StockEodRes {
    @SerializedName("id")
    private int id;

    @SerializedName("ticker_symbol")
    private String tickerSymbol;

    @SerializedName("price")
    private double price;

    @SerializedName("date")
    private String date;

    public int getId() {
        return id;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }
}
