package com.example.daffolapmac.wealthbook.screen.news.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsPublishAt implements Parcelable{
    public NewsPublishAt() {
    }

    protected NewsPublishAt(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsPublishAt> CREATOR = new Creator<NewsPublishAt>() {
        @Override
        public NewsPublishAt createFromParcel(Parcel in) {
            return new NewsPublishAt(in);
        }

        @Override
        public NewsPublishAt[] newArray(int size) {
            return new NewsPublishAt[size];
        }
    };
}
