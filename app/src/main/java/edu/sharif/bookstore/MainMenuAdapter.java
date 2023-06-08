package edu.sharif.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuViewHolder> {

    Context context;
    List<MainMenuItem> items;

    public MainMenuAdapter(Context context, List<MainMenuItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MainMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainMenuViewHolder(LayoutInflater.from(context).inflate(R.layout.main_menu_book_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuViewHolder holder, int position) {
        holder.titleTextView.setText(items.get(position).getTitle());
        holder.ratingTextView.setText(items.get(position).getRating());
        holder.priceTextView.setText(items.get(position).getPrice());
        holder.bookImageView.setImageDrawable(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
