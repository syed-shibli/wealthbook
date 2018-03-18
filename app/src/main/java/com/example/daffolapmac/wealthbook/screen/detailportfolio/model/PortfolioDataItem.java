package com.example.daffolapmac.wealthbook.screen.detailportfolio.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PortfolioDataItem implements Parcelable{
    @SerializedName("category_id")
    private Integer categoryId;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("holds")
    private List<Hold> holds = null;

    protected PortfolioDataItem(Parcel in) {
        if (in.readByte() == 0) {
            categoryId = null;
        } else {
            categoryId = in.readInt();
        }
        categoryName = in.readString();
        holds = in.createTypedArrayList(Hold.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (categoryId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(categoryId);
        }
        dest.writeString(categoryName);
        dest.writeTypedList(holds);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PortfolioDataItem> CREATOR = new Creator<PortfolioDataItem>() {
        @Override
        public PortfolioDataItem createFromParcel(Parcel in) {
            return new PortfolioDataItem(in);
        }

        @Override
        public PortfolioDataItem[] newArray(int size) {
            return new PortfolioDataItem[size];
        }
    };

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Hold> getHolds() {
        return holds;
    }
}
