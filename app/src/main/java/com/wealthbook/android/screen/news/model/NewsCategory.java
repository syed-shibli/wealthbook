package com.wealthbook.android.screen.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewsCategory implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public NewsCategory() {
    }

    protected NewsCategory(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsCategory> CREATOR = new Creator<NewsCategory>() {
        @Override
        public NewsCategory createFromParcel(Parcel in) {
            return new NewsCategory(in);
        }

        @Override
        public NewsCategory[] newArray(int size) {
            return new NewsCategory[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
