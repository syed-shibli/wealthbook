package com.wealthbook.android.screen.notificationalert.model;

import com.google.gson.annotations.SerializedName;

public class UpdatePortfolioReq {

    @SerializedName("id")
    private int id;

    @SerializedName("status_id")
    private int statusID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
}
