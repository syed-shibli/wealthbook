package com.example.daffolapmac.wealthbook.screen.login.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginRes {

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("token")
    private String token;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("email")
    private String email;

    @SerializedName("title")
    private Object title;

    @SerializedName("logo")
    private String logo;

    @SerializedName("mobile_no")
    private String mobileNo;

    @SerializedName("office_mobile_no")
    private String officeMobileNo;

    @SerializedName("street1")
    private String street1;

    @SerializedName("street2")
    private String street2;

    @SerializedName("city")
    private String city;

    @SerializedName("zip")
    private String zip;

    @SerializedName("state")
    private String state;

    @SerializedName("country")
    private String country;

    @SerializedName("company_logo")
    private String companyLogo;

    @SerializedName("company_name")
    private String companyName;

    @SerializedName("redirection_type")
    private Integer redirectionType;

    @SerializedName("user_type")
    private Integer userType;

    @SerializedName("rep_details")
    private RepDetails repDetails;

    @SerializedName("rep_attributes")
    private List<RepAttribute> repAttributes = null;

    @SerializedName("current_user_attributes")
    private List<CurrentUserAttribute> currentUserAttributes = null;

    @SerializedName("wb_eula_agreement")
    private WbEulaAgreement wbEulaAgreement;

    public Integer getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getEmail() {
        return email;
    }

    public Object getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getOfficeMobileNo() {
        return officeMobileNo;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getRedirectionType() {
        return redirectionType;
    }

    public Integer getUserType() {
        return userType;
    }

    public RepDetails getRepDetails() {
        return repDetails;
    }

    public List<RepAttribute> getRepAttributes() {
        return repAttributes;
    }

    public List<CurrentUserAttribute> getCurrentUserAttributes() {
        return currentUserAttributes;
    }

    public WbEulaAgreement getWbEulaAgreement() {
        return wbEulaAgreement;
    }
}
