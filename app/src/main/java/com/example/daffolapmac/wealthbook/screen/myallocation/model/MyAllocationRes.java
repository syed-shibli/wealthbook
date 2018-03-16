package com.example.daffolapmac.wealthbook.screen.myallocation.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyAllocationRes {
    @SerializedName("accounts")
    private List<AccountItem> accounts;

    @SerializedName("code")
    private Integer code;

    public List<AccountItem> getAccounts() {
        return accounts;
    }

    public Integer getCode() {
        return code;
    }
}
