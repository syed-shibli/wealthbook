package com.wealthbook.android.screen.advisoragreement.mode;

import com.google.gson.annotations.SerializedName;

public class UserAgreementRes {
    @SerializedName("agreement_status")
    private boolean agreementStatus;

    @SerializedName("user_id")
    private int userID;

    public boolean getAgreementStatus() {
        return agreementStatus;
    }

    public int getUserID() {
        return userID;
    }
}
