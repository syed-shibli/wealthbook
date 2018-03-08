package com.example.daffolapmac.wealthbook.usersession;

import com.google.gson.annotations.SerializedName;

public class UserSessionData {

    @SerializedName("user_id")
    private String mUserId;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("middle_name")
    private String mMiddleName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("mobile_no")
    private String mPhone;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("token")
    private String mToken;

    public String getmUserId() {
        return mUserId;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmMiddleName() {
        return mMiddleName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmToken() {
        return mToken;
    }

    public String getmCountry() {
        return mCountry;
    }
}
