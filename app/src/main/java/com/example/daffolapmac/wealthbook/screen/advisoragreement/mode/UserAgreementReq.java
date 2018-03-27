package com.example.daffolapmac.wealthbook.screen.advisoragreement.mode;

import com.google.gson.annotations.SerializedName;

public class UserAgreementReq {
    @SerializedName("agreement_status")
    private int agreementStatus;

    @SerializedName("user_id")
    private String userID;

    public int getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(int agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
