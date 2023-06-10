package edu.sharif.bookstore.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.bookCard.BookCardAdapter;
import edu.sharif.bookstore.bookCard.BookCardItem;
import edu.sharif.bookstore.bookCard.SelectBookCardListener;
import edu.sharif.bookstore.detailedBook.DetailedBookActivity;
import edu.sharif.bookstore.navigationBar.NavBarActivity;
import edu.sharif.bookstore.R;
import edu.sharif.bookstore.entity.Book;

public class SearchActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<List<Book>>, SelectBookCardListener {
    private RecyclerView recyclerView;
    private BookCardAdapter adapter;
//    ArrayList<BookCardItem> items;


    String[] dropdownItems = {"Title", "Publisher Name", "Author Name"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    SearchView searchView;

    String queryString, queryType = "inTitle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_search);
        handleParentView(R.layout.nav_search);


        autoCompleteTextView = findViewById(R.id.autoCompleteText);
        adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, dropdownItems);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setText(dropdownItems[0],false);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                Log.d("salam", "onItemClick: " + item);
                queryType = "in" + item;
                getQueryResult(queryString, queryType);
            }
        });


//        items = new ArrayList<>();
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));
//        items.add(new BookCardItem("book", "rouzbeh", "soheil", "10$"));


        recyclerView = findViewById(R.id.searchRecyclerView);


        searchView = findViewById(R.id.searchBar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                queryString = s;
                getQueryResult(queryString, queryType);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                queryString = s;
                return false;
            }
        });

    }


    private void getQueryResult(String queryString, String queryType) {
        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", queryString);
        queryBundle.putString("queryType", queryType);
        getSupportLoaderManager().restartLoader(0, queryBundle, this);
    }

    private void addBookItemsToRecyclerView(List<Book> books) {
        List<BookCardItem> items = new ArrayList<BookCardItem>();
        for (Book book : books) {
            items.add(new BookCardItem(book.getTitle(), book.getPublisher(), book.getAuthors().get(0),
                    String.valueOf(book.getPrice()), book.getBookId(), book.getImage()));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookCardAdapter(this, items, false, this);
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BookLoader(this, args.getString("queryString"), args.getString("queryType"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {
        if (books.size() > 0) {
            addBookItemsToRecyclerView(books);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {

    }

    @Override
    public void onItemClicked(BookCardItem bookCardItem) {
        startActivity(new Intent(this, DetailedBookActivity.class).putExtra("bookId", bookCardItem.getBookId()));
    }

    @Override
    public void onDeleteItemClicked(BookCardItem bookCardItem) {

    }
}
