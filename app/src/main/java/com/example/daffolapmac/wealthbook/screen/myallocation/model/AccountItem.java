package com.example.daffolapmac.wealthbook.screen.myallocation.model;

import com.google.gson.annotations.SerializedName;

public class AccountItem {

    @SerializedName("account_number")
    private String accountNumber;

    @SerializedName("account_title")
    private String accountTitle;

    @SerializedName("display_name")
    private String displayName;

    @SerializedName("account_holder")
    private String accountHolder;

    @SerializedName("company_name")
    private String companyName;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("advisor_name")
    private String advisorName;

    @SerializedName("advisor_contact_no")
    private String advisorContactNo;

    @SerializedName("portfolio_as_of")
    private String portfolioAsOf;

    @SerializedName("account_allocations")
    private AccountAllocations accountAllocations;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getProductName() {
        return productName;
    }

    public String getAdvisorName() {
        return advisorName;
    }

    public String getAdvisorContactNo() {
        return advisorContactNo;
    }

    public String getPortfolioAsOf() {
        return portfolioAsOf;
    }

    public AccountAllocations getAccountAllocations() {
        return accountAllocations;
    }
}
