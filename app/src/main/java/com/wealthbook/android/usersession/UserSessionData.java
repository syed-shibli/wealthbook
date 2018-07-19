package com.wealthbook.android.usersession;

import com.google.gson.annotations.SerializedName;
import com.wealthbook.android.screen.login.model.CurrentUserAttribute;
import com.wealthbook.android.screen.login.model.RepAttribute;
import com.wealthbook.android.screen.login.model.RepDetails;

import java.util.List;

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

    @SerializedName("company_name")
    private String mCompanyName;

    @SerializedName("user_type")
    private int userType;

    @SerializedName("rep_details")
    private RepDetails repDetails;

    @SerializedName("rep_attributes")
    private List<RepAttribute> repAttributes = null;

    @SerializedName("current_user_attributes")
    private List<CurrentUserAttribute> currentUserAttributes = null;

    @SerializedName("wb_eula_agreement")
    private UserAgreement userAgreement;

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

    public String getmCompanyName() {
        return mCompanyName;
    }

    public int getUserType() {
        return userType;
    }

    public RepDetails getRepDetails() {
        return repDetails;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public void setmMiddleName(String mMiddleName) {
        this.mMiddleName = mMiddleName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public void setmCompanyName(String mCompanyName) {
        this.mCompanyName = mCompanyName;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setRepDetails(RepDetails repDetails) {
        this.repDetails = repDetails;
    }

    public List<RepAttribute> getRepAttributes() {
        return repAttributes;
    }

    public void setRepAttributes(List<RepAttribute> repAttributes) {
        this.repAttributes = repAttributes;
    }

    public List<CurrentUserAttribute> getCurrentUserAttributes() {
        return currentUserAttributes;
    }

    public void setCurrentUserAttributes(List<CurrentUserAttribute> currentUserAttributes) {
        this.currentUserAttributes = currentUserAttributes;
    }

    public UserAgreement getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(UserAgreement userAgreement) {
        this.userAgreement = userAgreement;
    }
}
