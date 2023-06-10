package edu.sharif.bookstore.mainMenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.sharif.bookstore.bookCard.BookCardAdapter;
import edu.sharif.bookstore.bookCard.BookCardItem;
import edu.sharif.bookstore.bookCard.SelectBookCardListener;
import edu.sharif.bookstore.database.SQLDatabaseManager;
import edu.sharif.bookstore.search.BookLoader;
import edu.sharif.bookstore.detailedBook.DetailedBookActivity;
import edu.sharif.bookstore.R;
import edu.sharif.bookstore.entity.Book;
import edu.sharif.bookstore.navigationBar.NavBarActivity;

public class MainMenuActivity extends NavBarActivity implements LoaderManager.LoaderCallbacks<List<Book>>, SelectBookCardListener {
    private static final String sampleListQueryString = "android java";
    private static final String sampleListQueryType = "intitle";
    private ArrayList<Book> bookList;

    private BookCardAdapter adapter;
    private RecyclerView recyclerView;

    private int NightMode;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        pageName = "Main Menu";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_main_menu);
        handleParentView(R.layout.nav_main_menu);

        recyclerView = findViewById(R.id.bookListRecyclerView);

        Bundle queryBundle = new Bundle();
        queryBundle.putString("queryString", sampleListQueryString);
        queryBundle.putString("queryType", sampleListQueryType);
        getSupportLoaderManager().restartLoader(0, queryBundle, this);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        NightMode = AppCompatDelegate.getDefaultNightMode();

        sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putInt("NightModeInt", NightMode);
        editor.commit();
    }

    private void addBookItemsToRecyclerView(List<Book> books) {
        SQLDatabaseManager sqlDatabaseManager = SQLDatabaseManager.instanceOfDatabase(this);
        ArrayList<String> favouriteBooks = sqlDatabaseManager.getFavouriteDatabaseManager().getFavouriteBooks();

        List<BookCardItem> items = new ArrayList<BookCardItem>();
        for (Book book : books) {
            items.add(new BookCardItem(book.getAuthors(), book.getTitle(), book.getPublisher(),
                    String.valueOf(book.getPrice()), book.getBookId(), book.getImage(), book.getAvgRating(),  favouriteBooks.contains(book.getBookId())));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookCardAdapter(this, items, false, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(this, args.getString("queryString"), args.getString("queryType"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> books) {
            addBookItemsToRecyclerView(books);
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
