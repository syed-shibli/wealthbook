package com.wealthbook.android.screen.detailportfolio.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Hold implements Parcelable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("ticker_name")
    private String tickerName;
    @SerializedName("ticker_symbol")
    private String tickerSymbol;
    @SerializedName("quantity")
    private Integer quantity;
    @SerializedName("formatted_quantity")
    private String formattedQuantity;
    @SerializedName("eod_price")
    private Double eodPrice;
    @SerializedName("formated_eod_price")
    private String formatedEodPrice;
    @SerializedName("current_price")
    private Double currentPrice;
    @SerializedName("formated_current_price")
    private String formatedCurrentPrice;
    @SerializedName("purchase_price")
    private Double purchasePrice;
    @SerializedName("formated_purchase_price")
    private String formatedPurchasePrice;
    @SerializedName("gain_loss_percent")
    private Double gainLossPercent;
    @SerializedName("formated_gain_loss_percent")
    private String formatedGainLossPercent;
    @SerializedName("gain_loss")
    private Double gainLoss;
    @SerializedName("ticker_weight_percent")
    private Double tickerWeightPercent;
    @SerializedName("formated_ticker_weight_percent")
    private String formatedTickerWeightPercent;
    @SerializedName("category")
    private String category;

    protected Hold(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        tickerName = in.readString();
        tickerSymbol = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        formattedQuantity = in.readString();
        if (in.readByte() == 0) {
            eodPrice = null;
        } else {
            eodPrice = in.readDouble();
        }
        formatedEodPrice = in.readString();
        if (in.readByte() == 0) {
            currentPrice = null;
        } else {
            currentPrice = in.readDouble();
        }
        formatedCurrentPrice = in.readString();
        if (in.readByte() == 0) {
            purchasePrice = null;
        } else {
            purchasePrice = in.readDouble();
        }
        formatedPurchasePrice = in.readString();
        if (in.readByte() == 0) {
            gainLossPercent = null;
        } else {
            gainLossPercent = in.readDouble();
        }
        formatedGainLossPercent = in.readString();
        if (in.readByte() == 0) {
            gainLoss = null;
        } else {
            gainLoss = in.readDouble();
        }
        if (in.readByte() == 0) {
            tickerWeightPercent = null;
        } else {
            tickerWeightPercent = in.readDouble();
        }
        formatedTickerWeightPercent = in.readString();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(tickerName);
        dest.writeString(tickerSymbol);
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        dest.writeString(formattedQuantity);
        if (eodPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(eodPrice);
        }
        dest.writeString(formatedEodPrice);
        if (currentPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(currentPrice);
        }
        dest.writeString(formatedCurrentPrice);
        if (purchasePrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(purchasePrice);
        }
        dest.writeString(formatedPurchasePrice);
        if (gainLossPercent == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(gainLossPercent);
        }
        dest.writeString(formatedGainLossPercent);
        if (gainLoss == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(gainLoss);
        }
        if (tickerWeightPercent == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(tickerWeightPercent);
        }
        dest.writeString(formatedTickerWeightPercent);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hold> CREATOR = new Creator<Hold>() {
        @Override
        public Hold createFromParcel(Parcel in) {
            return new Hold(in);
        }

        @Override
        public Hold[] newArray(int size) {
            return new Hold[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getTickerName() {
        return tickerName;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getFormattedQuantity() {
        return formattedQuantity;
    }

    public Double getEodPrice() {
        return eodPrice;
    }

    public String getFormatedEodPrice() {
        return formatedEodPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public String getFormatedCurrentPrice() {
        return formatedCurrentPrice;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public String getFormatedPurchasePrice() {
        return formatedPurchasePrice;
    }

    public Double getGainLossPercent() {
        return gainLossPercent;
    }

    public String getFormatedGainLossPercent() {
        return formatedGainLossPercent;
    }

    public Double getGainLoss() {
        return gainLoss;
    }

    public Double getTickerWeightPercent() {
        return tickerWeightPercent;
    }

    public String getFormatedTickerWeightPercent() {
        return formatedTickerWeightPercent;
    }

    public String getCategory() {
        return category;
    }
}
