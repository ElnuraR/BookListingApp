package com.example.booklistingapp;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.booklistingapp.models.Book;
import com.example.booklistingapp.models.VolumeInfo;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CustomViewHolder> {
    private static final String LOG_TAG = BookAdapter.class.getSimpleName();

    private ArrayList<Book> volumeInfo;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public BookAdapter(OnItemClickListener listener) {
        onItemClickListener = listener;
        volumeInfo = new ArrayList<>();

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_item, parent, false);

        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.bind(volumeInfo.get(position), onItemClickListener);

        VolumeInfo volumeInfo = this.volumeInfo.get(position).getVolumeInfo();
        holder.title.setText(volumeInfo.getTitle());

        if (volumeInfo.getAuthors() != null) {
            holder.author.setText(volumeInfo.getAuthors().get(0));
        }
        if (volumeInfo.getPublishedDate() != null) {
            holder.date.setText(volumeInfo.getPublishedDate());
        }
        if (volumeInfo.pageCount != 0) {
            holder.pages.setText(volumeInfo.pageCount + " pages");
        } else {
            holder.pages.setText("N/A");
        }
        if (volumeInfo.averageRating != 0) {
            holder.rating.setText(String.valueOf(volumeInfo.averageRating));
        } else {
            holder.rating.setText("N/A");
        }
        if (volumeInfo.getImageLinks() != null) {
            Glide
                    .with(holder.cover.getContext())
                    .load(volumeInfo.getImageLinks().getSmallThumbnail().replace("http", "https"))
                    .into(holder.cover);
        }
    }

    @Override
    public int getItemCount() {
        if (volumeInfo != null) {
            return volumeInfo.size();
        } else {
            return 0;
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView cover;
        TextView title, author, date, pages, rating;

        public CustomViewHolder(View view) {
            super(view);
            cover = view.findViewById(R.id.book_cover);
            title = view.findViewById(R.id.title);
            author = view.findViewById(R.id.author);
            date = view.findViewById(R.id.date);
            pages = view.findViewById(R.id.pages);
            rating = view.findViewById(R.id.rating);
        }

        public void bind(final Book book, final OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.onItemClick(book));
        }
    }

    public void setBookData(List<Book> bookList) {
        this.volumeInfo.clear();
        this.volumeInfo.addAll(bookList);
        notifyDataSetChanged();
    }
}
