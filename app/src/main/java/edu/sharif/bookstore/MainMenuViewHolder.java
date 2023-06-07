package edu.sharif.bookstore;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainMenuViewHolder extends RecyclerView.ViewHolder {

    ImageView bookImageView;
    TextView titleTextView, ratingTextView, priceTextView;

    public MainMenuViewHolder(@NonNull View itemView) {
        super(itemView);
        bookImageView = itemView.findViewById(R.id.bookImageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        ratingTextView = itemView.findViewById(R.id.ratingTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
    }
}
