package edu.sharif.bookstore;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartHistoryViewHolder extends RecyclerView.ViewHolder {
    public TextView dateTV, addressTV, booksTV, totalPriceTV;
    public CartHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        dateTV = itemView.findViewById(R.id.date);
        addressTV = itemView.findViewById(R.id.address);
        booksTV = itemView.findViewById(R.id.books);
        totalPriceTV = itemView.findViewById(R.id.totalPrice);
    }
}
