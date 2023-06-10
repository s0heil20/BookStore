package edu.sharif.bookstore.bookCard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sharif.bookstore.R;
import edu.sharif.bookstore.database.SQLDatabaseManager;

public class BookCardAdapter extends RecyclerView.Adapter<BookCardViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<BookCardItem> items;
    private Boolean isDeleteVisible;

    private SelectBookCardListener selectBookCardListener;


    public BookCardAdapter(Context context, List<BookCardItem> items, Boolean isDeleteVisible, SelectBookCardListener selectBookCardListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.isDeleteVisible = isDeleteVisible;
        this.selectBookCardListener = selectBookCardListener;
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
        holder.authorNameTextView.setText(items.get(position).getAuthorNames());
        holder.priceTextView.setText(items.get(position).getPrice());
        holder.bookCardImageView.setImageDrawable(items.get(position).getBookCardImage());
        holder.ratingBar.setRating(items.get(position).getRating());
        if (items.get(position).isFavourite()){
            holder.favouriteImageView.setVisibility(View.VISIBLE);
        }else {
            holder.favouriteImageView.setVisibility(View.INVISIBLE);
        }

        if (isDeleteVisible) {
            holder.deleteImageView.setVisibility(View.VISIBLE);
        }

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectBookCardListener.onDeleteItemClicked(items.get(holder.getAdapterPosition()));
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectBookCardListener.onItemClicked(items.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
