package com.example.daffolapmac.wealthbook.screen.updates.model;

import com.google.gson.annotations.SerializedName;

public class UpdateCategory {
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("company_id")
    private Integer companyId;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCompanyId() {
        return companyId;
    }
}
