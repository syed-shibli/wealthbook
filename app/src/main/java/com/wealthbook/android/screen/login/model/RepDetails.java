package com.wealthbook.android.screen.login.model;

import com.google.gson.annotations.SerializedName;

public class RepDetails {

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("email")
    private String email;

    @SerializedName("title")
    private String title;

    @SerializedName("logo")
    private String logo;

    @SerializedName("company_logo")
    private String companyLogo;

    @SerializedName("company_name")
    private String companyName;

    @SerializedName("certifications")
    private String certifications;

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

    @SerializedName("diclaimer_text")
    private String diclaimerText;

    public Integer getUserId() {
        return userId;
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

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCertifications() {
        return certifications;
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

    public String getDiclaimerText() {
        return diclaimerText;
    }
}
