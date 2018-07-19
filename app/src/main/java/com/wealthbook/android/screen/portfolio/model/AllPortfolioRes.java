package com.wealthbook.android.screen.portfolio.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllPortfolioRes implements Parcelable {
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

    protected AllPortfolioRes(Parcel in) {
        if (in.readByte() == 0) {
            eod = null;
        } else {
            eod = in.readInt();
        }
        if (in.readByte() == 0) {
            totalPrice = null;
        } else {
            totalPrice = in.readInt();
        }
        formatedTotalPrice = in.readString();
        if (in.readByte() == 0) {
            gainLoss = null;
        } else {
            gainLoss = in.readInt();
        }
        formatedGainLoss = in.readString();
        if (in.readByte() == 0) {
            gainLossPercent = null;
        } else {
            gainLossPercent = in.readDouble();
        }
        formatedGainLossPercent = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (eod == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(eod);
        }
        if (totalPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalPrice);
        }
        dest.writeString(formatedTotalPrice);
        if (gainLoss == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(gainLoss);
        }
        dest.writeString(formatedGainLoss);
        if (gainLossPercent == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(gainLossPercent);
        }
        dest.writeString(formatedGainLossPercent);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AllPortfolioRes> CREATOR = new Creator<AllPortfolioRes>() {
        @Override
        public AllPortfolioRes createFromParcel(Parcel in) {
            return new AllPortfolioRes(in);
        }

        @Override
        public AllPortfolioRes[] newArray(int size) {
            return new AllPortfolioRes[size];
        }
    };

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
