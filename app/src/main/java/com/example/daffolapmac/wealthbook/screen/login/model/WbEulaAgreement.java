package com.example.daffolapmac.wealthbook.screen.login.model;

import com.google.gson.annotations.SerializedName;

public class WbEulaAgreement {
    @SerializedName("agreement_status")
    private Boolean agreementStatus;

    @SerializedName("agreement_text")
    private String agreementText;

    public Boolean getAgreementStatus() {
        return agreementStatus;
    }

    public String getAgreementText() {
        return agreementText;
    }
}
