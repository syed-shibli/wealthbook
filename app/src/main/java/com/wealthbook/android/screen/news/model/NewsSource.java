package com.wealthbook.android.screen.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsSource implements Parcelable{
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    public NewsSource() {
    }

    protected NewsSource(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
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
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsSource> CREATOR = new Creator<NewsSource>() {
        @Override
        public NewsSource createFromParcel(Parcel in) {
            return new NewsSource(in);
        }

        @Override
        public NewsSource[] newArray(int size) {
            return new NewsSource[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
