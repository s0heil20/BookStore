package edu.sharif.bookstore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookCardAdapter extends RecyclerView.Adapter<BookCardViewHolder> {
    private LayoutInflater layoutInflater;
    private List<BookCardItem> items;
    private Boolean isDeleteVisible;


    public BookCardAdapter(Context context, List<BookCardItem> items, Boolean isDeleteVisible) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.isDeleteVisible = isDeleteVisible;
    }

    @NonNull
    @Override
    public BookCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.book_card_item_layout, parent, false);
        return new BookCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookCardViewHolder holder, int position) {
        holder.bookTitleTextView.setText(items.get(position).getBookTitle());
        holder.publisherNameTextView.setText(items.get(position).getPublisherName());
        holder.authorNameTextView.setText(items.get(position).getAuthorName());
        holder.priceTextView.setText(items.get(position).getPrice());
        if (isDeleteVisible) {
            holder.deleteImageView.setVisibility(View.VISIBLE);
            holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("a", "onClick: h");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}