package com.example.daffolapmac.wealthbook.screen.news.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsItem implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("headline")
    private String headline;

    @SerializedName("image")
    private String image;

    @SerializedName("story")
    private String story;

    @SerializedName("published_at")
    private NewsPublishAt publishedAt;

    @SerializedName("authors")
    private List<String> authors = null;

    @SerializedName("categories")
    private List<NewsCategory> categories = null;

    @SerializedName("feed")
    private NewsFeed feed;

    @SerializedName("source")
    private NewsSource source;

    public NewsItem() {
    }

    protected NewsItem(Parcel in) {
        id = in.readInt();
        headline = in.readString();
        image = in.readString();
        story = in.readString();
        publishedAt = in.readParcelable(NewsPublishAt.class.getClassLoader());
        authors = in.createStringArrayList();
        categories = in.createTypedArrayList(NewsCategory.CREATOR);
        feed = in.readParcelable(NewsFeed.class.getClassLoader());
        source = in.readParcelable(NewsSource.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(headline);
        dest.writeString(image);
        dest.writeString(story);
        dest.writeParcelable(publishedAt, flags);
        dest.writeStringList(authors);
        dest.writeTypedList(categories);
        dest.writeParcelable(feed, flags);
        dest.writeParcelable(source, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getHeadline() {
        return headline;
    }

    public String getImage() {
        return image;
    }

    public String getStory() {
        return story;
    }

    public NewsPublishAt getPublishedAt() {
        return publishedAt;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<NewsCategory> getCategories() {
        return categories;
    }

    public NewsFeed getFeed() {
        return feed;
    }

    public NewsSource getSource() {
        return source;
    }
}
