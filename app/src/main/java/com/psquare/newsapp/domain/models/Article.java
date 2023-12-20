package com.psquare.newsapp.domain.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Article implements Parcelable {

    @SerializedName("urlToImage")
    private String urlToImage;

    @SerializedName("title")
    private String title;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (getUrlToImage() != null ? !getUrlToImage().equals(article.getUrlToImage()) : article.getUrlToImage() != null)
            return false;
        return getUrl() != null ? getUrl().equals(article.getUrl()) : article.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getUrlToImage() != null ? getUrlToImage().hashCode() : 0;
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.urlToImage);
        dest.writeString(this.title);
        dest.writeString(this.publishedAt);
        dest.writeString(this.content);
        dest.writeString(this.url);
    }

    public void readFromParcel(Parcel source) {
        this.urlToImage = source.readString();
        this.title = source.readString();
        this.publishedAt = source.readString();
        this.content = source.readString();
        this.url = source.readString();
    }

    public Article() {
    }

    protected Article(Parcel in) {
        this.urlToImage = in.readString();
        this.title = in.readString();
        this.publishedAt = in.readString();
        this.content = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel source) {
            return new Article(source);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
