package edu.sharif.bookstore;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainMenuViewHolder extends RecyclerView.ViewHolder {

    public ImageView bookImageView;
    public TextView titleTextView,  priceTextView;
    public RatingBar ratingBarMainMenu;
    public RelativeLayout relativeLayout;

    public MainMenuViewHolder(@NonNull View itemView) {
        super(itemView);
        bookImageView = itemView.findViewById(R.id.bookImageView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        ratingBarMainMenu = itemView.findViewById(R.id.ratingMainMenu);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        relativeLayout = itemView.findViewById(R.id.mainMenuItemContainer);
    }

}
