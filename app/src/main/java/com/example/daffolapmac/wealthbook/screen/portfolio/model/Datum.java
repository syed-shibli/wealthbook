package com.example.daffolapmac.wealthbook.screen.portfolio.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable {
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

    protected Datum(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            currentPrice = null;
        } else {
            currentPrice = in.readInt();
        }
        formatedCurrentPrice = in.readString();
        if (in.readByte() == 0) {
            gainLossPercent = null;
        } else {
            gainLossPercent = in.readDouble();
        }
        formatedGainLossPercent = in.readString();
        if (in.readByte() == 0) {
            gainLoss = null;
        } else {
            gainLoss = in.readInt();
        }
        lifetimeReturn = in.readString();
        formattedLifetimeReturn = in.readString();
        netTeturn = in.readString();
        formattedNetReturn = in.readString();
        inceptionDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        if (currentPrice == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(currentPrice);
        }
        dest.writeString(formatedCurrentPrice);
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
            dest.writeInt(gainLoss);
        }
        dest.writeString(lifetimeReturn);
        dest.writeString(formattedLifetimeReturn);
        dest.writeString(netTeturn);
        dest.writeString(formattedNetReturn);
        dest.writeString(inceptionDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

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
