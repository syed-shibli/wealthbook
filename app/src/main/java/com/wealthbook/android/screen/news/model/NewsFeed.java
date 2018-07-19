package com.wealthbook.android.screen.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsFeed implements Parcelable {
    @SerializedName("id")
    private Integer id;

    @SerializedName("url")
    private String url;

    public NewsFeed() {
    }

    protected NewsFeed(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsFeed> CREATOR = new Creator<NewsFeed>() {
        @Override
        public NewsFeed createFromParcel(Parcel in) {
            return new NewsFeed(in);
        }

        @Override
        public NewsFeed[] newArray(int size) {
            return new NewsFeed[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
