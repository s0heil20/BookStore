package edu.sharif.bookstore.bookCard;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import edu.sharif.bookstore.R;

public class BookCardViewHolder extends RecyclerView.ViewHolder {
    TextView bookTitleTextView, publisherNameTextView, authorNameTextView, priceTextView;
    ImageView deleteImageView, bookCardImageView;

    CardView cardView;

    RatingBar ratingBar;

    public BookCardViewHolder(@NonNull View itemView) {
        super(itemView);
        bookTitleTextView = itemView.findViewById(R.id.bookTitle);
        publisherNameTextView = itemView.findViewById(R.id.publisherName);
        authorNameTextView = itemView.findViewById(R.id.authorName);
        priceTextView = itemView.findViewById(R.id.price);
        deleteImageView = itemView.findViewById(R.id.deleteImageView);
        bookCardImageView = itemView.findViewById(R.id.bookCardImageView);
        cardView = itemView.findViewById(R.id.bookCardView);
        ratingBar = itemView.findViewById(R.id.cardRatingBar);
    }
}
