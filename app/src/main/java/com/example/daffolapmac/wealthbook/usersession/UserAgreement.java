package com.example.daffolapmac.wealthbook.usersession;

import com.google.gson.annotations.SerializedName;

public class UserAgreement {
    @SerializedName("agreement_status")
    private boolean agreementStatus;

    @SerializedName("agreement_text")
    private String agreementText;

    public boolean isAgreementStatus() {
        return agreementStatus;
    }

    public String getAgreementText() {
        return agreementText;
    }

    public void setAgreementStatus(boolean agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public void setAgreementText(String agreementText) {
        this.agreementText = agreementText;
    }
}
