package edu.sharif.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.utils.BookJsonParserUtil;
import edu.sharif.bookstore.utils.NavBarActivity;

public class MainMenuActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<List<Book>>, SelectMainMenuItemInterface {
    private static final String sampleListQueryString = "android java";
    private static final String sampleListQueryType = "intitle";
    private ArrayList<Book> bookList;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main_menu);
        handleParentView(R.layout.nav_main_menu);

        recyclerView = findViewById(R.id.bookListRecyclerView);

//        List<MainMenuItem> items = new ArrayList<MainMenuItem>();
//        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
//        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
//        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
//        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
//        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
//        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
//        items.add(new MainMenuItem(0, "SALAM", "5/11","10$"));
//
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MainMenuAdapter(getApplicationContext(), items));

        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", sampleListQueryString);
        queryBundle.putString("queryType", sampleListQueryType);
        getSupportLoaderManager().restartLoader(0, queryBundle, this);

    }

    private void addBookItemsToRecyclerView(List<Book> books) {
        List<MainMenuItem> items = new ArrayList<MainMenuItem>();
        for (Book book : books) {
            items.add(new MainMenuItem(book.getImage(), book.getTitle(), book.getAvgRating()+"", book.getPrice()+"", book.getBookId()));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainMenuAdapter(getApplicationContext(), items, this));
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
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
    public void onItemClicked(MainMenuItem bookItem) {
        // TODO make intent to go to detailed activity with bookID!
        Toast.makeText(this, "CLICKED ON" + bookItem.bookId , Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, DetailedBookActivity.class).putExtra("bookId", bookItem.bookId));
    }
}
