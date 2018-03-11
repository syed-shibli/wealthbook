package com.example.daffolapmac.wealthbook.screen.updates.model;

import com.google.gson.annotations.SerializedName;

public class UpdateRes {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("category")
    private UpdateCategory category;

    @SerializedName("featured_page")
    private String featuredPage;

    @SerializedName("description")
    private String description;

    @SerializedName("date_published")
    private String datePublished;

    @SerializedName("author_id")
    private int authorId;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public UpdateCategory getCategory() {
        return category;
    }

    public String getFeaturedPage() {
        return featuredPage;
    }

    public String getDescription() {
        return description;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public int getAuthorId() {
        return authorId;
    }
}
