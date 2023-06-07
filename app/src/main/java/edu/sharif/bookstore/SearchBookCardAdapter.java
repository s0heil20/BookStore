package edu.sharif.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchBookCardAdapter extends RecyclerView.Adapter<SearchBookCardViewHolder> {
    private LayoutInflater layoutInflater;
    private List<SearchBookItem> items;


    public SearchBookCardAdapter(Context context, List<SearchBookItem> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public SearchBookCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.search_book_card_holder, parent, false);
        return new SearchBookCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchBookCardViewHolder holder, int position) {
        holder.bookTitleTextView.setText(items.get(position).getBookTitle());
        holder.publisherNameTextView.setText(items.get(position).getPublisherName());
        holder.authorNameTextView.setText(items.get(position).getAuthorName());
        holder.priceTextView.setText(items.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
