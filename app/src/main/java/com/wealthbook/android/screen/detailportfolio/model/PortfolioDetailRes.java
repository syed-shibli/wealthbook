package com.wealthbook.android.screen.detailportfolio.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PortfolioDetailRes implements Parcelable{
    @SerializedName("data")
    private List<PortfolioDataItem> data = null;

    @SerializedName("eod")
    private Integer eod;

    @SerializedName("name")
    private String name;

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

    protected PortfolioDetailRes(Parcel in) {
        data = in.createTypedArrayList(PortfolioDataItem.CREATOR);
        if (in.readByte() == 0) {
            eod = null;
        } else {
            eod = in.readInt();
        }
        name = in.readString();
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
        dest.writeTypedList(data);
        if (eod == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(eod);
        }
        dest.writeString(name);
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

    public static final Creator<PortfolioDetailRes> CREATOR = new Creator<PortfolioDetailRes>() {
        @Override
        public PortfolioDetailRes createFromParcel(Parcel in) {
            return new PortfolioDetailRes(in);
        }

        @Override
        public PortfolioDetailRes[] newArray(int size) {
            return new PortfolioDetailRes[size];
        }
    };

    public List<PortfolioDataItem> getData() {
        return data;
    }

    public Integer getEod() {
        return eod;
    }

    public String getName() {
        return name;
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
