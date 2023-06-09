package edu.sharif.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuViewHolder> {

    private Context context;
    private List<MainMenuItem> items;
    private SelectMainMenuItemInterface listener;

    public MainMenuAdapter(Context context, List<MainMenuItem> items, SelectMainMenuItemInterface listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
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
        holder.relativeLayout.setOnClickListener((new View.OnClickListener(

        ) {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(items.get(holder.getAdapterPosition()));
            }
        }));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
