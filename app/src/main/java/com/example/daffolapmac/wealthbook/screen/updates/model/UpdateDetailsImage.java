package com.example.daffolapmac.wealthbook.screen.updates.model;

import com.google.gson.annotations.SerializedName;

public class UpdateDetailsImage {
    @SerializedName("id")
    private int id;

    @SerializedName("cropimg")
    private String cropimg;

    @SerializedName("path")
    private String path;

    public int getId() {
        return id;
    }

    public String getCropimg() {
        return cropimg;
    }

    public String getPath() {
        return path;
    }
}
