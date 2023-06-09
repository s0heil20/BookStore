package edu.sharif.bookstore.shoppingCart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.bookCard.BookCardAdapter;
import edu.sharif.bookstore.bookCard.BookCardItem;
import edu.sharif.bookstore.R;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.navigationBar.NavBarActivity;

public class ShoppingCartActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    RecyclerView recyclerView;
    BookCardAdapter adapter;
    ArrayList<BookCardItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_shopping_cart);
        handleParentView(R.layout.nav_shopping_cart);

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

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {

    }
}
