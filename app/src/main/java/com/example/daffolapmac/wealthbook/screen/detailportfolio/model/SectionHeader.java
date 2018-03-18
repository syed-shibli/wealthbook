package com.example.daffolapmac.wealthbook.screen.detailportfolio.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class SectionHeader implements Section<Hold>, Parcelable{
    List<Hold> childList;
    String sectionText;

    public SectionHeader(List<Hold> childList, String sectionText) {
        this.childList = childList;
        this.sectionText = sectionText;
    }

    protected SectionHeader(Parcel in) {
        childList = in.createTypedArrayList(Hold.CREATOR);
        sectionText = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(childList);
        dest.writeString(sectionText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SectionHeader> CREATOR = new Creator<SectionHeader>() {
        @Override
        public SectionHeader createFromParcel(Parcel in) {
            return new SectionHeader(in);
        }

        @Override
        public SectionHeader[] newArray(int size) {
            return new SectionHeader[size];
        }
    };

    @Override
    public List<Hold> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return sectionText;
    }
}
