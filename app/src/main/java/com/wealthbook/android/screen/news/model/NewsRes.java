package com.wealthbook.android.screen.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsRes implements Parcelable {

    @SerializedName("news")
    private List<NewsItem> newsList;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("category")
    private String category;

    public NewsRes() {
    }

    protected NewsRes(Parcel in) {
        newsList = in.createTypedArrayList(NewsItem.CREATOR);
        totalPages = in.readInt();
        category = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(newsList);
        dest.writeInt(totalPages);
        dest.writeString(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsRes> CREATOR = new Creator<NewsRes>() {
        @Override
        public NewsRes createFromParcel(Parcel in) {
            return new NewsRes(in);
        }

        @Override
        public NewsRes[] newArray(int size) {
            return new NewsRes[size];
        }
    };

    public List<NewsItem> getNewsList() {
        return newsList;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
