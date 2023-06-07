package edu.sharif.bookstore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BookCardAdapter adapter;
    ArrayList<BookCardItem> items;


    String[] dropdownItems = {"Title", "Publisher Name", "Author Name"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        items = new ArrayList<>();
        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));


        recyclerView = findViewById(R.id.searchRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookCardAdapter(this, items, true);
        recyclerView.setAdapter(adapter);


        autoCompleteTextView = findViewById(R.id.autoCompleteText);
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, dropdownItems);

        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                Log.d("salam", "onItemClick: " + item);
                Toast.makeText(SearchActivity.this, item, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
