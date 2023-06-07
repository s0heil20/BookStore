package edu.sharif.bookstore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchBookCardAdapter adapter;
    ArrayList<SearchBookItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        items = new ArrayList<>();
        items.add(new SearchBookItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new SearchBookItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new SearchBookItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new SearchBookItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new SearchBookItem("book", "rouzbeh", "soheil", "10$"));


        recyclerView = findViewById(R.id.searchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchBookCardAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }
}
