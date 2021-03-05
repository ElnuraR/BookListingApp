package com.example.booklistingapp.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookApi {
    @GET("volumes")
    Call<BookResponse> getMyBook(@Query("q") String searchBook,  @Query("maxResults") int maxResults);
}
