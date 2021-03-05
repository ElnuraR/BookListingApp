package com.example.booklistingapp.network;

import com.example.booklistingapp.models.Book;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookResponse implements Serializable {
    @SerializedName("items")
    @Expose
    private List<Book> items = null;

    public List<Book> getItems() {
        return items;
    }
}
