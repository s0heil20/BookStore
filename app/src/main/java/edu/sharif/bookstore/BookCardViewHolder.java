package edu.sharif.bookstore;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookCardViewHolder extends RecyclerView.ViewHolder {
    TextView bookTitleTextView, publisherNameTextView, authorNameTextView, priceTextView;
    ImageView deleteImageView;

    public BookCardViewHolder(@NonNull View itemView) {
        super(itemView);
        bookTitleTextView = itemView.findViewById(R.id.bookTitle);
        publisherNameTextView = itemView.findViewById(R.id.publisherName);
        authorNameTextView = itemView.findViewById(R.id.authorName);
        priceTextView = itemView.findViewById(R.id.price);
        deleteImageView = itemView.findViewById(R.id.deleteImageView);
    }
}