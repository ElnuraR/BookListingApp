package com.example.booklistingapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class VolumeInfo implements Serializable {
    @SerializedName("title")
    public String title;

    @SerializedName("authors")
    public List<String> authors;

    @SerializedName("averageRating")
    public double averageRating;

    @SerializedName("previewLink")
    public String previewLink;

    @SerializedName("publishedDate")
    public String publishedDate;

    @SerializedName("pageCount")
    public int pageCount;

    @SerializedName("infoLink")
    public String infoLink;

    @SerializedName("imageLinks")
    public ImageLinks imageLinks;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }
}
