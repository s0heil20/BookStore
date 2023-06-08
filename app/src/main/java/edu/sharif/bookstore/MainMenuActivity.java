package edu.sharif.bookstore;

import android.os.Bundle;

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

public class MainMenuActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private static final String sampleListQueryString = "android java";
    private static final String sampleListQueryType = "intitle";
    private ArrayList<Book> bookList;
    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_layout);

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
            items.add(new MainMenuItem(book.getImage(), book.getTitle(), "2", "100$"));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainMenuAdapter(getApplicationContext(), items));
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id,Bundle args) {
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
}
