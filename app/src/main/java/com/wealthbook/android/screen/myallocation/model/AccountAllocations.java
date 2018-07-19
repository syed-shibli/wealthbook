package com.wealthbook.android.screen.myallocation.model;

import com.google.gson.annotations.SerializedName;

public class AccountAllocations {

    @SerializedName("current_allocation")
    private String currentAllocation;

    @SerializedName("legacy_allocation")
    private String legacyAllocation;

    public String getCurrentAllocation() {
        return currentAllocation;
    }

    public String getLegacyAllocation() {
        return legacyAllocation;
    }
}
