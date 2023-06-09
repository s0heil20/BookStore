package edu.sharif.bookstore.cartHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sharif.bookstore.R;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.entity.Cart;

public class CartHistoryAdapter extends RecyclerView.Adapter<CartHistoryViewHolder> {
    private Context context;
    private ArrayList<Cart> items;

    public CartHistoryAdapter(Context context, ArrayList<Cart> items) {
        this.context = context;
        this.items = items;
    }


    @NonNull
    @Override
    public CartHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_history_cart_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartHistoryViewHolder holder, int position) {
        holder.dateTV.setText(items.get(position).getDate());
        holder.addressTV.setText(items.get(position).getAddress());

        StringBuilder bookData = new StringBuilder();
        for (Book book : items.get(position).getBooks()) {
            bookData
                    .append(book.getTitle())
                    .append("\nAuthors: ")
                    .append(String.join(", ", book.getAuthors()))
                    .append("\nPublisher: ")
                    .append(book.getPublisher())
                    .append("\nPrice: ")
                    .append(book.getPrice() + "$")
                    .append("\n\n");
        }
        holder.booksTV.setText(bookData.toString());

        holder.totalPriceTV.setText("Total Price: " + items.get(position).getTotalPrice() + "$");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
