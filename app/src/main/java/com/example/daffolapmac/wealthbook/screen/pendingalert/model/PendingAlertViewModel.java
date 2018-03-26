package com.example.daffolapmac.wealthbook.screen.pendingalert.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PendingAlertViewModel implements Parcelable{

    private int id;
    private String title;
    private String accountNumber;
    private String adviserName;
    private String adviserContact;
    private String alert;

    public PendingAlertViewModel() {
    }

    protected PendingAlertViewModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        accountNumber = in.readString();
        adviserName = in.readString();
        adviserContact = in.readString();
        alert = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(accountNumber);
        dest.writeString(adviserName);
        dest.writeString(adviserContact);
        dest.writeString(alert);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PendingAlertViewModel> CREATOR = new Creator<PendingAlertViewModel>() {
        @Override
        public PendingAlertViewModel createFromParcel(Parcel in) {
            return new PendingAlertViewModel(in);
        }

        @Override
        public PendingAlertViewModel[] newArray(int size) {
            return new PendingAlertViewModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAdviserName() {
        return adviserName;
    }

    public void setAdviserName(String adviserName) {
        this.adviserName = adviserName;
    }

    public String getAdviserContact() {
        return adviserContact;
    }

    public void setAdviserContact(String adviserContact) {
        this.adviserContact = adviserContact;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}
