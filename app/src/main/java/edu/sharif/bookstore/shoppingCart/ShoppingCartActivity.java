package edu.sharif.bookstore.shoppingCart;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sharif.bookstore.bookCard.BookCardAdapter;
import edu.sharif.bookstore.bookCard.BookCardItem;
import edu.sharif.bookstore.R;

public class ShoppingCartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BookCardAdapter adapter;
    ArrayList<BookCardItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        items = new ArrayList<>();
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));


//        recyclerView = findViewById(R.id.searchRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new BookCardAdapter(this, items, false);
//        recyclerView.setAdapter(adapter);
    }
}
