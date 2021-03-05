package com.example.booklistingapp.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklistingapp.BookAdapter;
import com.example.booklistingapp.R;
import com.example.booklistingapp.models.Book;
import com.example.booklistingapp.network.BookResponse;
import com.example.booklistingapp.network.BookApi;
import com.example.booklistingapp.network.RetroService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity {
    private static final int MAX_RESULTS = 10;

    private List<Book> volumeInfoList;
    private BookApi api;
    private BookAdapter adapter;
    private RecyclerView booksList;

    public String searchBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_list);

        Intent intent = getIntent();
        searchBook = intent.getStringExtra("Search").replace("\\s+", "+");

        api = RetroService.getBookApi();

        booksList = findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        booksList.setLayoutManager(layoutManager);
        booksList.setHasFixedSize(true);

        adapter = new BookAdapter(itemClickListener);
        booksList.setAdapter(adapter);

        doSearch();
    }

    BookAdapter.OnItemClickListener itemClickListener = book -> {
        String url = book.getVolumeInfo().getPreviewLink();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    };

    private void doSearch() {

        Call<BookResponse> call = api.getMyBook(searchBook, MAX_RESULTS);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {

                if (response.isSuccessful()) {
                    volumeInfoList = response.body().getItems();
                    adapter.setBookData(volumeInfoList);
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                booksList.setVisibility(View.INVISIBLE);
                Toast.makeText(BookActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
